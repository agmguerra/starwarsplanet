package br.com.agmg.desafiob2w.starwarsplanet.service;

/**
 * 
 * Interface associada aos serviços que recuperam info na api starwars.co
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
