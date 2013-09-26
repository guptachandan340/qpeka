package com.qpeka.utils;

import java.util.Random;

public class BookTinyURLIDGenerator {
	
	public static final String[] UNITS = {
		"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
		"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
		"0","1","2","3","4","5","6","7","8","9"
	};
	
	public static String generateBookTinyURLId() {
		StringBuffer buf = new StringBuffer(5);
		Random r = new Random();
		for(int i = 0; i < 5; i++) {
			buf.append(UNITS[r.nextInt(UNITS.length)]);
		}
		
		return buf.toString();
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 20; i++ ) {
			System.out.println(generateBookTinyURLId());
		}
	}
}
