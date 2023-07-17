package com.masai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.masai")
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("=================================================================");
		System.out.println("=========================Application Started=====================");
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("=========================Application Working Fine , Not Any Error=====================");
	}

}
