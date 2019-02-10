package com.coding.challenge.cityconnect;

import static springfox.documentation.builders.PathSelectors.any;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * City Connection Service Application This class is used to set up the
 * "swagger" documentaion and runs the spring boot application.
 * 
 * @author ajayk
 *
 */
@SpringBootApplication
@EnableSwagger2
public class CityConnectApplication {

	private static final Logger logger = LoggerFactory.getLogger(CityConnectApplication.class);

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Room").select()
				.apis(RequestHandlerSelectors.basePackage("com.coding.challenge.cityconnect")).paths(any()).build()
				.apiInfo(new ApiInfo("City Connections", "A set of cities that are connected by roads", "1.0.0", null,
						new Contact("Ajay Kapoor", "https://www.linkedin.com/in/ajay-kapoor-7322610782/", null), null,
						null));
	}

	public static void main(String[] args) {
		logger.debug("Starting CityConnectApplication");
		SpringApplication.run(CityConnectApplication.class, args);
	}

}
