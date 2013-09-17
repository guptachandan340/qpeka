package com.qpeka.services.user;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.qpeka.db.Badges;
import com.qpeka.managers.BadgesManager;

@Path("/user/badges")
public class BadgeServices {

	final Logger logger = Logger
			.getLogger(BadgeServices.class);
	
	@POST
	@Path("/readbadges")
	public Response retrievingBadgeService() {
		List<Badges> badges = BadgesManager.getInstance().readBadges();
		
		if(!badges.isEmpty() && badges != null) {
			return Response.status(200).entity(new Gson().toJson(badges)).build();
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Empty Badges");
			}
			return Response.status(200).entity(new Gson().toJson("")).build();
		}
	}
}
