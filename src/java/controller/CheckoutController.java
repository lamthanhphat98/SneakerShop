/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CartDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dto.Cart;
import dto.Order;
import dto.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class CheckoutController extends HttpServlet {

    private static final String HOME = "index.jsp";
    private static final String ERRORPAGE = "error.html";

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            String username = (String) request.getSession().getAttribute("USERNAME");
            CartDAO dao = new CartDAO();
            OrderDAO order = new OrderDAO();
            OrderDetailDAO detail = new OrderDetailDAO();
            List<Cart> list = dao.getAllCart(username);
            String url = ERRORPAGE;
            boolean isCompleted = true;
            if (list != null) {
                Date sqlDate = new Date(Calendar.getInstance().getTime().getTime());
                Order orderDto = new Order();
                orderDto.setDate(sqlDate);
                orderDto.setUserId(username);
                if (order.addToOrder(orderDto)) {
                    int getorderId = order.getOrderByUser(username);
                    for (Cart cart : list) {
                        OrderDetail detailDto = new OrderDetail();
                        detailDto.setId(0);
                        detailDto.setOrderId(getorderId);
                        detailDto.setPrice(cart.getPrice() * cart.getQuantity());
                        detailDto.setQuantity(cart.getQuantity());
                        detailDto.setProductId(cart.getProductId());
                        detailDto.setSizeName(cart.getSizeName());
                        boolean success = detail.addToOrderDetail(detailDto);
                        if (success) {
                            boolean delete = dao.deleteCart(cart.getCartId(), username, cart.getSizeName());
                            if (!delete) {
                                isCompleted = false;
                                System.out.println("Error at checkout product");
                            } else {
                                isCompleted = true;
                            }
                        }
                    }

                }
            }
            if (isCompleted == true) {
                url = HOME;
                request.setAttribute("COMPLETE", "OK");
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect(url);
            }
        } catch (Exception ex) {
            System.out.println("Error at CheckoutController : " + ex.getMessage());
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
