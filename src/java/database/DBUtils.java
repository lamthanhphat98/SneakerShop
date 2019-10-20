/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import dto.Sneaker;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ADMIN
 */
public class DBUtils implements Serializable {

    public static Connection getConnection() throws NamingException, SQLException {
        Context context = new InitialContext();
        Context tomcatCt = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatCt.lookup("SE1272");
        Connection con = ds.getConnection();
        return con;
    }

    public static String templateCentimet(Sneaker sneaker) {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<div class='product_block'>\n");
        builder.append("<h3>\n");
        builder.append("<a>\n");
        builder.append(sneaker.getName());
        builder.append("</a>\n");
        builder.append("</h3>\n");
        builder.append("<div class='product_price'>\n");
        builder.append("<span>\n");
        builder.append(sneaker.getPrice());
        builder.append("</span>\n");
        builder.append("</div>\n");
        builder.append("<div>\n");
        builder.append("<a class='product_image'>\n");
        builder.append("<img class='product_image' src='" + sneaker.getPicture() + "' />\n");
        builder.append("</a>\n");
        builder.append("</div>\n");
        builder.append("</div>\n");
        //System.out.println(builder.toString());
        return builder.toString();
    }

    public static String templateSaigonSneaker(Sneaker sneaker) {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<div class='product-block'>\n");
        builder.append("<h3>\n");
        builder.append("<a>\n");
        builder.append(sneaker.getName());
        builder.append("</a>\n");
        builder.append("</h3>\n");
        builder.append("<div class='box-pro-prices'>\n");
        builder.append("<span>\n");
        builder.append(sneaker.getPrice());
        builder.append("</span>\n");
        builder.append("</div>\n");
        builder.append("<div>\n");
        builder.append("<a class='product_image'>\n");
        builder.append("<img src='" + sneaker.getPicture() + "' />\n");
        builder.append("</a>\n");
        builder.append("</div>\n");
        builder.append("</div>\n");
        //System.out.println(builder.toString());
        return builder.toString();
    }

    public static String templateHoangPhuc(Sneaker sneaker) {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<div class='product-block'>\n");
        builder.append("<h3>\n");
        builder.append("<a>\n");
        builder.append(sneaker.getName());
        builder.append("</a>\n");
        builder.append("</h3>\n");
        builder.append("<div class='box-pro-prices'>\n");
        builder.append("<span>\n");
        builder.append(sneaker.getPrice());
        builder.append("</span>\n");
        builder.append("</div>\n");
        builder.append("<div>\n");
        builder.append("<a class='product_image'>\n");
        builder.append("<img src='" + sneaker.getPicture() + "' />\n");
        builder.append("</a>\n");
        builder.append("</div>\n");
        builder.append("</div>\n");
        //System.out.println(builder.toString());
        return builder.toString();
    }

    public static String templateKingshoes(Sneaker sneaker) {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<div class='ps-shoe mb-30'>\n");
        builder.append("<h3>\n");
        builder.append("<a>\n");
        builder.append(sneaker.getName());
        builder.append("</a>\n");
        builder.append("</h3>\n");
        builder.append("<div class='product_price'>\n");
        builder.append("<span>\n");
        builder.append(sneaker.getPrice());
        builder.append("</span>\n");
        builder.append("</div>\n");
        builder.append("<div>\n");
        builder.append("<a class='product_image'>\n");
        builder.append("<img class='product_image' src='" + sneaker.getPicture() + "' />\n");
        builder.append("</a>\n");
        builder.append("</div>\n");
        builder.append("</div>\n");
        //System.out.println(builder.toString());
        return builder.toString();
    }

    public static String templateJuno(Sneaker sneaker) {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<div class='product-block'>\n");
        builder.append("<h3>\n");
        builder.append("<a>\n");
        builder.append(sneaker.getName());
        builder.append("</a>\n");
        builder.append("</h3>\n");
        builder.append("<div class='box-pro-prices'>\n");
        builder.append("<span>\n");
        builder.append(sneaker.getPrice());
        builder.append("</span>\n");
        builder.append("</div>\n");
        builder.append("<div>\n");
        builder.append("<a class='product_image'>\n");
        builder.append("<img src='" + sneaker.getPicture() + "' />\n");
        builder.append("</a>\n");
        builder.append("</div>\n");
        builder.append("</div>\n");
        System.out.println(builder.toString());
        return builder.toString();
    }
}
