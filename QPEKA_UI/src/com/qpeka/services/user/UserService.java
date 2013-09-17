package com.qpeka.services.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.db.exceptions.CountryException;
import com.qpeka.db.exceptions.FileException;
import com.qpeka.db.exceptions.GenreException;
import com.qpeka.db.exceptions.LanguagesException;
import com.qpeka.db.exceptions.user.AddressException;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.exceptions.user.UserInterestsException;
import com.qpeka.db.exceptions.user.UserLanguageException;
import com.qpeka.db.exceptions.user.UserProfileException;
import com.qpeka.db.user.User;
import com.qpeka.managers.user.UserManager;
import com.qpeka.services.Response.ServiceResponseManager;

@Path("user")
public class UserService {
	
	@POST
	@Path("/login")
	public Response loginService(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("isEmail") boolean isEmail) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			response = UserManager.getInstance()
					.authenticateUser(username, password, isEmail);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response
				.status(200)
				.entity(new Gson().toJson(response))
				.build();	
	}

	@POST
	@Path("/logout")
	public Response logoutService(@FormParam("userid") long userid) {
		try {
			// No need to set response for updateLastActivity
			// Reason : We shouldn't let the user that we are tracking their
			// lastaccess and lastlogin record.
			// TODO set seesion and based on that return response Object
			UserManager.getInstance().updateLastActivity(userid, false);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200)
				.entity(new Gson().toJson("successfully logged out")).build();
	}

	@POST
	@Path("/signup")
	@Consumes("application/x-www-form-urlencoded")
	public Response signupService(MultivaluedMap<String, String> formParams) throws UserException {
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
		return Response.status(200).entity(new Gson().toJson(sresponse)).build();
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
			changedPassword = UserManager.getInstance().resetPassword(authName, isEmail);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200)
				.entity(new Gson()
						.toJson(changedPassword != null ? changedPassword
								: ServiceResponseManager.getInstance()
										.readServiceResponse(215))).build();
	}

	@POST
	@Path("/changepwd")
	public Response changePwdService(@FormParam("userid") long userid,
			@FormParam("currentpassword") String currentPassword,
			@FormParam("newpassword") String newPassword) {

		Map<String, Object> response = new HashMap<String, Object>();
		try {
			response = UserManager.getInstance()
					.changePassword(userid, currentPassword, newPassword);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response 
				.status(200)
				.entity(new Gson().toJson(response))
				.build();
	}
	
	@POST
	@Path("/getprofile")
	public Response getProfileService(@FormParam("userid") long userid)
			throws AddressException, CountryException, UserInterestsException,
			GenreException, UserLanguageException, LanguagesException {
		try {
			return Response
					.status(200)
					.entity(new Gson().toJson(UserManager.getInstance()
							.getProfile(userid))).build();
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(new Gson().toJson("")).build();
	}
	
	@POST
	@Path("/viewownprofile")
	public Response viewOwnProfileService(@FormParam("userid") long userid){
		try {
			return Response
					.status(200)
					.entity(new Gson().toJson(UserManager.getInstance()
							.viewOwnProfile(userid))).build();
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(new Gson().toJson("")).build();
	}
	
	@POST
	@Path("/deleteuser")
	public Response deleteUserService(@FormParam("userid") long userid){
		try {
			return Response
					.status(200)
					.entity(new Gson().toJson(UserManager.getInstance()
							.deleteUser(userid))).build();
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(new Gson().toJson("")).build();
	}

	@POST
	@Path("/editprofile")
	@Consumes("application/x-www-form-urlencoded")
	public Response editBasicSocialProfileService(MultivaluedMap<String, String> formParams)
			throws FileException, NumberFormatException, CountryException {
		Map<String, Object> sResponse = null;
		try {
			 sResponse = UserManager.getInstance().editProfile(formParams);
			if (!sResponse.isEmpty() && sResponse != null) {
				return Response.status(200).entity(new Gson().toJson(sResponse)).build();
			} else {
					return Response.status(200).entity(new Gson().toJson("")).build();
		}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	@Path("/verifypassword")
	public Response verifyPasswordService(@FormParam("userid") long userid,
			@FormParam("password") String password) {
		try {
			return Response
					.status(200)
					.entity(new Gson().toJson(UserManager.getInstance()
							.verifyPassword(userid, password) ? ServiceResponseManager
							.getInstance().readServiceResponse(200)
							: ServiceResponseManager.getInstance()
									.readServiceResponse(215))).build();
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(new Gson().toJson("")).build();
	}
}

// TODO WS for each param of edit profile
// TODO ws FOR SERVICE PROVIDERS



