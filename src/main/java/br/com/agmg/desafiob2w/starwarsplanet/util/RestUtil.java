package br.com.agmg.desafiob2w.starwarsplanet.util;

import org.springframework.http.HttpStatus;

/**
 * 
 * Classe utilitária
 *
 */
public class RestUtil {
	
   /**
    * A partir de um HttpStatus informa se retornou erro ou não
    * @param status da requisição rest
    * @return true ou false se ocorreu erro ou não
    */
   public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series)
                || HttpStatus.Series.SERVER_ERROR.equals(series));
   }

}
