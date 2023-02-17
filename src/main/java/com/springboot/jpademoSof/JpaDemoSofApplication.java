package com.springboot.jpademoSof;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.web.context.request.RequestContextListener;

import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		JpaDemoSofApplication.class,
		Jsr310JpaConverters.class
})
public class JpaDemoSofApplication {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoSofApplication.class, args);
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

}
