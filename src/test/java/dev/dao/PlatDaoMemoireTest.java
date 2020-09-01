package dev.dao;



import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.entite.Plat;

class PlatDaoMemoireTest {
private PlatDaoMemoire platDaoMemoire;

@BeforeEach
	void setUp() {
		this.platDaoMemoire = new PlatDaoMemoire();
	}

@Test
	void listerPlatsVideALInitialisation() {
	
	List<Plat> resultat = platDaoMemoire.listerPlats();
	
	assertThat (resultat.size()).isEqualTo(0);
	
	}

@Test
	void ajouterPlatCasPassants() {
	
	String Pâtescarbonara = null;
	
	platDaoMemoire.ajouterPlat(Pâtescarbonara,55);
	
	List<Plat> 	resultat = platDaoMemoire.listerPlats();
	
	assertEquals(1, resultat.size());
		
	}
}
