package com.qpeka.managers;

import com.qpeka.db.handler.CountryHandler;

public class CountryManager {
private static CountryManager instance =null;
CountryHandler countryHandler = new CountryHandler();
 public CountryManager() {
	 super();
 }
 
 public CountryManager getInstance() {
	 return (instance == null ? instance = new CountryManager() : instance);
 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
