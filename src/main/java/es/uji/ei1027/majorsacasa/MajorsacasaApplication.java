package es.uji.ei1027.majorsacasa;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

import com.itextpdf.text.DocumentException;
import es.uji.ei1027.majorsacasa.model.Invoice;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MajorsacasaApplication {
    private static final Logger log = Logger.getLogger(MajorsacasaApplication.class.getName());

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        new SpringApplicationBuilder(MajorsacasaApplication.class).run(args);
    }
}
