package com.adcc.mpdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.adcc.mpdemo.mapper")
@SpringBootApplication
public class MpdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MpdemoApplication.class, args);
	}

}
