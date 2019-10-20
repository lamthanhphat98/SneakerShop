/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBUtils;
import dto.Cart;
import dto.Sneaker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class CartDAO {

    public boolean insertCart(Cart cart) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Insert into Cart values(?,?,?,?,?,?,?)";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, cart.getProductId());
                pstm.setString(2, cart.getProductName());
                pstm.setInt(3, cart.getQuantity());
                pstm.setDouble(4, cart.getPrice());
                pstm.setString(5, cart.getSizeName());
                pstm.setString(6, cart.getPicture());
                pstm.setString(7, cart.getUserId());
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

    public boolean deleteCart(int cartId, String username, String sizeName) throws NamingException, SQLException {

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Delete from dbo.[Cart] where CartId = ? and UserId = ? and SizeName = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, cartId);
                pstm.setString(2, username);
                pstm.setString(3, sizeName);
                int rs = pstm.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error at deleteDAO :" + ex.getMessage());
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

    public boolean getItemByUser(String id, String username, String sizeName) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select * from Cart where ProductId = ? and UserId =? and SizeName = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, Integer.parseInt(id));
                pstm.setString(2, username);
                pstm.setString(3, sizeName);
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

    public boolean updateQuantity(String id, String username, String sizeName) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        //ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Update Cart set Quantity = (select Quantity from Cart where ProductId = ? and UserId = ? and SizeName = ? ) + 1 where ProductId = ? and UserId = ? and SizeName = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, Integer.parseInt(id));
                pstm.setString(2, username);
                pstm.setString(3, sizeName);
                pstm.setInt(4, Integer.parseInt(id));
                pstm.setString(5, username);
                pstm.setString(6, sizeName);
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

    public String getAllCartXML(String username) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Cart> listCart = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select CartId,ProductId,ProductName,Quantity,CAST(Price AS decimal(38,0)) as Price,SizeName,Picture,UserId from Cart where UserId =? For XML Path('shoes'),Root('shoese')";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getString(1);
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

    public List<Cart> getAllCart(String username) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Cart> listCart = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select * from Cart where UserId =?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                //pstm.setString(2, username);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("CartId");
                    int productId = rs.getInt("ProductId");
                    String productName = rs.getString("ProductName");
                    int quantity = rs.getInt("Quantity");
                    Double price = rs.getDouble("Price");
                    String sizeName = rs.getString("SizeName");
                    String picture = rs.getString("Picture");
                    String userId = rs.getString("UserId");
                    Cart cart = new Cart(id, productId, productName, quantity, price, sizeName, picture, userId);
                    listCart.add(cart);

                }
            }
            return listCart;
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

    public int getCart(String id) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select top 1 Quantity from Cart where CartId =?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, Integer.parseInt(id));
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
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

    public boolean plus(String id) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        //ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Update Cart set Quantity = (select Quantity from Cart where CartId = ?) + 1 where CartId = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, Integer.parseInt(id));
                pstm.setInt(2, Integer.parseInt(id));
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

    public boolean minus(String id) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        //ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                if (getCart(id) > 1) {
                    String sql = "Update Cart set Quantity = (select Quantity from Cart where CartId = ?) - 1 where CartId = ?";
                    pstm = conn.prepareStatement(sql);
                    pstm.setInt(1, Integer.parseInt(id));
                    pstm.setInt(2, Integer.parseInt(id));
                    int rs = pstm.executeUpdate();
                    if (rs > 0) {
                        return true;
                    }
                } else {
                    return false;
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
}
