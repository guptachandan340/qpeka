package com.qpeka.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemResourceHandler {
	private static SystemResourceHandler instance = new SystemResourceHandler();
	private Properties props = new Properties();
	
	private SystemResourceHandler() {
		try {
			InputStream fis = SystemResourceHandler.class.getClassLoader().getResourceAsStream("system.properties");
			props.load(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SystemResourceHandler getInstance() {
		if(instance == null) {
			synchronized (instance) {
				if(instance == null) {
					instance = new SystemResourceHandler();
				}
			}
		}
		
		return instance;
	}
	
	public String getBookContentFolder() {
		return props.getProperty(SystemConstants.BOOKCONTENTFOLDER);
	}
	
	public String getBookCoverPageFolder() {
		return props.getProperty(SystemConstants.BOOKCOVERPAGEFOLDER);
	}
	
	public String getUserImageFolder() {
		return props.getProperty(SystemConstants.USERIMAGEFOLDER);
	}
	
	
	public String getImageServerURL() {
		return props.getProperty(SystemConstants.IMAGESERVERURL);
	}
	
	public String getBaseTinyURL() {
		return props.getProperty(SystemConstants.BASETINYURL);
	}
	
	public String getSrcBookFolder() {
		return props.getProperty(SystemConstants.SRCBOOKFOLDER);
	}
	
	public String getUserCoverImg() {
		return props.getProperty(SystemConstants.USERCOVERIMGFILE);
	}
	
	public String getServerSalt() {
		return props.getProperty(SystemConstants.SERVERSALT);
	}
	
	public String getHost() {
		return props.getProperty("host");
	}
	
	public String getSenderEmail() {
		return props.getProperty(SystemConstants.SENDEREMAIL);
	}
	
	public String getPassword() {
		return props.getProperty(SystemConstants.PASSWORD);
	}
	
	public String getImageDir() {
		return props.getProperty(SystemConstants.IMAGEDIR);
	}
}