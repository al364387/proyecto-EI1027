package es.uji.ei1027.majorsacasa;

import java.util.logging.Logger;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MajorsacasaApplication {
    private static final Logger log = Logger.getLogger(MajorsacasaApplication.class.getName());

    public static void main(String[] args){
        new SpringApplicationBuilder(MajorsacasaApplication.class).run(args);
    }
}
