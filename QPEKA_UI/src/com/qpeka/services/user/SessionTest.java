package com.qpeka.services.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

@Path("/test")
public class SessionTest {

	@POST
	@Path("/session")
	@Consumes("application/x-www-form-urlencoded")
	public void usercreation(MultivaluedMap<String, String> userid, @Context HttpServletRequest request) {
		String a = request.getParameter("userid");
		System.out.println(a);
		HttpSession session = request.getSession();
		
		if(session.isNew()) {
		
		} else {
	
		}
	}
}