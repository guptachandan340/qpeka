package com.qpeka.db.exceptions;

public class QpekaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6105398925670192426L;

	protected Throwable throwable;

	/**
	 * Method 'DaoException'
	 * 
	 * @param message
	 */
	public QpekaException(String message) {
		super(message);
	}

	/**
	 * Method 'DaoException'
	 * 
	 * @param message
	 * @param throwable
	 */
	public QpekaException(String message, Throwable throwable) {
		super(message);
		this.throwable = throwable;
	}

	/**
	 * Method 'getCause'
	 * 
	 * @return Throwable
	 */
	public Throwable getCause() {
		return throwable;
	}

}
