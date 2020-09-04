package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;
import dev.config.JpaConfig;
import dev.entite.Ingredient;
import dev.entite.Plat;

@SpringJUnitConfig({ JdbcTestConfig.class, // DataSourceH2, JdbcTemplate
		JpaConfig.class, // EntityManagerFactory, JpaTrasaction
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("jpa")

public class PlatRepositoryIntegrationTest {

	@Autowired
	private PlatRepository dao;

	@Autowired
	private JdbcTemplate JdbcTemplate;

	@Test
	public void TestFindAll() {
		assertThat(dao.findAll()).isNotEmpty();
	}

	@Test
	void testFindAllSortAsc() {

		// List<Plat> listPlat = repo.findAll(Sort.by(Sort.Direction.ASC, "nom"));
		List<Plat> listPlat = dao.findAll(Sort.sort(Plat.class).by(Plat::getPrixEnCentimesEuros).ascending());

		assertThat(listPlat).isSortedAccordingTo(Comparator.comparing(Plat::getPrixEnCentimesEuros));
	}

	@Test
	void testtestFindAllSortDesc() {

		List<Plat> listPlat = dao.findAll(Sort.by(Sort.Direction.DESC, "nom"));

		assertThat(listPlat).isSortedAccordingTo(Comparator.comparing(Plat::getNom).reversed());

	}

	@Test
	void testFindAllPageable() {

		Pageable firstPageWithTwoElements = PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "nom"));

		Page<Plat> pagePlat = dao.findAll(firstPageWithTwoElements);
		assertThat(pagePlat.getSize()).isEqualTo(2);
		assertThat(pagePlat.getContent().get(0).getId()).isEqualTo(4);

	}

	@Test
	void testFindById() {
		Optional<Plat> byId = dao.findById(4);

		if (byId.isPresent()) {

			Plat plat1 = dao.findById(4).get();
			assertThat(plat1.getNom()).isEqualTo("Blanquette de veau");
			assertThat(plat1.getPrixEnCentimesEuros()).isEqualTo(1500);

			Plat plat = dao.findById(4)
					.orElseThrow(() -> new RuntimeException("L'id = 4 existe, la requête devrait retourner un plat"));

			assertThat(plat.getNom()).isEqualTo("Blanquette de veau");
			assertThat(plat.getPrixEnCentimesEuros()).isEqualTo(1500);
		}

	}

	@Test
	@Transactional
	void testGetOne() {

		Plat plat = dao.getOne(2);

		assertThat(plat.getNom()).isEqualTo("Moules-frites");
		assertThat(plat.getPrixEnCentimesEuros()).isEqualTo(1300);
	}

	@Test
	void testCount() {
		Long total = dao.count();
		assertThat(total).isEqualTo(6);
	}

	@Test
	void testFindByPrix() {
		List<Plat> platByPrix = dao.findByPrixEnCentimesEuros(1300);

		assertThat(platByPrix.size()).isEqualTo(2);
		assertThat(platByPrix.get(0).getNom()).isEqualTo("Magret de canard");

	}

	@Test
	void testAvgPrix() {
		Double moyenne = dao.findByAvgPrixSuppA(0);

		Double sommeAvecFindAll = 0.0;

		List<Plat> listPlat = dao.findAll();

		for (Plat plat : listPlat) {
			sommeAvecFindAll += plat.getPrixEnCentimesEuros();
		}

		double moyenneJava8 = listPlat.stream().mapToInt(Plat::getPrixEnCentimesEuros).average().orElse(0);

		assertThat(moyenne).isEqualTo(sommeAvecFindAll / listPlat.size());
		assertThat(moyenne).isEqualTo(moyenneJava8);

	}

	@Test
	void testFindByid() {
		Optional<Plat> p = dao.findById(1);
		assertThat(p.get().getNom()).isEqualTo("Magret de canard");
	}

	@Test
	void testFindByNomWithIngredients() {
		Plat plat = dao.findByNomWithIngredients("Magret de canard")
				.orElseThrow(() -> new RuntimeException("'Magret de canard' existe !!!"));

		assertThat(plat.getListIngredients()).extracting(Ingredient::getNom).contains("Sel");

	}

	@Test
	void testSave() {

		long CountBefore = dao.count();
		Plat plat = new Plat();
		plat.setNom("Pizza");
		plat.setPrixEnCentimesEuros(1400);

		dao.save(plat);

		long CountAfter = dao.count();

		assertThat(dao.findAll()).extracting(Plat::getNom).contains(plat.getNom());
	}

	@Transactional
	@Test
	void testChangerNom() {

		dao.updateNomPlat("Couscous", "Couscous-Merguez");

		List<Plat> listPlat = dao.findAll();

		assertThat(listPlat).extracting(Plat::getNom).contains("Couscous-Merguez", Index.atIndex(2));
	}

}
