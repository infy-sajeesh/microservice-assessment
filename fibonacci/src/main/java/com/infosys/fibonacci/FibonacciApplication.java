package com.infosys.fibonacci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FibonacciApplication {

	public static void main(String[] args) {
		SpringApplication.run(FibonacciApplication.class, args);
	}

}
