package br.com.anderson.silva.apiconvert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiGetVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGetVideoApplication.class, args);
    }

}
