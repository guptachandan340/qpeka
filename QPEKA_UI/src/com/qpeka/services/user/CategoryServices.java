package com.qpeka.services.user;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.managers.CategoryManager;

@Path("/category")
public class CategoryServices {

	@POST
	@Path("/readcategorybytype")
	public Response retrievingCategoryService(@FormParam("categorytype") String categoryType) {
		Map<Short, Map.Entry<String, String>> category = new HashMap<Short, Map.Entry<String, String>>();
		String  response= null;
		category = CategoryManager.getInstance().readCategory(categoryType);
		if(category != null) {
			Gson gson = new Gson();
			response = gson.toJson(category);
		}
		return Response.status(200).entity(response).build();
	}

}
