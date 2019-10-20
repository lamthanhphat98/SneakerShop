/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBUtils;
import dto.Customer;
import dto.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class CustomerDAO {

    public boolean addNewUser(Customer user) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "insert into dbo.[User] values(?,?,?,?,?,?)";
                pstm = conn.prepareStatement(sql);
                if (user.getUsername() == null) {
                    return false;
                }
                pstm.setString(1, user.getUsername());
                pstm.setString(2, user.getPassword());
                pstm.setString(3, user.getFullName());
                pstm.setString(4, user.getEmail());
                pstm.setString(5, user.getPhone());
                pstm.setString(6, user.getAddress());
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

    public String checklogin(String username, String password) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select Username,Fullname from dbo.[User] where Username = ? and Password = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setString(2, password);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getString("Fullname");
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
}
