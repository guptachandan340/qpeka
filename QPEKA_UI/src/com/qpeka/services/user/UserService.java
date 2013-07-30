package com.qpeka.services.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.db.Badges;
import com.qpeka.db.Category;
import com.qpeka.db.Country;
import com.qpeka.db.Files;
import com.qpeka.db.Languages;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.handler.FilesHandler;
import com.qpeka.db.user.User;
import com.qpeka.managers.BadgesManager;
import com.qpeka.managers.CategoryManager;
import com.qpeka.managers.CountryManager;
import com.qpeka.managers.FilesManager;
import com.qpeka.managers.LanguagesManager;
import com.qpeka.managers.user.UserManager;

@Path("/user")
public class UserService {

	@POST
	@Path("/login")
	public Response loginService(@FormParam("username") String username,
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
	public Response registerService(@FormParam("firstname") String firstName,
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
	
	@POST
	@Path("/retrievingfiles")
	public Response retrievingFileService() {
		List<Files> files = new ArrayList<Files>();
		files = FilesManager.getInstance().readFiles();
		Gson gson = new Gson();
		String response = gson.toJson(files);
		return Response.status(200).entity(response).build();
	}
	
	@POST
	@Path("/retrievinglanguages")
	public Response retrievingLanguageService() {
		List<Languages> languages = new ArrayList<Languages>();
		languages = LanguagesManager.getInstance().readLanguages();
		Gson gson = new Gson();
		String response = gson.toJson(languages);
		return Response.status(200).entity(response).build();
	}
	
	@POST
	@Path("/retrievingcategory")
	public Response retrievingCategoryService() {
		List<Category> category = new ArrayList<Category>();
		category = CategoryManager.getInstance().readCategory();
		Gson gson = new Gson();
		String response = gson.toJson(category);
		return Response.status(200).entity(response).build();
	}
	
	@POST
	@Path("/retrievingcountry")
	public Response retrievingCountryService() {
		List<Country> country = new ArrayList<Country>();
		country = CountryManager.getInstance().RetrieveCountry();
		Gson gson = new Gson();
		String response = gson.toJson(country);
		return Response.status(200).entity(response).build();
	}
	
	@POST
	@Path("/retrievingbadges")
	public Response retrievingBadgeService() {
		List<Badges> badges = new ArrayList<Badges>();
		badges = BadgesManager.getInstance().readBadges();
		Gson gson = new Gson();
		String response = gson.toJson(badges);
		return Response.status(200).entity(response).build();
	}
	
}

