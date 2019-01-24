package br.com.agmg.desafiob2w.starwarsplanet.seervice;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.boon.json.JsonFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BasicTest {

	public static void main(String[] args) {
		String json = "{" +
				"\"count\": 1," +
				"\"next\": null," +
				"\"previous\": null," +
				"\"results\": [" +
					"{" +
						"\"name\": \"Tatooine\"," +
						"\"climate\": \"arid\"," +
						"\"terrain\": \"desert\"," +
						"\"residents\": [" +
							"\"https://swapi.co/api/people/1/\"," +
							"\"https://swapi.co/api/people/2/\"" +
						"]," +
						"\"films\": [" +
							"\"https://swapi.co/api/films/5/\"," +
							"\"https://swapi.co/api/films/4/\"" +
						"]," +
						"\"created\": \"2014-12-09T13:50:49.641000Z\"," +
						"\"edited\": \"2014-12-21T20:48:04.175778Z\"," +
						"\"url\": \"https://swapi.co/api/planets/1/\"" +
					"}" +
				"]" + 
			"}";
		
		long val = 0;
		
		val = System.currentTimeMillis();
		org.boon.json.ObjectMapper mapper = JsonFactory.create();
		Map jsonMap = mapper.readValue(json, Map.class);
		List<Map> resultList = (List<Map>) jsonMap.get("results");
		
		Map info = resultList.get(0);
		List filmes = (List)info.get("films");
		System.out.println(filmes.size());
		System.out.println(System.currentTimeMillis() - val);
		
		try {
			val = System.currentTimeMillis();
			ObjectMapper mapperJackson = new ObjectMapper();
			JsonNode root = mapperJackson.readTree(json);
			JsonNode results = root.path("results");
			JsonNode ele = results.get(0);
			JsonNode films = ele.path("films");
			System.out.println(films.size());
			System.out.println(System.currentTimeMillis() - val);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
