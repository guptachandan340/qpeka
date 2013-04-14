package com.qpeka.util;

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
	public String getHostString()
	{
		return props.getProperty("host");
	}
	
	
}
