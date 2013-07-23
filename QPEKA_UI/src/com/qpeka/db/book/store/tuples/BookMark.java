package com.qpeka.db.book.store.tuples;

import java.util.Set;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class BookMark {

	public static final String BOOKID = "bookId";
	public static final String PAGES = "pages";
	
	private String bookId = "";
	private Set<Integer> pageIds = null;
	
	public BookMark(String bookId, Set<Integer> pageIds) {
		super();
		this.bookId = bookId;
		this.pageIds = pageIds;
	}
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public Set<Integer> getPageIds() {
		return pageIds;
	}
	public void setPageIds(Set<Integer> pageIds) {
		this.pageIds = pageIds;
	}
	
	public DBObject toDBObject()
	{
		BasicDBObject dbObj = new BasicDBObject();
		
		dbObj.put(BOOKID, new ObjectId(bookId));
		dbObj.put(PAGES, pageIds.toString());
		
		return dbObj;
	}

	
}
