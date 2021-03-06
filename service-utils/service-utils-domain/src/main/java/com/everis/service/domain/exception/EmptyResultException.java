package com.everis.service.domain.exception;

/**
 * The Class EmptyResultException.
 * 
 * @author gbritoch
 *
 */
public class EmptyResultException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new empty result exception.
	 */
	public EmptyResultException() {
		super();
	}

	/**
	 * Instantiates a new empty result exception.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 * @param arg2 the arg2
	 * @param arg3 the arg3
	 */
	public EmptyResultException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		//super(arg0, arg1, arg2, arg3);
	}

	/**
	 * Instantiates a new empty result exception.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public EmptyResultException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * Instantiates a new empty result exception.
	 *
	 * @param arg0 the arg0
	 */
	public EmptyResultException(String arg0) {
		super(arg0);
	}

	/**
	 * Instantiates a new empty result exception.
	 *
	 * @param arg0 the arg0
	 */
	public EmptyResultException(Throwable arg0) {
		super(arg0);
	}

}
