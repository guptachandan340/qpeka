package com.qpeka.services.user;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.managers.LanguagesManager;

@Path("/user")
public class LanguageServices {

	@GET
	@Path("/readlanguages")
	public Response retrievingLanguageService() {
		Map<Short, String> languages = new HashMap<Short, String>();
		String response = null;
		languages = LanguagesManager.getInstance().retrieveLangugage();
		if(!languages.isEmpty() && languages != null) {
			Gson gson = new Gson();
			response = gson.toJson(languages);
		}
		return Response.status(200).entity(response).build();
	}
}
