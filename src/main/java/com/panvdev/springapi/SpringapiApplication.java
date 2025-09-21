package com.panvdev.springapi;

import com.panvdev.springapi.core.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpringapiApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringapiApplication.class, args);
	}
}
