package com.qpeka.managers.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;

import com.qpeka.db.Constants.INVITESTATUS;
import com.qpeka.db.exceptions.QpekaException;
import com.qpeka.db.handler.user.UserInvitesHandler;
import com.qpeka.db.handler.user.UserProfileHandler;
import com.qpeka.db.user.profile.Name;
import com.qpeka.db.user.profile.UserInvites;
import com.qpeka.db.user.profile.UserProfile;
import com.qpeka.security.bcrypt.BCrypt;
import com.qpeka.services.Response.ServiceResponseManager;

public class UserInvitesManager {
	private static UserInvitesManager instance = null;

	public UserInvitesManager() {
		super();
	}

	public static UserInvitesManager getInstance() {
		return (instance == null ? (instance = new UserInvitesManager())
				: instance);
	}

	protected static final Logger logger = Logger.getLogger(UserManager.class);

	public Map<String, Object> createUserInvites(long userid,
			MultivaluedMap<String, String> inviteParams) throws QpekaException {
		UserInvites userInvites = UserInvites.getInstance();
		Set<String> keySet = inviteParams.keySet();
		Map<String, Object> hashvalues = new HashMap<String, Object>();
		String hashvalue = null;

		for (String key : keySet) {
			if (!key.equalsIgnoreCase("userid")) {
				List<String> inviteInfo = inviteParams.get(key);
				if (!inviteInfo.isEmpty() && inviteInfo != null) {
					for (String inviteIdentifier : inviteInfo) {
						userInvites.setInviteidentifier(inviteIdentifier);
						userInvites.setType(key);
						userInvites.setUserid(userid);
						// Set hash value
						hashvalue = BCrypt.hashpw(
								(userid + key + inviteIdentifier),
								BCrypt.gensalt());
						userInvites.setHashvalue(hashvalue);
						hashvalues.put(inviteIdentifier, hashvalue);
						// Set status
						userInvites.setStatus(INVITESTATUS.PENDING);
						try {
							UserInvitesHandler.getInstance()
									.insert(userInvites);
						} catch (QpekaException e) {
							throw new QpekaException("UserInvites creation Exception : ");
						}
					}
				}
			}
		}
		if (!hashvalues.isEmpty() && hashvalue != null) {
			return hashvalues;
		} else {
			// TODO Set Error Code or "" . by default I am keeping 215.
			return ServiceResponseManager.getInstance()
					.readServiceResponse(215);
		}
	}

	/************************************ Send an Invitation  ******************************/

	public Map<String, Object> setInviteSent(List<String> hashvalues) throws QpekaException {
		List<Object> hashvalObj = new ArrayList<Object>();
		for (String hash : hashvalues) {
			hashvalObj.add(hash);
		}
		try {
			List<UserInvites> userInvitesList = UserInvitesHandler
					.getInstance().findByDynamicWhere(
							UserManager.getInstance().buildQuery("hashvalue",
									hashvalObj.size()), hashvalObj);
			if (!userInvitesList.isEmpty() && userInvitesList != null) {
				UserInvites userInvitesObj = UserInvites.getInstance();
				for (UserInvites userInvites : userInvitesList) {
					// update status for userInvites
					userInvitesObj.setStatus(INVITESTATUS.SENT);
					UserInvitesHandler.getInstance().update(
							userInvites.getInviteid(), userInvitesObj);
				}
				return ServiceResponseManager.getInstance()
						.readServiceResponse(201);
			}
		} catch (QpekaException e) {
			throw new QpekaException("UserInvites Sending Exception : ");
		}
		// TODO ask if no match found in database
		return ServiceResponseManager.getInstance().readServiceResponse(215);
	}

	/********************************* Accept an Invitation *****************************/

	public Map<String, Object> setInviteAccepted(String hashvalue) throws QpekaException {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			List<UserInvites> userInvitesList = UserInvitesHandler
					.getInstance().findWhereHashvalueEquals(hashvalue);
			if (!userInvitesList.isEmpty() && userInvitesList != null) {
				// Set full name of user who has sent an invitation
				long userid = userInvitesList.iterator().next().getUserid();
				List<UserProfile> userProfile = UserProfileHandler
						.getInstance().findWhereUseridEquals(
								userid);
				
				// Set fullname
				Map<String, String> fullname = new HashMap<String, String>();
				if (!userProfile.isEmpty() && userProfile != null) {
					for (UserProfile usrprofile : userProfile) {
						fullname.put(Name.FIRSTNAME, usrprofile.getName()
								.getFirstname());
						fullname.put(Name.LASTNAME, usrprofile.getName()
								.getLastname());
						fullname.put(Name.MIDDLENAME, usrprofile.getName()
								.getMiddlename());
					}
				}

				// update status as Accepted
				UserInvites userInvitesObj = UserInvites.getInstance();
				for (UserInvites userInvites : userInvitesList) {
					// update status for userInvites
					userInvitesObj.setStatus(INVITESTATUS.ACCEPTED);
					UserInvitesHandler.getInstance().update(
							userInvites.getInviteid(), userInvitesObj);
					
					// update status as rejected for all other users who has invited the same person
					List<Object> userObjList = new ArrayList<Object>();
					userObjList.add(userid);
					userObjList.add(userInvites.getInviteidentifier());
					List<UserInvites> usrinvites = UserInvitesHandler.getInstance().findByDynamicWhere("userid NOT IN (?) AND inviteIdentifier IN (?)", userObjList);
					UserInvites inviteObj = UserInvites.getInstance();
					if(!usrinvites.isEmpty() && usrinvites != null) {
						for(UserInvites invites : usrinvites) {
							inviteObj.setStatus(INVITESTATUS.REJECTED);
							UserInvitesHandler.getInstance().update(
									invites.getInviteid(), userInvitesObj);
						}
					}
					
					// Set response for on invitation sent
					response.put("fullname", fullname);
					response.put("type", userInvites.getType());
					response.put("inviteidentifier",
							userInvites.getInviteidentifier());
					response.put("hashvalue", userInvites.getHashvalue());
				}
			}
		} catch (QpekaException e) {
			throw new QpekaException("UserInvites Acceptance Exception");
		}
		// TODO ask for empty check or not
		return response;
	}
}
