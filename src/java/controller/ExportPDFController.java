/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import crawler.WebCrawler;
import dao.SneakerDAO;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;
import parser.XSLTransform;
import pdf.Reports;

/**
 *
 * @author ADMIN
 */
public class ExportPDFController extends HttpServlet {

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
            String admin = (String) request.getSession().getAttribute("ADMIN");
            JAXBContext jc = JAXBContext.newInstance(Reports.class);
            Marshaller ms = jc.createMarshaller();
            StringWriter sw = new StringWriter();
            String getfilePath = getServletContext().getRealPath("/");
            SneakerDAO dao = new SneakerDAO();
            String reportUrl = "";
            if (admin.equals("saigonsneakeradmin")) {
                reportUrl = "https://saigonsneaker.com";
            } else if (admin.equals("kingshoesadmin")) {
                reportUrl = "https://kingshoes.vn/other-brands/sneaker-authentic-c110.html";
            } else if (admin.equals("centimetadmin")) {
                reportUrl = "https://centimet.vn";
            } else if (admin.equals("hoangphucadmin")) {
                reportUrl = "https://hoang-phuc.com";
            }
            dao.getReportViewByAdmin(reportUrl);
            ms.marshal(dao.getReports(), sw);
            String fo = XSLTransform.transformParamsToPDF(getfilePath + "WEB-INF/xsl/report.xsl", sw.toString(), getfilePath);
            exportXMLtoPDF(getfilePath, fo, request, response);
        } catch (Exception e) {
            System.out.println("Error at ExportPDF : " + e.getMessage());
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

    private void exportXMLtoPDF(String filePath, String fo, HttpServletRequest request, HttpServletResponse response) throws FOPException, IOException, TransformerException, SAXException {
        response.setContentType("application/pdf;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        FopFactory ffactory = FopFactory.newInstance();
        ffactory.setUserConfig(filePath + "WEB-INF/xml/pdf.xml"); // pdf format
        FOUserAgent fua = ffactory.newFOUserAgent();
        Fop fop = ffactory.newFop(MimeConstants.MIME_PDF, fua, out); // configure FOP
        TransformerFactory tff = TransformerFactory.newInstance();
        Transformer trans = tff.newTransformer();
        StringReader reader = new StringReader(fo);
        StreamSource source = new StreamSource(reader);
        SAXResult result = new SAXResult(fop.getDefaultHandler()); //create resulting SAX events (the generated FO) must be piped through to
        trans.transform(source, result);
    }
}
