package com.qpeka.services.user;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import com.qpeka.db.Constants.CATEGORY;
import com.qpeka.db.Constants.LANGUAGES;
import com.qpeka.db.Constants.WORKTYPE;
import com.qpeka.db.book.store.tuples.Work;
import com.qpeka.db.handler.user.PublisherHandler;
import com.qpeka.db.user.profile.type.Publisher;
import com.qpeka.epub.provider.EpubProcessorNew;
import com.qpeka.managers.WorkContentManager;
import com.qpeka.utils.SystemConfigHandler;

@Path("/user/work")
public class WorkUploadService {

	@POST
	@Path("/fileprocess")
	@Consumes("application/x-www-form-urlencoded")
	public Response workUpload(MultivaluedMap<String, String> formParams) {

		String response = "success";
		String bookname = null;
		String covername = null;
		// file upload part
		String title = "";
		String bookDesc = "";
		CATEGORY bookCategory = CATEGORY.CHILDREN;
		WORKTYPE type = WORKTYPE.BOOK;
		LANGUAGES language = LANGUAGES.ENGLISH;
		boolean isPublished = false;
		String authorId = "";
		int bookEdition = 0;
		String publisherId = "";
		String publisherName = "";
		int pday = 0;
		int pmonth = 0;
		int pyear = 0;
		String isbn = "";
		String searchKey = "";
        Set<String> keySet = formParams.keySet();
		Iterator<String> itr = keySet.iterator();
		while (itr.hasNext()) {
			String controlName = itr.next();
			List<String> listOfValue = formParams.get(controlName);
			// gives values by taking keys.

			for (String value : listOfValue) {

				if (controlName.equalsIgnoreCase(Work.TITLE)) {
					title = value;
					System.out.println("title :" + title);

				}

				if (controlName.equalsIgnoreCase(Work.CATEGORY))
					try {
						bookCategory = CATEGORY.valueOf(value);
						System.out.println("bookCategory :" + bookCategory);

					} catch (Exception e) {
						System.out.println("error bookcategory");
					}

				if (controlName.equalsIgnoreCase(Work.TYPE))
					try {
						type = WORKTYPE.valueOf(value);
						System.out.println("worktype :" + type);
					} catch (Exception e) {
						System.out.println("error bookcategory");
					}

				if (controlName.equalsIgnoreCase(Work.LANGUAGE))
					try {
						language = LANGUAGES.valueOf(value);
						System.out.println("language :" + language);
					} catch (Exception e) {
						System.out.println("error language");
					}

				if (value.equalsIgnoreCase(Work.DESCRIPTION)) {
					bookDesc = value;
					System.out.println("bookdesc :" + bookDesc);
				}

				if (controlName.equalsIgnoreCase(Work.AUTHORID)) {
					authorId = value;
					System.out.println("authorId :" + authorId);
				}

				if (controlName.equalsIgnoreCase(Work.ISPUBLISHED)) {
					isPublished = Boolean.parseBoolean(value);
					System.out.println("IsPublished :" + isPublished);
				}

				if (controlName.equalsIgnoreCase(Work.SEARCHKEY)) {
					searchKey = value;
					System.out.println("searchKey :" + searchKey);
				}

				if (controlName.equalsIgnoreCase(Work.EDITION)) {
					bookEdition = Integer.parseInt(value);
					System.out.println("BookEdition :" + bookEdition);
				}

				if (controlName.equalsIgnoreCase(Work.PUBLISHERID)) {
					publisherId = value;
					System.out.println("pblisheredid :" + publisherId);
				}

				if (controlName.equalsIgnoreCase("publisherName")) {
					publisherName = value;
					System.out.println("publishername :" + publisherName);
				}

				if (controlName.equalsIgnoreCase("pday")) {
					pday = Integer.parseInt(value);
					System.out.println("pday :" + pday);
				}

				if (controlName.equalsIgnoreCase("pmonth")) {
					pmonth = Integer.parseInt(value);
					System.out.println("pmonth :" + pmonth);
				}

				if (controlName.equalsIgnoreCase("pyear")) {
					pyear = Integer.parseInt(value);
					System.out.println("pyear :" + pyear);
				}

				if (controlName.equalsIgnoreCase(Work.ISBN)) {
					isbn = value;
					System.out.println("ISBN :" + isbn);
				}

				if (controlName.equalsIgnoreCase("file")) {
					bookname = value;
					System.out.println("file :" + bookname);
				}
				if (controlName.equalsIgnoreCase("cover")) {
					covername = value;
					System.out.println("cover :" + covername);
				}
			} // forloop

		}// while

		try {
			String pId = "";
			if (publisherName != null && publisherName.length() > 0) {
				pId = PublisherHandler.getInstance().addPublisher(
						new Publisher(publisherName, ""));
			} else {
				pId = publisherId;
			}

			String coverPageFile = SystemConfigHandler.getInstance()
					.getBookCoverPageFolder() + "/" + title + ".jpg";
			System.out.println("coverpagefile  :" + coverPageFile);
			JSONObject metadata = new JSONObject();
			metadata.put(Work.SEARCHKEY, searchKey);

			String _id = WorkContentManager.getInstance().addWork("", title,
					authorId, coverPageFile, bookCategory, type, 0, metadata,
					bookDesc, language, isPublished,
					new Date(pyear, pmonth, pday).getTime(), bookEdition, isbn,
					pId);

			File filePath = new File(SystemConfigHandler.getInstance()
					.getBookCoverPageFolder() + "/" + covername);
			if (filePath != null && filePath.exists()) {
				File idedFile = new File(SystemConfigHandler.getInstance()
						.getBookCoverPageFolder() + "/" + _id + ".jpg");
				FileUtils.copyFile(filePath, idedFile);
			} else {
				response = "coverpage dose not exists";
			}
			filePath = new File(SystemConfigHandler.getInstance()
					.getSrcBookFolder() + "/" + bookname);
			if (filePath != null && filePath.exists()) {
				if (filePath.getName().endsWith("epub")) {
					EpubProcessorNew.processEpub(filePath.getAbsolutePath(),
							SystemConfigHandler.getInstance()
									.getSrcBookFolder() + "/" + _id + ".epub");
					
				}
			} else {
				response = "file dose not exists";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(response).build();

	}

}
