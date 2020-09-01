package dev.dao;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.AppConfig;
import dev.entite.Plat;
import dev.ihm.Menu;
import dev.service.PlatServiceVersion2;

@SpringJUnitConfig (PlatDaoFichier.class)
@TestPropertySource("classpath:app.properties")
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

class PlatDaoFichierTest {
	
	private PlatDaoFichier platServiceVersion2;


	@Test
	public void TestAjoutPlat() {
		platServiceVersion2.ajouterPlat("Tomates", 10000);
		List<Plat> list = platServiceVersion2.listerPlats();
		Assertions.assertThat(list.size()).isEqualTo(1);
	}

	@Test
	public void TestAjoutPlatBis() {
		platServiceVersion2.ajouterPlat("", 10000);
		List<Plat> list = platServiceVersion2.listerPlats();
		Assertions.assertThat(list.size()).isEqualTo(1);
	}

}

