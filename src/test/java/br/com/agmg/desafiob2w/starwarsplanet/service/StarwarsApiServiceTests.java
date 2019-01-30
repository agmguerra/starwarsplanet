package br.com.agmg.desafiob2w.starwarsplanet.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.agmg.desafiob2w.starwarsplanet.StarwarsplanetApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarwarsplanetApplication.class)
public class StarwarsApiServiceTests {

	@Autowired
	private StarwarsApiService starwarsApiService;
	
	@Test
	public void testGetNumberOfFilmAppereanceOk() {
		
		Integer num = starwarsApiService.getNumberOfFilmAppereance("Yavin IV");
		assertTrue(num > 0);
		
	}
	
	@Test
	public void testGetNumberOfFilmAppereanceWithError() {
		
		Integer num = starwarsApiService.getNumberOfFilmAppereance("name 1");
		assertEquals(new Integer(0), num);
	}
		
}
