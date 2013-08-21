package com.qpeka.services.user;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.managers.CountryManager;

@Path("/user")
public class CountryServices {


	@GET
	@Path("/readcountries")
	public Response retrievingCountryService() {
		Map<String, String> country = new HashMap<String, String>();
		String response = null;
		country = CountryManager.getInstance().retrieveCountry();
		if(!country.isEmpty() && country != null) {
			Gson gson = new Gson();
			response = gson.toJson(country);
		}
		return Response.status(200).entity(response).build();
	}
}
