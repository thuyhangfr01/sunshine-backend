package com.ute.sunshinebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.ute.sunshinebackend.*" })
public class SunshineBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(SunshineBackendApplication.class, args);
	}

}
