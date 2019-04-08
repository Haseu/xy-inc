package br.com.zup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.zup.entity"})
public class ZupApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZupApplication.class, args);
    }

}
