package com.qpeka.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;

import com.qpeka.db.book.store.AuthorHandler;
import com.qpeka.db.book.store.TinyBookURLIdGenerator;
import com.qpeka.db.book.store.UserCommentHandler;
import com.qpeka.db.book.store.UserRatingHandler;
import com.qpeka.db.book.store.WorksHandler;
import com.qpeka.db.book.store.tuples.Author;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.db.book.store.tuples.Constants.LANGUAGES;
import com.qpeka.db.book.store.tuples.UserComments;
import com.qpeka.db.book.store.tuples.UserRating;
import com.qpeka.db.book.store.tuples.Work;
import com.qpeka.utils.BookTinyURLIDGenerator;

public class WorkContentManager {
	
	private static WorkContentManager instance = null;
	
	private WorkContentManager()
	{
		
	}
	
	public static WorkContentManager getInstance()
	{
		if(instance == null)
		{	
			synchronized (WorkContentManager.class) {
			
				instance = new WorkContentManager();
			}
			
		}
		
		return instance;
	}
	
	//BookDetail Methods
	
	public Work getWorkDetails(String workId)
	{
		return WorksHandler.getInstance().getWork(workId);
	}
	
	public List<Work> getWorkDetailsByCategory(CATEGORY category)
	{
		return WorksHandler.getInstance().getWorksByCategory(category,-1,-1);
	}
	
	public List<Work> getWorkDetailsByTitle(String title)
	{
		return WorksHandler.getInstance().getWorksByTitle(title,-1,-1);
	}
	
	public List<Work> getWorkDetailsByAuthor(String authorId)
	{
		return WorksHandler.getInstance().getWorksByAuthorId(authorId,-1,-1);
	}
	
	public List<Work> getWorkDetailsByAuthorName(String name)
	{
		List<String> authors = new ArrayList<String>();
		
		for(Author author : AuthorHandler.getInstance().getAuthorsByLikelyName(name))
		{
			authors.add(author.get_id());
		}
		
		return WorksHandler.getInstance().getWorksByAuthorIds(authors);
	}
	
	public List<Work> getWorksByCriteria(String criteria)
	{
		/*
		 * the hierarcy is as follows
		 *  1) bookTitle
		 *  2) Category 
		 *  3) Description
		 *  4) Metakey -> searchKey 
		 */
		
		return WorksHandler.getInstance().getWorksSpecificCriteriaHierarchy(criteria);
	}
	
//	public JSONObject getContentGivenBookPageId(String workId , int page) 
//	{
//		Work b  = WorksHandler.getInstance().getWork(workId);
//		int numPagesPerFile = 50;
//		
//		if(b.getMetaData().has(Work.NUMPAGESPERFILE))
//		{
//			try
//			{
//				numPagesPerFile = b.getMetaData().has(Work.NUMPAGESPERFILE) ? Integer.parseInt(b.getMetaData().getString(Work.NUMPAGESPERFILE)) : 50;
//			} catch (NumberFormatException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		int fileIndex = 0;
//		if(page/numPagesPerFile < 1)
//			fileIndex = 1 ;
//		else if(page%numPagesPerFile == 0)
//			fileIndex = page/numPagesPerFile;
//		else
//			fileIndex = ((int)page/numPagesPerFile) + 1;
//		
//		String fileName = SystemConfigHandler.getInstance().getBookContentFolder() + "/" + b.get_id() + "/" + b.getTitle() + "-" + fileIndex;
//		FileReader fr = null;
//		JSONObject returnObj = new JSONObject();
//		
//		try
//		{
//			fr = new FileReader(new File(fileName));
//			
//			char[] cbuf = new char[1000000];
//			fr.read(cbuf);
//			String s = new String(cbuf);
//			s = s.trim();
//			
//			JSONObject j = new JSONObject(s);
//			
//			returnObj.put("bookId", bookId);
//			returnObj.put("pageId", page);
//			returnObj.put("content", j.get(page+""));
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally{
//			try {
//				fr.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return returnObj;
//	}
//	
	public List<UserComments> getBookComments(String bookId)
	{
		return UserCommentHandler.getInstance().getCommentsGivenBook(bookId);
	}
	
	public List<UserRating> getBookRatings(String bookId)
	{
		return UserRatingHandler.getInstance().getRatingGivenBook(bookId);
	}
	
	public String addWork(String _id, String title, String authorId,
			String coverPageFile,
			com.qpeka.db.book.store.tuples.Constants.CATEGORY category,
			com.qpeka.db.book.store.tuples.Constants.TYPE type, int numPages,
			JSONObject metaData, String description, LANGUAGES language,
			boolean isPub, long dateOfPub, int edition, String isbn,
			String publisherId)
	{
		try
		{
			Work b = new Work("", title, authorId, coverPageFile, category, type, numPages, metaData, description, language, isPub);
			
			if(isPub)
			{
				if(publisherId != null && publisherId.length() > 0)
					b.setPublisherId(publisherId);
				if(dateOfPub > 0)
					b.setDateOfPub(dateOfPub);
				if(isbn != null && isbn.length() > 0)
					b.setIsbn(isbn);
				if(edition > -1)
					b.setEdition(edition);
			}
			
			String id =   WorksHandler.getInstance().addWork(b);
			boolean success = TinyBookURLIdGenerator.getInstance().addMapping(BookTinyURLIDGenerator.generateBookTinyURLId(),id);
			
			while(!success)
				success = TinyBookURLIdGenerator.getInstance().addMapping(BookTinyURLIDGenerator.generateBookTinyURLId(),id);
			
			return id;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString());
	}
}
