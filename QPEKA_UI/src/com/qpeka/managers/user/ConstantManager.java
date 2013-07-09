package com.qpeka.managers.user;

import com.qpeka.db.handler.LanguagesHandler;

public class ConstantManager {
	private static ConstantManager instance = null;
	ConstantHandler constantHandler = new ConstantHandler();
	
	public ConstantManager() {
		super();
	}

	public ConstantManager getInstance() {
		return(instance == null ? instance = new ConstantManager() : instance);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
