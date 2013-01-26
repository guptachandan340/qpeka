package com.qpeka.db.book.store.tuples;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class UserBookViewLog {
	
	private String bookId = "";
	private String userId = "";
	private long lastViewTime = 0l;
	private BasicDBList viewTimes;
	
	public static final String BOOKID = "bookId";
	public static final String USERID = "userId";
	public static final String VIEWTIME = "viewTime";
	public static final String LASTVIEWTIME = "lastViewTime";
	
	public UserBookViewLog()
	{
		
	}
	
	public UserBookViewLog(String bookId, String userId, BasicDBList viewTimes, long lastViewTime) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.viewTimes = viewTimes;
		this.lastViewTime = lastViewTime;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public BasicDBList getViewTimes() {
		return viewTimes;
	}

	public void setViewTimes(BasicDBList viewTimes) {
		this.viewTimes = viewTimes;
	}
	
	public long getLastViewTime() {
		return lastViewTime;
	}
	
	public void setLastViewTime(long lastViewTime) {
		this.lastViewTime = lastViewTime;
	}

	public BasicDBObject toDBObject(boolean b) {
		
		BasicDBObject dbObj = new BasicDBObject();
		
		dbObj.put(BOOKID, bookId);
		dbObj.put(USERID, userId);
		//dbObj.put(LASTVIEWTIME , lastViewTime);
		//dbObj.put(VIEWTIME, viewTimes);
		
		return dbObj;
	}
	
	public static UserBookViewLog getBookPageViewLogfromDBObject(BasicDBObject obj)
	{
		return new UserBookViewLog(obj.getString(BOOKID), obj.getString(USERID), (BasicDBList)obj.get(VIEWTIME), obj.getLong(LASTVIEWTIME));
	}
}
