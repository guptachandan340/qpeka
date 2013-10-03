package com.qpeka.services.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.db.Constants.SESSIONSTATUS;
import com.qpeka.db.Session;
import com.qpeka.db.exceptions.CountryException;
import com.qpeka.db.exceptions.FileException;
import com.qpeka.db.exceptions.GenreException;
import com.qpeka.db.exceptions.LanguagesException;
import com.qpeka.db.exceptions.QpekaException;
import com.qpeka.db.exceptions.user.AddressException;
import com.qpeka.db.exceptions.user.SessionException;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.exceptions.user.UserFieldVisibilityException;
import com.qpeka.db.exceptions.user.UserInterestsException;
import com.qpeka.db.exceptions.user.UserLanguageException;
import com.qpeka.db.exceptions.user.UserProfileException;
import com.qpeka.db.handler.SessionHandler;
import com.qpeka.db.user.User;
import com.qpeka.managers.SessionsManager;
import com.qpeka.managers.user.UserInvitesManager;
import com.qpeka.managers.user.UserManager;
import com.qpeka.services.Response.ServiceResponseManager;

@Path("user")
public class UserService {

	@POST
	@Path("/login")
	public Response loginService(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("isEmail") boolean isEmail,
			@Context HttpServletRequest request) {

		Map<String, Object> response = new HashMap<String, Object>();
		try {
			response = UserManager.getInstance().authenticateUser(username,
					password, isEmail);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (response.get(User.PROFILEID) != null) {
			long sessionid = 0;

			// if(SessionsManager.getInstance().ActiveSessionExist(sessionid,
			// Long.parseLong(response.get(User.PROFILEID).toString())))
			sessionid = SessionsManager.getInstance().createSession(
					Long.parseLong(response.get(User.PROFILEID).toString()),
					username, password, request.getRemoteAddr());
			if (sessionid > 0) {
				response.put(Session.SESSIONID, sessionid);
			}
		}
		return Response.status(200).entity(new Gson().toJson(response)).build();
	}

	@POST
	@Path("/logout")
	public Response logoutService(@FormParam("userid") long userid,
			@FormParam("sessionid") long sessionid) {
		try {
			// No need to set response for updateLastActivity
			// Reason : We shouldn't let the user that we are tracking their
			// lastaccess and lastlogin record.
			Session session = Session.getInstance();
			session.setStatus((short) SESSIONSTATUS.DESTROYED.ordinal());
			SessionHandler.getInstance().update(sessionid, session);

			UserManager.getInstance().updateLastActivity(userid, false);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200)
				.entity(new Gson().toJson("successfully logged out")).build();
	}

	@POST
	@Path("/signup")
	@Consumes("application/x-www-form-urlencoded")
	public Response signupService(MultivaluedMap<String, String> formParams)
			throws UserException {
		Map<String, Object> sresponse = null;
		List<String> emailList = formParams.get(User.EMAIL);
		for (String email : emailList) {
			try {
				sresponse = (!UserManager.getInstance().userExists(email, true) ? UserManager
						.getInstance().registerUser(formParams)
						: ServiceResponseManager.getInstance()
								.readServiceResponse(34));

			} catch (UserException e) {
				throw new UserException(" Registration Exception : ");
			}
		}
		return Response.status(200).entity(new Gson().toJson(sresponse))
				.build();
	}

	@POST
	@Path("/resetpwd")
	public Response resetPwdService(@FormParam("authname") String authName) {
		boolean isEmail = false;
		String changedPassword = null;
		if (authName.indexOf("@") != -1) {
			isEmail = true;
		}
		try {
			changedPassword = UserManager.getInstance().resetPassword(authName,
					isEmail);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response
				.status(200)
				.entity(new Gson()
						.toJson(changedPassword != null ? changedPassword
								: ServiceResponseManager.getInstance()
										.readServiceResponse(215))).build();
	}

	@POST
	@Path("/changepwd")
	public Response changePwdService(@FormParam("userid") long userid,
			@FormParam("currentpassword") String currentPassword,
			@FormParam("newpassword") String newPassword,
			@FormParam("sessionid") long sessionid) {

		if (SessionsManager.getInstance().ActiveSessionExist(sessionid, userid)) {
			if (!newPassword.isEmpty() && newPassword != null) {
				Map<String, Object> response = new HashMap<String, Object>();
				try {
					response = UserManager.getInstance().changePassword(userid,
							currentPassword, newPassword);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return Response.status(200).entity(new Gson().toJson(response))
						.build();
			}
		}
		return Response
				.status(200)
				.entity(new Gson().toJson(ServiceResponseManager.getInstance()
						.readServiceResponse(401))).build();
	}

	@POST
	@Path("/getprofile")
	public Response getProfileService(@FormParam("userid") long userid,
			@FormParam("sessionid") long sessionid) throws AddressException,
			CountryException, UserInterestsException, GenreException,
			UserLanguageException, LanguagesException,
			UserFieldVisibilityException {

		if (SessionsManager.getInstance().ActiveSessionExist(sessionid, userid)) {
			try {
				return Response
						.status(200)
						.entity(new Gson().toJson(UserManager.getInstance()
								.getProfile(userid))).build();
			} catch (UserProfileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Response
				.status(200)
				.entity(new Gson().toJson(ServiceResponseManager.getInstance()
						.readServiceResponse(401))).build();

	}

	@POST
	@Path("/viewownprofile")
	public Response viewOwnProfileService(@FormParam("userid") long userid,
			@FormParam("sessionid") long sessionid) {
		if (SessionsManager.getInstance().ActiveSessionExist(sessionid, userid)) {
			try {
				return Response
						.status(200)
						.entity(new Gson().toJson(UserManager.getInstance()
								.viewOwnProfile(userid))).build();
			} catch (UserProfileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Response
				.status(200)
				.entity(new Gson().toJson(ServiceResponseManager.getInstance()
						.readServiceResponse(401))).build();
	}

	@POST
	@Path("/deleteuser")
	public Response deleteUserService(@FormParam("userid") long userid,
			@FormParam("sessionid") long sessionid) {

		if (SessionsManager.getInstance().ActiveSessionExist(sessionid, userid)) {
			try {
				return Response
						.status(200)
						.entity(new Gson().toJson(UserManager.getInstance()
								.deleteUser(userid))).build();
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Response
				.status(200)
				.entity(new Gson().toJson(ServiceResponseManager.getInstance()
						.readServiceResponse(401))).build();
	}

	@POST
	@Path("/editprofile")
	@Consumes("application/x-www-form-urlencoded")
	public Response editBasicSocialProfileService(
			MultivaluedMap<String, String> formParams) throws FileException,
			NumberFormatException, CountryException,
			UserFieldVisibilityException {

		if (!formParams.isEmpty() && formParams != null) {
			long sessionid = 0;
			long userid = 0;
			if (formParams.get("sessionid") != null
					&& formParams.get("userid") != null) {
				sessionid = Long.parseLong(formParams.get("sessionid")
						.iterator().next());
				userid = Long.parseLong(formParams.get("userid").iterator()
						.next());

				if (SessionsManager.getInstance().ActiveSessionExist(sessionid,
						userid)) {
					List<Map<String, Object>> sResponse = new ArrayList<Map<String, Object>>();
					try {
						sResponse = UserManager.getInstance().editProfile(
								formParams);
						if (!sResponse.isEmpty() && sResponse != null) {
							return Response.status(200)
									.entity(new Gson().toJson(sResponse))
									.build();
						} else {
							return Response.status(200)
									.entity(new Gson().toJson("")).build();
						}
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return Response
				.status(200)
				.entity(new Gson().toJson(ServiceResponseManager.getInstance()
						.readServiceResponse(401))).build();
	}

	@POST
	@Path("/verifypassword")
	public Response verifyPasswordService(@FormParam("userid") long userid,
			@FormParam("password") String password) {
		try {
			return Response
					.status(200)
					.entity(new Gson()
							.toJson(UserManager.getInstance().verifyPassword(
									userid, password) ? ServiceResponseManager
									.getInstance().readServiceResponse(200)
									: ServiceResponseManager.getInstance()
											.readServiceResponse(215))).build();
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(new Gson().toJson("")).build();
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Path("/createuserinvites")
	public Response createuserInvites(
			MultivaluedMap<String, String> inviteParams) throws QpekaException {
		/* if (!inviteParams.isEmpty() && inviteParams != null) { */
		long sessionid = 0;
		long userid = 0;
		if (inviteParams.get("sessionid") != null
				&& inviteParams.get("userid") != null) {
			sessionid = Long.parseLong(inviteParams.get("sessionid").iterator()
					.next());
			userid = Long.parseLong(inviteParams.get("userid").iterator()
					.next());

			if (SessionsManager.getInstance().ActiveSessionExist(sessionid,
					userid)) {
				return Response
						.status(200)
						.entity(new Gson().toJson(UserInvitesManager
								.getInstance().createUserInvites(userid,
										inviteParams))).build();
			}
		}
		return Response
				.status(200)
				.entity(new Gson().toJson(ServiceResponseManager.getInstance()
						.readServiceResponse(401))).build();

	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Path("/userinvitesent")
	public Response userInviteSent(MultivaluedMap<String, String> hashvalues)
			throws QpekaException {
		/* if (!hashvalues.isEmpty() && hashvalues != null) { */
		long sessionid = 0;
		long userid = 0;
		if (hashvalues.get("sessionid") != null
				&& hashvalues.get("userid") != null) {
			sessionid = Long.parseLong(hashvalues.get("sessionid").iterator()
					.next());
			userid = Long.parseLong(hashvalues.get("userid").iterator().next());

			if (SessionsManager.getInstance().ActiveSessionExist(sessionid,
					userid)) {
				return Response
						.status(200)
						.entity(new Gson().toJson(UserInvitesManager
								.getInstance().setInviteSent(
										hashvalues.get("hashvalues")))).build();
			}
		}
		return Response
				.status(200)
				.entity(new Gson().toJson(ServiceResponseManager.getInstance()
						.readServiceResponse(401))).build();
	}

	@POST
	@Path("/userinviteaccept")
	public Response userInviteAccept(@FormParam("hashvalue") String hashvalues,
			@FormParam("sessionid") long sessionid,
			@FormParam("userid") long userid) throws QpekaException {

		if (SessionsManager.getInstance().ActiveSessionExist(sessionid, userid)) {
			return Response
					.status(200)
					.entity(new Gson().toJson(UserInvitesManager.getInstance()
							.setInviteAccepted(hashvalues))).build();
		} else {
			return Response
					.status(200)
					.entity(new Gson().toJson(ServiceResponseManager
							.getInstance().readServiceResponse(401))).build();
		}
	}

}

// TODO WS for each param of edit profile
// TODO ws FOR SERVICE PROVIDERS

