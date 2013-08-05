package com.qpeka.services.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.db.Badges;
import com.qpeka.db.Files;
import com.qpeka.db.Languages;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.user.User;
import com.qpeka.db.user.profile.Name;
import com.qpeka.db.user.profile.UserProfile;
import com.qpeka.managers.BadgesManager;
import com.qpeka.managers.CategoryManager;
import com.qpeka.managers.CountryManager;
import com.qpeka.managers.FilesManager;
import com.qpeka.managers.LanguagesManager;
import com.qpeka.managers.user.UserManager;

@Path("/user")
public class UserService {

	@Context
	private HttpServletRequest request =null;
	
	@POST
	@Path("/login")
	public Response loginService(@FormParam("username") String username,
			@FormParam("password") String password) {
		boolean isEmail = false;
		User user = null;
		String response = null;
		if (username.indexOf("@") != -1) {
			isEmail = true;
		}
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
	@Path("/register")
	public Response registerService(@FormParam("firstname") String firstName,
			@FormParam("lastname") String lastName,
			@FormParam("email") String email,
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("gender") String gender, 
			@FormParam("dob") String dob,
			@FormParam("languages") String languages) {
		
		
		String str = null;
		str = request.getParameter("languages");
		// emailexist verification and password existence
		User user = null;
		String response = null;
		List<String> languageList = new ArrayList<String>();
		languageList = Arrays.asList(languages.split(","));
		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date dateOfBirth = null;
		try {
			dateOfBirth = (Date)formatter.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (email.indexOf("@") != -1) {
			user = UserManager.getInstance().registerUser(firstName, lastName,
					email, username, password, gender, dateOfBirth, languageList);
			if (user != null) {
				Gson gson = new Gson();
				response = gson.toJson(user);
			}
		}
		return Response.status(200).entity(response).build();
	}

	@POST
	@Path("/verifyemailexist")
	public Response verifyEmailService(@FormParam("email") String email) {
		// boolean emailStatus = true;
		String response = null;
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
		String response = null;
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
			Gson gson = new Gson();
			response = gson.toJson(changedPassword);
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
	
	@POST
	@Path("/editprofile")
	public Response editProfileService(@FormParam("firstname") String firstName,
			@FormParam("lastname") String lastName,
			@FormParam("email") String email,
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("gender") String gender, @FormParam("dob") Date dob,
			@FormParam("nationality") String nationality) {
		
		Map <String, Object> profile = new HashMap<String, Object>();
		System.out.println(dob);
		User user = new User();
		UserProfile userprofile = new UserProfile();
        UserProfile userprofile1 =null;
		Name name = new Name();
		profile.put(user.PROFILEID, (long)2);
		profile.put(name.FIRSTNAME ,firstName );
		profile.put(name.LASTNAME ,lastName);
		profile.put(user.EMAIL,email);
	    profile.put(user.USERNAME,username );
	    profile.put(user.PASSWORD ,password );
	    profile.put(userprofile.GENDER,gender);
	    
		//profile.put(userprofile.DOB,dob);
	    //profile.put(userprofile.NATIONALITY,nationality);
	    
	    userprofile1 = UserManager.getInstance().editProfile(profile);
	    
	    Gson gson = new Gson();
		String json = gson.toJson(userprofile1);
		return Response.status(200).entity(json).build();  
		    
	}
}

