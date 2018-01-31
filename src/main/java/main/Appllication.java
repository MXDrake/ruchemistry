package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@SpringBootApplication
public class Appllication {
    private static final Logger logger = LoggerFactory.getLogger(Appllication.class);

    public static void main(String[] args) {
        SpringApplication.run(Appllication.class,args);
        logger.info("Application \"Ruchemistry\" started");
    }
}
