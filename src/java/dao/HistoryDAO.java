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
public class HistoryDAO {

    public boolean trackHistory(String username, int shoesId) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "insert into dbo.[History] values(?,?)";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setInt(2, shoesId);
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

    public boolean getHistoryByUserAndShoes(String username, int productId) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // List<Product> listProduct = new ArrayList();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select top 1 ShoesId from History where Username = ? and ShoesId = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setInt(2, productId);
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

}
