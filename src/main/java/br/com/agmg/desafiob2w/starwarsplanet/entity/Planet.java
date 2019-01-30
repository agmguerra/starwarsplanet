package br.com.agmg.desafiob2w.starwarsplanet.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.agmg.desafiob2w.starwarsplanet.validadores.ValidPlanet;
import io.swagger.annotations.ApiModel;



/**
 * Classe que representa o planeta
 * 
 *
 */
@ApiModel(description="Contém informações de planetas mencionados na saga Star Wars")
@Entity
@Table(name = "planet")
@ValidPlanet
public class Planet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * id do planeta
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	/**
	 * nome do planeta
	 */
	@Column(name = "NAME", unique = true, nullable = false, length=150)
	private String name;

	/**
	 * clima do planeta
	 */
	@Column(name = "CLIMATE", nullable = false, length=150)
	private String climate;

	/**
	 * terreno do planeta
	 */
	@Column(name = "TERRAIN", nullable = false, length=150)
	private String terrain;

	/**
	 * número de aparições do planeta em filmes
	 */
	@Transient
	private Integer numberOfMovieAppearence;
	
	
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
	
}
