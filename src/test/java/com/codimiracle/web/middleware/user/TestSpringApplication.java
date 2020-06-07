package com.codimiracle.web.middleware.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.codimiracle.web.middleware.user")
@MapperScan("com.codimiracle.web.middleware.user.mapper")
public class TestSpringApplication {
}
