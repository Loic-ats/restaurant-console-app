package dev.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {
	
	//On récupérer les infos du fichier properties pour établir la connection equivalen a ressourceBundle en JDBC classique
	@Bean
	public DataSource dataSource(
			@Value("${bdd.driver}") String driver, 
			@Value("${bdd.user}") String utilisateur,
			@Value("${bdd.pass}") String motDePasse, 
			@Value("${bdd.url}") String url) {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(utilisateur);
		dataSource.setPassword(motDePasse);
		return dataSource;
	}
}

