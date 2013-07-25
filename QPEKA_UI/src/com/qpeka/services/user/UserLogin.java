package com.qpeka.services.user;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.user.User;
import com.qpeka.managers.user.UserManager;


@Path("user")
public class UserLogin {
 
	@POST
	@Path("/login")
	public Response userlogin(
		@FormParam("username") String username,
		@FormParam("password") String password) {
		User userDetails = null;
		try {
		    userDetails = UserManager.getInstance().authenticateUser(username, password, false);
		    System.out.println(userDetails);
			//User user1 = usermanager.authenticateUser(username, password, true);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(userDetails != null) {
		//	JSONObject jsonobj = new JSONObject();
			
		//JSONObject.fromObject((Object)userDetails);
		return Response.status(200).entity(userDetails).build();
		} else {
			return Response.status(200).entity("It is Wrong").build();
		}
		}
}
