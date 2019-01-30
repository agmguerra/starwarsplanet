package br.com.agmg.desafiob2w.starwarsplanet.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.agmg.desafiob2w.starwarsplanet.dto.PlanetDto;
import br.com.agmg.desafiob2w.starwarsplanet.dto.PlanetsDto;
import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;
import br.com.agmg.desafiob2w.starwarsplanet.exception.NotFoundException;
import br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService;


@RestController
public class PlanetResource {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private PlanetService planetService;

	/**
	 * Cadastra informações de um planeta
	 * @param planet
	 * @return
	 */
	@PostMapping("/api/planets")
	public ResponseEntity<Object> savePlanet(@Valid @RequestBody Planet planet) {
		
		Planet planetSaved = planetService.savePlanet(planet);
		
		URI location = ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(planetSaved.getId()).toUri();
		
		ResponseEntity<Object> ret = ResponseEntity.created(location).build();
	
		return ret;
	}
	
	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @param uriBuilder
	 * @param response
	 * @return
	 */
	@GetMapping(params = {"page"}, path="/api/planets")
	public ResponseEntity<PlanetsDto> getAllPlanets(@RequestParam Integer page) {
		
		List<Planet> planets = null;
		
		Page<Planet> pageResult = planetService.getAll(page - 1, 10);
		if (page > pageResult.getTotalPages()) {
			throw new NotFoundException("error.planet.not.found");
		}
		planets = pageResult.getContent();
		PlanetsDto planetsDto = new PlanetsDto(planets, request.getRequestURL().toString(), page, 10);

		return ResponseEntity.ok(planetsDto);
	}
	
	/**
	 * Recupera informações de todos os planetas
	 * @return
	 */
	@GetMapping("/api/planets")
	public ResponseEntity<PlanetsDto> getAllPlanets() { 
		List<Planet> planets = planetService.getAll();
		
		PlanetsDto planetsDto = new PlanetsDto(planets, request.getRequestURL().toString());
				
		return ResponseEntity.ok(planetsDto);
	}
	
	/**
	 * Recupera informações do planeta do id informado
	 * @return
	 */
	@GetMapping("/api/planets/{id}")
	public ResponseEntity<PlanetDto> getById(@PathVariable Long id) { 
		Planet planet = planetService.getById(id);
		
		PlanetDto planetDto = new PlanetDto(planet, request.getRequestURL().toString());
		
		return ResponseEntity.ok(planetDto);
	}

	
	/**
	 * Exclui o planeta com o id informado
	 * @param id
	 */
	@DeleteMapping("/api/planets/{id}")
	public void deletePlanet(@PathVariable Long id) {
		planetService.deletePlanet(id);
	}


}
