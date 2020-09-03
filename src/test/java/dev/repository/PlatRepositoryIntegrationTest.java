package dev.repository;

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

		int taillelist = dao.findAll().size();
		assertThat(taillelist > 0);
	}

	@Test
	void testFindAllSortAsc() {

	}

}
