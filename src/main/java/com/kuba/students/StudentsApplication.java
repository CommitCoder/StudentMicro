package com.kuba.students;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class StudentsApplication {


public static void main(String[] args) {
		SpringApplication.run(StudentsApplication.class, args);
	}

	@Value("${my.dogs}")
	private List<String> dogs;

	@Value("${show.dogs}")
	private boolean showDogs;

	// method will launch once after initialization - good for feeding DB with sample data during launching app
	@PostConstruct
	void showDogs(){
		
		if(showDogs){
			dogs.forEach(System.out::println);
		}else {
			System.out.println("No dogs in list");
		}

	}



}
