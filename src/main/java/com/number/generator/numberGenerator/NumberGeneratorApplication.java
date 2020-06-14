package com.number.generator.numberGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.number.generator.*"})
public class NumberGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(NumberGeneratorApplication.class, args);
	}

}
