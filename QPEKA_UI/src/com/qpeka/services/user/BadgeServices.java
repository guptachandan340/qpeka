package com.qpeka.services.user;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.db.Badges;
import com.qpeka.managers.BadgesManager;

@Path("/badges")
public class BadgeServices {

	@POST
	@Path("/readbadges")
	public Response retrievingBadgeService() {
		List<Badges> badges = new ArrayList<Badges>();
		badges = BadgesManager.getInstance().readBadges();
		Gson gson = new Gson();
		String response = gson.toJson(badges);
		return Response.status(200).entity(response).build();
	}
}
