package com.qpeka.converter;

import com.adobe.dp.conv.word2epub.Main;

public class WordToEpub {
	
	public static void main(String[] args) {
		String[] ags ={"/home/manoj/testhello.doc","/home/manoj/GITHUB/"};  
		try{
			Main.main(ags);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Main.source + " " + Main.dest);
	}
}
