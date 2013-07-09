package com.qpeka.managers;

import com.qpeka.db.handler.CategoryHandler;

public class CategoryManager {
private static CategoryManager instance= null;
CategoryHandler categoryHandler = new CategoryHandler();

public CategoryManager() {
	super();
}

public CategoryManager getInstance() {
	return(instance == null ? instance = new CategoryManager() : instance);
}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
