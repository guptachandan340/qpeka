package com.qpeka.db.book.store.tuples;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UserRating {
	
	public static final String RATING = "rating";
	public static final String USERID = "userId";
	public static final String BOOKID = "bookId";
	
	private String userId = "";
	private String bookId = "";
	private int rating; //out of ten

	public UserRating()
	{
		
	}
	
	public UserRating(String userId, String bookId, int rating) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.rating = rating;
	}

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getBookId() {
		return bookId;
	}


	public void setBookId(String bookId) {
		this.bookId = bookId;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public DBObject toDBObject()
	{
		BasicDBObject dbObj = new BasicDBObject();
		
		dbObj.put(RATING, rating);
		dbObj.put(USERID, userId);
		dbObj.put(BOOKID, bookId);
		
		return dbObj;
	}
	
	public static UserRating getUserRatingfromDBObject(BasicDBObject obj)
	{
		return new UserRating(obj.getString(USERID), obj.getString(BOOKID), obj.getInt(RATING));
	}
}
