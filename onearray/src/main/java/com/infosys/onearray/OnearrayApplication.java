package com.infosys.onearray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OnearrayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnearrayApplication.class, args);
	}

}
