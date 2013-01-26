package com.qpeka.db.book.store.tuples;

import com.mongodb.BasicDBObject;

public class BookPageViewLog {
	
	private String bookId = "";
	private String userId = "";
	private int pageId;
	private long viewTime;
	
	public static final String BOOKID = "bookId";
	public static final String USERID = "userId";
	public static final String PAGEID = "pageId";
	public static final String VIEWTIME = "viewTime";
	
	public BookPageViewLog()
	{
		
	}
	
	public BookPageViewLog(String bookId, String userId, int pageId,
			long viewTime) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.pageId = pageId;
		this.viewTime = viewTime;
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
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public long getViewTime() {
		return viewTime;
	}
	public void setViewTime(long viewTime) {
		this.viewTime = viewTime;
	}

	public BasicDBObject toDBObject(boolean b) {
		
		BasicDBObject dbObj = new BasicDBObject();
		
		dbObj.put(BOOKID, bookId);
		dbObj.put(USERID, userId);
		dbObj.put(PAGEID, pageId);
		dbObj.put(VIEWTIME, viewTime);
		
		return dbObj;
	}
	
	public static BookPageViewLog getBookPageViewLogfromDBObject(BasicDBObject obj)
	{
		return new BookPageViewLog(obj.getString(BOOKID), obj.getString(USERID), obj.getInt(PAGEID), obj.getLong(VIEWTIME));
	}
}
