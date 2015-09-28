package foo.twiddle.jasper;

import java.util.HashMap;
import java.util.Locale;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import net.sf.jasperreports.view.JasperViewer;
import org.w3c.dom.Document;

public class SimpleReport {

    public SimpleReport() {
        JasperPrint jasperPrint = null;
        HashMap params = new HashMap();
        try {
            Document document = JRXmlUtils.parse(JRLoader.getLocationInputStream("treats.xml"));
            params.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, document);
            params.put(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, "yyyy-MM-dd");
            params.put(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, "#,##0.##");
            params.put(JRXPathQueryExecuterFactory.XML_LOCALE, Locale.ENGLISH);
            params.put(JRParameter.REPORT_LOCALE, Locale.US);

            JasperCompileManager.compileReportToFile("reports/treats.jrxml");
            jasperPrint = JasperFillManager.fillReport("reports/treats.jasper", params);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    	new SimpleReport();
    }

}
