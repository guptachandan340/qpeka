package com.qpeka.managers;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class SessionManager {
	
	private HashMap<String, HttpSession> map = new HashMap<String, HttpSession>();
	
	private static SessionManager instance = new SessionManager();
	
	public static SessionManager getInstance()
	{
		return instance;
	}
	
	private void SessionManager(){
		
	}
	
	public synchronized void addSession(String userId, HttpSession session)
	{
		map.put(userId, session);
	}
	
	public synchronized void destroySession(String userId)
	{
		map.remove(userId);
	}
	
	public synchronized HttpSession getSession(String user)
	{
		return map.get(user);
	}
}
