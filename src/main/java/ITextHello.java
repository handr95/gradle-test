import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ITextHello {
    public static final String RESULT = "build.pdf";

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        new ITextHello().createPdf(RESULT);
    }

    public void createPdf(String filename) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        document.add(new Paragraph("Hello Wolrd"));

        document.close();
    }

}
