package com.qpeka.managers;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionExpiryListener
 *
 */
public class SessionExpiryListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public SessionExpiryListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
       String uid = (String)arg0.getSession().getAttribute("uid");
       
       if(uid != null)
    	   SessionManager.getInstance().destroySession(uid);
    }
	
}
