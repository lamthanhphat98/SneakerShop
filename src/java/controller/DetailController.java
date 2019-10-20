/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CartDAO;
import dao.HistoryDAO;
import dao.SneakerDAO;
import dto.Cart;
import dto.Products;
import dto.Sneaker;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import static parser.Format.ISO_8859_1;
import static parser.Format.UTF_8;

/**
 *
 * @author ADMIN
 */
public class DetailController extends HttpServlet {

    private static final String DETAILPAGE = "detail.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/xml;charset=UTF-8");
        try {
            String id = request.getParameter("id");
            SneakerDAO dao = new SneakerDAO();
            Sneaker dto = null;
            if (id != null && id.length() != 0) {
                dto = dao.getDetailShoes(id);

            }
            String task = request.getParameter("task");
            if ("addToCart".equals(task)) {
                String shoesId = request.getParameter("shoesId");
                String name = request.getParameter("name");
                byte[] getByte = name.getBytes(ISO_8859_1);
                String formatName = new String(getByte, UTF_8);
                String price = request.getParameter("price");
                String picture = request.getParameter("image");
                String size = request.getParameter("size");
                String username = (String) request.getSession().getAttribute("USERNAME");
                Cart cart = new Cart(0, Integer.parseInt(shoesId), formatName, 1, Double.parseDouble(price), size, picture, username);
                addToCart(request, response, cart);
            }
            if (dto != null && (task == null || task.length() == 0)) {
                if (dao.updateViewDetail(id)) {
                    HistoryDAO historyDao = new HistoryDAO();
                    String username = (String) request.getSession().getAttribute("USERNAME");
                    if (username != null) {
                        if (!historyDao.getHistoryByUserAndShoes(username, Integer.parseInt(id))) {
                            if (!historyDao.trackHistory(username, Integer.parseInt(id))) {
                                System.out.println("Tracking history error");
                            }
                        }
                    }
                    request.setAttribute("DTO", dto);
                    request.getRequestDispatcher(DETAILPAGE).forward(request, response);
                }
            }
            response.getWriter().close();
        } catch (Exception ex) {
            System.out.println("Error at DetailController : " + ex.getMessage());
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response, Cart cart) throws NamingException, SQLException, JAXBException, IOException {
        PrintWriter out = response.getWriter();
        CartDAO dao = new CartDAO();
        if (dao.getItemByUser("" + cart.getProductId(), cart.getUserId(), cart.getSizeName())) {
            if (dao.updateQuantity("" + cart.getProductId(), cart.getUserId(), cart.getSizeName())) {
                out.print("OK");
            } else {
                out.print("ERROR");
            }
        } else {
            if (dao.insertCart(cart)) {
                out.print("OK");
            } else {
                out.print("ERROR");
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
