package br.com.agmg.desafiob2w.starwarsplanet.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.agmg.desafiob2w.starwarsplanet.StarwarsplanetApplication;
import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;
import br.com.agmg.desafiob2w.starwarsplanet.exception.AlreadyExistsException;
import br.com.agmg.desafiob2w.starwarsplanet.exception.GenericException;
import br.com.agmg.desafiob2w.starwarsplanet.exception.NotFoundException;
import br.com.agmg.desafiob2w.starwarsplanet.repository.PlanetRepository;
import br.com.agmg.desafiob2w.starwarsplanet.service.PlanetService;
import br.com.agmg.desafiob2w.starwarsplanet.service.PlanetServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarwarsplanetApplication.class)
public class PlanetServiceTests {

	@TestConfiguration
    static class PlanetServiceImplTestContextConfiguration {
  
        @Bean
        public PlanetService planetService() {
            return new PlanetServiceImpl();
        }
    }

	@Autowired
	private PlanetService planetService;
	
    @MockBean
    private PlanetRepository planetRepository;

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
		planet2.setName(null);
		planet2.setTerrain("terrain 2");
		planet2.setClimate("climate 2");
		
		planets = Arrays.asList(planet, planet2);
		
		Mockito.when(planetRepository.findById(planet.getId()))
	      .thenReturn(Optional.of(planet));
		
		Mockito.when(planetRepository.findById(10L))
	      .thenReturn(Optional.ofNullable(null));
		
		Mockito.when(planetRepository.findByName(planetWithoutId.getName()))
		  .thenReturn(Optional.of(planet));
		
		Mockito.when(planetRepository.findByName("not found name"))
		  .thenReturn(Optional.ofNullable(null));

		Mockito.when(planetRepository.save(planetWithoutId)).thenReturn(planet);
		Mockito.when(planetRepository.save(planet2)).thenThrow(DataIntegrityViolationException.class);
		
		Mockito.when(planetRepository.findAll()).thenReturn(Arrays.asList(planet));
		
		Mockito.when(planetRepository.existsById(1L)).thenReturn(true);
		Mockito.when(planetRepository.existsById(10L)).thenReturn(false);
		
		Mockito.doNothing().when(planetRepository).deleteById(Mockito.anyLong());

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
			planetService.savePlanet(planet2);
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
			planetService.getByName("not found name");
		} catch (NotFoundException e) {
			assertEquals("error.planet.not.found", e.getMessage());
		} catch (GenericException e) {
			assertEquals("error.generic.message", e.getMessage());
		}
	
	}

	
	@Test
	public void testGetAllPlanets() {
		List<Planet> planets = planetService.getAll();
		
		assertNotNull(planets);
		assertEquals(1, planets.size());
	}

	@Test
	public void testDeletePlanetOk() {
		
		planetService.deletePlanet(1L);
	}

	@Test
	public void testDeletePlanetWithError() {
		
		try {
			planetService.deletePlanet(10L);
		} catch (NotFoundException e) {
			assertEquals("error.planet.not.found", e.getMessage());
		} catch (Exception e) {
			assertEquals("error.planet.invalid.delete", e.getMessage());
		}
	}

}

