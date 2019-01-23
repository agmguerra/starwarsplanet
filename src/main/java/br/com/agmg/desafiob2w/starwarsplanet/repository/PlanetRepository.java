package br.com.agmg.desafiob2w.starwarsplanet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;


/**
 * Representa o repository para cadastramento de planetas
 * 
 * @author alexgmg
 *
 */
@RepositoryRestResource(exported = false)
public interface PlanetRepository extends JpaRepository<Planet, Long> {
		
	Optional<Planet> findByName(String name);

}