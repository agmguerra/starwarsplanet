package br.com.agmg.desafiob2w.starwarsplanet.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;
import br.com.agmg.desafiob2w.starwarsplanet.exception.NotFoundException;
import br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService;


@RestController
public class PlanetResource {
	
	
	
	@Autowired
	private PlanetService planetService;

	/**
	 * 
	 * @param planet
	 * @return
	 */
	@PostMapping("/api/planets")
	public ResponseEntity<Object> createPlanet(@Valid @RequestBody Planet planet) {
		
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
	@GetMapping(params = { "page", "size" }, path="/api/planets")
	public ResponseEntity<Resources<Resource<Planet>>> getAllPlanets(@RequestParam Integer page, @RequestParam Integer pageSize) {
		
		List<Planet> planets = null;
		
		if (page == null || page == 0) {
			planets = planetService.getAll();
		} else {
			Page<Planet> pageResult = planetService.getAll(page, pageSize);
			if (page > pageResult.getTotalPages()) {
				throw new NotFoundException("error.planet.not.found");
			}
			planets = pageResult.getContent();
		}

		List<Resource<Planet>> resourcesList = planets.stream().map(planet -> new Resource<Planet>(planet)).collect(Collectors.toList());
		Resources<Resource<Planet>> resources = new Resources<Resource<Planet>>(resourcesList);
		return ResponseEntity.ok(resources);
	}
	
	/**
	 * 
	 * @return
	 */
	public ResponseEntity<Resources<Resource<Planet>>> getAllPlanets() { 
		List<Planet> planets = planetService.getAll();
		List<Resource<Planet>> resourcesList = planets.stream().map(planet -> new Resource<Planet>(planet)).collect(Collectors.toList());
		
		Resources<Resource<Planet>> resources = new Resources<Resource<Planet>>(resourcesList);

		return ResponseEntity.ok(resources);
	}
	
	
	/**
	 * 
	 * @param id
	 */
	@DeleteMapping("/api/planets/{id}")
	public void deletePlanet(@PathVariable Long id) {
		planetService.deletePlanet(id);
	}


}
