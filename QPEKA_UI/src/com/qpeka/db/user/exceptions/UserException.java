package com.qpeka.db.user.exceptions;

import com.qpeka.db.exceptions.QpekaException;

public class UserException extends QpekaException {
	
	/**
	 * Method 'UserDaoException'
	 * 
	 * @param message
	 */
	public UserException(String message)
	{
		super(message);
	}

	/**
	 * Method 'UserDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public UserException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
