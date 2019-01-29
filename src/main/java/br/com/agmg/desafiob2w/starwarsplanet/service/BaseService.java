package br.com.agmg.desafiob2w.starwarsplanet.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import br.com.agmg.desafiob2w.starwarsplanet.exception.RestResponseErrorHandler;

/**
 * Classe que serve de base para todos os serviços
 * Prove a injeção de objetos necessários e métodos que normalmente
 * serão utilizados por qualquer serviço
 *
 */
public abstract class BaseService {
		
	
	@Autowired
	private RestResponseErrorHandler restResponseErrorHandler;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private HttpServletRequest request;

	
	
	/**
	 * Retorna o handler que verifica que algum erro foi retornado 
	 * a partir de uma requisição rest
	 * @return RestResponseErrorHandler
	 */
	protected RestResponseErrorHandler getRestResponseErroHandler() {
		return restResponseErrorHandler;
	}
	
	/**
	 * Retorna o objeto MessageSource que recupera as mensagens 
	 * do arquivo de properties
	 * @return MessageSource
	 */
	protected MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * Retorna o HttpServletRequest
	 * @return HttpServletRequest
	 */
	protected HttpServletRequest getRequest() {
		return request;
	}
	
	/**
	 * Cria um objeto RestTemplate preparado para capturar os 
	 * erros
	 * @return RestTemplate
	 */
	protected RestTemplate createRestTemplate() {
		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);		
		RestTemplate rest = new RestTemplate(requestFactory);
		
		rest.setErrorHandler(restResponseErrorHandler);
		
		return rest;
	}
	
}
