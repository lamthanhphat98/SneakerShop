/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SneakerDAO;
import dto.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author ADMIN
 */
public class ShowListController extends HttpServlet {

    private static String SHOWLISTPAGE = "showlist.jsp";

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
        response.setContentType("text/xml;charset=UTF-8");
        try {
            String shopName = request.getParameter("shopUrl");
            // String load = request.getParameter("load");
            String page = request.getParameter("page");
            HttpSession session = request.getSession();
            String task = request.getParameter("task");
            //session.removeAttribute("PAGE");
            // session.setAttribute("shopName", shopName);
            SneakerDAO dao = new SneakerDAO();
            if (task != null) {
                String sortBy = request.getParameter("sort");
                if (sortBy != null) {
                    moveToPageNumberBySorting(request, response, shopName, Integer.parseInt(page), sortBy);
                } else {
                    moveToPageNumber(request, response, shopName, Integer.parseInt(page));
                }
            } else {
                if (shopName.equals("Saigon")) {
                    int numberOfPage = dao.getNumberOfPageByShopName(shopName) / 10;
                    session.setAttribute("PAGE", numberOfPage);
                    //response.sendRedirect("showlist.jsp");
                    session.setAttribute("SHOPNAME", shopName);
                    dao.getProductsByShopName(shopName, 1);
                    request.setAttribute("LIST", dao.getListProducts().getProduct());
                    request.getRequestDispatcher(SHOWLISTPAGE).forward(request, response);
                } else if (shopName.equals("KingShoes")) {
                    int numberOfPage = dao.getNumberOfPageByShopName(shopName) / 10;
                    session.setAttribute("PAGE", numberOfPage);
                    session.setAttribute("SHOPNAME", shopName);
                    dao.getProductsByShopName(shopName, 1);
                    request.setAttribute("LIST", dao.getListProducts().getProduct());
                    request.getRequestDispatcher(SHOWLISTPAGE).forward(request, response);
                } else if (shopName.equals("Centimet")) {
                    int numberOfPage = dao.getNumberOfPageByShopName(shopName) / 10;
                    session.setAttribute("PAGE", numberOfPage);
                    session.setAttribute("SHOPNAME", shopName);
                    dao.getProductsByShopName(shopName, 1);
                    request.setAttribute("LIST", dao.getListProducts().getProduct());
                    request.getRequestDispatcher(SHOWLISTPAGE).forward(request, response);
                }
            }
            response.getWriter().close();

        } catch (Exception ex) {
            System.out.println("Error at ShowListController : " + ex.getMessage());
        }
    }

    private void loadByShopName(HttpServletRequest request, HttpServletResponse response, String shopName, int page) throws NamingException, SQLException, JAXBException, IOException {
        SneakerDAO dao = new SneakerDAO();
        PrintWriter out = response.getWriter();
        dao.getProductsByShopName(shopName, page);
        JAXBContext jc = JAXBContext.newInstance(Products.class);
        Marshaller ms = jc.createMarshaller();
        StringWriter writer = new StringWriter();
        ms.marshal(dao.getListProducts(), writer);
        String xml = writer.toString();
        out.print(xml);
    }

    private void moveToPageNumber(HttpServletRequest request, HttpServletResponse response, String shopName, int page) throws NamingException, SQLException, JAXBException, IOException {
        SneakerDAO dao = new SneakerDAO();
        PrintWriter out = response.getWriter();
        dao.getProductsByShopName(shopName, page);
        JAXBContext jc = JAXBContext.newInstance(Products.class);
        Marshaller ms = jc.createMarshaller();
        StringWriter writer = new StringWriter();
        ms.marshal(dao.getListProducts(), writer);
        String xml = writer.toString();
        out.print(xml);
    }

    private void moveToPageNumberBySorting(HttpServletRequest request, HttpServletResponse response, String shopName, int page, String sortBy) throws NamingException, SQLException, JAXBException, IOException {
        SneakerDAO dao = new SneakerDAO();
        PrintWriter out = response.getWriter();
        dao.getProductsByShopNameAndSort(shopName, page, sortBy);
        JAXBContext jc = JAXBContext.newInstance(Products.class);
        Marshaller ms = jc.createMarshaller();
        StringWriter writer = new StringWriter();
        ms.marshal(dao.getListProducts(), writer);
        String xml = writer.toString();
        out.print(xml);
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
