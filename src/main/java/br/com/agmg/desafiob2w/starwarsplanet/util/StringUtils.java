package br.com.agmg.desafiob2w.starwarsplanet.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe de helper para tratamento de strings
 * 
 * @author alexgmg
 *
 */
public class StringUtils {
	
	/**
	 * Retira os caracteres ".", "-" e "/" de um string
	 * @param str string para que os "." sejam retirados
	 * @return string sem os pontos
	 */
	public static final String retiraMascara(String str) {
		return str.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("\\/", "");
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
