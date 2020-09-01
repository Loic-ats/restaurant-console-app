package dev.dao;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
	
	assertEquals(0, resultat.size());
	
	}

@Test
	void ajouterPlatCasPassants() {
	
	String Pâtescarbonara;
	
	List<Plat> 	resultat = platDaoMemoire.ajouterPlat(Pâtescarbonara,15);
	
	assertEquals(1, resultat.size());
	
	
	}
}
