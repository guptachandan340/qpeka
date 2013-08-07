package com.qpeka.services.user;

import java.util.Date;
import java.util.HashMap;

import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.user.User;
import com.qpeka.db.user.profile.Name;
import com.qpeka.db.user.profile.UserProfile;
import com.qpeka.managers.user.UserManager;

@Path("/user")
public class UserService {

	//http://docs.oracle.com/javaee/6/tutorial/doc/gilik.html
	
	@POST
	@Path("/login")
	public Response loginService(@FormParam("username") String username,
			@FormParam("password") String password, @FormParam("isEmail") boolean isEmail) {
		User user = null;
		String response = null;
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
			Gson gson = new Gson();
			response = gson.toJson(user);
		}
		return Response.status(200).entity(response).build();
	}

	@POST
	@Path("/logout")
	// pass user id  or user session object.
	public Response logoutService(long lastaccess) {
		User user = new User();
		user.setUserid(1);
		short counter = 0;
		counter = UserManager.getInstance().updateLastLogin(lastaccess, user.getUserid(), false);
		if(counter > 0) {
			// or return true;
			return Response.status(200).entity("successfully updated").build();
		}
		return null;
	}

	@POST
	@Path("/registerpost")
	@Consumes("application/x-www-form-urlencoded")
	public Response registerpost(MultivaluedMap<String, String> formParams) {
		User user = null;
		String response = null;
		for (String keys : formParams.keySet()) {
			if (keys.equalsIgnoreCase(User.EMAIL)) {
				for (String email : formParams.get(keys)) {
					try {
						if (!UserManager.getInstance().emailExists(email)) {
							user = UserManager.getInstance().registerUser(
									formParams);
						} else {
							// TODO set error code
							response = "Already Available in our System, Try with other";
							Response.status(200)
									.entity(response)
									.build();
						}
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if (user != null) {
			Gson gson = new Gson();
			response = gson.toJson(user);
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
		JSONObject response = null;
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
		if(changedPassword != null) {
			 response = JSONObject.fromObject(changedPassword);
		}
		return Response.status(200).entity(response).build();
	}
	
	/*
	@POST
	@Path("/confirmpassword")
	public Response cofirmPasswordService(@FormParam("password") String password, @FormParam("confirmpassword") String confirmPassword) {
		String response = null;
		if(password.length() < 8) {
			response = "Minimum 8 characters are allowed for password";
		} else {
			if(!password.equals(confirmPassword)) {
				response = "Password doesnot matches";
			}
			else {
				response = "Correct password";
			}
		}
		return Response.status(200).entity(response).build();
	}
	*/
	
	@POST
	@Path("/changepassword")
	public Response changePasswordService(
			@FormParam("currentpassword") String currentPassword,
			@FormParam("newpassword") String newPassword) {
		User user = new User();
		// TODO This user object will be passed from session
		user.setUserid((long) 5);
		user.setUsername("ankita");
		user.setPassword("$2a$10$GBtjQbLlMKkeZKBTjLBvBuDr6IG8i2NCaJXE96PylSmtUdqift0jS");
		user.setEmail("anki546.malani@gmail.com");
		user.setCreated(1375033767);
		user.setLastaccess(0);
		user.setLastlogin(0);
		user.setStatus((short) 0);
		user.setType((short) 0);
		user.setTimezone("East");
		 JSONObject response = null;
				try {
					user = UserManager.getInstance().changePassword(user,currentPassword, newPassword);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (user != null) {
					response = JSONObject.fromObject(user);
				}
		return Response.status(200).entity(response).build();
	}
}

