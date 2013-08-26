package com.qpeka.services.user;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.qpeka.db.user.profile.Type;
import com.qpeka.managers.TypeManager;

@Path("/user/type")
public class TypeService {
	
	// Retrieve all row of UserType
	@GET
	@Path("/readtype")
	public Response retrievingTypeService() {
		String response = null;
		List<Type> type = TypeManager.getInstance().readType();
		if (!type.isEmpty() && type != null) {
			Gson gson = new Gson();
			response = gson.toJson(type);
		} else
			response = "usertypes is not avilable";
		return Response.status(200).entity(response).build();
	}
   
	// Retrieve UserType By ID
	@GET
	@Path("/readtypebyid")
	public Response retrievingTypeService(@QueryParam("typeid") String typeid) {
		String response = null;
		if (typeid.isEmpty()) {
			response = "don't enter null value";
		} else {
			Type type = TypeManager.getInstance().readType(
					Short.parseShort(typeid));
			if (type != null) {
				Gson gson = new Gson();
				response = gson.toJson(type);
			} else
				response = "usertype is not avilable";
		}
		return Response.status(200).entity(response).build();

	}

}
