package com.example.configurationclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConfigurationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationClientApplication.class, args);
	}
}

@RefreshScope
@RestController
class MessageRestController {


	private final String message;

	public MessageRestController(@Value("${message:Hello default}") String message) {
		this.message = message;
	}

	@RequestMapping("/message")
	String getMessage() {
		return this.message;
	}
}
