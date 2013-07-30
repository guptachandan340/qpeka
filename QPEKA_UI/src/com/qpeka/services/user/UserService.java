package com.qpeka.services.user;

import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.user.User;
import com.qpeka.managers.user.UserManager;

@Path("/user")
public class UserService {

	@POST
	@Path("/login")
	public Response loginUserService(@FormParam("username") String username,
			@FormParam("password") String password) {
		boolean flag;
		User user = null;
		if (username.indexOf("@") != -1) {
			flag = true;
		} else {
			flag = false;
		}
		try {
			user = UserManager.getInstance().authenticateUser(username,
					password, flag);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();
		String json = gson.toJson(user);

		return Response.status(200).entity(json).build();

	}

	@POST
	@Path("/register")
	public Response registerUserService(@FormParam("firstname") String firstName,
			@FormParam("lastname") String lastName,
			@FormParam("email") String email,
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("gender") String gender, @FormParam("dob") Date dob,
			@FormParam("nationality") String nationality) {

		// Ask for emailexist, username exist and confirm password criteria
		User user = null;
		user = UserManager.getInstance().registerUser(firstName, lastName,
				email, username, password, gender, dob, nationality);

		Gson gson = new Gson();
		String json = gson.toJson(user);
		return Response.status(200).entity(json).build();

	}

	
	@POST
	@Path("/verifyemailexist")
	public Response verifyEmailService(@FormParam("email") String email) {
		// boolean emailStatus = true;
		String response= null;
		System.out.println(email);
		if (email.indexOf("@") != -1) {
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
		System.out.println(response);
		 } else {
			 response = "Enter Email Id Properly";
		}
		return Response.status(200).entity(response).build();
	}
	
	
	@POST
	@Path("/verifyusernameexist")
	public Response verifyUserNameService(@FormParam("username") String userName) {
		// boolean emailStatus = true;
		String response= null;
		System.out.println(userName);
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
		System.out.println(response);
		return Response.status(200).entity(response).build();
	}
	
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
}

