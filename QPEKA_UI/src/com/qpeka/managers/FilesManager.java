package com.qpeka.managers;

import com.qpeka.db.handler.FilesHandler;
import com.qpeka.db.handler.user.UserHandler;

public class FilesManager {
	private static FilesManager instance = null; 
	FilesHandler filesHandler = new FilesHandler();


	public FilesManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilesManager getIntance()  {
		return (instance == null ? (instance = new FilesManager()) : instance);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	
	}
}
