package com.qpeka.services.library;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.qpeka.db.book.store.WorksHandler;
import com.qpeka.db.book.store.tuples.Work;
import com.qpeka.db.exceptions.user.UserProfileException;
import com.qpeka.db.handler.user.UserProfileHandler;
import com.qpeka.db.user.profile.Name;
import com.qpeka.db.user.profile.UserProfile;
import com.qpeka.utils.Utils;

@Path("library/books")
public class BooksService {

	@GET
	@Path("{bookid}")
	public Response getBookDesc(@PathParam("bookid") String bookid) {
		Work work = WorksHandler.getInstance().getWork(bookid);
		BasicDBObject bdo = (BasicDBObject) work.toDBObject(false);
			try {
				List<UserProfile> userProfile = UserProfileHandler.getInstance().findWhereUseridEquals(Long.parseLong(bdo.getString(Work.AUTHORID)));
				Name name = userProfile.iterator().next().getName();
				bdo.put("author", name.getFirstname() + " " + name.getLastname());
				bdo.put("released", Utils.getFormatedDate().format(new Date(bdo.getLong("dateofpublication"))));
			} catch (UserProfileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return Response.status(200).entity(new Gson().toJson(bdo)).build();
	}
}
