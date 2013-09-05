package com.qpeka.services.user;

import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.qpeka.db.Files;
import com.qpeka.managers.FilesManager;

@Path("/user/files")
public class FileService {

	@POST
	// pass userObject instead of userId
	@Path("/readbyuserfiles")
	public Response retrievingFileService(@FormParam("userid") long userid, @FormParam("FileType") String filetype) {
		Map<String, Entry<String, String>> files = null;
		String response = null;
		files = FilesManager.getInstance().readFiles(userid,filetype,Files.FILETYPE);
	
		if(!files.isEmpty() && files != null) {
			Gson gson = new Gson();
			response = gson.toJson(files);
		}
		return Response.status(200).entity(response).build();
	}
}
