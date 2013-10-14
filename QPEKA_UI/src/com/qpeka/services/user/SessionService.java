package com.qpeka.services.user;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.managers.SessionsManager;

@Path("/user/session")
public class SessionService {

	@POST
	@Path("/sessionstate")
	public Response sessionService(@FormParam("sessionstate") String sessionState) {
		if (sessionState != null) {
			return Response
					.status(200)
					.entity(new Gson().toJson(SessionsManager.getInstance()
							.setSessionStatus(sessionState))).build();
		} else {
			return Response.status(200)
					.entity(new Gson().toJson("Please select any value"))
					.build();
		}
	}
}


