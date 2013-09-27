package com.qpeka.managers;

import java.util.ArrayList;
import java.util.List;

import com.qpeka.db.Constants.SESSIONSTATUS;
import com.qpeka.db.exceptions.user.SessionException;
import com.qpeka.db.handler.SessionHandler;
import com.qpeka.db.Session;
import com.qpeka.security.bcrypt.BCrypt;

public class SessionsManager {

	public static SessionsManager instance = null;

	public SessionsManager() {
		super();
	}

	public static SessionsManager getInstance() {
		return (instance == null ? (instance = new SessionsManager())
				: instance);
	}

	public long createSession(long userid, String username, String password,
			String hostname) {
		long sessionid = 0;
		Session session = Session.getInstance();
		session.setUserid(userid);
		session.setCreated(System.currentTimeMillis());
		session.setStatus((short) SESSIONSTATUS.ACTIVE.ordinal());
		// create nonce by xoring current time in millisecond with some fixed
		// value and returning its hashed value
		String nonce = BCrypt.hashpw(
				Integer.toString(((byte) System.currentTimeMillis()) ^ 0xDE),
				BCrypt.gensalt());
		session.setSession(BCrypt.hashpw((username + nonce + password),
				BCrypt.gensalt()));
		session.setHostname(hostname);

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

}
