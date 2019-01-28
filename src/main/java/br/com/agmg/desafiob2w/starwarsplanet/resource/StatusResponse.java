package br.com.agmg.desafiob2w.starwarsplanet.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Classe responsável por retornar informações de um request
 * que apresentou problema
 * @author alexgmg
 *
 */
public class StatusResponse {
	
	private String httpStatusCode;
	private String httpStatuMsg;
	private Date timestamp;
	private List<String> messages = new ArrayList<String>();
	
	@JsonInclude(Include.NON_EMPTY)
	private String details;
	
	public StatusResponse(HttpStatus httpStatus, Date timestamp, BindingResult result, String details) {
		this.httpStatusCode = httpStatus.toString();
		this.httpStatuMsg = httpStatus.getReasonPhrase();
		this.timestamp = timestamp;
		this.details = details;
		for (ObjectError error : result.getAllErrors()) {
			messages.add(error.getDefaultMessage());
		}
	}
	
	public StatusResponse(HttpStatus httpStatus, BindingResult result, String details) {
		this(httpStatus, new Date(), result, details);
	}
	
	public StatusResponse(HttpStatus httpStatus, Date timestamp, String message, String details) {
		this.httpStatusCode = httpStatus.toString();
		this.httpStatuMsg = httpStatus.getReasonPhrase();
		this.timestamp = new Date();
		this.details = details;
		messages.add(message);
	}

	public StatusResponse(HttpStatus httpStatus, String message, String details) {
		this(httpStatus, new Date(), message, details);
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public List<String> getMessages() {
		return messages;
	}

	public String getDetails() {
		return details;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public String getHttpStatuMsg() {
		return httpStatuMsg;
	}

}
