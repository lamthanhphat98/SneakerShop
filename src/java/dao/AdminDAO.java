/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBUtils;
import dto.Product;
import dto.Sneaker;
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
public class AdminDAO {

    public String checkAdminLogin(String username, String password) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select Username from dbo.[Admin] where Username = ? and Password = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setString(2, password);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getString("Username");
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

    public boolean updateView(String admin, int updateView) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Update Admin set Viewed = ? where Username = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, admin);
                pstm.setInt(2, updateView);
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

    public int getViewedByAdmin(String admnin) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select Viewed from Admin where Username = ?";
                pstm = conn.prepareStatement(sql);
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
}
