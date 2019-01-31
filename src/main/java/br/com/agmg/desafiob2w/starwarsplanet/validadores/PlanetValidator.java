package br.com.agmg.desafiob2w.starwarsplanet.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.agmg.desafiob2w.starwarsplanet.entity.Planet;
/**
 * Classe validadora do planet
 * @author alexgmg
 *
 */
public class PlanetValidator implements ConstraintValidator<ValidPlanet, Planet>{

	
	
	private static final String ERROR_PLANET_INVALID = "{error.planet.invalid}";
	private static final String ERROR_PLANET_INVALID_NAME = "{error.planet.invalid.name}";
	private static final String ERROR_PLANET_INVALID_CLIMATE = "{error.planet.invalid.climate}";
	private static final String ERROR_PLANET_INVALID_TERRAIN = "{error.planet.invalid.terrain}";
	
	
	/**
	 * valida o documento
	 */
	@Override
	public boolean isValid(Planet planet, ConstraintValidatorContext ctx) {
		
		boolean ret = true;
		if (planet == null) {
			ctx.disableDefaultConstraintViolation();
	        ctx.buildConstraintViolationWithTemplate(ERROR_PLANET_INVALID)
	           .addConstraintViolation();

			ret = false;
		} else {
			
			if (planet.getName() == null || planet.getName().isEmpty()) {
				ctx.disableDefaultConstraintViolation();
		        ctx.buildConstraintViolationWithTemplate(ERROR_PLANET_INVALID_NAME)
		           .addConstraintViolation();
	
				ret = false;			
			} 
			
			if (planet.getClimate() == null || planet.getClimate().isEmpty()) {
				ctx.disableDefaultConstraintViolation();
		        ctx.buildConstraintViolationWithTemplate(ERROR_PLANET_INVALID_CLIMATE)
		           .addConstraintViolation();
	
				ret = false;
			} else if (planet.getTerrain() == null || planet.getTerrain().isEmpty()) {
				ctx.disableDefaultConstraintViolation();
		        ctx.buildConstraintViolationWithTemplate(ERROR_PLANET_INVALID_TERRAIN)
		           .addConstraintViolation();
				
		        ret = false;							
			} 
					
			
		}
		return ret;
	}
	
	

}
