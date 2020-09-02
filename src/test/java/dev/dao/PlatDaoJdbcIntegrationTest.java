package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import dev.entite.Plat;
import dev.config.JdbcTestConfig;
import dev.dao.PlatDaoJdbc;

//On définit le contexte jdbc (on utilise JdbcTestConfig car il a une datasource et la config Jdbc), on veux tester PlatDaoJdbc
//J'active le profil "jdbc" car on l'a déclarer dans PlatDaoJdbc (attention @ActiveProfiles n'est valable que pour les test...)

@SpringJUnitConfig ({JdbcTestConfig.class, PlatDaoJdbc.class})
@ActiveProfiles("jdbc")

public class PlatDaoJdbcIntegrationTest {

	@Autowired
	private PlatDaoJdbc platDaoJdbc; 
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// On vérifie que PlatDaoJdbc::listerPlats ne retourne pas une liste vide
	
    @Test
    void listerPlatsNonVide() {
    	List<Plat> plats = platDaoJdbc.listerPlats();
        assertThat(plats).isNotEmpty();
    }
	
	
	@Test
	public void ajouterPlat() 
	{
        platDaoJdbc.ajouterPlat("Tartiflette", 1500);

        Integer prix = jdbcTemplate.queryForObject("select prix from plat where nom='Tartiflette'", Integer.class);

        assertThat(prix).isEqualTo(1500);
	
	}
}
