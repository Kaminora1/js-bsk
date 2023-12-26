package com.rd.bsk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rd.bsk.mapper")
public class BskApplication {

    public static void main(String[] args) {
        SpringApplication.run(BskApplication.class, args);
    }

}
