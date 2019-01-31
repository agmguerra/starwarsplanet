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
   
   /**
    * Obtem a página anterior baseado em uma pagina atual em uma consulta paginada
    * @param actualPage
    * @return
    */
   public static Integer getPreviusPage(Integer actualPage) {
	   return (actualPage - 1 <= 0 ? 1 : actualPage - 1);
   }
   
   /**
    * Obtem a próxima página baseado em uma página atual em uma consulta paginada
    * @param actualPage
    * @param size
    * @param count
    * @return
    */
   public static Integer getNextPage(Integer actualPage, Integer totalPages) {
	   
	   Integer next = null;
       if (actualPage + 1 > totalPages) {
		   next = totalPages;
	   } else {
		   next = actualPage + 1;
	   }
	     
	   return next;
	   
   }
   
}
