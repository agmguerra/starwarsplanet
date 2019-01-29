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

	
	/**
	 * valida o documento
	 */
	@Override
	public boolean isValid(Planet planet, ConstraintValidatorContext ctx) {
		
		boolean ret = true;
		if (planet == null) {
			ctx.disableDefaultConstraintViolation();
	        ctx.buildConstraintViolationWithTemplate("{erro.planet.invalid}")
	           .addConstraintViolation();

			ret = false;
		} else {
			
			if (planet.getName() == null || planet.getName().isEmpty()) {
				ctx.disableDefaultConstraintViolation();
		        ctx.buildConstraintViolationWithTemplate("{erro.planet.invalid.name}")
		           .addConstraintViolation();
	
				ret = false;			
			} 
			
			if (planet.getClimate() == null || planet.getClimate().isEmpty()) {
				ctx.disableDefaultConstraintViolation();
		        ctx.buildConstraintViolationWithTemplate("{erro.cliente.tipo-documento.invalido.mensagem}")
		           .addConstraintViolation();
	
				ret = false;
			} else if (planet.getTerrain() == null || planet.getTerrain().isEmpty()) {
				ctx.disableDefaultConstraintViolation();
		        ctx.buildConstraintViolationWithTemplate("{erro.cliente.documento.invalido.mensagem}")
		           .addConstraintViolation();
				
		        ret = false;							
			} 
					
			
		}
		return ret;
	}
	
	

}
