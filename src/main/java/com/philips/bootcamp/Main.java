package com.philips.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = "com.philips.bootcamp.domain")
@ComponentScan(basePackages = "com.philips.bootcamp")
public class Main{
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
