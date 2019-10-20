/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import java.io.File;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

public class JAXBParser {

    private static final String XSD_FILE = "G:\\ProjectJava\\SneakerShop\\web\\WEB-INF\\xsd\\pdf.xsd";
    private static final String OUTPUT_FOLDER = "G:\\ProjectJava\\SneakerShop\\src\\java";

    /**
     * Separated main function to parse .xsd schema into .java class binding
     * object To use this parser, hit Shift+F6
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            String output = OUTPUT_FOLDER;
            SchemaCompiler sc = XJC.createSchemaCompiler();
            sc.setErrorListener(new ErrorListener() {
                @Override
                public void error(SAXParseException saxpe) {
                    System.out.println(saxpe);
                }

                @Override
                public void fatalError(SAXParseException saxpe) {
                    System.out.println(saxpe);

                }

                @Override
                public void warning(SAXParseException saxpe) {
                    System.out.println(saxpe);

                }

                @Override
                public void info(SAXParseException saxpe) {
                    System.out.println(saxpe);
                }
            });
            sc.forcePackageName("pdf");
            File schema = new File(XSD_FILE);
            InputSource is = new InputSource(schema.toURI().toString());
            sc.parseSchema(is);
            S2JJAXBModel model = sc.bind();
            JCodeModel code = model.generateCode(null, null);
            code.build(new File(output));
            System.out.println("Finished");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
