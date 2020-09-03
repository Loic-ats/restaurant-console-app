package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;
import dev.config.JpaConfig;

@SpringJUnitConfig({ JdbcTestConfig.class, // DataSourceH2, JdbcTemplate
		JpaConfig.class, // EntityManagerFactory, JpaTrasaction
		PlatDaoJpa.class, // Classe a tester
})

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("jpa")
public class PlatDaoJpaTest {

	@Autowired
	private IPlatDao dao;

	@Autowired
	private JdbcTemplate JdbcTemplate;

	@Test
	void listerPlatsNonVide() {
		int tailleListe = dao.listerPlats().size();
		assertThat(tailleListe > 0);
	}

	// Deuxième méthode pour tester la méthode ajouter Plat, on regarde si le prix
	// du plat ajouté est égale à celui inséré
	@Test
	void ajouterPlatMethode2() {

		dao.ajouterPlat("Gratin", 1600);
		Integer prix = JdbcTemplate.queryForObject("select prix from Plat p where p.nom='Gratin'", Integer.class);
		assertThat(prix).isEqualTo(1600);
	}
}
