package com.qpeka.managers;

import com.qpeka.db.handler.BadgesHandler;

public class BadgesManager {
private static BadgesManager instance = null;
BadgesHandler badgesHandler = new BadgesHandler();

public BadgesManager() {
	super();
}

public BadgesManager getInstance() {
	return (instance == null? instance = new BadgesManager() : instance);
}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
