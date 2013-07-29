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
	public Response loginUser(@FormParam("uname") String username,
			@FormParam("pwd") String password) {
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
			System.out.println(user.toString());
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
	public Response registerUser(@FormParam("firstname") String firstName,
			@FormParam("lastname") String lastName,
			@FormParam("email") String email,
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("gender") String gender, @FormParam("dob") Date dob,
			@FormParam("nationality") String nationality) {
		
		//Ask for emailexist, username exist and confirm passwor criteria
		User user = null;
		user = UserManager.getInstance().registerUser(firstName,
						lastName, email, username, password, gender, dob,
						nationality);

		Gson gson = new Gson();
		String json = gson.toJson(user);

		return Response.status(200).entity(json).build();

	}

	/*
	@POST
	@Path("/verifyEmailExist")
	public Response VerifyEmail(@FormParam("email") String email) {
		boolean emailStatus = true;
		if (email.indexOf("@") != -1) {
			try {
				emailStatus = UserManager.getInstance().emailExists(email);
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!emailStatus) {
				return Response.status(200).entity("available").build();
			}
			return Response.status(200)
					.entity("Already Available, Try with other").build();

		}
		return null;
	}
*/
}