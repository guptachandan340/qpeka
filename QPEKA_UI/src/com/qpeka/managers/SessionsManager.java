package com.qpeka.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qpeka.db.Constants.SESSIONSTATUS;
import com.qpeka.db.Session;
import com.qpeka.db.exceptions.user.SessionException;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.handler.SessionHandler;
import com.qpeka.db.handler.user.UserHandler;
import com.qpeka.db.user.User;
import com.qpeka.db.user.profile.Name;
import com.qpeka.security.bcrypt.BCrypt;
import com.qpeka.services.Response.ServiceResponseManager;

public class SessionsManager {

	public static SessionsManager instance = null;

	public SessionsManager() {
		super();
	}

	public static SessionsManager getInstance() {
		return (instance == null ? (instance = new SessionsManager())
				: instance);
	}

	public long createSession(Map<String, String> userSession) {
		Session session = Session.getInstance();
		session.setUserid(Long.parseLong(userSession.get(User.PROFILEID)));
		session.setCreated(System.currentTimeMillis());
		session.setStatus((short) SESSIONSTATUS.ACTIVE.ordinal());
		// create nonce by xoring current time in millisecond with some fixed
		// value and returning its hashed value
		String nonce = BCrypt.hashpw(
				Integer.toString(((byte) System.currentTimeMillis()) ^ 0xDE),
				BCrypt.gensalt());
		session.setSession(BCrypt.hashpw(
				(userSession.get("username") + nonce + userSession
						.get(User.PASSWORD)), BCrypt.gensalt()));
		session.setHostname(userSession.get("hostname"));
		System.out.println("/*************************** SESSION FOR NEW USER*******************/");
		System.out.println("session ===> "+session);
		
		// Putting session in list so that it can become easy to identify it in sessionobj
		List<String> sessionlist = new ArrayList<String>();
		sessionlist.add(session.toString());
		
		Map<String, String> sessionobj = new HashMap<String, String>();
		System.out.println("map before creation : "+sessionobj.toString());
		sessionobj.put("Session", sessionlist.toString());
		sessionobj.put(Name.FIRSTNAME, userSession.get(Name.FIRSTNAME));
		sessionobj.put(Name.LASTNAME, userSession.get(Name.LASTNAME));
		System.out.println("map after putting values"+sessionobj.toString());
		session.setSessionobj(sessionobj.toString());
		System.out.println("map after setting session values" + sessionobj.toString());

		long sessionid = 0;
		try {
			sessionid = SessionHandler.getInstance().insert(session);
		} catch (SessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sessionid;
	}

	public boolean ActiveSessionExist(long sessionid, long userid) {
		if (sessionid > 0 && userid > 0) {
			List<Session> session = null;
			List<Object> readObjSession = new ArrayList<Object>();
			readObjSession.add(sessionid);
			readObjSession.add(userid);
			readObjSession.add((short) SESSIONSTATUS.ACTIVE.ordinal());
			try {
				session = SessionHandler.getInstance().findByDynamicWhere(
						"sessionid = ? AND userid = ? AND status = ?",
						readObjSession);
				if (!session.isEmpty() && session != null) {
					return true;
				}
			} catch (SessionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	// Set session status to inactive or expired
	public Map<String, Object> setSessionStatus(String status) {
		short response = 200;
		List<User> user = null;
		try {
			List<Session> session = SessionHandler.getInstance().findWhereStatusEquals((short) SESSIONSTATUS.ACTIVE.ordinal());
			if(!session.isEmpty()) {
				for(Session userSession : session) {
					user = UserHandler.getInstance().findWhereUseridEquals(userSession.getUserid());
					if(!user.isEmpty()) {
						for(User usr : user) {
							Session session1 = Session.getInstance();
							if(status.equalsIgnoreCase("inactive")) {
								if(System.currentTimeMillis() >= (usr.getLastaccess()+1800)) {
									session1.setStatus((short) SESSIONSTATUS.INACTIVE.ordinal());
								} 
							} else if(status.equalsIgnoreCase("expired")) {
								if(System.currentTimeMillis() >= (usr.getLastaccess() + 86400)) {
									session1.setStatus((short) SESSIONSTATUS.EXPIRED.ordinal());
								}
							}
							SessionHandler.getInstance().update(userSession.getSessionid(), session1);
						}
					}
				}
			} else {
				response = 415;
			}
		} catch (SessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ServiceResponseManager.getInstance().readServiceResponse(response);
	}
}
