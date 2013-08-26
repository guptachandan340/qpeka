package com.qpeka.services.user;

import java.util.HashMap;
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
import com.qpeka.db.user.profile.UserProfile;
import com.qpeka.managers.user.UserManager;

@Path("user")
public class UserService {
	
	@POST
	@Path("/login")
	public Response loginService(@FormParam("username") String username,
			@FormParam("password") String password, @FormParam("isEmail") boolean isEmail) {
		
		Map<String, Object> user = new HashMap<String, Object>();
		String response = null;
		Gson gson = new Gson();
		Object error = null;
		try {
			user = UserManager.getInstance().authenticateUser(username,
					password, isEmail);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!user.isEmpty() && user != null) {
			response = gson.toJson(user);
		} else {
				error = "error : 34";
				response = gson.toJson(error);
		}
		return Response.status(200).entity(response).build();
	}

	@POST
	@Path("/logout")
	public Response logoutService(@FormParam("userid") long userid) {
		short counter = 0;
		Object error = null;
		String response = null;
		Gson gson = new Gson();
		counter = UserManager.getInstance().updateLastActivity(userid, false);
		if(counter > 0) {
			error = "success : 200";
			response = gson.toJson(error);
			
		} else {
			error = "error : 215";
			response = gson.toJson(error);
		}
		return Response.status(200).entity(response).build();
	}

	@POST
	@Path("/signup")
	@Consumes("application/x-www-form-urlencoded")
	public Response signupService(MultivaluedMap<String, String> formParams) {
		User user = null;
		String response = null;
		Map<String, Object> serviceResult = new HashMap<String, Object>();
		Gson gson = new Gson();
		for (String keys : formParams.keySet()) {
			if (keys.equalsIgnoreCase(User.EMAIL)) {
				for (String email : formParams.get(keys)) {
					try {
						if (!UserManager.getInstance().userExists(email,true)) {
							user = UserManager.getInstance().registerUser(
									formParams);
							if (user != null) {
								serviceResult.put("success", "200");
								response = gson.toJson(serviceResult);
							} else {
								serviceResult.put("error", "215");
								response = gson.toJson(serviceResult);
							}
						} else {
							serviceResult.put("error", "34");
							response = gson.toJson(serviceResult);
//							return Response.status(200).entity(response).build();
						}
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		/*if (user != null) {
			serviceResult.put("success", "200");
			response = gson.toJson(serviceResult);
		} else {
			serviceResult.put("error", "215");
			response = gson.toJson(serviceResult);
		}*/
		if(response == null) {
			serviceResult.put("error", "500");
			response = gson.toJson(serviceResult);
		}
		return Response.status(200).entity(response).build();
	}
	
	/*
	 * // This will be used for edit profile service
	@POST
	@Path("/verifyusernameexist")
	public Response verifyUserNameService(@FormParam("username") String userName) {
		// boolean emailStatus = true;
		String response= null;
		try {
			if (UserManager.getInstance().usernameExists(userName)) {
				response = "Already Available in our System, Try with other";
			} else {
				response = "Available for you";
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(response).build();
	}
	*/
	
	@POST
	@Path("/resetpwd")
	public Response resetPwdService(@FormParam("authname") String authName) {
		Gson gson = new Gson();
		String response = null;
		boolean isEmail = false;;
		String changedPassword = null;
		Object error = null;
		if (authName.indexOf("@") != -1) {
			isEmail = true;
		}
		try {
			changedPassword = UserManager.getInstance().resetPassword(authName, isEmail);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(changedPassword != null) {
				response = gson.toJson(changedPassword);
		} else {
			error = "error : 215";
			response = gson.toJson(error);
		}
		return Response.status(200).entity(response).build();
	}
	
	
	@POST
	@Path("/changepwd")
	public Response changePwdService(@FormParam("userid") long userid, @FormParam("currentpassword") String currentPassword,
			@FormParam("newpassword") String newPassword) {
		Gson gson = new Gson();
		Object user = null;
		String response = null;
		try {
			user = UserManager.getInstance().changePassword(userid,currentPassword, newPassword);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null) {
			response = gson.toJson(user);
		}
		return Response.status(200).entity(response).build();
	}
	
	
	@POST
	@Path("/getProfile")
	public Response getProfileService(@FormParam("userid") long userid) {
		String response = null;
		MultiValueMap<String, Object> userprofile = new MultiValueMap<String, Object>();
		try {
			userprofile = UserManager.getInstance().getProfile(userid);
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!userprofile.isEmpty()) {
			response = (new Gson()).toJson(userprofile);
		} else {
			response = "";
		}
		
		return Response.status(200).entity(response).build();
		
	}
	
	@POST
	@Path("/editprofile")
	@Consumes("application/x-www-form-urlencoded")
	public Response editProfileService(MultivaluedMap<String, String> formParams) {
		 String response = null;
		 Gson gson = new Gson();
		 try {
			response = UserManager.getInstance().editProfile(formParams);
		} catch (FileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(gson.toJson(response)).build();
	}               
	

// TODO WS for each param of edit profile
// TODO ws FOR SERVICE PROVIDERS

}

