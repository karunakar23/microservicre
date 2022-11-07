package com.wecare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class WecareApplication implements WebMvcConfigurer{
	
	public static void main(String[] args) {
		SpringApplication.run(WecareApplication.class, args);
	}
	public void addCorsMapping(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET","POST","PUT","DELETE");
	}
//	@Bean
//	RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

}
