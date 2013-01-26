package com.qpeka.db.book.store.tuples;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UserComments {
	
	public static final String ID = "_id";
	public static final String BOOKID = "bookId";
	public static final String COMMENT = "comment";
	public static final String USERID = "userId";
	
	private String _id = "";
	private String bookId = "";
	private String comment = "";
	private String userId;
	
	public UserComments()
	{
		
	}
	
	public UserComments(String _id, String bookId, String comment,
			String userId) {
		super();
		this._id = _id;
		this.bookId = bookId;
		this.comment = comment;
		this.userId = userId;
	}

	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUserInfo() {
		return userId;
	}
	public void setUserInfo(String userId) {
		this.userId = userId;
	}
	
	public DBObject toDBObject(boolean insert)
	{
		BasicDBObject dbObj = new BasicDBObject();
		if(!insert)
			dbObj.put(ID, _id);
		dbObj.put(BOOKID, bookId);
		dbObj.put(COMMENT, comment);
		dbObj.put(USERID, userId);
		
		return dbObj;
	}
	
	public static UserComments getUserCommentfromDBObject(BasicDBObject obj)
	{
		return new UserComments(obj.getString(ID), obj.getString(BOOKID), obj.getString(COMMENT) , obj.getString(USERID));
	}
	
}
