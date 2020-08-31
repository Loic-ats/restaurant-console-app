package dev;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.config.AppConfig;
import dev.ihm.Menu;

//On créer notre context spring en utilisant le constructeur implicite de la class AnnotationConfigApplicationContext 
//et en plaçant en parametre la classe de configuration créé précédement (AppConfig.class)

public class AppSpringJava {

	public static void main(String[] args) {
	
		// Création du contexte Spring à partir d'une configuration Java
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// récupération du bean Menu
		Menu menu = context.getBean(Menu.class);
		menu.afficher();
		// fermeture du Scanner
		context.getBean(Scanner.class).close();
		// fermeture du contexte Spring
		context.close();

	}

}
