package br.com.agmg.desafiob2w.starwarsplanet.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenericException(String message) {
		super(message);
	}

	private String message;

	private List<String> messages;

	/**
	 * Construtor padrão da classe FrameworkException.
	 */
	public GenericException() {
		super();
	}

	/**
	 * Construtor alternativo da classe FrameworkException.
	 * 
	 * @param message mensagem de erro customizada.
	 * @param cause   exception que causou o erro.
	 * 
	 */
	public GenericException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	/**
	 * Construtor alternativo da classe FrameworkException.
	 * 
	 * @param messages mensagem de erro customizada.
	 * 
	 */
	public GenericException(List<String> messages) {
		super();
		this.messages = messages;
	}

	/**
	 * Construtor alternativo da classe FrameworkException.
	 * 
	 * @param cause exception que causou o erro.
	 * 
	 */
	public GenericException(Throwable cause) {
		super(cause);
	}

	/**
	 * Construtor alternativo da classe FrameworkException.
	 * 
	 * @param messages vararg do tipo {@link Message} para armazenar os detalhes da
	 *                 exception.
	 * 
	 */
	public GenericException(String... messages) {
		super();
		this.messages = new ArrayList<String>(Arrays.asList(messages));
	}

	/**
	 * Construtor alternativo da classe FrameworkException
	 * 
	 * @param message  mensagem de erro customizada.
	 * 
	 * @param cause    exception que causou o erro.
	 * 
	 * @param messages vararg do tipo {@link Message} para armazenar os detalhes da
	 *                 exception.
	 * 
	 */
	public GenericException(String message, Throwable cause, String... messages) {
		super(message, cause);
		this.messages = new ArrayList<String>(Arrays.asList(messages));
		this.message = message;
	}

	/**
	 * Construtor alternativo da classe FrameworkException
	 * 
	 * @param message  mensagem de erro customizada.
	 * @param messages vararg do tipo {@link Message} para armazenar os detalhes da
	 *                 exception.
	 */
	public GenericException(String message, String... messages) {
		super(message);
		this.messages = new ArrayList<String>(Arrays.asList(messages));
		this.message = message;
	}

	/**
	 * Construtor alternativo da classe FrameworkException.
	 * 
	 * @param cause    exception que causou o erro.
	 * @param messages vararg do tipo {@link Message} para armazenar os detalhes da
	 *                 exception.
	 */
	public GenericException(Throwable cause, String... messages) {
		super(cause);
		this.messages = new ArrayList<String>(Arrays.asList(messages));
	}

	/**
	 * Retorna o valor de messages.
	 * 
	 * @return lista com as mensagem de erros e parâmetros utilizados.
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * Retorna o valor de message.
	 * 
	 * @return valor de message.
	 */
	public String getMessage() {
		if (message == null && getCause() != null) {
			message = getCause().getMessage();
		}
		if (message == null && messages != null) {
			message = "";
			for (int i = 0; i < messages.size(); i++) {
				String innerMessage = messages.get(i);
				message += innerMessage;
				if (i + 1 < messages.size()) {
					message += ", ";

				}
			}
		}
		return message;
	}

	/**
	 * Repassa o java.io.PrintStream recebido.
	 * 
	 * @param err stream contendo o stack trace do erro.
	 * 
	 */
	@Override
	public void printStackTrace(PrintStream err) {
		String exceptionDetail = getExceptionDetail();
		if (exceptionDetail != null && exceptionDetail.length() > 0) {
			err.append(exceptionDetail + " - ");
		}
		super.printStackTrace(err);
	}

	/**
	 * Repassa o java.io.PrintWriter recebido.
	 * 
	 * @param err stream contendo o stack trace do erro.
	 * 
	 */
	@Override
	public void printStackTrace(PrintWriter err) {
		String exceptionDetail = getExceptionDetail();
		if (exceptionDetail != null && exceptionDetail.length() > 0) {
			err.append(exceptionDetail + " - ");
		}
		super.printStackTrace(err);
	}

	/**
	 * Retorna exceção detalhada.
	 * 
	 * @return exceção detalhada.
	 */
	private String getExceptionDetail() {
		StringBuilder sb = new StringBuilder();
		if (getMessages() != null) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append("messages=").append(getMessages());
		}
		if (sb.length() > 0) {
			return "[" + sb.toString() + "]";
		}
		return "";
	}

	/**
	 * Define uma lista de e parâmetros.
	 * 
	 * @param messages lista de parâmetros e mensagens.
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	/**
	 * Define uma mensagem de erro customizada.
	 * 
	 * @param message mensagem de erro customizada.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
