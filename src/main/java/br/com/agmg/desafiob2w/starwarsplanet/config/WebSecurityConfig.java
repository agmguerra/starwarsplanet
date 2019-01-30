package br.com.agmg.desafiob2w.starwarsplanet.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable().authorizeRequests()
        .antMatchers("/**").permitAll().and()
         
        // desabilita a criação de sessão pelo Spring Security
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//Permitir o uso do console do H2 (somente para fins de desenovlvimento)
		 httpSecurity.headers().frameOptions().disable();

	}
	
}
