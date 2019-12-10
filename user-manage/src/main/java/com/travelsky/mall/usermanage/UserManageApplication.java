package com.travelsky.mall.usermanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = "com.travelsky.mall.usermanage.mapper")
@SpringBootApplication
public class UserManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManageApplication.class, args);
    }

}
