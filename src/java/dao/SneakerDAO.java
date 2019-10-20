/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBUtils;
import dto.Product;
import dto.Products;
import dto.Sneaker;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.naming.NamingException;
import pdf.Report;
import pdf.Reports;

/**
 *
 * @author ADMIN
 */
public class SneakerDAO {

    Products searchProducts = new Products();
    Products newProducts = new Products();
    Products products = new Products();
    Products listProducts = new Products();
    Reports reports = new Reports();
    Products history = new Products();

    public Products getHistory() {
        return history;
    }

    public Reports getReports() {
        return reports;
    }

    public Products getListProducts() {
        return listProducts;
    }

    public Products getNewProducts() {
        return newProducts;
    }

    public Products getSearchProducts() {
        return searchProducts;
    }

    public Products getProducts() {
        return products;
    }

    public void resetProduct() {
        products = new Products();
    }

    public boolean insertShoes(Sneaker sneaker) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Insert into Shoes values(?,?,?,?,?,?,?)";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, sneaker.getName());
                pstm.setDouble(2, sneaker.getPrice());
                pstm.setString(3, sneaker.getPicture());
                pstm.setBoolean(4, sneaker.isSale());
                pstm.setInt(5, sneaker.getQuantity());
                pstm.setString(6, sneaker.getSelledBy());
                pstm.setInt(7, 0);

                int rs = pstm.executeUpdate();
                if (rs > 0) {
                    return true;
                }

            }
        } finally {

            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public void getNewSale() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select top 8 Id,Name,Price,Picture from Shoes where Sale='true' order by newid()";
                pstm = conn.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    double price = rs.getDouble("Price");;
                    String picture = rs.getString("Picture");;
                    Product dto = new Product(id, name, BigDecimal.valueOf(price), picture);
                    //String name = rs.getString("roleName");
                    products.getProduct().add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        //   return products;
    }

    public Sneaker getDetailShoes(String id) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select * from Shoes where Id = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, Integer.parseInt(id));
                rs = pstm.executeQuery();
                if (rs.next()) {
                    int shoesId = rs.getInt("Id");
                    String name = rs.getString("Name");
                    double price = rs.getDouble("Price");;
                    String picture = rs.getString("Picture");
                    Boolean sale = rs.getBoolean("Sale");
                    int quantity = rs.getInt("Quantity");
                    String shop = rs.getString("SelledBy");
                    Sneaker dto = new Sneaker(shoesId, name, price, picture, sale, quantity, shop);
                    //String name = rs.getString("roleName");
                    return dto;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public void getNewArrival() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select Top 8 Id,Name,Price,Picture from Shoes order by Id DESC";
                pstm = conn.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    double price = rs.getDouble("Price");;
                    String picture = rs.getString("Picture");;
                    Product dto = new Product(id, name, BigDecimal.valueOf(price), picture);
                    //String name = rs.getString("roleName");
                    newProducts.getProduct().add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        //   return products;
    }

    public void searchProducts(String productName) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select top 10 Id,Name,Price,Picture from Shoes where Name like ? order by Id,Viewed DESC";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, "%" + productName + "%");
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    double price = rs.getDouble("Price");;
                    String picture = rs.getString("Picture");;
                    Product dto = new Product(id, name, BigDecimal.valueOf(price), picture);
                    //String name = rs.getString("roleName");
                    searchProducts.getProduct().add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void getProductsByShopName(String shopName, int page) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "";
                if (page == 1) {
                    sql = "Select top 10 Id,Name,Price,Picture from Shoes where SelledBy like ? order by Id DESC"; //default 10
                } else {
                    sql = "Select Id,Name,Price,Picture from Shoes where SelledBy like ? order by Id DESC offset " + (page - 1) * 10 + " rows fetch next 10 rows only";
                }
                pstm = conn.prepareStatement(sql);
                if (shopName.equals("Saigon")) {
                    pstm.setString(1, "%" + "saigonsneaker" + "%");
                } else if (shopName.equals("KingShoes")) {
                    pstm.setString(1, "%" + "kingshoes" + "%");
                } else if (shopName.equals("Centimet")) {
                    pstm.setString(1, "%" + "centimet" + "%");
                }
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    double price = rs.getDouble("Price");;
                    String picture = rs.getString("Picture");;
                    Product dto = new Product(id, name, BigDecimal.valueOf(price), picture);
                    //String name = rs.getString("roleName");
                    listProducts.getProduct().add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        //   return products;
    }

    public int getNumberOfPageByShopName(String shopName) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select count(Id) from Shoes where SelledBy like ?";
                pstm = conn.prepareStatement(sql);
                if (shopName.equals("Saigon")) {
                    pstm.setString(1, "%" + "saigonsneaker" + "%");
                } else if (shopName.equals("KingShoes")) {
                    pstm.setString(1, "%" + "kingshoes" + "%");
                } else if (shopName.equals("Centimet")) {
                    pstm.setString(1, "%" + "centimet" + "%");
                }
                rs = pstm.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count;

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return 0;
    }

    public int getNumberOfPage(String productName) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select count(Id) from Shoes where Name like ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, "%" + productName + "%");
                rs = pstm.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return 0;
        //   return products;
    }

    public void selectPage(String productName, int page) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        searchProducts = new Products();
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "";
                if (page == 1) {
                    sql = "Select top 10 Id,Name,Price,Picture from Shoes where Name like ? order by Id DESC"; //default 10
                } else {
                    sql = "Select Id,Name,Price,Picture from Shoes where Name like ? order by Id DESC offset " + (page - 1) * 10 + " rows fetch next 10 rows only";
                }
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, "%" + productName + "%");
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    double price = rs.getDouble("Price");;
                    String picture = rs.getString("Picture");;
                    Product dto = new Product(id, name, BigDecimal.valueOf(price), picture);
                    //String name = rs.getString("roleName");
                    searchProducts.getProduct().add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        //   return products;
    }

    public boolean updateViewDetail(String shoesId) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Update Shoes set Viewed = (Select Viewed from Shoes Where Id = ? ) + 1 where Id = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, Integer.parseInt(shoesId));
                pstm.setInt(2, Integer.parseInt(shoesId));
                int rs = pstm.executeUpdate();
                if (rs > 0) {
                    return true;
                }

            }
        } finally {

            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean getExistedShoesByDomain(String name, String url) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select Id from Shoes where Name = ? and SelledBy = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, name);
                pstm.setString(2, url);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public void getReportViewByAdmin(String shopUrl) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select top 15 Id,Name,Price,Picture,Viewed from Shoes where SelledBy = ? order by Viewed DESC";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, shopUrl);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    double price = rs.getDouble("Price");;
                    String picture = rs.getString("Picture");
                    int view = rs.getInt("Viewed");
                    Report rp = new Report(id, name, BigDecimal.valueOf(price), picture, view);
                    //String name = rs.getString("roleName");
                    reports.getReport().add(rp);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        //   return products;
    }

    public List<Sneaker> suggestSearch(String productName) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Sneaker> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select top 3 Id,Name,Picture from Shoes where Name like ? order by Id DESC";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, "%" + productName + "%");
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    String picture = rs.getString("Picture");;
                    Sneaker sneaker = new Sneaker(id, name, picture);
                    listProduct.add(sneaker);
                }
                return listProduct;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public void getHistoryByUser(String username) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select top 8 Id,Name,Price,Picture from Shoes s join History h on s.Id = h.ShoesId join dbo.[User] u on u.Username = h.Username and u.Username = ? order by s.Id DESC";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    double price = rs.getDouble("Price");;
                    String picture = rs.getString("Picture");;
                    Product dto = new Product(id, name, BigDecimal.valueOf(price), picture);
                    history.getProduct().add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        //   return products;
    }

    public String getTrending() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select top 8 Id,Name,Price,Picture from Shoes order by Viewed DESC For XML Path('product'),Root('products')";
                pstm = conn.prepareStatement(sql);
                rs = pstm.executeQuery();
                String result = "";
                while (rs.next()) {
                    result += rs.getString(1);
                }
                return result;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public HashMap<String, Double> recommendShoes(String[] listSearch, HashMap<String, Double> hashShoes) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        // HashMap<String, Integer> hashShoes = new HashMap<String, Integer>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                for (String item : listSearch) {
                    String sql = "Select count(SelledBy),SelledBy from Shoes where Name like ? group by SelledBy";
                    pstm = conn.prepareStatement(sql);
                    pstm.setString(1, "%" + item + "%");
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        String shopUrl = rs.getString(2);
                        double countShoes = rs.getDouble(1);
                        if (hashShoes.containsKey(shopUrl)) {
                            Double oldValue = hashShoes.get(shopUrl);
                            Double newValue = oldValue + countShoes;
                            hashShoes.replace(shopUrl, newValue);
                        } else {
                            hashShoes.put(shopUrl, countShoes);
                        }
                    }
                }
                return hashShoes;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public HashMap<String, Double> trackUser(ArrayList<String> listSearch, HashMap<String, Double> hashShoes) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        // HashMap<String, Integer> hashShoes = new HashMap<String, Integer>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                for (String item : listSearch) {
                    String sql = "Select count(SelledBy),SelledBy from Shoes where Id = ? group by SelledBy";
                    pstm = conn.prepareStatement(sql);
                    pstm.setInt(1, Integer.parseInt(item));
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        String shopUrl = rs.getString(2);
                        double countShoes = rs.getDouble(1);
                        if (hashShoes.containsKey(shopUrl)) {
                            Double oldValue = hashShoes.get(shopUrl);
                            Double newValue = oldValue + countShoes;
                            hashShoes.replace(shopUrl, newValue);
                        } else {
                            hashShoes.put(shopUrl, countShoes);
                        }
                    }
                }
                return hashShoes;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public String recommendShopXML(String productName) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select top 4 Id,Name,Price,Picture from Shoes where SelledBy like ? order by newid() For XML Path('product'),Root('products')";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, "%" + productName + "%");
                rs = pstm.executeQuery();
                String result = "";
                while (rs.next()) {
                    result += rs.getString(1);
                }
                return result;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public String trackShopXML(String shopName) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select top 8 Id,Name,Price,Picture from Shoes where SelledBy like ? order by newid() For XML Path('product'),Root('products')";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, "%" + shopName + "%");
                rs = pstm.executeQuery();
                String result = "";
                while (rs.next()) {
                    result += rs.getString(1);
                }
                return result;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public void categoryByShop(String selledby) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select top 8 Id,Name,Price,Picture from Shoes where SelledBy like ? order by Viewed";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, "%" + selledby + "%");
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    double price = rs.getDouble("Price");;
                    String picture = rs.getString("Picture");;
                    Product dto = new Product(id, name, BigDecimal.valueOf(price), picture);
                    //String name = rs.getString("roleName");
                    newProducts.getProduct().add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        //return null;
    }

    public String saleCategory(String selledBy) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select top 8 Id,Name,Price,Picture from Shoes where SelledBy like ? order by newid() For XML Path('product'),Root('products')";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, "%" + selledBy + "%");
                rs = pstm.executeQuery();
                String result = "";
                while (rs.next()) {
                    result += rs.getString(1);
                }
                return result;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public ArrayList<String> getHistory(String username) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        ArrayList<String> listItem = new ArrayList<>();

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select top 5 ShoesId from History where Username =  ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    listItem.add(String.valueOf(rs.getInt(1)));
                }
                return listItem;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public void getProductsByShopNameAndSort(String shopName, int page, String sortBy) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "";
                if (page == 1) {
                    if (sortBy.equals("DESC")) {
                        sql = "Select top 10 Id,Name,Price,Picture from Shoes where SelledBy like ? order by Price DESC"; //default 10
                    } else if (sortBy.equals("ASC")) {
                        sql = "Select top 10 Id,Name,Price,Picture from Shoes where SelledBy like ? order by Price ASC"; //default 10
                    }
                } else {
                    if (sortBy.equals("DESC")) {
                        sql = "Select Id,Name,Price,Picture from Shoes where SelledBy like ? order by Price DESC offset " + (page - 1) * 10 + " rows fetch next 10 rows only";
                    } else if (sortBy.equals("ASC")) {
                        sql = "Select Id,Name,Price,Picture from Shoes where SelledBy like ? order by Price ASC offset " + (page - 1) * 10 + " rows fetch next 10 rows only";
                    }
                }
                pstm = conn.prepareStatement(sql);
                if (shopName.equals("Saigon")) {
                    pstm.setString(1, "%" + "saigonsneaker" + "%");
                } else if (shopName.equals("KingShoes")) {
                    pstm.setString(1, "%" + "kingshoes" + "%");
                } else if (shopName.equals("Centimet")) {
                    pstm.setString(1, "%" + "centimet" + "%");
                }
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    double price = rs.getDouble("Price");;
                    String picture = rs.getString("Picture");;
                    Product dto = new Product(id, name, BigDecimal.valueOf(price), picture);
                    //String name = rs.getString("roleName");
                    listProducts.getProduct().add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        //   return products;
    }

    public void selectPageBySorting(String productName, int page, String sortBy) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        searchProducts = new Products();
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "";
                if (page == 1) {
                    if (sortBy.equals("DESC")) {
                        sql = "Select top 10 Id,Name,Price,Picture from Shoes where Name like ? order by Price DESC"; //default 10
                    } else if (sortBy.equals("ASC")) {
                        sql = "Select top 10 Id,Name,Price,Picture from Shoes where Name like ? order by Price ASC"; //default 10
                    }
                } else {
                    if (sortBy.equals("DESC")) {
                        sql = "Select Id,Name,Price,Picture from Shoes where Name like ? order by Price DESC offset " + (page - 1) * 10 + " rows fetch next 10 rows only";
                    } else if (sortBy.equals("ASC")) {
                        sql = "Select Id,Name,Price,Picture from Shoes where Name like ? order by Price ASC offset " + (page - 1) * 10 + " rows fetch next 10 rows only";
                    }
                }
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, "%" + productName + "%");
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    double price = rs.getDouble("Price");;
                    String picture = rs.getString("Picture");;
                    Product dto = new Product(id, name, BigDecimal.valueOf(price), picture);
                    searchProducts.getProduct().add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        //   return products;
    }
}
