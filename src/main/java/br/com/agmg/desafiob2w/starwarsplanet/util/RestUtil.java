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
	   return (actualPage - 1 >= 0 ? actualPage - 1 : 1);
   }
   
   /**
    * Obtem a próxima página baseado em uma página atual em uma consulta paginada
    * @param actualPage
    * @param size
    * @param count
    * @return
    */
   public static Integer getNextPage(Integer actualPage, Integer size, Integer count) {
	   int maxPage = count / size;
	   if (count % size > 0) {
		   maxPage++;
	   } 
	   return (actualPage + 1 < maxPage ? maxPage + 1 : maxPage);
	   
   }
   
}
