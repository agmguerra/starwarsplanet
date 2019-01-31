package br.com.agmg.desafiob2w.starwarsplanet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;
import br.com.agmg.desafiob2w.starwarsplanet.exception.AlreadyExistsException;
import br.com.agmg.desafiob2w.starwarsplanet.exception.GenericException;
import br.com.agmg.desafiob2w.starwarsplanet.exception.NotFoundException;
import br.com.agmg.desafiob2w.starwarsplanet.repository.PlanetRepository;


/**
 * 
 * Implementação da classe de serviços de negócio ralacionados a planeta
 *
 */
@Service
public class PlanetServiceImpl extends BaseService implements PlanetService {
		
	@Autowired
	private PlanetRepository planetRepository;

	@Autowired
	private StarwarsApiService starwarsApiService;
	
	@Value("${spring.starwarsapiplanet}")
	private String starWarsApiPlanetBaseUrl;
	
	
	private static final String ERROR_PLANET_ALREADY_EXISTS = "error.planet.already.exists";
	private static final String ERROR_GENERIC_MESSAGE = "error.generic.message";
	private static final String ERROR_PLANET_NOT_FOUND = "error.planet.not.found";
	private static final String ERROR_PLANET_INVALID_DELETE = "error.planet.invalid.delete";
	private static final String PAGE_SORT_ORDER_ATRIBUTE = "id";
	
	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#savePlanet(Planet)
	 */
	@Override
	public Planet savePlanet(Planet planet) {
		
		Planet planetSaved = null;
		try {
			planetSaved = planetRepository.save(planet);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			throw new AlreadyExistsException(ERROR_PLANET_ALREADY_EXISTS, e);
	    } catch (Exception e) {
	    	e.printStackTrace();
			throw new GenericException(ERROR_GENERIC_MESSAGE, e);
		}
		
		return planetSaved;

	}
	
	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#deletePlanet(Long)
	 */
	@Override
	public void deletePlanet(Long id) {
	
		try {
			if (planetRepository.existsById(id)) {
				planetRepository.deleteById(id);
			} else {
				throw new NotFoundException(ERROR_PLANET_NOT_FOUND);
			}
		} catch (GenericException e) {
			e.printStackTrace();
			throw e;		
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericException(ERROR_PLANET_INVALID_DELETE, e);
		}
	}

	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#getAll()
	 */
	@Override
	public List<Planet> getAll() {
		
		List<Planet> planets;
		try {
			planets = planetRepository.findAll();
			
			for (Planet planet : planets) {
				planet.setNumberOfMovieAppearence(starwarsApiService.getNumberOfFilmAppereance(planet.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericException(ERROR_GENERIC_MESSAGE, e);
		}
		
		return planets;
		
	}


	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#getAll(Integer, Integer)
	 */
	@Override
	public Page<Planet> getAll(Integer page, Integer pageSize) {
		
		Page<Planet> planets = null;
		try {
			Pageable pageRequest = createOrderedPageRequestById(page, pageSize);		
			
			planets = planetRepository.findAll(pageRequest);
			
			for (Planet planet : planets) {
				planet.setNumberOfMovieAppearence(starwarsApiService.getNumberOfFilmAppereance(planet.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericException(ERROR_GENERIC_MESSAGE, e);
		}

		return planets;
	}

	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#getById(Long)
	 */
	@Override
	public Planet getById(Long id) {
		
		Planet planet = null;
		try {
			Optional<Planet> queryResult = planetRepository.findById(id);
			if (queryResult.isPresent()) {
				planet = getPlanet(queryResult);
			} else {
				throw new NotFoundException(ERROR_PLANET_NOT_FOUND);
			}
		} catch (GenericException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericException(ERROR_GENERIC_MESSAGE, e);
		}
		
		return planet;
		
	}

	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#getByName(String)
	 */
	@Override
	public Planet getByName(String name) {
		
		Planet planet = null;
		try {
			Optional<Planet> queryResult = planetRepository.findByName(name);
			if (queryResult.isPresent()) {
				planet = getPlanet(queryResult);
			} else {
				throw new NotFoundException(ERROR_PLANET_NOT_FOUND);
			}
		} catch (GenericException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericException(ERROR_GENERIC_MESSAGE);
		}
		
		return planet;
		
	}

	private Planet getPlanet(Optional<Planet> planetResult) {
		if (planetResult.isPresent()) {
			Planet planet = planetResult.get();
			planet.setNumberOfMovieAppearence(starwarsApiService.getNumberOfFilmAppereance(planet.getName()));
			
			return planet;
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
		
		//Diminui 1 da página porque o repositório trata a página inicial como zero
		return PageRequest.of(page - 1, pageSize, new Sort(Sort.Direction.ASC, PAGE_SORT_ORDER_ATRIBUTE));
	}
	
	

}
