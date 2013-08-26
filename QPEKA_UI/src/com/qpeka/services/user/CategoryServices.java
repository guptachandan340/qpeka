package com.qpeka.services.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.managers.CategoryManager;

@Path("/user/category")
public class CategoryServices {

	@POST
	@Path("/readcategorybytype")
	public Response retrievingCategoryService(@FormParam("categorytype") String categoryType) {
		Map<Short, Map.Entry<String, String>> category = new HashMap<Short, Map.Entry<String, String>>();
		String  response= null;
		category = CategoryManager.getInstance().readCategory(categoryType);
		if(!category.isEmpty() && category != null) {
			Gson gson = new Gson();
			response = gson.toJson(category);
		}
		return Response.status(200).entity(response).build();
	}
	
	@POST
	@Path("/readAllcategory")
	public Response readAllCategoryService() {
		Set<String> category = new HashSet<String>();
		String  response= null;
		category = CategoryManager.getInstance().readCategory();
		if(!category.isEmpty() && category != null) {
			Gson gson = new Gson();
			response = gson.toJson(category);
		}
		return Response.status(200).entity(response).build();
	}
}
