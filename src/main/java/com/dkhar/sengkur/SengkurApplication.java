package com.dkhar.sengkur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
//		(exclude = {HibernateJpaAutoConfiguration.class} )
public class SengkurApplication extends SpringBootServletInitializer   {

	public static void main(String[] args) {

		SpringApplication.run(SengkurApplication.class, args);
	}



}
