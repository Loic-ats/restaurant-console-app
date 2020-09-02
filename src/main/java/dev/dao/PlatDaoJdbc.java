package dev.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.entite.Plat;

@Repository
@Profile("jdbc")
public class PlatDaoJdbc implements IPlatDao {

	//Je créer une instance de jdbc Templat
	private JdbcTemplate jdbcTemplate;

	
	//je créer un constructeur DaoJDB et place en parametre la datasource créée précédement
    
	public PlatDaoJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	
	@Override
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		this.jdbcTemplate.update("insert into plat(nom,prix) values(?,?)", nomPlat, prixPlat);
		
	}
	
	@Override
	public List<Plat> listerPlats() {
		return this.jdbcTemplate.query("select * from plat", new PlatRowMapper());
	
	}

}
