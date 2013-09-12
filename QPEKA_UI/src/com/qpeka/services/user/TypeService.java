package com.qpeka.services.user;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.qpeka.db.user.profile.Type;
import com.qpeka.managers.TypeManager;

@Path("/user/type")
public class TypeService {
	
	final Logger logger = Logger.getLogger(TypeService.class);
	
	@GET
	@Path("/readtype")
	public Response retrievingTypeService() {
		List<Type> type = TypeManager.getInstance().readType();
		if (!type.isEmpty() && type != null) {
			return Response.status(200).entity(new Gson().toJson(type)).build();
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Empty User Type/ Not available");
			}
			return Response.status(200).entity(new Gson().toJson("")).build();
		}
	}
   
	// Retrieve UserType By ID
	@GET
	@Path("/readtypebyid")
	public Response retrievingTypeService(@QueryParam("typeid") String typeid) {
		Type type = TypeManager.getInstance().readType(
					Short.parseShort(typeid));
		if (type != null) {
			return Response.status(200).entity(new Gson().toJson(type)).build();
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Empty User Type/ Not available");
			}
			return Response.status(200).entity(new Gson().toJson("")).build();
		}
	}
}
