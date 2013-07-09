package com.qpeka.managers.user;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.qpeka.db.handler.LanguagesHandler;
import com.qpeka.db.handler.user.UserHandler;

public class LanguagesManager {
	private static LanguagesManager instance = null;
	LanguagesHandler languageHandler = new LanguagesHandler();

	public LanguagesManager() {
		super();
	}

	public LanguagesManager getInstance() {
		return (instance == null ? (instance = new LanguagesManager()) : instance);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

}
