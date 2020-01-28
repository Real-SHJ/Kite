package com.wildbody.kite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class KiteSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(KiteSpringApplication.class, args);
    }

}
