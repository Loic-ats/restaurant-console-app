package dev.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.dao.PlatDaoFichier;
import dev.dao.PlatDaoMemoire;
import dev.entite.Plat;
import dev.exception.PlatException;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PlatServiceVersion2.class, PlatDaoMemoire.class})

public class PlatServiceVersion2IntegrationTest {

	// injection du bean à tester, on demande au context spring une instance de la classe
	@Autowired 
	private PlatServiceVersion2  platserviceVersion2;

	
	@Test
	public void ajouterPlatValide() {
	
		platserviceVersion2.ajouterPlat("Tomates", 1200);
		List<Plat> list = platserviceVersion2.listerPlats();
		Assertions.assertThat(list.size()).isEqualTo(1);
	}
	
	@Test
	public void testIntegratePrixInvalid() {
		Assertions.assertThatThrownBy ( () -> platserviceVersion2.ajouterPlat("Tomates", 6)).isInstanceOf(PlatException.class).hasMessage("Le prix doit être superieur à 10 euros ");
		
		/*
		try {
			platserviceVersion2.ajouterPlat("Tomates", 6);
			fail("exception produite");
		} catch (PlatException e) {
			Assertions.assertThat(e.getMessage()).isEqualTo("le prix doit être supérieur à 10 €");
		}
		*/
	}

}
