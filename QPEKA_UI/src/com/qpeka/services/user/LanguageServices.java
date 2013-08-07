package com.qpeka.services.user;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.qpeka.managers.LanguagesManager;
import com.sun.jersey.api.client.ClientResponse;

@Path("/languages")
public class LanguageServices {

//	@SuppressWarnings("unchecked")
	@GET
	@Path("/readlanguages")
	public Response retrievingLanguageService() {
		
		Map<Short, String> languages = new HashMap<Short, String>();
		String response = null;
		languages = LanguagesManager.getInstance().retrieveLangugage();
		if(languages != null) {
			Gson gson = new Gson();
			response = gson.toJson(languages);
			//response = JSONObject.fromObject(gson.toJson(languages));
			/*response = resource.type(MediaType.APPLICATION_XML).put(ClientResponse.class, b1); //consume
			or
			response = resource.accept(MediaType.APPLICATION_XML).put(ClientResponse.class, b1); //produce
			 */
//			ClientResponse response1 = resource.type(MediaType.APPLICATION_XML).put(ClientResponse.class, response);
		}
		
		return Response.status(200).entity(response).build();
	}
}
