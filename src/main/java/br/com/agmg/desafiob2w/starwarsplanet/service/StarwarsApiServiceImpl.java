package br.com.agmg.desafiob2w.starwarsplanet.service;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.agmg.desafiob2w.starwarsplanet.util.RestUtil;

/**
 * 
 * @author guerra
 *
 */
@Service
public class StarwarsApiServiceImpl extends BaseService implements StarwarsApiService {

	
	@Value("${spring.starwarsapiplanet}")
	private String starWarsApiPlanetBaseUrl;

	/**
	 * @see br.com.agmg.desafiob2w.starwarsplanet.service.StarwarsApiService#getNumberOfFilmAppereance(String)
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Integer getNumberOfFilmAppereance(String planetName) {
		int numberOfFilms = 0;
		
		try {
			StringBuffer url = new StringBuffer(starWarsApiPlanetBaseUrl).append("?search=").append(planetName.replace(' ', '+'));
			URI starWarsApiUri = new URI(url.toString());
			
			RestTemplate rest = createRestTemplate();
			

			ResponseEntity<String> resp = rest.getForEntity(starWarsApiUri, String.class);
			
			if (!RestUtil.isError(resp.getStatusCode())) {
				
				String jsonResult = resp.getBody();
				ObjectMapper mapper = JsonFactory.create();

				Map jsonMap = mapper.readValue(jsonResult, Map.class);
				List<Map> resultList = (List<Map>) jsonMap.get("results");
				
				if (resultList != null && resultList.size() > 0) {
					
					Map planetInfo = resultList.get(0);
					List films = (List)planetInfo.get("films");
					if (films != null) {
						numberOfFilms = films.size();
					}
				}
				
				
			} else {
				return -1;
			} 
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		return numberOfFilms;
	}

}
