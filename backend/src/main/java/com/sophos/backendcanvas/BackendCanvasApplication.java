package com.sophos.backendcanvas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.sophos.backendcanvas"})
public class BackendCanvasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendCanvasApplication.class, args);
	}

}
