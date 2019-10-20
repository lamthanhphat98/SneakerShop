/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import static algorithm.Recommend.getMax;
import dao.SneakerDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class Track {

    public static HashMap<String, Double> recommendShop(ArrayList<String> listSearch) throws SQLException, NamingException {
        SneakerDAO dao = new SneakerDAO();
        HashMap<String, Double> hashMap = new HashMap<>();
        hashMap = dao.trackUser(listSearch, hashMap);
        return hashMap;
    }

    public static String processingHash(ArrayList<String> listSearch) throws SQLException, NamingException {
        HashMap<String, Double> hashShoes = recommendShop(listSearch);
        Double total = 0.0;
        double percentSaigonShop = 0;
        double percentKingshoes = 0;
        double percentJuno = 0;
        double percentCentimet = 0;
        double percentHoangPhuc = 0;

        for (Map.Entry<String, Double> m : hashShoes.entrySet()) {
            System.out.println("Sản phẩm của shop " + m.getKey().toString() + " mà người dùng đã search có : " + m.getValue() + " sản phẩm");
            total = total + m.getValue();
        }
        for (Map.Entry m : hashShoes.entrySet()) {
            System.out.println("Sản phẩm của shop " + m.getKey().toString() + " có " + m.getValue() + " trên tổng số " + total + " sản phẩm đã tìm thấy");
        }

        for (Map.Entry<String, Double> m : hashShoes.entrySet()) {
            System.out.println("Hệ thống sẽ gợi ý cho bạn shop có sản phẩm mà bạn có thể sẽ quan tâm!!! xin vui lòng đợi trong giây lát.");
            if (m.getKey().equals("https://saigonsneaker.com")) {
                percentSaigonShop = m.getValue() / total;
                System.out.println("Shop " + m.getKey().toString() + " có số sản phẩm xuất hiện là : " + percentSaigonShop + "%");
            } else if (m.getKey().equals("https://kingshoes.vn/other-brands/sneaker-authentic-c110.html")) {
                percentKingshoes = m.getValue() / total;
                System.out.println("Shop " + m.getKey().toString() + " có số sản phẩm xuất hiện là : " + percentKingshoes + "%");
            } else if (m.getKey().equals("https://juno.vn/collections/giay-sneaker")) {
                percentJuno = m.getValue() / total;
                System.out.println("Shop " + m.getKey().toString() + " có số sản phẩm xuất hiện là : " + percentJuno + "%");
            } else if (m.getKey().equals("https://centimet.vn")) {
                percentCentimet = m.getValue() / total;
                System.out.println("Shop " + m.getKey().toString() + " có số sản phẩm xuất hiện là : " + percentCentimet + "%");
            } else if (m.getKey().equals("https://hoang-phuc.com")) {
                percentHoangPhuc = m.getValue() / total;
                System.out.println("Shop " + m.getKey().toString() + " có số sản phẩm xuất hiện là : " + percentHoangPhuc + "%");
            }

        }
        if (getMax(percentSaigonShop, percentKingshoes, percentJuno, percentCentimet, percentHoangPhuc) == percentSaigonShop) {
            return "saigonsneaker";
        }
        if (getMax(percentSaigonShop, percentKingshoes, percentJuno, percentCentimet, percentHoangPhuc) == percentKingshoes) {
            return "kingshoes";
        }
        if (getMax(percentSaigonShop, percentKingshoes, percentJuno, percentCentimet, percentHoangPhuc) == percentJuno) {
            return "juno";
        }
        if (getMax(percentSaigonShop, percentKingshoes, percentJuno, percentCentimet, percentHoangPhuc) == percentCentimet) {
            return "centimet";
        }
        if (getMax(percentSaigonShop, percentKingshoes, percentJuno, percentCentimet, percentHoangPhuc) == percentHoangPhuc) {
            return "hoang-phuc";
        }

        return null;
    }

    public static double getMax(double shopA, double shopB, double shopC, double shopD, double shopE) {
        double max = 0;
        if (shopA > shopB && shopA > shopC && shopA > shopD && shopA > shopE) {
            max = shopA;
        }
        if (shopB > shopC && shopB > shopD && shopB > shopA && shopB > shopE) {
            max = shopB;
        }
        if (shopC > shopD && shopC > shopA && shopC > shopB && shopC > shopE) {
            max = shopC;
        }
        if (shopD > shopA && shopD > shopB && shopD > shopC && shopD > shopE) {
            max = shopD;
        }
        if (shopE > shopA && shopE > shopB && shopE > shopC && shopE > shopD) {
            max = shopE;
        }
        return max;
    }
}
