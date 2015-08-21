/*
 * Copyright (c) 2012-2015, Andrea Funto'. All rights reserved. See LICENSE for details.
 */

package org.dihedron.struts.plugin;

/**
 * @author Andrea Funto'
 */
public class AutoBindingException extends Exception {

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 422495022161867043L;

	/**
	 * Default constructor.
	 */
	public AutoBindingException() {
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 *   the exception message.
	 */
	public AutoBindingException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 * 
	 * @param cause
	 *   the inner, causing exception.
	 */
	public AutoBindingException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 *   the exception message.
	 * @param cause
	 *   the inner, causing exception.
	 */
	public AutoBindingException(String message, Throwable cause) {
		super(message, cause);
	}
}
