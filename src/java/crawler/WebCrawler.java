/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import dao.CategoryDAO;
import dao.SneakerDAO;
import database.DBUtils;
import dto.Sneaker;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.naming.NamingException;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import parser.ShoesHandler;
import parser.XSLTransform;

/**
 *
 * @author ADMIN
 */
public class WebCrawler {

    public static String getXSLFilePath(String filename) {
        String realPath = "G:\\ProjectJava\\SneakerShop\\web";
        return realPath + "/WEB-INF/xsl/" + filename;
    }

    public static String getXSDFilePath(String filename) {
        String realPath = "G:\\ProjectJava\\SneakerShop\\web";
        return realPath + "/WEB-INF/xsd/" + filename;
    }

    public static String process(String content, String fileName) {
        String xsl = getXSLFilePath(fileName);
        String xml = XSLTransform.transformXMLtoXSL(xsl, content);
        xml = validateXSD(xml);
        if (xml == null) {
            return "0";
        }
        return xml;
    }

    public static String validateXSD(String xml) {
        StringReader sr = new StringReader(xml);
        StreamSource xmlSource = new StreamSource(sr);
        StreamSource xsd = new StreamSource(getXSDFilePath("products.xsd")); // read schema
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); // create schema factory
            Schema schema = sf.newSchema(xsd);
            Validator validator = schema.newValidator();
            validator.validate(xmlSource); // processing validateXSD schema
            return xml;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void crawlKingShoesWebDriver(String url) {

        try {
            //String getUrl = "https://centimet.vn/danh-muc/thoi-trang-nam/giay-nam/giay-sneakers-nam/";
            System.setProperty("webdriver.ie.driver", "G:\\ProjectJavaWeb\\IEDriverServer.exe");
            WebDriver driver = new InternetExplorerDriver();
            for (int p = 0; p < 10; p++) {
                url = url.substring(0, url.length() - 1) + p;
                driver.get(url);
                String xpath = "//section[2]//div[@class='row']//div[@class='col-xl-3 col-lg-3 col-md-4 col-sm-4 col-6 itemPro']";
                boolean exist = driver.findElements(By.xpath(xpath)).size() > 0;
                if (exist == false) {
                    break;
                }
                List<WebElement> listproduct = driver.findElements(By.xpath(xpath));
                int i = 0;
                for (WebElement element : listproduct) {
                    String wellform = wellformedHTML(element);
                    if (i > listproduct.size()) {
                        break;
                    }
                    i = i + 1;
                    String name = element.findElement(By.xpath(xpath + "[" + i + "]" + "//div[@class='ps-shoe__detail']//a")).getText();
                    if (name.length() == 0) {
                        name = element.findElement(By.xpath(xpath + "[" + i + "]" + "//div[@class='ps-shoe__detail']//a")).getAttribute("innerHTML");
                    }
                    String price = element.findElement(By.xpath(xpath + "[" + i + "]" + "//div[@class='ps-shoe__detail']//span")).getAttribute("innerHTML").trim();
                    boolean deletePrice = element.findElements(By.xpath(xpath + "[" + i + "]" + "//div[@class='ps-shoe__detail']//span//del")).size() > 0;
                    if (deletePrice) {
                        String html = element.findElement(By.xpath(xpath + "[" + i + "]" + "//div[@class='ps-shoe__detail']//span")).getAttribute("innerHTML").trim();
                        String[] split = html.split("</del>");
                        price = split[1];
                        if (price.startsWith("<")) {
                            price = price.substring(8);
                        }
                    }
                    String imgSrc = "https://kingshoes.vn/" + element.findElement(By.xpath(xpath + "[" + i + "]" + "//div[@class='ps-shoe__thumbnail']//img")).getAttribute("data-src");
                    String saleoff = "";
                    boolean sale = false;
                    boolean saleon = element.findElements(By.xpath(xpath + "[" + i + "]" + "//div[@class='ps-shoe__thumbnail']//div//span")).size() > 0;
                    if (saleon) {
                        sale = true;
                        saleoff = element.findElement(By.xpath(xpath + "[" + i + "]" + "//div[@class='ps-shoe__thumbnail']//div//span")).getText();
                    }
                    int quantity = 0;
                    String shopUrl = "https://kingshoes.vn/other-brands/sneaker-authentic-c110.html";
                    if (price.length() > 0) {
                        price = price.replaceAll(",", "");
                        price = price.substring(0, price.length() - 3);
                    } else {
                        price = "0";
                    }
                    Sneaker sneaker = new Sneaker(0, name, Double.parseDouble(price), imgSrc, sale, quantity, shopUrl);
                    String process = process(wellform, "kingshoes.xsl");
                    if (!process.equals("0")) {
                        ShoesHandler handler = new ShoesHandler(sneaker);
                        parseXMLtoSAX(process, handler);
                        System.out.println(handler.message);
                    }
                }
            }
            driver.quit();
            System.out.println("Done");
            CategoryDAO dao = new CategoryDAO();
            dao.updateCrawl("https://kingshoes.vn/other-brands/sneaker-authentic-c110.html");
        } catch (Exception ex) {
            System.out.println("Error at crawlKingshoes : " + ex.getMessage());
        }

    }

    public static void crawlStealSneaker(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            int i = 0;
            String replaceUrl = "";
            Elements rows = doc.select(".content-product-list div.product-resize");
            String html = rows.outerHtml();
            for (Element row : doc.select(".content-product-list div.product-resize")) {
                Elements subRows = row.select("img");;
                for (Element subRow : subRows) {
                    if (i == 0) {
                        subRow = subRows.first().appendText("</img>");
                    }
                    if (i >= 1) {
                        subRow.remove();
                    }
                    i = i + 1;
                }
                String replaceText = row.outerHtml();
                String process = process(replaceText, "juno.xsl");
                if (!process.equals("0"));
                {
                    boolean sale = false;
                    String urlPicture = "https:" + row.select("div.product-block div.product-img a picture img.img-loop.lazyload").attr("data-src");
                    String name = row.select("div.product-detail div.box-pro-detail h3 a").text().trim();
                    String price = row.select("div.product-detail div.box-pro-prices p").text().replaceAll(",", "").trim();
                    price = price.substring(0, price.length() - 1);
                    int quantity = 0;
                    Sneaker sneaker = new Sneaker(0, name, Double.parseDouble(price), urlPicture, sale, quantity, url);
                    ShoesHandler handler = new ShoesHandler(sneaker);
                    parseXMLtoSAX(process, handler);
                    System.out.println(handler.message);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error at crawlStealSneaker : " + ex.getMessage());
        }
    }

    public static void crawlKingShoes(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            for (Element row : doc.select(".col-lg-9 div.col-xl-3")) {
                Elements subRows = row.select("img");
                Elements subSelect = row.select("select");
                subSelect.remove();
                int i = 0;
                for (Element subRow : subRows) {
                    if (i == 0) {
                        subRow = subRows.first().appendText("</img>");
                    }
                    if (i >= 1) {
                        subRow.remove();
                    }
                    i = i + 1;
                }
                String replaceText = row.outerHtml();
                String process = process(replaceText, "kingshoes.xsl");
                if (!process.equals("0")) {
                    boolean sale = false;
                    String price = "";
                    String salePercent = row.select("div.ps-shoe.mb-30 div.ps-shoe__thumbnail div.ps-badge.ps-badge--sale.ps-badge--2nd span").text().replaceAll("-", "").replaceAll("%", "");
                    if (salePercent.length() != 0) {
                        //price = price*Integer.parseInt(salePercent);
                        price = row.select("span.ps-shoe__price del").text().replaceAll(",", "").trim();
                        sale = true;
                    } else {
                        price = row.select("span.ps-shoe__price").text().replaceAll(",", "").trim();
                    }
                    String urlPicture = "https://kingshoes.vn/" + row.select("div.ps-shoe.mb-30 div.ps-shoe__thumbnail img.lazy").attr("data-src");
                    String name = row.select("div.ps-shoe__content div.ps-shoe__detail a.ps-shoe__name").text();
                    price = price.substring(0, price.length() - 3);
                    Double parsedPrice = Double.parseDouble(price);
                    if (salePercent.length() != 0) {
                        parsedPrice = parsedPrice - parsedPrice * (Double.parseDouble(salePercent) / 100);
                    }
                    int quantity = 0;
                    Sneaker sneaker = new Sneaker(0, name, parsedPrice, urlPicture, sale, quantity, url);
                    ShoesHandler handler = new ShoesHandler(sneaker);
                    parseXMLtoSAX(process, handler);
                    System.out.println(handler.message);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error at crawlKingShoes : " + ex.getMessage());
        }
    }

    public static void parseXMLtoSAX(String xml, DefaultHandler handler) {
        try {
            StringReader sr = new StringReader(xml);
            InputSource source = new InputSource(sr);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sax = spf.newSAXParser();
            sax.parse(source, handler);
        } catch (Exception ex) {
            System.out.println("Error at parseStringToSAX : " + ex.getMessage());
        }
    }

    public static void crawlCentimetWebDriver(String url) {

        try {
            //String getUrl = "https://centimet.vn/danh-muc/thoi-trang-nam/giay-nam/giay-sneakers-nam/";
            System.setProperty("webdriver.ie.driver", "G:\\ProjectJavaWeb\\IEDriverServer.exe");
            WebDriver driver = new InternetExplorerDriver();
            driver.get(url);
            int i = 0;
            List<WebElement> listproduct = driver.findElements(By.xpath("//body//div[6]//div[2]//div[2]//div[1]//div[1]//div[@class='product-list-wapper']//ul//li"));
            while (true) {
                i = i + 1;
                String xpath = "//body//div[6]//div[2]//div[2]//div[1]//div[1]//div[@class='product-list-wapper']//ul//li[" + i + "]";
                WebElement element = driver.findElement(By.xpath(xpath));
                String price = "";
                String name = element.findElement(By.xpath(xpath + "//h2")).getText();
                Boolean getPrice = element.findElements(By.xpath(xpath + "//div//span//span[@class='woocommerce-Price-amount amount']")).size() > 0;
                if (getPrice) {
                    price = element.findElement(By.xpath(xpath + "//div//span//span[@class='woocommerce-Price-amount amount']")).getText();
                }
                String imgSrc = element.findElement(By.xpath(xpath + "//div[@class='thumb-link']//div//a//img")).getAttribute("data-src");
                if (price.equals("")) {
                    price = "0";
                } else {
                    price = price.replaceAll(",", "");
                    price = price.substring(0, price.length() - 2);
                }
                boolean sale = false;
                int quantity = 0;
                String shopUrl = "https://centimet.vn";
                Sneaker sneaker = new Sneaker(0, name, Double.parseDouble(price), imgSrc, sale, quantity, shopUrl);
                String template = DBUtils.templateCentimet(sneaker);
                String process = process(template, "centimet.xsl");
                if (!process.equals("0")) {
                    ShoesHandler handler = new ShoesHandler(sneaker);
                    parseXMLtoSAX(process, handler);
                    System.out.println(handler.message);
                }
                if (i == listproduct.size()) {
                    break;
                }
            }

            driver.quit();
            System.out.println("Done");
            CategoryDAO dao = new CategoryDAO();
            dao.updateCrawl("https://centimet.vn");
        } catch (Exception ex) {
            System.out.println("Error at crawlSaigonSneaker : " + ex.getMessage());
        }

    }

    public static String wellformedHTML(WebElement element) {
        String html = element.getAttribute("innerHTML");
        String[] split = html.split("\n");
        String wellformed = "";
        for (String string : split) {
            if (string.contains("<img") || string.startsWith("<img")) {
                string += "\n</img>";
            }
            wellformed += string.trim() + "\n";
        }
        return wellformed;
    }

    public static void crawlSaigonSneakerWebDriver(String url) {
        try {
            System.setProperty("webdriver.ie.driver", "G:\\ProjectJavaWeb\\IEDriverServer.exe");
            WebDriver driver = new InternetExplorerDriver();
            driver.get(url);
            String xpath = "//div[@class='elementor-inner']//section[@class='elementor-element elementor-element-d5da8eb elementor-section-boxed elementor-section-height-default elementor-section-height-default elementor-section elementor-top-section'][1]//div[@class='elementor-element elementor-element-4bd17ad elementor-products-grid elementor-wc-products elementor-show-pagination-border-yes elementor-widget elementor-widget-wc-archive-products']//ul[@class='products columns-6']//li";
            // String test = driver.findElement(By.xpath(xpath)).getAttribute("innerHTML");
            List<WebElement> listproduct = driver.findElements(By.xpath(xpath));
            int i = 0;
            for (WebElement element : listproduct) {
                i = i + 1;
                String name = element.findElement(By.xpath(xpath + "[" + i + "]" + "//a//h2")).getText();
                String price = element.findElement(By.xpath(xpath + "[" + i + "]" + "//a//span[@class='price']//span[@class='woocommerce-Price-amount amount']")).getText();
                String imgSrc = element.findElement(By.xpath(xpath + "[" + i + "]" + "//a//img")).getAttribute("src");
                boolean saleon = element.findElements(By.xpath(xpath + "[" + i + "]" + "//a//span[@class='onsale']")).size() > 0;
                String saleoff = "";
                boolean sale = false;
                if (saleon) {
                    saleoff = element.findElement(By.xpath(xpath + "[" + i + "]" + "//a//span[@class='onsale']")).getText();
                    sale = true;
                }
                int quantity = 0;
                String shopUrl = "https://saigonsneaker.com";
                if (price.length() > 0) {
                    price = price.replaceAll(",", "");
                    price = price.substring(0, price.length() - 3);
                } else {
                    price = "0";
                }
                Sneaker sneaker = new Sneaker(0, name, Double.parseDouble(price), imgSrc, sale, quantity, shopUrl);
                String template = DBUtils.templateSaigonSneaker(sneaker);
                String process = process(template, "saigonsneaker.xsl");
                if (!process.equals("0")) {
                    ShoesHandler handler = new ShoesHandler(sneaker);
                    parseXMLtoSAX(process, handler);
                    System.out.println(handler.message);
                }
            }
            driver.quit();
            System.out.println("Done");
            CategoryDAO dao = new CategoryDAO();
            dao.updateCrawl("https://saigonsneaker.com");

        } catch (Exception ex) {
            System.out.println("Error at crawlSaigonSneaker : " + ex.getMessage());
        }

    }

    public static void crawlHoangPhucWebDriver(String url) {
        try {
            System.setProperty("webdriver.ie.driver", "G:\\ProjectJavaWeb\\IEDriverServer.exe");
            WebDriver driver = new InternetExplorerDriver();
            driver.get(url);
            String listPageString = "//body//div[@class='main-container col2-left-layout']//div[@class='col-lg-9 col-md-9 col-sm-9']//div[@class='category-products']//div[2]//ul//li";
            List<WebElement> listPage = driver.findElements(By.xpath(listPageString));
            for (int i = 1; i < listPage.size() - 1; i++) {
                url = url.substring(0, url.length() - 1) + i;
                driver.get(url);
                int j = 0;
                String xpath = "//body//div[@class='main-container col2-left-layout']//div[@class='col-lg-9 col-md-9 col-sm-9']//div[@class='category-products']//ul//li[@class='product-item item last']";
                List<WebElement> listShoes = driver.findElements(By.xpath(xpath));
                for (WebElement element : listShoes) {
                    if (j == listShoes.size()) {
                        break;
                    }
                    j = j + 1;
                    String name = element.findElement(By.xpath(xpath + "[" + j + "]" + "//div[@class='product-info']//h2")).getText().trim();
                    String price = element.findElement(By.xpath(xpath + "[" + j + "]" + "//div[@class='price-box']//span[@class='price'][1]")).getText().trim();
                    String imgSrc = element.findElement(By.xpath(xpath + "[" + j + "]" + "//img")).getAttribute("src");
                    boolean saleon = element.findElements(By.xpath(xpath + "[" + j + "]" + "//div[@class='product-sticker circle']//span")).size() > 0;
                    String saleoff = "";
                    boolean sale = false;
                    if (saleon) {
                        saleoff = element.findElement(By.xpath(xpath + "[" + j + "]" + "//div[@class='product-sticker circle']//span")).getText();
                        sale = true;
                    }
                    int quantity = 0;
                    String shopUrl = "https://hoang-phuc.com";
                    if (price.length() > 0) {
                        //price = price.replaceAll(".", "");
                        price = price.substring(0, price.length() - 2);
                    } else {
                        price = "0";
                    }
                    NumberFormat format = NumberFormat.getInstance(Locale.GERMAN);
                    double value = format.parse(price).doubleValue();
                    Sneaker sneaker = new Sneaker(0, name, value, imgSrc, sale, quantity, shopUrl);
                    String template = DBUtils.templateHoangPhuc(sneaker);
                    String process = process(template, "hoangphuc.xsl");
                    if (!process.equals("0")) {
                        ShoesHandler handler = new ShoesHandler(sneaker);
                        parseXMLtoSAX(process, handler);
                        System.out.println(handler.message);
                    }
                }
            }
            driver.quit();
            System.out.println("Done");
            CategoryDAO dao = new CategoryDAO();
            dao.updateCrawl("https://hoang-phuc.com");
        } catch (Exception ex) {
            System.out.println("Error at crawlSaigonSneaker : " + ex.getMessage());
        }

    }

    public static void crawlSaiGonSneaker(String url) {

        try {
            Document doc = Jsoup.connect(url).get();
            for (Element row : doc.select("ul.products.columns-6 li")) {
                String replaceText = row.outerHtml();
                String process = process(replaceText, "saigonsneaker.xsl");
                if (!process.equals("0")) {
                    boolean sale = false;
                    String urlPicture = row.select("a noscript img").attr("src");
                    String name = row.select("a h2").text();
                    String price = row.select("span.price ins span").text().replaceAll(",", "").trim();
                    price = price.substring(0, price.length() - 3);
                    if (row.select("a span.onsale").text().length() != 0) {
                        sale = true;
                    }
                    int quantity = 0;
                    String shopUrl = "https://saigonsneaker.com";
                    Sneaker sneaker = new Sneaker(0, name, Double.parseDouble(price), urlPicture, sale, quantity, shopUrl);
                    ShoesHandler handler = new ShoesHandler(sneaker);
                    parseXMLtoSAX(process, handler);
                    System.out.println(handler.message);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error at crawlSaigonSneaker : " + ex.getMessage());
        }

        //listDto.add(new Mobie(title, rating, urlImage));
    }
}
