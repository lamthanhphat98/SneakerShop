/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SneakerDAO;
import dto.Product;
import dto.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.List;
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
public class SearchController extends HttpServlet {

    private static final String SHOWSEARCH = "search.jsp";

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
        //  response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/xml;charset=UTF-8");
        try {
            String page = request.getParameter("page");
            String search = request.getParameter("txtSearch");
            String task = request.getParameter("task");
            List<Product> list;
            if (search.length() != 0) {
                if (task != null) {
                    String sortBy = request.getParameter("sort");
                    if (sortBy != null) {
                        moveToPageNumberBySorting(request, response, search, Integer.parseInt(page), sortBy);
                    } else {
                        moveToPageNumber(request, response, search, page);
                    }

                } else {
                    SneakerDAO dao = new SneakerDAO();
                    dao.searchProducts(search);
                    list = dao.getSearchProducts().getProduct();
                    request.setAttribute("SEARCHLIST", list);
                    HttpSession session = request.getSession();
                    int numberOfPage = dao.getNumberOfPage(search) / 10;
                    session.setAttribute("PAGE", numberOfPage);
                    request.setAttribute("SEARCH", search);
                    request.getRequestDispatcher(SHOWSEARCH).forward(request, response);
                }
            }
            response.getWriter().close();
        } catch (Exception ex) {
            System.out.println("Error at SearchController :" + ex.getMessage());
        }
    }

    private void searchProducts(HttpServletRequest request, HttpServletResponse response, String search) throws NamingException, SQLException, JAXBException, IOException {
        PrintWriter out = response.getWriter();
        SneakerDAO dao = new SneakerDAO();
        //dao.all();
        dao.searchProducts(search);
        JAXBContext jc = JAXBContext.newInstance(Products.class);
        Marshaller ms = jc.createMarshaller();
        StringWriter writer = new StringWriter();
        //  String url = "G:\\\\ProjectJava\\\\SneakerShop\\\\web\\\\WEB-INF\\\\xml\\\\products.xml";
        ms.marshal(dao.getSearchProducts(), writer);
        //dao.resetProduct();
        String xml = writer.toString();
        out.print(xml);
    }

    private void moveToPageNumber(HttpServletRequest request, HttpServletResponse response, String search, String page) throws NamingException, SQLException, JAXBException, IOException {
        SneakerDAO dao = new SneakerDAO();
        PrintWriter out = response.getWriter();
        dao.selectPage(search, Integer.parseInt(page));
        JAXBContext jc = JAXBContext.newInstance(Products.class);
        Marshaller ms = jc.createMarshaller();
        StringWriter writer = new StringWriter();
        ms.marshal(dao.getSearchProducts(), writer);
        String xml = writer.toString();
        out.print(xml);
    }

    private void moveToPageNumberBySorting(HttpServletRequest request, HttpServletResponse response, String search, int page, String sortBy) throws NamingException, SQLException, JAXBException, IOException {
        SneakerDAO dao = new SneakerDAO();
        PrintWriter out = response.getWriter();
        dao.selectPageBySorting(search, page, sortBy);
        JAXBContext jc = JAXBContext.newInstance(Products.class);
        Marshaller ms = jc.createMarshaller();
        StringWriter writer = new StringWriter();
        ms.marshal(dao.getSearchProducts(), writer);
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
