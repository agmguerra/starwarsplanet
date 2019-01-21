package br.com.agmg.desafiob2w.starwarsplanet.config;

import javax.sql.DataSource;

import org.h2.server.web.WebServlet;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe utilizada para configurar datasource e hibilitar o console do H2
 * 
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages={"br.com.agmg.desafiob2w.starwarsplanetrepositorio"})
@EnableTransactionManagement
public class RepositoryConfiguration {

	/**
	 * Habilita a configuração através de propriedades externas
	 * para configuração do datasource 
	 * 
	 * @return DataSource 
	 * 
	 */
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
	
	/**
	 * Registra um bean que aabilita a console do banco H2. 
	 * Deve ser usado apenas para teste no ambiente de desenvolvimento
	 * 
	 * @return ServletRegistrationBean bean configurado 
	 */
    @Bean
    ServletRegistrationBean<WebServlet> h2servletRegistration(){
        ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<WebServlet>( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
    
    @Configuration
    public class PersistenceHibernateConfig {
       @Bean
       public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
          return new PersistenceExceptionTranslationPostProcessor();
       }
    }
}
