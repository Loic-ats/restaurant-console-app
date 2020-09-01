package dev.dao;


import org.assertj.core.data.Index;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class DaoBlagueTest {

    private DaoBlague daoBlague;

    @Before
    public void init() {
        this.daoBlague = new DaoBlague();
    }

    @Test
    public void testListerPremiereValeurA() {
        // initialiser
        //DaoBlague daoBlague = new DaoBlague();

        // invoquer la méthode à tester
        List<String> resultat = daoBlague.lister();

        // vérifier le résultat
        // => on utilise une assertion

        // Assertion JUnit
        assertEquals("a", resultat.get(0));
        // AssertJ
        assertThat(resultat.get(0)).isEqualTo("a");
        assertThat(resultat).contains("a", Index.atIndex(0));
    }

    @Test
    public void testListerSecondeValeurB() {
        // initialiser
        //DaoBlague daoBlague = new DaoBlague();

        // invoquer la méthode à tester
        List<String> resultat = daoBlague.lister();

        // vérifier le résultat
        // => on utilise une assertion

        // Assertion JUnit
        assertEquals("b", resultat.get(1));
    }

    @Test
    public void testListerNombreElementsEst2() {
        // initialiser
        //DaoBlague daoBlague = new DaoBlague();

        // invoquer la méthode à tester
        List<String> resultat = daoBlague.lister();

        // vérifier le résultat
        // => on utilise une assertion

        // Assertion JUnit
        assertEquals(2, resultat.size());
    }

}
