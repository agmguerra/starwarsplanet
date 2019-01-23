package br.com.agmg.desafiob2w.starwarsplanet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.client.RestTemplate;

import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;
import br.com.agmg.desafiob2w.starwarsplanet.repository.PlanetRepository;

/**
 * 
 * @author alexgmg
 *
 */
public class PlanetServiceImpl implements PlanetService {
	
	
	@Autowired
	private PlanetRepository planetRepository;

	
	
	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#savePlanet(Planet)
	 */
	@Override
	public Planet savePlanet(Planet planet) {
		
		return planetRepository.save(planet);

	}
	
	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#deletePlanet(Long)
	 */
	@Override
	public void deletePlanet(Long id) {
		planetRepository.deleteById(id);
	}

	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#getAll()
	 */
	@Override
	public List<Planet> getAll() {
		
		List<Planet> planets = planetRepository.findAll();
		
		for (Planet planet : planets) {
			int num = getNumberAppearence(planet.getName());
		}
		
		return planetRepository.findAll();
		
	}

	private int getNumberAppearence(String name) {

		//StringBuffer url = new StringBuffer()
		
		RestTemplate rest = new RestTemplate();
		
		//Response response = rest.getForObject( "", Response.class); 
		//log.info("==== RESTful API Response using Spring RESTTemplate START ======="); 
		//log.info(response.toString()); 
		//log.info("==== RESTful API Response using Spring RESTTemplate END =======");

		
		return 0;
		

	}

	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#getAll(Integer, Integer)
	 */
	@Override
	public Page<Planet> getAll(Integer page, Integer pageSize) {
		
		Pageable pageRequest = createOrderedPageRequestById(page, pageSize);		
		
		return planetRepository.findAll(pageRequest);

	}

	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#getById(Long)
	 */
	@Override
	public Planet getById(Long id) {
		
		Optional<Planet> planet = planetRepository.findById(id);
		
		if (planet.isPresent()) {
			return planet.get();
		} else {
			return null;
		}
		
	}

	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#getByName(String)
	 */
	@Override
	public Planet getByName(String name) {
		
		Optional<Planet> planet = planetRepository.findByName(name);
		
		if (planet.isPresent()) {
			return planet.get();
		} else {
			return null;
		}
		
	}

	/**
	 * Criar um page request
	 * @param número da página
	 * @param tamanho da página
	 * @return Pageable
	 */
	private Pageable createOrderedPageRequestById(Integer page, Integer pageSize) {
		return PageRequest.of(page, pageSize, new Sort(Sort.Direction.ASC, "id"));
	}


}
