/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBUtils;
import dto.Cart;
import dto.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO {

    public boolean updateCrawl(String shopUrl) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Update Category set Crawl = (select Crawl from Category where ShopUrl = ? ) + 1 where ShopUrl = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, shopUrl);
                pstm.setString(2, shopUrl);
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
}
