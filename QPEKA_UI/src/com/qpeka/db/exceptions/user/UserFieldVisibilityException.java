package com.qpeka.db.exceptions.user;

import com.qpeka.db.exceptions.QpekaException;

public class UserFieldVisibilityException extends QpekaException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1512455513784134436L;

	/**
	 * Method 'UserFieldVisibilityDaoException'
	 * 
	 * @param message
	 */
	public UserFieldVisibilityException(String message)
	{
		super(message);
	}

	/**
	 * Method 'UserFieldVisibilityDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public UserFieldVisibilityException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
