package com.qpeka.db.book.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.qpeka.db.book.store.tuples.Author;
import com.qpeka.db.book.store.tuples.Constants.AUTHORTYPE;
import com.qpeka.db.book.store.tuples.Constants.GENDER;
import com.qpeka.db.book.store.tuples.Constants.LANGUAGES;
import com.qpeka.db.book.store.tuples.Constants.TYPE;
import com.qpeka.db.book.store.tuples.Name;
import com.qpeka.db.book.store.tuples.Work;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;

/*
 * 1) Primary key testing
 * 2) Index testing 
 * 3) Author testing
 * 4) Testing dependencies
 *
 */

public class WorksHandler {
	
	private static WorksHandler instance = new WorksHandler();
	private DB db = null;
	private DBCollection works = null;
	private static final Logger logger = Logger.getLogger(WorksHandler.class.getName());

	private WorksHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookstore");
		if(!db.isAuthenticated())
			db.authenticate("qpeka", new char[]{'q','p','e','k','a'});
		works = db.getCollection("works");
		
		works.createIndex(new BasicDBObject(Work.CATEGORY, 1));
		works.createIndex(new BasicDBObject(Work.TYPE, 1));
		works.createIndex(new BasicDBObject(Work.AUTHORID, 1));
		works.createIndex(new BasicDBObject(Work.TITLE, 1));
	}
	
	public static WorksHandler getInstance()
	{
		return instance;
	}
	
	public String addWork(Work book)
	{
		long start = System.currentTimeMillis();
		BasicDBObject dObj = (BasicDBObject)book.toDBObject(true);
		WriteResult result = works.insert(dObj, WriteConcern.SAFE);
		ObjectId id =  dObj.getObjectId("_id");
		logger.log(Level.INFO, "time(addBook) = " + (System.currentTimeMillis() - start));
		return id.toString();
	}
	
	public void updateWork(Work work)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Work.ID, new ObjectId(work.get_id()));
		
		works.update(q, new BasicDBObject("$set" , work.toDBObject(true)), true, false, WriteConcern.SAFE);
	}
	
	
	public Work getWork(String id)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Work.ID, new ObjectId(id));
		
		
		DBCursor cursor = works.find(q);
		
        try 
        {
            if(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                Work book = Work.getBookfromDBObject(dObj);
                return book;
            }
            else
            	return null;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return null;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Work> getWorksByCategory(CATEGORY category)
	{
		List<Work> listToReturn = new ArrayList<Work>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(Work.CATEGORY, category.toString());
		
		DBCursor cursor = works.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 Work book = Work.getBookfromDBObject(dObj);
                 listToReturn.add(book);
             }
             return listToReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Work> getWorksByType(TYPE type)
	{
		List<Work> listToReturn = new ArrayList<Work>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(Work.TYPE, type.toString());
		
		DBCursor cursor = works.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 Work book = Work.getBookfromDBObject(dObj);
                 listToReturn.add(book);
             }
             return listToReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	
	public List<Work> getWorksByTypeCategory(TYPE type, CATEGORY cat)
	{
		List<Work> listToReturn = new ArrayList<Work>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(Work.TYPE, type.toString());
		q.put(Work.CATEGORY, cat.toString());
		
		DBCursor cursor = works.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 Work book = Work.getBookfromDBObject(dObj);
                 listToReturn.add(book);
             }
             return listToReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	public Work getWorkOfTheDayByType(TYPE type)
	{
		
		BasicDBObject q = new BasicDBObject();
		q.put(Work.TYPE, type.toString());
		
		BasicDBObject cursor = (BasicDBObject)works.findOne(q);
		
        try 
        {
        	 if(cursor != null) 
             {
                 Work book = Work.getBookfromDBObject(cursor);
                 return book; 
             }
        	 
        	 return null;
            
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return null;
		}
        finally {
           
        }

	}
	
	public Work getFeauredWorkByType(TYPE type)
	{
		
		BasicDBObject q = new BasicDBObject();
		q.put(Work.TYPE, type.toString());
		
		BasicDBObject cursor = (BasicDBObject)works.findOne(q);
		
        try 
        {
        	 if(cursor != null) 
             {
                 Work book = Work.getBookfromDBObject(cursor);
                 return book; 
             }
        	 
        	 return null;
            
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return null;
		}
        finally {
           
        }

	}
	
	public List<Work> getWorksByTitle(String title)
	{
		List<Work> listToReturn = new ArrayList<Work>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(Work.TITLE, "/"+title+"/");
		
		DBCursor cursor = works.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 Work book = Work.getBookfromDBObject(dObj);
                 listToReturn.add(book);
             }
             return listToReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Work> getWorksByCriteria(String criteria)
	{
		List<Work> listToReturn = new ArrayList<Work>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(Work.CATEGORY, "/"+criteria+"/");
		
		DBCursor cursor = works.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 Work book = Work.getBookfromDBObject(dObj);
                 listToReturn.add(book);
             }
             return listToReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Work> getBooksByDescription(String description)
	{
		List<Work> listToReturn = new ArrayList<Work>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(Work.DESCRIPTION, "/"+description+"/");
		
		DBCursor cursor = works.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 Work book = Work.getBookfromDBObject(dObj);
                 listToReturn.add(book);
             }
             return listToReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Work> getBooksByMetaKey(String query)
	{
		List<Work> listToReturn = new ArrayList<Work>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(Work.METADATA+"."+Work.SEARCHKEY, "/"+query+"/");
		
		DBCursor cursor = works.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 Work book = Work.getBookfromDBObject(dObj);
                 listToReturn.add(book);
             }
             return listToReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Work> getWorksByAuthorId(String authorId)
	{
		List<Work> listToReturn = new ArrayList<Work>();
	
		BasicDBObject q = new BasicDBObject();
		q.put(Work.AUTHORID, new ObjectId(authorId));
		
		DBCursor cursor = works.find(q);
		
        try 
        {
            while(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                Work book = Work.getBookfromDBObject(dObj);
                listToReturn.add(book);
            }
            return listToReturn;
           
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Work> getWorksByAuthorIds(List<String> aurthorIds)
	{
		List<Work> books = new ArrayList<Work>();
		
		for(String authorId : aurthorIds)
		{
			List<Work> bk = getWorksByAuthorId(authorId); // can add $in query here
			if(bk != null && bk.size() > 0)
				books.addAll(bk);
		}
		
		return books;
		
	}
	
	public List<Work> getAllBooks()
	{
		List<Work> listToReturn = new ArrayList<Work>();
		DBCursor cursor = works.find();
        try 
        {
            while(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                Work book = Work.getBookfromDBObject(dObj);
                listToReturn.add(book);
            }
            return listToReturn;
           
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return null;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Work> getFirstFiveBooks()
	{
		List<Work> listToReturn = new ArrayList<Work>();
		DBCursor cursor = works.find();
		int i = 5;
        try 
        {
            while(cursor.hasNext() && i > 0) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                Work book = Work.getBookfromDBObject(dObj);
                listToReturn.add(book);
                i++;
            }
            return listToReturn;
           
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return null;
		}
        finally {
            cursor.close();
        }

	}
	public List<Work> getWorksSpecificCriteriaHierarchy(String criteria)
	{
		List<Work> books = new ArrayList<Work>();
		
		books.addAll(getWorksByTitle(criteria));
		books.addAll(getWorksByCriteria(criteria));
		books.addAll(getBooksByMetaKey(criteria));
		books.addAll(getBooksByDescription(criteria));
		
		return books;
	}
	
	
	public static void main(String[] args) throws JSONException {
		
		com.qpeka.db.book.store.tuples.Constants.CATEGORY.valueOf("ART");
		
		for(int i = 0; i<10;i++)
		{
		Author a = new Author("", new Name("Mark"+i, "Donald"+i, "Shane"+i), GENDER.MALE, new Date(), "INDIAN", "", "He is an awsome writer"+i, "http://google.com/mark", CATEGORY.ART,
				AUTHORTYPE.LEVEL1);
		
		String aid = AuthorHandler.getInstance().addAuthor(a);
		
		JSONObject metadata = new JSONObject();
		metadata.put(Work.SEARCHKEY, "art,history");
		
		Work w = new Work("", "Marks work"+1, aid, "", CATEGORY.ART, TYPE.BOOK, 100, metadata, "AWSOME BOOK"+i, LANGUAGES.ENGLISH, false);
		
		WorksHandler.getInstance().addWork(w);
		}
//		String[] titles = {"Harry Potter" , "Pride & Prejudice" , "Gone with the wind"};
//		String[] authorIds = {"5055fa47c4e7aaf93796c90a" , "5055fa47c4e7aaf93796c90d" , "5055fa47c4e7aaf93796c90f" , "5055fa47c4e7aaf93796c907" , "5055fa47c4e7aaf93796c906"};
//		String[] publisherIds = {"50560049c4e7a9cad3686ed1","50560049c4e7a9cad3686ed4","50560049c4e7a9cad3686ed6"};
//		
//		Work book = null;
//		for(int i = 0; i<20;i++)
//		{
//			book = new Work();
//			book.set_id("");
//			book.setTitle(titles[i%titles.length]);
//			book.setAuthorId(authorIds[i%authorIds.length]);
//			book.setEdition(i);
//			book.setCoverPageFile("/tmp/img"+i+".jpg");
//			book.setNumPages(200 + i*10);
//			book.setPublisher(publisherIds[i%publisherIds.length]);
//			book.setAvgRating(3.6f);
//			book.setCategory(CATEGORY.values()[i%CATEGORY.values().length]);
//			
//			WorksHandler.getInstance().addBook(book);
//		}
//
//		
//		List<UserRating> ratings = new ArrayList<UserRating>();
//		ratings.add(new UserRating(3, new UserInfoIdentifier(512, "danger anna1", new Name("manoj1", "rameshchandra", "Thakur"))));
//		ratings.add(new UserRating(4, new UserInfoIdentifier(513, "danger anna2", new Name("manoj2", "rameshchandra", "Thakur"))));
//		ratings.add(new UserRating(6, new UserInfoIdentifier(514, "danger anna3", new Name("manoj3", "rameshchandra", "Thakur"))));
//		ratings.add(new UserRating(2, new UserInfoIdentifier(515, "danger anna4", new Name("manoj4", "rameshchandra", "Thakur"))));
//		ratings.add(new UserRating(1, new UserInfoIdentifier(516, "danger anna5", new Name("manoj5", "rameshchandra", "Thakur"))));
//		ratings.add(new UserRating(0, new UserInfoIdentifier(517, "danger anna6", new Name("manoj6", "rameshchandra", "Thakur"))));
//		
//		List<UserComments> comments = new ArrayList<UserComments>();
//		comments.add(new UserComments("Hello Comment1", new UserInfoIdentifier(512, "danger anna1", new Name("manoj1", "rameshchandra", "Thakur"))));
//		comments.add(new UserComments("Hello Comment2", new UserInfoIdentifier(512, "danger anna2", new Name("manoj2", "rameshchandra", "Thakur"))));
//		comments.add(new UserComments(123457, "Hello Comment3", new UserInfoIdentifier(512, "danger anna6", new Name("manoj6", "rameshchandra", "Thakur"))));
//		comments.add(new UserComments(123458, "Hello Comment4", new UserInfoIdentifier(512, "danger anna1", new Name("manoj1", "rameshchandra", "Thakur"))));
//		comments.add(new UserComments(123459, "Hello Comment5", new UserInfoIdentifier(512, "danger anna1", new Name("manoj1", "rameshchandra", "Thakur"))));
//		
//		book.setRatings(ratings);
//		book.setComments(comments);
//		
//		String x = BookHandler.getInstance().addBook(book);
//		BookHandler.getInstance().addBookRating("504c83b6364509aed091c801", new UserRating(300, new UserInfoIdentifier(516, "danger anna4", new Name("manoj4", "rameshchandra", "Thakur"))));
//		
//		
//		//System.out.println(BookHandler.getInstance().getBookByAuthorId("504b7a67e0c1b0da3bcafc16"));
	}
	
}
