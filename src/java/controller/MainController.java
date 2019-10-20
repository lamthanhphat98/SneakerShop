package controller;

import algorithm.Recommend;
import algorithm.Track;
import crawler.WebCrawler;
import dao.CategoryDAO;
import dao.SneakerDAO;
import dto.Product;
import dto.Products;
import dto.Sneaker;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import parser.JAXBParser;

/**
 *
 * @author ADMIN
 */
public class MainController extends HttpServlet {

    private static final String DEFAULTPAGE = "index.jsp";
    private static final String ADMINPAGE = "admin.jsp";
    private static final String INSERTCONTROLLER = "InsertController";
    private static final String LOGINCONTROLLER = "LoginController";
    private static final String ADMINCONTROLLER = "AdminController";
    private static final String SEARCHCONTROLLER = "SearchController";
    private static final String DETAILCONTROLLER = "DetailController";
    private static final String SHOWLISTCONTROLLER = "ShowListController";
    private static final String VIEWCARTCONTROLLER = "ViewCartController";
    private static final String EXPORTPDFCONTROLLER = "ExportPDFController";
    private static final String DELETECARTCONTROLLER = "DeleteCartController";
    private static final String CARTCONTROLLER = "CartController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // request.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml;charset=UTF-8");
        // response.setContentType("text/html;charset=UTF-8");
        String url = DEFAULTPAGE;
        //List<Mobie> listDto = new ArrayList<>();
        try {
            String action = request.getParameter("action");
            request.getSession().removeAttribute("PAGE");
            request.getSession().removeAttribute("SEARCH");
            request.getSession().removeAttribute("shopName");
            request.getSession().setAttribute("ROOT", getServletContext().getRealPath("/"));
            //loadXML(request, response);
            switch (action) {
                case "loadPage": {
                    String username = (String) request.getSession().getAttribute("USERNAME");
                    if (username != null) {
                        loadTrackUser(username, request, response);
                    } else {
                        loadNewSale(request, response);
                    }
                    break;
                }
                case "register": {
                    url = INSERTCONTROLLER;
                    request.getRequestDispatcher(url).forward(request, response);
                    break;
                }
                case "Crawl": {
                    String admin = (String) request.getSession().getAttribute("ADMIN");
                    String saigonsneakerUrl = "https://saigonsneaker.com/collections/bestseller/";
                    String stealSneaker = "https://juno.vn/collections/giay-sneaker";
                    String kingshoes = "https://kingshoes.vn/san-pham/?page=0";
                    String centimet = "https://centimet.vn/danh-muc/thoi-trang-nam/giay-nam/giay-sneakers-nam/";
                    String hoangphuc = "https://hoang-phuc.com/danh-cho-nam/giay-nam/giay-sneaker-nam.html?p=1";
                    String crawlPage = "";
                    if (admin.equals("saigonsneakeradmin")) {
                        crawlPage = saigonsneakerUrl;
                        WebCrawler.crawlSaigonSneakerWebDriver(crawlPage);
                    } else if (admin.equals("kingshoesadmin")) {
                        crawlPage = kingshoes;
                        WebCrawler.crawlKingShoesWebDriver(crawlPage);
                    } else if (admin.equals("centimetadmin")) {
                        crawlPage = centimet;
                        WebCrawler.crawlCentimetWebDriver(crawlPage);
                    } else if (admin.equals("hoangphucadmin")) {
                        crawlPage = hoangphuc;
                        WebCrawler.crawlHoangPhucWebDriver(crawlPage);
                    }
                    response.sendRedirect(ADMINPAGE);
                    break;
                }
                case "login": {
                    url = LOGINCONTROLLER;
                    request.getRequestDispatcher(url).forward(request, response);
                    break;
                }
                case "loadNewArrival": {
                    loadNewArrivals(request, response);
                    break;

                }
                case "loadNewArrivalByCategory": {
                    String option = request.getParameter("category");
                    if (!option.equals("0")) {
                        loadNewArrivalByCategory(option, request, response);

                    } else {
                        loadNewArrivals(request, response);
                    }
                    break;
                }
                case "loadSaleByCategory": {
                    String option = request.getParameter("category");
                    if (!option.equals("0")) {
                        loadSaleByCategory(option, request, response);

                    } else {
                        loadNewSale(request, response);
                    }
                    break;
                }
                case "Search": {
                    url = SEARCHCONTROLLER;
                    request.getRequestDispatcher(url).forward(request, response);
                    break;
                }
                case "detail": {
                    url = DETAILCONTROLLER;
                    request.getRequestDispatcher(url).forward(request, response);
                    break;
                }
                case "showshop": {
                    url = SHOWLISTCONTROLLER;
                    request.getRequestDispatcher(url).forward(request, response);
                    break;
                }
                case "admin": {
                    url = ADMINCONTROLLER;
                    request.getRequestDispatcher(url).forward(request, response);
                    break;
                }
                case "logout": {
                    url = DEFAULTPAGE;
                    request.getSession().removeAttribute("USERNAME");
                    request.getSession().removeAttribute("FULLNAME");
                    request.getRequestDispatcher(url).forward(request, response);
                    break;
                }
                case "addToCart": {
                    url = DETAILCONTROLLER;
                    request.getRequestDispatcher(url).forward(request, response);
                    break;
                }
                case "viewcart": {
                    url = VIEWCARTCONTROLLER;
                    request.getRequestDispatcher(url).forward(request, response);
                    break;
                }
                case "Export PDF": {
                    String admin = (String) request.getSession().getAttribute("ADMIN");
                    if (admin != null) {
                        url = EXPORTPDFCONTROLLER;
                        request.getRequestDispatcher(url).forward(request, response);
                    }
                    break;
                }
                case "suggest": {
                    String search = request.getParameter("search");
                    findSearch(request, response, search);
                    break;
                }
                case "loadHistory": {
                    String username = (String) request.getSession().getAttribute("USERNAME");
                    if (username != null) {
                        loadHistory(request, response, username);
                    }
                    break;
                }
                case "loadTrending": {
                    loadTrending(request, response);
                    break;
                }
                case "deleteCart": {
                    url = DELETECARTCONTROLLER;
                    request.getRequestDispatcher(url).forward(request, response);
                    break;
                }
                case "recommendShoes": {
                    String[] listSearch = request.getParameterValues("list")[0].split(",");
                    loadRecommendShoes(request, response, listSearch);
                    break;
                }
                case "test": {
                    String[] listSearch = request.getParameterValues("list")[0].split(",");
                    loadRecommendShoes(request, response, listSearch);
                    break;
                }
                case "minus": {
                    url = CARTCONTROLLER;
                    request.getRequestDispatcher(url).forward(request, response);
                    break;
                }
                case "plus": {
                    url = CARTCONTROLLER;
                    request.getRequestDispatcher(url).forward(request, response);
                    break;
                }
                default:
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
            }
            response.getWriter().close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadNewSale(HttpServletRequest request, HttpServletResponse response) throws NamingException, SQLException, JAXBException, IOException {
        PrintWriter out = response.getWriter();
        SneakerDAO dao = new SneakerDAO();
        //dao.all();
        dao.getNewSale();
        JAXBContext jc = JAXBContext.newInstance(Products.class);
        Marshaller ms = jc.createMarshaller();
        StringWriter writer = new StringWriter();
        //  String url = "G:\\\\ProjectJava\\\\SneakerShop\\\\web\\\\WEB-INF\\\\xml\\\\products.xml";
        ms.marshal(dao.getProducts(), writer);
        //dao.resetProduct();
        String xml = writer.toString();
        out.print(xml);
    }

    private void findSearch(HttpServletRequest request, HttpServletResponse response, String search) throws NamingException, SQLException, JAXBException, IOException {
        PrintWriter out = response.getWriter();
        SneakerDAO dao = new SneakerDAO();
        List<Sneaker> listShoes = dao.suggestSearch(search);
        // StringWriter writer = new StringWriter();
        StringBuilder sb = new StringBuilder();
        sb.append("<ul style='margin-top:10px;'>");
        for (int i = 0; i < listShoes.size(); i++) {
            Sneaker sneaker = listShoes.get(i);
            String src = sneaker.getPicture();
            sb.append("<li>");
            sb.append("<input type='hidden' value='" + sneaker.getShoesId() + "' />");
            sb.append("<a href='MainController?action=detail&id=" + sneaker.getShoesId() + "' style='font-size:10px;'>");
            sb.append(sneaker.getName());
            sb.append("</a>");
            sb.append("</li>");
        }
        sb.append("</ul>");
        //  writer.
        out.print(sb);
    }

    private void loadNewArrivals(HttpServletRequest request, HttpServletResponse response) throws NamingException, SQLException, JAXBException, IOException {
        PrintWriter out = response.getWriter();
        SneakerDAO dao = new SneakerDAO();
        dao.getNewArrival();
        JAXBContext jc = JAXBContext.newInstance(Products.class);
        Marshaller ms = jc.createMarshaller();
        StringWriter writer = new StringWriter();
        ms.marshal(dao.getNewProducts(), writer);
        String xml = writer.toString();
        out.print(xml);
    }

    private void loadNewArrivalByCategory(String category, HttpServletRequest request, HttpServletResponse response) throws NamingException, SQLException, JAXBException, IOException {
        PrintWriter out = response.getWriter();
        SneakerDAO dao = new SneakerDAO();
        dao.categoryByShop(category);
        JAXBContext jc = JAXBContext.newInstance(Products.class);
        Marshaller ms = jc.createMarshaller();
        StringWriter writer = new StringWriter();
        ms.marshal(dao.getNewProducts(), writer);
        String xml = writer.toString();
        out.print(xml);
    }

    private void loadSaleByCategory(String category, HttpServletRequest request, HttpServletResponse response) throws NamingException, SQLException, JAXBException, IOException {
        PrintWriter out = response.getWriter();
        SneakerDAO dao = new SneakerDAO();
        String xml = dao.saleCategory(category);
        out.print(xml);
    }

    private void loadHistory(HttpServletRequest request, HttpServletResponse response, String username) throws NamingException, SQLException, JAXBException, IOException {
        PrintWriter out = response.getWriter();
        SneakerDAO dao = new SneakerDAO();
        dao.getHistoryByUser(username);
        JAXBContext jc = JAXBContext.newInstance(Products.class);
        Marshaller ms = jc.createMarshaller();
        StringWriter writer = new StringWriter();
        ms.marshal(dao.getHistory(), writer);
        String xml = writer.toString();
        out.print(xml);
    }

    private void loadTrending(HttpServletRequest request, HttpServletResponse response) throws NamingException, SQLException, JAXBException, IOException {
        PrintWriter out = response.getWriter();
        SneakerDAO dao = new SneakerDAO();
        String xml = dao.getTrending();
        out.print(xml);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void loadXML(HttpServletRequest request, HttpServletResponse response) throws JAXBException, SQLException, NamingException {
        SneakerDAO dao = new SneakerDAO();
        dao.getNewArrival();
        String path = getServletContext().getRealPath("/WEB-INF/xml/products.xml");
        JAXBContext jc = JAXBContext.newInstance(Products.class);
        Marshaller ms = jc.createMarshaller();
        ms.marshal(dao.getNewProducts(), new File(path));
    }

    private void loadRecommendShoes(HttpServletRequest request, HttpServletResponse response, String[] listSearch) throws JAXBException, SQLException, NamingException, IOException {
        PrintWriter out = response.getWriter();
        SneakerDAO dao = new SneakerDAO();
        String recommender = Recommend.processingHash(listSearch);
        String xml = dao.recommendShopXML(recommender);
        out.print(xml);
    }

    private void loadTrackUser(String username, HttpServletRequest request, HttpServletResponse response) throws JAXBException, SQLException, NamingException, IOException {
        PrintWriter out = response.getWriter();
        SneakerDAO dao = new SneakerDAO();
        ArrayList<String> listItem = dao.getHistory(username);
        String recommender = Track.processingHash(listItem);
        String xml = dao.trackShopXML(recommender);
        out.print(xml);
    }
}
