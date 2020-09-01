package dev.metier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.dao.IPlatDao;
import dev.exception.PlatException;
import dev.service.PlatServiceVersion1;

public class PlatServiceVersion1Test {
	
	private IPlatDao dao;
	private PlatServiceVersion1 plat;

    @BeforeEach
    public void setUp() {

    	//On créer une instance de IPlatDao grace à mockito
        dao = Mockito.mock(IPlatDao.class);
        plat = new PlatServiceVersion1(dao); 
      
    }
    
    @Test
    public void testAjouterPlatInvalide() throws PlatException {
    	assertThrows(PlatException.class,()-> plat.ajouterPlat("pâte Carbonara", 20)) ;

    }
    
    
    @Test
    public void testAjouterPlatPrixInvalide() throws PlatException {
    
    	assertThrows(PlatException.class,()-> plat.ajouterPlat("Tarte Légumes", 5)) ;

    }
    
    @Test
    public void MethodeInvoque(){
    
    	plat.ajouterPlat("Tarte Légumes", 600);
    	verify(dao, atLeastOnce()).ajouterPlat("Tarte Légumes", 600);
      }

}
