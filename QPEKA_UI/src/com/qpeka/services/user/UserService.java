package com.qpeka.services.user;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.user.User;
import com.qpeka.managers.user.UserManager;

@Path("/user")
public class UserService {
	
	@POST
	@Path("/login")
	public Response loginService(@FormParam("username") String username,
			@FormParam("password") String password, @FormParam("isEmail") boolean isEmail) {
		Map<String, Object> user = new HashMap<String, Object>();
		String response = null;
		Gson gson = new Gson();
		Object error = null;
		/*if (username.indexOf("@") != -1) {
			isEmail = true;
		}*/
		try {
			user = UserManager.getInstance().authenticateUser(username,
					password, isEmail);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null) {
			response = gson.toJson(user);
		} else {
				error = "Error : 34";
				response = gson.toJson(error);
		}
		return Response.status(200).entity(response).build();
	}

	@POST
	@Path("/logout")
	public Response logoutService(@FormParam("userid") long userid,@FormParam("lastaccess") long lastaccess) {
		short counter = 0;
		counter = UserManager.getInstance().updateLastLogin(lastaccess, userid, false);
		if(counter > 0) {
			// or return true;
			return Response.status(200).entity(true).build();
		}
		return null;
	}

	@POST
	@Path("/registerpost")
	@Consumes("application/x-www-form-urlencoded")
	public Response registerpost(MultivaluedMap<String, String> formParams) {
		User user = null;
		String response = null;
		Object error = null;
		Gson gson = new Gson();
		for (String keys : formParams.keySet()) {
			if (keys.equalsIgnoreCase(User.EMAIL)) {
				for (String email : formParams.get(keys)) {
					try {
						if (!UserManager.getInstance().emailExists(email)) {
							user = UserManager.getInstance().registerUser(
									formParams);
						} else {
							error = "Error : 34";
							response = gson.toJson(error);
							return Response.status(200).entity(response).build();
						}
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if (user != null) {
			response = gson.toJson(user);
		} else {
			error = "Error : 215";
			response = gson.toJson(error);
		}
		return Response.status(200).entity(response).build();
	}
	
	/*
	@POST
	@Path("/verifyemailexist")
	public Response verifyEmailService(@FormParam("email") String email) {
		String response = null;
			try {
				if (UserManager.getInstance().emailExists(email)) {
					response = "Already Available in our System, Try with other";
				} else {
					response = "Available for you";
				}
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else {
			response = "Enter Email Id Properly";
		}
		return Response.status(200).entity(response).build();
	}
	*/
	
	
	// This will be used for edit profile service
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
	
	@POST
	@Path("/resetpassword")
	public Response resetPasswordService(@FormParam("authname") String authName) {
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
			error = "Error : 215";
			response = gson.toJson(error);
		}
		return Response.status(200).entity(response).build();
	}
	
	/*
	@POST
	@Path("/changepassword")
	public Response changePasswordService(
			@FormParam("currentpassword") String currentPassword,
			@FormParam("newpassword") String newPassword) {
		User user = new User();Gson gson = new Gson();
		// TODO This user object will be passed from session
		user.setUserid((long) 27);
		//user.setUsername(" ");
		user.setPassword("$2a$10$2XDpu7jbvqa79JehxN4rUumKgYNt0Hccvt6Hsmsgc6lRpmVhehpI.");
		user.setEmail("jinalmashruwala@gmail.com");
		user.setCreated(1375958659);
		user.setLastaccess(0);
		user.setLastlogin(0);
		user.setStatus((short) 0);
		user.setType((short) 0);
		String response = null;
				try {
					user = UserManager.getInstance().changePassword(user,currentPassword, newPassword);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (user != null) {
						Gson gson = new Gson();
						response = gson.toJson(user);
					}
		return Response.status(200).entity(response).build();
	}
	*/
}

