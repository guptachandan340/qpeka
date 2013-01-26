package com.qpeka.db.book.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

public class MongoAccessor 
{
	private static MongoAccessor instance = new MongoAccessor();
	
	public static MongoAccessor getInstance()
	{
		return instance;
	}
	
	private Mongo mongo = null;
	
	public Mongo getMongo() {
		return mongo;
	}

	public void setMongo(Mongo mongo) {
		this.mongo = mongo;
	}

	private MongoAccessor()
	{
		Properties props = new Properties();
		String host = "";
		int port = 0;
		JSONObject json = null;
		try
		{
			//props.load(MongoAccessor.class.getClassLoader().getResourceAsStream("mongo.properties"));
			props.load(new FileInputStream(new File("/home/manoj/QPEKA/qpeka/QPEKA_UI/WebContent/WEB-INF/classes/mongo.properties")));
			host = props.getProperty("host");
			port = Integer.parseInt(props.getProperty("port"));
			json = new JSONObject(props.getProperty("connectionOptions"));
			
			mongo = new Mongo(host, port);
			
			MongoOptions mongoOptions = new MongoOptions();

			int connectionsPerHost = json.optInt("connectionsPerHost", -1);
			if (connectionsPerHost != -1)
			{
				mongoOptions.connectionsPerHost = connectionsPerHost;
			}

			int threadsAllowedToBlockForConnectionMultiplier = json.optInt("threadsAllowedToBlockForConnectionMultiplier", -1);
			if (threadsAllowedToBlockForConnectionMultiplier != -1)
			{
				mongoOptions.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
			}

			boolean autoConnectRetry = json.optBoolean("autoConnectRetry", false);
			if (autoConnectRetry != false)
			{
				mongoOptions.autoConnectRetry = autoConnectRetry;
			}

			mongo = new Mongo(new ServerAddress(host, port), mongoOptions);
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
