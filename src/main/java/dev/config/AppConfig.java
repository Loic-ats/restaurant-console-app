package dev.config;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//On commence par créer notre classe de configuration

//Le stéréotype @Configuration précise que cette classe servira de configuration.

@Configuration
//Annotation qui va activer la recherche de Bean spring

@ComponentScan ("dev") //Je recherche dans le package dev et sous package comprit

public class AppConfig {
	
	//Constructeur de bean pour le scanner
	
	@Bean
	public Scanner scanner () {
		return new Scanner (System.in);
		
	}
}