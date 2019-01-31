package br.com.agmg.desafiob2w.starwarsplanet.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;
import br.com.agmg.desafiob2w.starwarsplanet.util.RestUtil;

/**
 * Dto para retornar a lista de planetas
 * @author alexgmg
 *
 */
public class PlanetsDto {
	
	private Integer count;
	
	@JsonInclude(Include.NON_EMPTY)
	private String next = null;
	
	@JsonInclude(Include.NON_EMPTY)
	private String previus = null;
	
	private List<PlanetDto> planets;
	
	
	private static final String PAGE_CONCAT_PART = "?page=";

	public PlanetsDto(List<Planet> planets, String baseUrl) {
		
		this.count = planets.size();
		
		this.planets = planets.stream().map(planet -> new PlanetDto(planet, baseUrl + "/" + planet.getId())).collect(Collectors.toList());
		
	}
	
	public PlanetsDto(List<Planet> planets, String baseUrl, Integer page, Integer totalPages) {
		
		this.count = planets.size();		
		this.planets = planets.stream().map(planet -> new PlanetDto(planet, baseUrl + "/" + planet.getId())).collect(Collectors.toList());
		this.previus = baseUrl + PAGE_CONCAT_PART + RestUtil.getPreviusPage(page);
		this.next = baseUrl + PAGE_CONCAT_PART + RestUtil.getNextPage(page, totalPages);
		
	}

	
	public List<PlanetDto> getPlanets() {
		return planets;
	}

	public Integer getCount() {
		return count;
	}

	public String getPrevius() {
		return previus;
	}
	
	public String getNext() {
		return next;
	}

	public void setPrevius(String previus) {
		this.previus = previus;
	}

	public void setPlanets(List<PlanetDto> planets) {
		this.planets = planets;
	}
	
	

}
