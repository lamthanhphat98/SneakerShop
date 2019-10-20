/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class TrendDAO {

    public boolean trackHistory(String username, String search) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "insert into dbo.[Trend] values(?,?)";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setString(2, search);
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
