package br.com.agmg.desafiob2w.starwarsplanet.service;

/**
 * 
 * @author guerra
 *
 */
public interface StarwarsApiService {
	
	/**
	 * Recupera o número de filmes onde o planeta informado aparece
	 * @param planetName nome do planeta
	 * @return Integer que representa o número de filmes onde o planeta aparece
	 */
	public Integer getNumberOfFilmAppereance(String planetName);

}
