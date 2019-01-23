package br.com.agmg.desafiob2w.starwarsplanet.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;


public interface PlanetService {
	
	/**
	 * Cria ou atualiza um planeta
	 * @param planet
	 * @return Planet planeta salvo
	 */
	public Planet savePlanet(Planet planet);
	
	/**
	 * Exclui um planeta
	 * @param id
	 */
	public void deletePlanet(Long id);
	
	/**
	 * Recupera todos os planetas
	 * @return
	 */
	public List<Planet> getAll();
	
	/**
	 * Recupera os planetas de forma paginada
	 * @param pagina
	 * @param tamanhoPagina
	 * @return List<Planet> lista de planetas
	 */
	public Page<Planet> getAll(Integer pagina, Integer tamanhoPagina);
	
	/**
	 * Recupera um planeta por id
	 * @param id
	 * @return Planet
	 */
	public Planet getById(Long id);

	/**
	 * Recupera pelo nome ou parte do nome
	 * @param name
	 * @return List<Planet> lista de planetas com o nome ou parte do nome
	 */
	public Planet getByName(String name);

}
