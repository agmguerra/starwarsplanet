package br.com.agmg.desafiob2w.starwarsplanet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;

/**
 * Classe de configuração que permite expor os id no retorno dos serviços quando usando
 * HATEOAS
 * 
 *
 */
@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		
		config.exposeIdsFor(Planet.class);
	}
	
}
