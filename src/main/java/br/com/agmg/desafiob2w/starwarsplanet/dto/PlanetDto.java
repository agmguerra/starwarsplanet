package br.com.agmg.desafiob2w.starwarsplanet.dto;

import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;

/**
 * Dto para faciltar a conversão de Planet para json padrão e retorno
 * @author guerra
 *
 */
public class PlanetDto {
	
	/**
	 * id do planeta
	 */
	private Long id;

	/**
	 * nome do planeta
	 */
	private String name;

	/**
	 * clima do planeta
	 */
	private String climate;

	/**
	 * terreno do planeta
	 */
	private String terrain;

	/**
	 * número de aparições do planeta em filmes
	 */
	private Integer numberOfMovieAppearence;
	
	/**
	 * Url do recurso
	 */
	private String url;
	
	/**
	 * Construtor 
	 * @param planet
	 * @param url
	 */
	public PlanetDto(Planet planet, String url) {
		this.id = planet.getId();
		this.name = planet.getName();
		this.climate = planet.getClimate();
		this.terrain = planet.getTerrain();
		this.numberOfMovieAppearence = planet.getNumberOfMovieAppearence();
		this.url = url;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Integer getNumberOfMovieAppearence() {
		return numberOfMovieAppearence;
	}

	public void setNumberOfMovieAppearence(Integer numberOfMovieAppearence) {
		this.numberOfMovieAppearence = numberOfMovieAppearence;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
