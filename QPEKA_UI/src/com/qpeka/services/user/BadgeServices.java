package com.qpeka.services.user;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.db.Badges;
import com.qpeka.managers.BadgesManager;

@Path("/user/badges")
public class BadgeServices {

	@POST
	@Path("/readbadges")
	public Response retrievingBadgeService() {
		List<Badges> badges = null;
		badges = BadgesManager.getInstance().readBadges();
		if(!badges.isEmpty() && badges != null) {
			return Response.status(200).entity(new Gson().toJson(badges)).build();
		}
		return Response.status(200).entity("empty" +
				"").build();
	}
}
