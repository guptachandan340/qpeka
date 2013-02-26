package com.qpeka.db.book.store.tuples;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.db.book.store.tuples.Constants.GENDER;
import com.qpeka.db.book.store.tuples.Constants.LANGUAGES;
import com.qpeka.db.book.store.tuples.Constants.USERLEVEL;
import com.qpeka.db.book.store.tuples.Constants.USERTYPE;

/*
 * 
 * user
	{
		_id : "",
		name : {
					firstName : "manoj",
					middleName : "R"
					lastName : "thakur"
				},
		gender : M ,
		email : {
					emailid1 : e1@gmail.com,
					emailid2 : e2@gmail.com,
					emailid3 : e3@gmail.com,
				}
		address : "something something"
		preferences : [fiction , scifi , comedy]
		userType : premium,
		bookmarks :{
					bookId : "123123",
					pages : [12,32,34,45,54,32]
					}
	
	}
 */

public class User {
	
	public static final String ID = "_id";
	public static final String NAME = "name";
	public static final String GENDER = "gender";
	public static final String DOB = "dob";
	public static final String NATIONALITY = "nationality";
	public static final String IMAGEFILE = "imageFile";
	public static final String ADDRESS = "address";
	public static final String INTERESTS = "interests";
	public static final String EMAIL = "email";
	public static final String AGE = "age";
	public static final String BOOKMARKS = "bookMarks";
	public static final String PHONE = "phone";
	public static final String RLANG = "rLang";
	public static final String WLANG = "wLang";
	public static final String PENNAME = "penname";
	public static final String DESC = "desc";
	public static final String USERNAME = "userName";
	public static final String USERTYPE = "type";
	public static final String USERLEVEL = "level";
	
	private String userName = ""; //*
	private String _id = "";
	private Name name = null; //*
	private GENDER gender = com.qpeka.db.book.store.tuples.Constants.GENDER.MALE; //*
	private Address address = new Address("", "", "", "", "", "");
	private Set<CATEGORY> interests = new HashSet<Constants.CATEGORY>();
	private USERLEVEL userlevel = com.qpeka.db.book.store.tuples.Constants.USERLEVEL.FREE;
	private USERTYPE type = com.qpeka.db.book.store.tuples.Constants.USERTYPE.READER;
	private List<BookMark> bookMarks = new ArrayList<BookMark>();
	private int age = 0;
	private Date dob = new Date();
	private String nationality ="";
	private String imageFile ="";
	private String email ="";
	private String phone ="";
	private String penName = "";
	private Set<LANGUAGES> rLang = new HashSet<Constants.LANGUAGES>();
	private Set<LANGUAGES> wLang = new HashSet<Constants.LANGUAGES>();
	private String desc = "";
	
	public User()
	{
		
	}
	
	public User(String userName, Name name,
			com.qpeka.db.book.store.tuples.Constants.GENDER gender, String email) {
		super();
		this.userName = userName;
		this.name = name;
		this.gender = gender;
		this.email = email;
	}

	public User(String userName, String _id, Name name,
			com.qpeka.db.book.store.tuples.Constants.GENDER gender,
			Address address, Set<CATEGORY> interests,
			com.qpeka.db.book.store.tuples.Constants.USERLEVEL userlevel,
			com.qpeka.db.book.store.tuples.Constants.USERTYPE type,
			List<BookMark> bookMarks, int age, Date dob, String nationality,
			String imageFile, String email, String phone, String penName,
			LANGUAGES rLang, LANGUAGES wLang, String desc) {
		super();
		this.userName = userName;
		this._id = _id;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.interests = interests;
		this.userlevel = userlevel;
		this.type = type;
		this.bookMarks = bookMarks;
		this.age = age;
		this.dob = dob;
		this.nationality = nationality;
		this.imageFile = imageFile;
		this.email = email;
		this.phone = phone;
		this.penName = penName;
		this.rLang.add(rLang);
		this.wLang.add(wLang);
		this.desc = desc;
	}

	public User(String userName, String _id, Name name,
			com.qpeka.db.book.store.tuples.Constants.GENDER gender,
			Address address, Set<CATEGORY> interests,
			com.qpeka.db.book.store.tuples.Constants.USERLEVEL userlevel,
			com.qpeka.db.book.store.tuples.Constants.USERTYPE type,
			List<BookMark> bookMarks, int age, Date dob, String nationality,
			String imageFile, String email, String phone, String penName,
			Set<LANGUAGES> rLang, Set<LANGUAGES> wLang, String desc) {
		super();
		this.userName = userName;
		this._id = _id;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.interests = interests;
		this.userlevel = userlevel;
		this.type = type;
		this.bookMarks = bookMarks;
		this.age = age;
		this.dob = dob;
		this.nationality = nationality;
		this.imageFile = imageFile;
		this.email = email;
		this.phone = phone;
		this.penName = penName;
		this.rLang = rLang;
		this.wLang = wLang;
		this.desc = desc;
	}

	public User(String userName, String _id, Name name,
			com.qpeka.db.book.store.tuples.Constants.GENDER gender,
			String email, Address address,
			Set<CATEGORY> interests, USERLEVEL level, List<BookMark> bookMarks,
			int age, Date dob, String nationality, String imageFile, String phone, USERTYPE type) {
		super();
		this.userName = userName;
		this._id = _id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.interests = interests;
		this.userlevel = level;
		this.bookMarks = bookMarks;
		this.age = age;
		this.dob = dob;
		this.nationality = nationality;
		this.imageFile = imageFile;
		this.phone = phone;
		this.type = type;
	}
	
	public User(String userName, String _id, Name name,
			com.qpeka.db.book.store.tuples.Constants.GENDER gender,
			String email, Address address,
			Set<CATEGORY> interests, USERLEVEL level, List<BookMark> bookMarks,
			int age, Date dob, String nationality, String imageFile, String phone, USERTYPE type, LANGUAGES rLang, LANGUAGES wLang) {
		super();
		this.userName = userName;
		this._id = _id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.interests = interests;
		this.userlevel = level;
		this.bookMarks = bookMarks;
		this.age = age;
		this.dob = dob;
		this.nationality = nationality;
		this.imageFile = imageFile;
		this.phone = phone;
		this.type = type;
		this.rLang.add(rLang);
		this.wLang.add(wLang);
		
	}
	
	public User(String userName, String _id, Name name,
			com.qpeka.db.book.store.tuples.Constants.GENDER gender,
			String email, Address address,
			Set<CATEGORY> interests, USERLEVEL level, List<BookMark> bookMarks,
			int age, Date dob, String nationality, String imageFile, String phone, USERTYPE type, String penName, String desc) {
		super();
		this.userName = userName;
		this._id = _id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.interests = interests;
		this.userlevel = level;
		this.bookMarks = bookMarks;
		this.age = age;
		this.dob = dob;
		this.nationality = nationality;
		this.imageFile = imageFile;
		this.phone = phone;
		this.type = type;
		this.penName = penName;
		this.desc = desc;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Set<LANGUAGES> getrLang() {
		return rLang;
	}
	public void addrLang(LANGUAGES rLang) {
		this.rLang.add(rLang);
	}
	public void addwLang(LANGUAGES wLang) {
		this.wLang.add(wLang);
	}
	public void setrLang(Set<LANGUAGES> rLang) {
		this.rLang = rLang;
	}
	public Set<LANGUAGES> getwLang() {
		return wLang;
	}
	public void setwLang(Set<LANGUAGES> wLang) {
		this.wLang = wLang;
	}
	public String getPenName() {
		return penName;
	}
	public void setPenName(String penName) {
		this.penName = penName;
	}

	public USERLEVEL getUserlevel() {
		return userlevel;
	}
	public void setUserlevel(USERLEVEL userlevel) {
		this.userlevel = userlevel;
	}
	public void setType(USERTYPE type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Set<CATEGORY> getInterests() {
		return interests;
	}
	public void setInterests(Set<CATEGORY> interests) {
		this.interests = interests;
	}
	public com.qpeka.db.book.store.tuples.Constants.USERTYPE getType() {
		return type;
	}
	public void setType(USERLEVEL type) {
		this.userlevel = type;
	}
	public List<BookMark> getBookMarks() {
		return bookMarks;
	}
	public void setBookMarks(List<BookMark> bookMarks) {
		this.bookMarks = bookMarks;
	}
	
	public boolean isWriter()
	{
		return this.type == com.qpeka.db.book.store.tuples.Constants.USERTYPE.AUTHOR;
	}
	
	public DBObject toDBObject(boolean insert)
	{
		BasicDBObject dbObj = new BasicDBObject();
		
		if(!insert)
			dbObj.put(ID, new ObjectId(_id));
		
		dbObj.put(USERNAME, this.userName);
		dbObj.put(NAME, name.toDBObject());
		dbObj.put(DOB, dob.getTime());
		dbObj.put(USERTYPE, type.toString());
		dbObj.put(USERLEVEL, userlevel.toString());
		dbObj.put(GENDER, gender.toString());
		dbObj.put(NATIONALITY, nationality);
		dbObj.put(IMAGEFILE, imageFile);
		dbObj.put(ADDRESS, address.toDBObject());
		dbObj.put(INTERESTS, interests.toString());
		dbObj.put(EMAIL, email);
		dbObj.put(AGE, age);
		dbObj.put(DESC, desc);
		dbObj.put(RLANG, rLang.toString());
		dbObj.put(WLANG, wLang.toString());
		dbObj.put(PENNAME, penName);
		
		Set<BasicDBObject> bookMarkSet = new HashSet<BasicDBObject>();
		for(BookMark mark : bookMarks)
		{
			bookMarkSet.add((BasicDBObject)mark.toDBObject());
		}
		
		dbObj.put(BOOKMARKS, bookMarkSet);
		dbObj.put(PHONE, phone);
		
		return dbObj;
	}
	
	public static void main(String[] args)
	{
		BasicDBObject d = new BasicDBObject();
		d.put("name", new Name("df", "nm", "ln").toDBObject());
		
		System.out.println(((BasicDBObject)d.get("name")).getString(Name.FIRSTNAME));
		
	}
	
	public static User getUserfromDBObject(BasicDBObject obj)
	{
		//return new User(obj.getString(ID), Name.getNamefromDBObject((BasicDBObject)obj.get(NAME)), com.qpeka.db.book.store.tuples.Constants.GENDER.valueOf(obj.getString(GENDER)), identities, address, interests, type, bookMarks, age, dob, nationality, imageFile)
		BasicDBObject name = (BasicDBObject)obj.get("name");
		Set<LANGUAGES> rlang = new HashSet<Constants.LANGUAGES>();
		Set<LANGUAGES> wlang = new HashSet<Constants.LANGUAGES>();
		
		try 
		{
			JSONArray ja = new JSONArray(obj.getString(RLANG));
			for(int i = 0 ; i < ja.length() ; i++)
			{
				rlang.add(LANGUAGES.valueOf(ja.getString(i)));
			}
			
			ja = new JSONArray(obj.getString(WLANG));
			for(int i = 0 ; i < ja.length() ; i++)
			{
				wlang.add(LANGUAGES.valueOf(ja.getString(i)));
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new User(obj.getString(USERNAME), obj.getString(ID), new Name(name.getString(Name.FIRSTNAME), name.getString(Name.MIDDLENAME), name.getString(Name.LASTNAME)),
				com.qpeka.db.book.store.tuples.Constants.GENDER.valueOf(obj.getString(GENDER)),new Address("", "", "", "", "", ""), new HashSet<CATEGORY>(), com.qpeka.db.book.store.tuples.Constants.USERLEVEL.valueOf(obj.getString(USERLEVEL)),
				com.qpeka.db.book.store.tuples.Constants.USERTYPE.valueOf(obj.getString(USERTYPE)),
				new ArrayList(), obj.getInt(AGE), new Date(obj.getLong(DOB)), obj.getString(NATIONALITY), obj.getString(IMAGEFILE)
				, obj.getString(EMAIL), obj.getString(PHONE), obj.getString(PENNAME), rlang, wlang, obj.getString(DESC));
	}
}
