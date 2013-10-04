package com.qpeka.services.user;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/user/session")
public class SessionService {

	@GET
	@Path("/status")
	public void sessionService() {
		System.out.println("hello");
	}
}

