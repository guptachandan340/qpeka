package com.qpeka.db.book.store.tuples;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qpeka.db.book.store.tuples.Constants.AUTHORTYPE;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.db.book.store.tuples.Constants.GENDER;

/*
 * 
 * authors
	{
	_id : "",
	name : {
				firstName : "manoj",
				middleName : "R"
				lastName : "thakur"
			},
	gender : M,
	dob : 12321312,
	nationality : "Indian",
	imageFile : "/tmp/img3.jpg",
	shortBio : "jrcbkuhgr grlfgruflrgflrgfk, gfkjsdgf,sjdg flksjagf",
	infoLink : "http://google.com",
	genre : "Fiction"
	
	}

 */

public class Author {

	public static final String ID = "_id";
	public static final String NAME = "name";
	public static final String GENDER = "gender";
	public static final String DOB = "dob";
	public static final String NATIONALITY = "nationality";
	public static final String IMAGEFILE = "imageFile";
	public static final String SHORTBIO = "shortBio";
	public static final String INFOLINK = "infoLink";
	public static final String GENRE = "genre";
	public static final String TYPE = "type";
	
	private String _id ;
	private Name name;
	private GENDER gender;
	private Date dob;
	private String nationality;
	private String imageFile;
	private String shortBio;
	private String infoLink;
	private Set<CATEGORY> genre = new HashSet<Constants.CATEGORY>();
	private AUTHORTYPE type = AUTHORTYPE.LEVEL1;
	
	public Author() {
		super();
	}
	
	public Author(String _id, Name name,
			com.qpeka.db.book.store.tuples.Constants.GENDER gender, Date dob,
			String nationality, String imageFile, String shortBio,
			String infoLink, Set<CATEGORY> genre, AUTHORTYPE type) {
		super();
		this._id = _id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.nationality = nationality;
		this.imageFile = imageFile;
		this.shortBio = shortBio;
		this.infoLink = infoLink;
		this.genre = genre;
		this.type = type;
	}
	
	public Author(String _id, Name name,
			com.qpeka.db.book.store.tuples.Constants.GENDER gender, Date dob,
			String nationality, String imageFile, String shortBio,
			String infoLink, CATEGORY genre, AUTHORTYPE type) {
		super();
		this._id = _id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.nationality = nationality;
		this.imageFile = imageFile;
		this.shortBio = shortBio;
		this.infoLink = infoLink;
		this.genre.add(genre);
		this.type = type;
	}
	
	
	public AUTHORTYPE getType() {
		return type;
	}
	public void setType(AUTHORTYPE type) {
		this.type = type;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public GENDER getGender() {
		return gender;
	}
	public void setGender(GENDER gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public String getShortBio() {
		return shortBio;
	}
	public void setShortBio(String shortBio) {
		this.shortBio = shortBio;
	}
	public String getInfoLink() {
		return infoLink;
	}
	public void setInfoLink(String infoLink) {
		this.infoLink = infoLink;
	}
	public Set<CATEGORY> getGenre() {
		return genre;
	}
	public void setGenre(Set<CATEGORY> genre) {
		this.genre = genre;
	}
	
	public DBObject toDBObject(boolean insert)
	{
		BasicDBObject dbObj = new BasicDBObject();
		if(!insert)
			dbObj.put(ID, _id);
		
		dbObj.put(NAME, name.toDBObject());
		dbObj.put(GENDER, gender.toString());
		dbObj.put(DOB, dob.getTime());
		dbObj.put(NATIONALITY, nationality);
		dbObj.put(IMAGEFILE, imageFile);
		dbObj.put(SHORTBIO, shortBio);
		dbObj.put(INFOLINK, infoLink);
		
		BasicDBList bdl = new BasicDBList();
		for(CATEGORY cat : genre)
			bdl.add(cat.toString());
		
		dbObj.put(GENRE, bdl);
		dbObj.put(TYPE, type.toString());
		
		return dbObj;
	}
	
	public static Author getAuthorfromDBObject(BasicDBObject obj)
	{
		Set<CATEGORY> genre = new HashSet<CATEGORY>();
		try 
		{
			BasicDBList list = (BasicDBList)obj.get(GENRE);	
			
			for(int index = 0 ; index < list.size() ; index++)
			{
				genre.add(CATEGORY.valueOf((String)list.get(index)));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Author(obj.getString(ID), Name.getNamefromDBObject((BasicDBObject)obj.get(NAME)), com.qpeka.db.book.store.tuples.Constants.GENDER.valueOf(obj.getString(GENDER)),
				new Date(obj.getLong(DOB)), obj.getString(NATIONALITY) , obj.getString(IMAGEFILE), obj.getString(SHORTBIO),obj.getString(INFOLINK), genre , AUTHORTYPE.LEVEL3);
	}
	
}
