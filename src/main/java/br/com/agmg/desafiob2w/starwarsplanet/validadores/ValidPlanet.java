package br.com.agmg.desafiob2w.starwarsplanet.validadores;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PlanetValidator.class)
public @interface ValidPlanet {
	
	String message() default "{error.planet.invalid}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
