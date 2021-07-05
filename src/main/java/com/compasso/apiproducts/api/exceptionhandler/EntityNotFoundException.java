package com.compasso.apiproducts.api.exceptionhandler;


public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2827627913153219291L;

	public EntityNotFoundException (String message) {
		super(message);
	}
}
