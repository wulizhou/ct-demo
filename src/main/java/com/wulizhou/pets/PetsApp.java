package com.wulizhou.pets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wulizhou.pets.model.mapper")
public class PetsApp {

	public static void main(String[] args) {
		SpringApplication.run(PetsApp.class, args);
	}
	
}
