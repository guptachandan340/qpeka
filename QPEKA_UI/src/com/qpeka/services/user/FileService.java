package com.qpeka.services.user;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.qpeka.db.Files;
import com.qpeka.managers.FilesManager;

@Path("/user/files")
public class FileService {

	final Logger logger = Logger.getLogger(FileService.class);
	
	@POST
	// pass userObject instead of userId
	@Path("/readbyuserfiles")
	public Response retrievingFileService(@FormParam("userid") long userid, @FormParam("FileType") String filetype) {
		List<Files> files = FilesManager.getInstance()
				.readFiles(userid, filetype, Files.FILETYPE);
	
		if(!files.isEmpty() && files != null) {
			return Response.status(200).entity(new Gson().toJson(files)).build();
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Empty Files list");
			}
			return Response.status(200).entity(new Gson().toJson("")).build();
		}
	}
}
