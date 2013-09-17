package com.qpeka.services.user;

import java.util.Map;
import java.util.Set;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.qpeka.db.Category;
import com.qpeka.managers.CategoryManager;

@Path("/user/category")
public class CategoryServices {

	final Logger logger = Logger
			.getLogger(CategoryServices.class);
	
	@POST
	@Path("/readcategorybytype")
	public Response retrievingCategoryService(@FormParam("categorytype") String categoryType) {
		Map<String, Object> category = CategoryManager
				.getInstance().readCategory(categoryType, Category.TYPE);
		if(!category.isEmpty() && category != null) {
			return Response.status(200).entity(new Gson().toJson(category)).build();
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Empty Category");
			}
			return Response.status(200).entity(new Gson().toJson("")).build();
		}
	}
	
	@GET
	@Path("/readAllcategory")
	public Response readAllCategoryService() {
		Set<String> category = CategoryManager.getInstance().readCategory();
		if(!category.isEmpty() && category != null) {
			return Response.status(200).entity(new Gson().toJson(category)).build();
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Empty Category");
			}
			return Response.status(200).entity(new Gson().toJson("")).build();
		}
	}
}
