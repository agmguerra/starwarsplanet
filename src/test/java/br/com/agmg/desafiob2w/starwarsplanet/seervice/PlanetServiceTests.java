package br.com.agmg.desafiob2w.starwarsplanet.seervice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.agmg.desafiob2w.starwarsplanet.StarwarsplanetApplication;
import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;
import br.com.agmg.desafiob2w.starwarsplanet.exception.AlreadyExistsException;
import br.com.agmg.desafiob2w.starwarsplanet.exception.GenericException;
import br.com.agmg.desafiob2w.starwarsplanet.exception.NotFoundException;
import br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarwarsplanetApplication.class)
public class PlanetServiceTests {


	@Autowired
	private PlanetService planetService;
	
    //@MockBean
    //private PlanetRepository planetRepository;

    Planet planetWithoutId = null;
    Planet planet = null;
    Planet planet2 = null;
    List<Planet> planets = null;
    

	@Before
	public void preparaTestes() {
		
		planetWithoutId = new Planet();
		planetWithoutId.setName("planet 1");
		planetWithoutId.setTerrain("terrain 1");
		planetWithoutId.setClimate("climate 1");
		
		planet = new Planet();
		planet.setId(1L);
		planet.setName("planet 1");
		planet.setTerrain("terrain 1");
		planet.setClimate("climate 1");
		
		planet2 = new Planet();
		planet2.setId(1000L);
		planet2.setName("planet 2");
		planet2.setTerrain("terrain 2");
		planet2.setClimate("climate 2");
		
		planets = Arrays.asList(planet, planet2);
				
	}
	
	@Test
	public void testSavePlanetOk() {
		Planet planetSaved = planetService.savePlanet(planetWithoutId);	
		assertNotNull(planetSaved);
		assertEquals(planet.getId(), planetSaved.getId());			
	}
	
	@Test
	public void testSavePlanetWithError() {
		try {
			planetWithoutId.setName(null);
			planetService.savePlanet(planetWithoutId);
			fail();
		} catch (AlreadyExistsException e) {
			assertEquals("error.planet.already.exists", e.getMessage());
		} catch (GenericException e) {
			assertEquals("error.generic.message", e.getMessage());
		}
	}

	
	@Test
	public void testGetPlanetByIdOk() {
	
		Planet planetRet = planetService.getById(planet.getId());	
		assertNotNull(planetRet);		
	}
	
	@Test
	public void testGetPlanetByIdWithError() {
	
		try {
			planetService.getById(100L);
		} catch (NotFoundException e) {
			assertEquals("error.planet.not.found", e.getMessage());
		} catch (GenericException e) {
			assertEquals("error.generic.message", e.getMessage());
		}
			
	}
	
		
	@Test
	public void testGetPlanetByNameOk() {
		
		Planet planetRet = planetService.getByName(planetWithoutId.getName());
		
		assertNotNull(planetRet);
		
	}
	
	@Test
	public void testGetPlanetByNameWithError() {
				
		try {
			planetService.getByName(planetWithoutId.getName());
		} catch (NotFoundException e) {
			assertEquals("error.planet.not.found", e.getMessage());
		} catch (GenericException e) {
			assertEquals("error.generic.message", e.getMessage());
		}
	
	}

//	
//	@Test
//	public void testGetAllPlanets() {
//		List<Planet> planets = planetService.getAll();
//		
//		assertNotNull(planets);
//		assertEquals(1, planets.size());
//	}
//
//	@Test
//	public void testDeletePlanetPlanet() {
//		
//		try {
//			planetService.deletePlanet(10L);
//		} catch (Exception e) {
//				e.printStackTrace();
//		}
//	}

}

