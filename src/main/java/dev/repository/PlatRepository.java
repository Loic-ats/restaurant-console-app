package dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.entite.Plat;

public interface PlatRepository extends JpaRepository<Plat, Integer> {

	List<Plat> findByPrixEnCentimesEuros(Integer prix);

	@Query("select avg(p.prixEnCentimesEuros) from Plat p where p.prixEnCentimesEuros > ?1 ")
	Double findByAvgPrixSuppA(Integer prixEnCentimeEuros);

	// @Query("select ing from Ingredient ing left join
	// ing.listPlatsQuiContiennentIng p where p.nom = ?1")
	// List<Ingredient> findIngredientByPlatNom(String nom);

	@Modifying
	@Query("UPDATE Plat p SET nom = :nouveauNom WHERE p.nom = :ancienNom")
	void updateNomPlat(@Param("ancienNom") String ancienNom, @Param("nouveauNom") String nouveauNom);

	@Query("select p from Plat p join fetch p.listIngredients where p.nom = ?1")
	Optional<Plat> findByNomWithIngredients(String nom);

}
