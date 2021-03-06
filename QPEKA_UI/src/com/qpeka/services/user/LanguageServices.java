package com.qpeka.services.user;

import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.qpeka.managers.LanguagesManager;

@Path("/user/languages")
public class LanguageServices {

	final Logger logger = Logger.getLogger(LanguageServices.class);
	
	@GET
	@Path("/readlanguages")
	public Response retrievingLanguageService() {
		Map<Short, Object> languages = LanguagesManager.getInstance().retrieveLangugage();	

		if (!languages.isEmpty() && languages != null) {
			return Response.status(200).entity(new Gson().toJson(languages))
					.build();
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Empty Languages list");
			}
			return Response.status(200).entity(new Gson().toJson("")).build();
		}
	}
}
