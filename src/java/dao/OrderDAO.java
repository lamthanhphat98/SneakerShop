/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.*;
import database.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class OrderDAO {

    public boolean addToOrder(Order order) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Insert into dbo.[Order] values(?,?)";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, order.getUserId());
                pstm.setDate(2, order.getDate());
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

    public int getOrderByUser(String userId) throws SQLException, NamingException, InterruptedException {
        // wait(5000);
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select top 1 OrderId from dbo.[Order] where UserId = ? order by OrderId DESC";
                pstm = conn.prepareStatement(sql);;
                pstm.setString(1, userId);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    int orderId = rs.getInt("OrderId");
                    return orderId;
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
