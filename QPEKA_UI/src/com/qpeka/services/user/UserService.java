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
import com.qpeka.db.exceptions.FileException;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.user.User;
import com.qpeka.db.user.profile.Name;
import com.qpeka.db.user.profile.UserProfile;
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
		/* if (username.indexOf("@") != -1) {
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
		Object error = null;
		String response = null;
		Gson gson = new Gson();
		counter = UserManager.getInstance().updateLastLogin(lastaccess, userid, false);
		if(counter > 0) {
			error = "Success : 200";
			response = gson.toJson(error);
			
		} else {
			error = "Error : 215";
			response = gson.toJson(error);
		}
		return Response.status(200).entity(response).build();
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
	
	
	@POST
	@Path("/changepassword")
	public Response changePasswordService(@FormParam("userid") long userid, @FormParam("currentpassword") String currentPassword,
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
	
	/*
	@POST
	@Path("/editprofile")
	@Consumes("application/x-www-form-urlencoded")
	public Response editProfileService(MultivaluedMap <String, String> param) {
		Map<String, Object> profile =new HashMap<String, Object>();
		 UserProfile userprofile =null;
		 	 
		for(String keys : param.keySet()) {
			for(String keyvalues : param.get(keys)) {
				System.out.println("map key " + keys);
				System.out.println("map value " + keyvalues);
			
				if(keys.equalsIgnoreCase(UserProfile.USERID)){
						profile.put(UserProfile.USERID,keyvalues); 
				} else if(keys.equalsIgnoreCase(UserProfile.PENNAME)) {
						profile.put(UserProfile.PENNAME,keyvalues);
				} else if(keys.equalsIgnoreCase(Name.FIRSTNAME)) {
						profile.put(Name.FIRSTNAME,keyvalues);
				} else if(keys.equalsIgnoreCase(Name.MIDDLENAME)) {
						profile.put(Name.MIDDLENAME,keyvalues);
				} else if(keys.equalsIgnoreCase(Name.LASTNAME)) {
						profile.put(Name.LASTNAME,keyvalues);
				} else if(keys.equalsIgnoreCase(UserProfile.GENDER)) {
						profile.put(UserProfile.GENDER,keyvalues);
				} else if(keys.equalsIgnoreCase(UserProfile.DOB)) {
						profile.put(UserProfile.DOB,keyvalues);
				} else if(keys.equalsIgnoreCase(UserProfile.NATIONALITY)) {
						profile.put(UserProfile.NATIONALITY,keyvalues);
				} else if(keys.equalsIgnoreCase(UserProfile.WEBSITE)) {
						profile.put(UserProfile.WEBSITE,keyvalues);
				} else if(keys.equalsIgnoreCase(UserProfile.BIOGRAPHY)) {
						profile.put(UserProfile.BIOGRAPHY,keyvalues);
				} else if(keys.equalsIgnoreCase(UserProfile.PROFILEPIC)) {
						profile.put(UserProfile.PROFILEPIC,keyvalues);
				} else if(keys.equalsIgnoreCase(UserProfile.PENNAME)) {
					profile.put(UserProfile.PENNAME,keyvalues);
				} else if(keys.equalsIgnoreCase(UserProfile.PENNAME)) {
					profile.put(UserProfile.PENNAME,keyvalues);
				} else if(keys.equalsIgnoreCase(UserProfile.PENNAME)) {
					profile.put(UserProfile.PENNAME,keyvalues);
				} else if(keys.equalsIgnoreCase(UserProfile.PENNAME)) {
					profile.put(UserProfile.PENNAME,keyvalues);
				} else if(keys.equalsIgnoreCase(UserProfile.PENNAME)) {
					profile.put(UserProfile.PENNAME,keyvalues);
			
			}
			}
		}
		try {
			userprofile = UserManager.getInstance().editProfile(profile);
		} catch (FileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(userprofile);
		return Response.status(200).entity(json).build();  
	    
	} */                         
	

// TODO WS for each param of edit profile
// TODO ws FOR SERVICE PROVIDERS

}

