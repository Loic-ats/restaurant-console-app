package dev.config;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//On commence par créer notre classe de configuration

//Le stéréotype @Configuration précise que cette classe servira de configuration.

@Configuration

//Permet de lire un fichier que j'ai défini dans app.properties
@PropertySource("app.properties")

//Annotation qui va activer la recherche de Bean spring dans le package et sous package spécifier 
//Dans notre exemple de package dev...
@ComponentScan ("dev")

public class AppConfig {
	
	//Constructeur de bean pour le scanner
	
	@Bean
	public Scanner scanner () {
		return new Scanner (System.in);
		
	}
	
	
}