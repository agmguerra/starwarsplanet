package br.com.agmg.desafiob2w.starwarsplanet.seervice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import org.springframework.test.context.junit4.SpringRunner;

import br.com.agmg.desafiob2w.starwarsplanet.StarwarsplanetApplication;
import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;
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
		
		Planet planet2 = new Planet();
		planet = new Planet();
		planet.setId(1000L);
		planet.setName("planet 2");
		planet.setTerrain("terrain 2");
		planet.setClimate("climate 2");

		
		//Planet[] array = {planet, planet2};
		
		planets = Arrays.asList(planet, planet2);
		
		Mockito.when(planetRepository.findById(planet.getId()))
	      .thenReturn(Optional.of(planet));
		
		Mockito.when(planetRepository.findByName(planetWithoutId.getName()))
		  .thenReturn(Optional.of(planet));
		
		Mockito.when(planetRepository.save(planetWithoutId)).thenReturn(planet);
		
		Mockito.when(planetRepository.findAll()).thenReturn(Arrays.asList(planet));
		
	}
	
	@Test
	public void testSavePlanetOk() {
				
		Planet planetSaved = planetService.savePlanet(planetWithoutId);	
		assertNotNull(planetSaved);
		assertEquals(planet.getId(), planetSaved.getId());
					
	}
	
	@Test
	public void testFindPlanetById() {
		
		Planet planetRet = planetService.getById(planet.getId());
	
		assertNotNull(planetRet);
	}
	
	@Test
	public void testFindPlanetByName() {
		
		Planet planetRet = planetService.getByName(planetWithoutId.getName());
		
		assertNotNull(planetRet);
		
	}
	
	@Test
	public void testeRecuperarTodosClientes() {
		List<Planet> planets = planetService.getAll();
		
		assertNotNull(planets);
		assertEquals(1, planets.size());
	}



}

