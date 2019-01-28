package br.com.agmg.desafiob2w.starwarsplanet.exception;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import br.com.agmg.desafiob2w.starwarsplanet.util.RestUtil;

/**
 * 
 * Trata os erros de uma requisição rest
 *
 */
@Component
public class RestResponseErrorHandler implements ResponseErrorHandler {

	/**
	 * 
	 */
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }

    /**
     * Informa se houve ou não erro na requisição rest
     */
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return RestUtil.isError(response.getStatusCode());
    }    
    
    
}