package br.com.agmg.desafiob2w.starwarsplanet.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;
import br.com.agmg.desafiob2w.starwarsplanet.exception.GenericException;
import br.com.agmg.desafiob2w.starwarsplanet.exception.IntegrationException;
import br.com.agmg.desafiob2w.starwarsplanet.repository.PlanetRepository;

/**
 * 
 * @author alexgmg
 *
 */
public class PlanetServiceImpl implements PlanetService {
	
	
	@Autowired
	private PlanetRepository planetRepository;

	@Value("{starwarsapiplanet")
	private String starWarsApiPlanetBaseUrl;
	
	
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
			int numAppereance = getNumberAppearence(planet.getName());
			
			planet.setNumberOfMovieAppearence(numAppereance);
		}
		
		return planets;
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private int getNumberAppearence(String name) {
		
		int numberOfFilms = 0;
		
		try {
			StringBuffer url = new StringBuffer(starWarsApiPlanetBaseUrl).append("?search=").append(name);
			URI starWarsApiUri = new URI(url.toString());
			
			RestTemplate rest = new RestTemplate();
			
			String jsonResult = rest.getForObject(starWarsApiUri, String.class);
			
			ObjectMapper mapper = JsonFactory.create();

			Map jsonMap = mapper.readValue(jsonResult, Map.class);
			List<Map> resultList = (List<Map>) jsonMap.get("results");
			
			if (resultList != null && resultList.size() > 0) {
				
				Map planetInfo = resultList.get(0);
				List films = (List)planetInfo.get("films");
				if (films != null) {
					numberOfFilms = films.size();
				}
			}
			
		} catch (RestClientException e) {
			throw new IntegrationException(e.getMessage(), e);
		} catch (URISyntaxException e) {
			throw new GenericException(e);
		}

		
		return numberOfFilms;
		

	}

	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#getAll(Integer, Integer)
	 */
	@Override
	public Page<Planet> getAll(Integer page, Integer pageSize) {
		
		Pageable pageRequest = createOrderedPageRequestById(page, pageSize);		
		
		Page<Planet> planets = planetRepository.findAll(pageRequest);
		
		for (Planet planet : planets) {
			int numAppereance = getNumberAppearence(planet.getName());
			
			planet.setNumberOfMovieAppearence(numAppereance);
		}

		return planets;
	}

	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#getById(Long)
	 */
	@Override
	public Planet getById(Long id) {
		
		Optional<Planet> planetResult = planetRepository.findById(id);
		
		return getPlanet(planetResult);
		
	}

	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService#getByName(String)
	 */
	@Override
	public Planet getByName(String name) {
		
		Optional<Planet> planetResult = planetRepository.findByName(name);
		
		return getPlanet(planetResult);
		
	}

	private Planet getPlanet(Optional<Planet> planetResult) {
		if (planetResult.isPresent()) {
			Planet planet = planetResult.get();
			int numAppereance = getNumberAppearence(planet.getName());
			planet.setNumberOfMovieAppearence(numAppereance);
			
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
		return PageRequest.of(page, pageSize, new Sort(Sort.Direction.ASC, "id"));
	}
	
	

}
