package br.com.agmg.desafiob2w.starwarsplanet.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;
import br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService;


@RestController
public class PlanetResource {
	
	
	
	@Autowired
	private PlanetService planetService;

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

	

}
