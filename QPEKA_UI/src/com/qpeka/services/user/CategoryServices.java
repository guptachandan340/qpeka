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
import com.qpeka.db.Category;
import com.qpeka.managers.CategoryManager;

@Path("/user/category")
public class CategoryServices {

	@POST
	@Path("/readcategorybytype")
	public Response retrievingCategoryService(@FormParam("categorytype") String categoryType) {
		System.out.println("hiiii");
		Map<String, Object> category = new HashMap<String, Object>();
		category = CategoryManager.getInstance().readCategory(categoryType, Category.TYPE);
		if(!category.isEmpty() && category != null) {
			return Response.status(200).entity(new Gson().toJson(category)).build();
		}
		return Response.status(200).entity("").build();
	}
	
	@POST
	@Path("/readAllcategory")
	public Response readAllCategoryService() {
		Set<String> category = new HashSet<String>();
		category = CategoryManager.getInstance().readCategory();
		if(!category.isEmpty() && category != null) {
			return Response.status(200).entity(new Gson().toJson(category)).build();
		}
		return Response.status(200).entity("").build();
	}
}
