package com.buptcnc.projectmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.buptcnc.projectmanager.mapper")
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class ProjectManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectManagerApplication.class, args);
    }
}


