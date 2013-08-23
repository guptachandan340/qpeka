package com.qpeka.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemConfigHandler 
{
	private static SystemConfigHandler instance = new SystemConfigHandler();
	private Properties props = new Properties();
	
	private SystemConfigHandler()
	{
		try 
		{
			InputStream fis = SystemConfigHandler.class.getClassLoader().getResourceAsStream("system.properties");
			props.load(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SystemConfigHandler getInstance()
	{
		if(instance == null)
		{
			synchronized (instance) {
				
				if(instance == null)
				{
					instance = new SystemConfigHandler();
				}
			}
		}
		
		return instance;
	}
	///var/lib/openshift/6d134eafb7434f86981aed6dcbc101cb/jbossews-1.0/data/books/content/
	public String getBookContentFolder()
	{
		return props.getProperty(SystemConstants.BOOKCONTENTFOLDER);
	}
	
	public String getBookCoverPageFolder()
	{
		return props.getProperty(SystemConstants.BOOKCOVERPAGEFOLDER);
	}
	
	public String getUserImageFolder()
	{
		return props.getProperty(SystemConstants.USERIMAGEFOLDER);
	}
	
	
	public String getImageServerURL()
	{
		return props.getProperty(SystemConstants.IMAGESERVERURL);
	}
	
	public String getBaseTinyURL()
	{
		return props.getProperty(SystemConstants.BASETINYURL);
	}
	
	public String getSrcBookFolder()
	{
		return props.getProperty(SystemConstants.SRCBOOKFOLDER);
	}
	
	public String getUserCoverImg()
	{
		return props.getProperty(SystemConstants.USERCOVERIMGFILE);
	}
	
	public String getServerSalt()
	{
		return props.getProperty(SystemConstants.SERVERSALT);
	}
	
	public String getHost()
	{
		return props.getProperty("host");
	}
	
	public String getSenderEmail()
	{
		return props.getProperty(SystemConstants.SENDEREMAIL);
	}
	
	public String getPassword()
	{
		return props.getProperty(SystemConstants.PASSWORD);
	}
}
