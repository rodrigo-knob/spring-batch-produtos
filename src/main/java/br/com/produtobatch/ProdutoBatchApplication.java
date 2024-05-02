package br.com.produtobatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProdutoBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdutoBatchApplication.class, args);
    }

}
