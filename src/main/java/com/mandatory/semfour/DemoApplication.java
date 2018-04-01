package com.mandatory.semfour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication(scanBasePackages={"com.mandatory.semfour.model","com.mandatory.semfour.repo"})
//@SpringBootApplication(scanBasePackages = {"com.mandatory.semfour"})
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
