package com.qpeka.db.book.store.tuples;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qpeka.db.Constants;
import com.qpeka.db.Constants.CATEGORY;
import com.qpeka.db.Constants.LANGUAGES;
import com.qpeka.db.Constants.WORKTYPE;

/*
 * 
 * books
	{
	"_id" : "12345677",
	"title" : "Harry Potter",
	"authorId" : "13254",
	"coverPageFile" : "/tmp/file/img.jpg",
	"edition" : "1",
	"category" : "Fiction",
	"numPages" : "200",
	"publisherId" : "1345",
	"rating" : "3",
	"metaData" : "",
	"description" : "",
	"comments" : [{
				commentText : "bla bla...",
				commenter : "321",
				timeOfComment : "123123124124"
				}.......],
	"ratings" : [{
				userId : "123",
				rating : "3"
				}....]
	}
 */

public class Work {
	
	public static final String ID ="_id";
	public static final String TITLE ="title";
	public static final String AUTHORID ="authorId";
	public static final String CATEGORY ="category";
	public static final String TYPE ="type";
	public static final String NUMPAGES ="numPages";
	public static final String DESCRIPTION ="description"; 
	public static final String LANGUAGE ="language"; 
	public static final String ISPUBLISHED ="ispublished"; 
	public static final String METADATA ="metaData";
	public static final String COVERPAGEFILE ="coverPageFile";
	public static final String WORKFILE = "workfile";
	public static final String WORKFILEORIGINNAME = "workfileoriginname";
	public static final String ISWORKFILE = "isworkfile";
	
	public static final String ISBN ="isbn"; 
	public static final String DATEOFPUBLICATION ="dateofpublication"; 
	public static final String EDITION ="edition";
	public static final String PUBLISHERID ="publisherId";
	//meta Keys
	public static final String SEARCHKEY ="searchKey";
	
	private String _id = "";
	private String title = "";
	private String authorId = "";
	private String coverPageFile = "";
	private String workfile = "";
	private String workfileoriginname = "";
	private CATEGORY category = com.qpeka.db.Constants.CATEGORY.CHILDREN;
	private WORKTYPE type = Constants.WORKTYPE.BOOK;
	private int numPages = 0;
	private JSONObject metaData = new JSONObject();
	private String description = "";
	private LANGUAGES language = LANGUAGES.ENGLISH;
	private boolean isPub = true; 
	private boolean isWorkFile = false;
	
	private long dateOfPub = -1;
	private int edition = 0;
	private String isbn = "";
	private String publisherId = "";
	

	public Work() {
		
	}

	public Work(String _id, String title, String authorId,
			String coverPageFile,
			com.qpeka.db.Constants.CATEGORY category,
			WORKTYPE type, int numPages,
			JSONObject metaData, String description, LANGUAGES language,
			boolean isPub) {
		super();
		this._id = _id;
		this.title = title;
		this.authorId = authorId;
		this.coverPageFile = coverPageFile;
		this.category = category;
		this.type = type;
		this.numPages = numPages;
		this.metaData = metaData;
		this.description = description;
		this.language = language;
		this.isPub = isPub;
	}

	public Work(String _id, String title, String authorId,
			String coverPageFile,
			com.qpeka.db.Constants.CATEGORY category,
			WORKTYPE type, int numPages,
			JSONObject metaData, String description, LANGUAGES language,
			boolean isPub, long dateOfPub, int edition, String isbn,
			String publisherId) {
		super();
		this._id = _id;
		this.title = title;
		this.authorId = authorId;
		this.coverPageFile = coverPageFile;
		this.category = category;
		this.type = type;
		this.numPages = numPages;
		this.metaData = metaData;
		this.description = description;
		this.language = language;
		this.isPub = isPub;
		this.dateOfPub = dateOfPub;
		this.edition = edition;
		this.isbn = isbn;
		this.publisherId = publisherId;
	}

	public long getDateOfPub() {
		return dateOfPub;
	}
	public void setDateOfPub(long dateOfPub) {
		this.dateOfPub = dateOfPub;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getCoverPageFile() {
		return coverPageFile;
	}

	public void setCoverPageFile(String coverPageFile) {
		this.coverPageFile = coverPageFile;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public CATEGORY getCategory() {
		return category;
	}

	public void setCategory(CATEGORY category) {
		this.category = category;
	}

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	public WORKTYPE getType() {
		return type;
	}

	public void setType(WORKTYPE type) {
		this.type = type;
	}

	public LANGUAGES getLanguage() {
		return language;
	}

	public void setLanguage(LANGUAGES language) {
		this.language = language;
	}

	public boolean isPub() {
		return isPub;
	}

	public void setPub(boolean isPub) {
		this.isPub = isPub;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public JSONObject getMetaData() {
		return metaData;
	}

	public void setMetaData(JSONObject metaData) {
		this.metaData = metaData;
	}
	
	public boolean isWorkFile() {
		return isWorkFile;
	}

	public void setWorkFile(boolean isWorkFile) {
		this.isWorkFile = isWorkFile;
	}

	public String getWorkfile() {
		return workfile;
	}

	public void setWorkfile(String workfile) {
		this.workfile = workfile;
	}

	public String getWorkfileoriginname() {
		return workfileoriginname;
	}

	public void setWorkfileoriginname(String workfileoriginname) {
		this.workfileoriginname = workfileoriginname;
	}

	public DBObject toDBObject(boolean insert)
	{
		BasicDBObject dbObj = new BasicDBObject();
		if(!insert) {
			dbObj.put(ID, new ObjectId(_id));
		}
		
		dbObj.put(TITLE, title);
		dbObj.put(AUTHORID, authorId);//new ObjectId(authorId));
		dbObj.put(EDITION, edition);
		dbObj.put(CATEGORY, category.toString());
		dbObj.put(TYPE, type.toString());
		dbObj.put(NUMPAGES, numPages);
		dbObj.put(LANGUAGE, language.toString());
		dbObj.put(ISWORKFILE, isWorkFile);
		dbObj.put(ISPUBLISHED, isPub);
		dbObj.put(DESCRIPTION, description);
		dbObj.put(METADATA, metaData.toString());
		
		if(isWorkFile) {
			if(coverPageFile != null && coverPageFile.length() > 0) {
				dbObj.put(COVERPAGEFILE, coverPageFile);
			}
			
			if(workfileoriginname != null && workfileoriginname.length() > 0) {
				dbObj.put(WORKFILEORIGINNAME, workfileoriginname);
			}
			
			if(workfile != null && workfile.length() > 0) {
				dbObj.put(WORKFILE, workfile);
			}
		}
		
		if(isPub) {
			if(publisherId != null && publisherId.length() > 0) {
				dbObj.put(PUBLISHERID, publisherId);
			}
			
			if(dateOfPub > 0) {
				dbObj.put(DATEOFPUBLICATION, dateOfPub);
			}
			
			if(isbn != null && isbn.length() > 0) {
				dbObj.put(ISBN, isbn);
			}
			
			if(edition > -1) {
				dbObj.put(EDITION, edition);
			}
		}
		
		return dbObj;
	}
	
	public static Work getBookfromDBObject(BasicDBObject obj) {
		try {
			com.qpeka.db.Constants.CATEGORY workcategory = com.qpeka.db.Constants.CATEGORY.valueOf(obj.getString(CATEGORY));
			WORKTYPE worktype = Constants.WORKTYPE.valueOf(obj.getString(TYPE));
			Work work = new  Work(obj.getString(ID),obj.getString(TITLE),obj.getString(AUTHORID),obj.getString(COVERPAGEFILE), 
					workcategory, worktype, obj.getInt(NUMPAGES),new JSONObject(obj.getString(METADATA)), 
					obj.getString(DESCRIPTION),LANGUAGES.valueOf(obj.getString(LANGUAGE)),
					obj.getBoolean(ISPUBLISHED));
			
			if(work.isPub()) {
				String pub = obj.getString(PUBLISHERID);
				if(pub != null && pub.length() > 0) {
					work.setPublisherId(pub);
				}
					
				String isbn = obj.getString(ISBN);
				if(pub != null && pub.length() > 0) {
					work.setIsbn(isbn);
				}
				
				int edition = obj.getInt(EDITION);
				if(edition > -1) {
					work.setEdition(edition);
				}
				
				long dop = obj.getLong(DATEOFPUBLICATION);
				if(dop > 0) {
					work.setDateOfPub(dop);
				}
			}
			
			if(obj.getBoolean(ISWORKFILE)) {
				work.setWorkFile(obj.getBoolean(ISWORKFILE));
				String workfile = obj.getString(WORKFILE);
				if(workfile != null && workfile.length() > 0) {
					work.setWorkfile(workfile);
				}
				
				String coverpage = obj.getString(COVERPAGEFILE);
				if(coverpage != null && coverpage.length() > 0) {
					work.setCoverPageFile(coverpage);
				}
				
				String workfileOriginName = obj.getString(WORKFILEORIGINNAME);
				if(workfileOriginName != null && workfileOriginName.length() > 0) {
					work.setWorkfileoriginname(workfileOriginName);
				}
			}
			
			return work;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) throws JSONException {
		JSONObject k = new JSONObject();
		k.put("5", "uyeiweuiweuwiey\"wierywiey\"riwey");
		System.out.println(k.get("5"));
	}
}
