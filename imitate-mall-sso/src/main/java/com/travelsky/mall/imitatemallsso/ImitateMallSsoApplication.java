package com.travelsky.mall.imitatemallsso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.travelsky.mall.imitatemallsso.mapper.LoginMapper")
@SpringBootApplication
public class ImitateMallSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImitateMallSsoApplication.class, args);
    }

}
