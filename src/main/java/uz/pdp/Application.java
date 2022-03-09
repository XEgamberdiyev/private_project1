package uz.pdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages="uz.pdp.config")
public class Application {
        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }
}
