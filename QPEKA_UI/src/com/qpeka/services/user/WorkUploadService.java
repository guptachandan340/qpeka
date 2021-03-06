package com.qpeka.services.user;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.qpeka.db.Constants.CATEGORY;
import com.qpeka.db.Constants.LANGUAGES;
import com.qpeka.db.Constants.WORKTYPE;
import com.qpeka.db.book.store.tuples.Work;
import com.qpeka.db.handler.user.PublisherHandler;
import com.qpeka.db.user.profile.type.Publisher;
import com.qpeka.epub.provider.EpubProcessorNew;
import com.qpeka.managers.WorkContentManager;
import com.qpeka.services.Response.ServiceResponseManager;
import com.qpeka.utils.SystemResourceHandler;
import com.qpeka.utils.Utils;

@Path("/user/works")
public class WorkUploadService {

	@POST
	@Path("/upload")
	@Consumes("application/x-www-form-urlencoded")
	public Response workUpload(MultivaluedMap<String, String> formParams) {

		Map<String, Object> sResponse = null;
		String workid = null;
		String workfileOriginName = null;
		String workfile = null;
		String coverpage = null;
		// file upload part
		String title = "";
		String bookDesc = "";
		CATEGORY bookCategory = CATEGORY.CHILDREN;
		WORKTYPE type = WORKTYPE.BOOK;
		LANGUAGES language = LANGUAGES.ENGLISH;
		boolean isPublished = false;
		boolean isWorkFile = false;
		String authorId = "";
		int bookEdition = 0;
		String publisherId = "";
		String publisherName = "";
		Date publishdate = null;
		String isbn = "";
		String searchKey = "";
        Set<String> keySet = formParams.keySet();
		for (String controlName : keySet) {
			List<String> listOfValue = formParams.get(controlName);
			// gives values by taking keys.
			for (String value : listOfValue) {
				if (controlName.equalsIgnoreCase(Work.TITLE)) {
					title = value;
				}

				if (controlName.equalsIgnoreCase(Work.CATEGORY))
					try {
						bookCategory = CATEGORY.valueOf(value.toUpperCase());
					} catch (Exception e) {
						System.out.println("error bookcategory");
					}

				if (controlName.equalsIgnoreCase(Work.TYPE))
					try {
						type = WORKTYPE.valueOf(value.toUpperCase());
					} catch (Exception e) {
						System.out.println("error bookcategory");
					}

				if (controlName.equalsIgnoreCase(Work.LANGUAGE))
					try {
						language = LANGUAGES.valueOf(value.toUpperCase());
					} catch (Exception e) {
						System.out.println("error language");
					}

				if (controlName.equalsIgnoreCase(Work.DESCRIPTION)) {
					bookDesc = value;
				}

				if (controlName.equalsIgnoreCase(Work.AUTHORID)) {
					authorId = value;
				}

				if (controlName.equalsIgnoreCase(Work.ISPUBLISHED)) {
					isPublished = Boolean.parseBoolean(value);
				}

				if (controlName.equalsIgnoreCase(Work.SEARCHKEY)) {
					searchKey = value;
				}

				if (controlName.equalsIgnoreCase(Work.EDITION) && !value.isEmpty()) {
					bookEdition = Integer.parseInt(value);
				}

				if (controlName.equalsIgnoreCase(Work.PUBLISHERID)) {
					publisherId = value;
				}

				if (controlName.equalsIgnoreCase("publisher") && !value.isEmpty()) {
					publisherName = value;
					if (!isPublished) {
						isPublished = true;
					}
				}

				if(controlName.equalsIgnoreCase("publishdate") && !value.isEmpty()) {
					try {
						publishdate = (Date) Utils.getFormatedDate().parse(value);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					publishdate = new Date();
				}

				if (controlName.equalsIgnoreCase(Work.ISBN)) {
					isbn = value;
				}

				if(controlName.equalsIgnoreCase("workid")) {
					workid = value;
				}
				
				if(controlName.equalsIgnoreCase("workfileoriginname")) {
					workfileOriginName = value;
				}
				
				if (controlName.equalsIgnoreCase("workfile")) {
					workfile = value;
					if (!isWorkFile) {
						isWorkFile = true;
					}
				}
				if (controlName.equalsIgnoreCase("cover")) {
					coverpage = value;
				}
			} // forloop

		}// for

		try {
			String pId = "";
			if (publisherName != null && publisherName.length() > 0) {
				pId = PublisherHandler.getInstance().addPublisher(
						new Publisher(publisherName, ""));
			} else {
				pId = publisherId;
			}

			JSONObject metadata = new JSONObject();
			metadata.put(Work.SEARCHKEY, searchKey);
			

			if(workid != null && !workid.isEmpty()) {
				File filePath = new File(SystemResourceHandler.getInstance()
						.getSrcBookFolder() + "/" + workfile);
				if (filePath != null && filePath.exists()) {
					if (filePath.getName().endsWith("epub")) {
						EpubProcessorNew.processEpub(filePath.getAbsolutePath(),
								SystemResourceHandler.getInstance()
										.getSrcBookFolder() + "/" + workfile);
					}
				} else {
					// TODO: Add handling here: send response
				}
				
				workid = WorkContentManager.getInstance().updateWork(workid, isWorkFile, workfileOriginName, coverpage, workfile);
			} else {
				/*String coverPageFile = SystemResourceHandler.getInstance()
						.getBookCoverPageFolder() + "/" + title + ".jpg";*/
				workid = WorkContentManager.getInstance().addWork("", title,
						authorId, "", bookCategory, type, 0, metadata,
						bookDesc, language, isPublished,
						publishdate.getTime(), bookEdition, isbn,
						pId);
			}
			
			/*File filePath = new File(SystemResourceHandler.getInstance()
					.getBookCoverPageFolder() + "/" + coverpage);
			if (filePath != null && filePath.exists()) {
				File idedFile = new File(SystemResourceHandler.getInstance()
						.getBookCoverPageFolder() + "/" + _id + ".jpg");
				FileUtils.copyFile(filePath, idedFile);
			} else {
				response = "coverpage dose not exists";
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!workid.isEmpty()) {
			sResponse = ServiceResponseManager.getInstance().readServiceResponse(200);
			sResponse.put("workid", workid);
		} else {
			sResponse = ServiceResponseManager.getInstance().readServiceResponse(404);
		}

		return Response.status(200).entity(new Gson().toJson(sResponse)).build();

	}

}
