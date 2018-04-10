package com.everis.service.domain;

import java.io.Serializable;

/**
 * @author gregorio.de.chaves
 *
 */
public class MessageTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	
}