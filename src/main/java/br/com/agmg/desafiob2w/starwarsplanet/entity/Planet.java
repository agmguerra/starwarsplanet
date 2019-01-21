package br.com.agmg.desafiob2w.starwarsplanet.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;



/**
 * Classe que representa o cartão
 * 
 * @author alexgmg
 *
 */
@ApiModel(description="Contém informações de planetas mencionados na saga Star Wars")
@Entity
@Table(name = "planet")
public class Planet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * id do planeta
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "planet_id", unique = true, nullable = false)
	private Long id;

	/**
	 * nome do planeta
	 */
	@Column(name = "planet_name", nullable = false, length=100)
	private String name;

	/**
	 * clima do planeta
	 */
	@Column(name = "planet_climate", nullable = false, length=60)
	private String climate;

	/**
	 * terreno do planeta
	 */
	@Column(name = "planet_terrain", nullable = false, length=60)
	private String terrain;


}
