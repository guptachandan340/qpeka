package com.qpeka.services.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.collections4.map.MultiValueMap;

import com.google.gson.Gson;
import com.qpeka.db.exceptions.FileException;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.exceptions.user.UserProfileException;
import com.qpeka.db.user.User;
import com.qpeka.managers.ServiceErrorManager;
import com.qpeka.managers.user.UserManager;
import com.qpeka.services.Errors.ServiceError;

@Path("user")
public class UserService {
	
	@POST
	@Path("/login")
	public Response loginService(@FormParam("username") String username,
			@FormParam("password") String password, @FormParam("isEmail") boolean isEmail) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			response = UserManager.getInstance().authenticateUser(username,
					password, isEmail);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(new Gson().toJson(response)).build();
	}

	@POST
	@Path("/logout")
	public Response logoutService(@FormParam("userid") long userid) {
		List<ServiceError> response = null;
		response = UserManager.getInstance().updateLastActivity(userid, false);
		return Response.status(200).entity(new Gson().toJson(response)).build();
	}

	@POST
	@Path("/signup")
	@Consumes("application/x-www-form-urlencoded")
	public Response signupService(MultivaluedMap<String, String> formParams) {
		List<ServiceError> response = null;
		for (String keys : formParams.keySet()) {
			if (keys.equalsIgnoreCase(User.EMAIL)) {
				for (String email : formParams.get(keys)) {
					try {
						if (!UserManager.getInstance().userExists(email,true)) {
							response = UserManager.getInstance().registerUser(
									formParams);
						} else {
							response = ServiceErrorManager.getInstance().readBadges(34);
						}
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return Response.status(200).entity(new Gson().toJson(response)).build();
	}
	
	@POST
	@Path("/resetpwd")
	public Response resetPwdService(@FormParam("authname") String authName) {
		boolean isEmail = false;;
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
		if (changedPassword != null) {
			return Response.status(200)
					.entity(new Gson().toJson(changedPassword)).build();
		} else {
			return Response
					.status(200)
					.entity(new Gson().toJson(ServiceErrorManager.getInstance()
							.readBadges(215))).build();
		}
	}

	@POST
	@Path("/changepwd")
	public Response changePwdService(@FormParam("userid") long userid, @FormParam("currentpassword") String currentPassword,
			@FormParam("newpassword") String newPassword) {
		List<ServiceError> response = new ArrayList<ServiceError>();
		try {
			response = UserManager.getInstance().changePassword(userid,currentPassword, newPassword);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(response).build();
	}
	
	@POST
	@Path("/getProfile")
	public Response getProfileService(@FormParam("userid") long userid) {
		MultiValueMap<String, Object> response = new MultiValueMap<String, Object>();
		try {
			response = UserManager.getInstance().getProfile(userid);
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!response.isEmpty()) {
			return Response.status(200).entity(new Gson().toJson(response)).build();
		} else {
			return Response.status(200).entity("").build();
		}		
	}
	
	@POST
	@Path("/editprofile")
	@Consumes("application/x-www-form-urlencoded")
	
	public Response editProfileService(MultivaluedMap<String, String> formParams) {
		 List<ServiceError> sError = null;
		 String response = null;
		 try {
			   sError = UserManager.getInstance().editProfile(formParams);
			   if(!sError.isEmpty() && sError != null) {
				   response = (new Gson()).toJson(sError);
			   } else 
				   response = "hello";
		} catch (FileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(response).build();
	}               
	

// TODO WS for each param of edit profile
// TODO ws FOR SERVICE PROVIDERS

}

