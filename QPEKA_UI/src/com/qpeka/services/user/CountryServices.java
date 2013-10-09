package com.qpeka.services.user;

import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.qpeka.managers.CountryManager;
import com.qpeka.managers.SessionsManager;

@Path("/user/country")
public class CountryServices {

	final Logger logger = Logger.getLogger(LanguageServices.class);

	@GET
	@Path("/readcountries")
	public Response retrievingCountryService() {
		Map<String, String> country = CountryManager.getInstance()
				.retrieveCountry();
		if (!country.isEmpty() && country != null) {
			return Response.status(200).entity(new Gson().toJson(country))
					.build();
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Empty Country list");
			}
			return Response.status(200).entity(new Gson().toJson("")).build();
		}
	}
}

