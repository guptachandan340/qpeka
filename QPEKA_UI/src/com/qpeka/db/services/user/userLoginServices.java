package com.qpeka.db.services.user;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.user.User;
import com.qpeka.managers.user.UserManager;
 
@Path("/users")
public class userLoginServices {
 
	@POST
	@Path("/login")
	public Response addUser(
		@FormParam("uname") String username,
		@FormParam("pwd") String password) {
		boolean flag;
		User user = null;
		if(username.indexOf("@")!= -1) {
			flag = true;
		} else {
			flag = false;
		}
		try {
			user = UserManager.getInstance().authenticateUser(username, password,flag);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		Gson gson = new Gson();
		gson.toJson(user);
		
		return Response.status(200)
			.entity("addUser is called, name : " + username + ", pwd : " + password)
			.build();
 
	}
 
}