package com.ptithcm.discoverys_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NewsDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsDiscoveryServerApplication.class, args);
	}

}
