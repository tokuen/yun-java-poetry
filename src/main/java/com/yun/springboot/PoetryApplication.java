package com.yun.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.yun.springboot.filter")
public class PoetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoetryApplication.class, args);
	}


}
