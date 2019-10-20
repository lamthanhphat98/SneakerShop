/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTransform {

    public static String transformParamsToPDF(String xsl, String xml, String filePath) {
        try {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            String strDate = formatter.format(date);
            StringReader reader = new StringReader(xml);
            StringWriter writer = new StringWriter();
            StreamSource src = new StreamSource(reader);
            StreamResult res = new StreamResult(writer);
            StreamSource xslSource = new StreamSource(xsl);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer(xslSource);
            trans.setParameter("date", strDate); // pass param date to pdf xsl
            trans.setParameter("root", filePath); // pass param root to pdf xsl
            trans.transform(src, res);
            return res.getWriter().toString();
        } catch (Exception ex) {
            System.out.println("Error at Transform XML to PDF: " + ex.getMessage());
        }
        return null;

    }

    public static String transformXMLtoXSL(String xsl, String xml) {
        try {
            StringReader reader = new StringReader(xml); //convert String to character stream
            StringWriter writer = new StringWriter(); // output string
            StreamSource src = new StreamSource(reader); // read Stream
            StreamResult res = new StreamResult(writer); // output based on writer
            StreamSource xslSource = new StreamSource(xsl); // read Stream from file
            TransformerFactory tf = TransformerFactory.newInstance(); // create transformer factory
            Transformer trans = tf.newTransformer(xslSource); //create transform api
            trans.transform(src, res); // processing transform
            return res.getWriter().toString(); // output String from Stream
        } catch (Exception ex) {
            System.out.println("Error at Transform XML to XSL : " + ex.getMessage());
        }
        return null;
    }
}
