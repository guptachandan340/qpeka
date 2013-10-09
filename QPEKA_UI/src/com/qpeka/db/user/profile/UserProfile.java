package com.qpeka.db.user.profile;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.qpeka.db.Badges;
import com.qpeka.db.Constants;
import com.qpeka.db.Constants.BADGES;
import com.qpeka.db.Constants.GENDER;
import com.qpeka.db.Constants.USERLEVEL;
import com.qpeka.db.Constants.USERTYPE;
import com.qpeka.db.Genre;
import com.qpeka.db.Languages;

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

public class UserProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6528874005921471552L;

	// Account information
	public static final String USERID = "userid";

	/*
	 * Profile Information
	 */
	// Personal Information
	public static final String NAME = "name";
	public static final String GENDER = "gender";
	public static final String DOB = "dob";
	public static final String AGE = "age";
	public static final String NATIONALITY = "nationality";
	public static final String WEBSITE = "website";
	public static final String BIOGRAPHY = "biography";
	public static final String PROFILEPIC = "profilepic";
	//Terms and conditions
	public static final String TNC = "tnc";

	// Address
	public static final String ADDRESS = "address";
	// public static final String PHONE = "phone";
	// Interests
	public static final String INTERESTS = "interests";
	// Languages
	public static final String RLANG = "rlang";
	public static final String WLANG = "wlang";
	// QPeka User management information
	public static final String USERBADGES = "userbadges";
	public static final String USERTYPE = "usertype";
	public static final String USERLEVEL = "userlevel";
	public static final String USERPOINTS = "userpoints";
	// Bookmarks
	public static final String BOOKMARKS = "bookmarks";
	

	// These attributes maps to the columns of the userprofile table.
	private long userid;
	private Name name = null;
	private GENDER gender = Constants.GENDER.UNSPECIFIED;
	private Date dob = new Date();
	private short age = 0;
	private short nationality = 0;
	private String website = "";
	private String biography = "";
	private long profilepic = 0;
	// terms and conditions : 1 for agree and 0 for not agree
	private short tnc = 0;
	private USERLEVEL userlevel = Constants.USERLEVEL.FREE;

	private Address address = new Address();

	private Set<Genre> interests = new HashSet<Genre>();
	private Set<Languages> rLang = new HashSet<Languages>();
	private Set<Languages> wLang = new HashSet<Languages>();

	private Set<Badges> userbadges = new HashSet<Badges>();
	private Map<String, Integer> userpoints = new HashMap<String, Integer>();
	private USERTYPE usertype = Constants.USERTYPE.READER;
	// private List<BookMark> bookmarks = new ArrayList<BookMark>();

	// These attributes represents whether the above attributes has been
	// modified since being read from the database.
	protected boolean useridModified = false;
	protected boolean nameModified = false;
	protected boolean genderModified = false;
	protected boolean dobModified = false;
	protected boolean ageModified = false;
	protected boolean nationalityModified = false;
	protected boolean websiteModified = false;
	protected boolean biographyModified = false;
	protected boolean profilepicModified = false;
	protected boolean tncModified = false;
	protected boolean addressModified = false;
	protected boolean interestsModified = false;
	protected boolean rLangModified = false;
	protected boolean wLangModified = false;
	protected boolean userbadgesModified = false;
	protected boolean userpointsModified = false;
	protected boolean userlevelModified = false;
	protected boolean usertypeModified = false;
	
	/**
	 * This attribute represents whether the primitive attribute profile pic is
	 * null.
	 */
	protected boolean profilepicNull = true;
	//public static UserProfile instance = null;

	// protected boolean bookmarksModified = false;

	/*
	 * Constructors
	 */

	public UserProfile() {
		super();
	}

	public UserProfile(long userid, Name name, Constants.GENDER gender) {
		super();
		this.userid = userid;
		// this.email = email;
		this.name = name;
		this.gender = gender;
	}

	public UserProfile(long userid, Name name,
			com.qpeka.db.Constants.GENDER gender, Date dob, short age,
			short nationality, String website, String biography,
			int profilepic, short tnc, Address address, Set<Genre> interests,
			Set<Languages> rLang, Set<Languages> wLang, Set<Badges> userbadges,
			Map<String, Integer> userpoints, USERLEVEL userlevel,
			USERTYPE usertype) {// , List<BookMark>
								// bookmarks) {
		super();
		this.userid = userid;
		// this.email = email;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.age = age;
		this.nationality = nationality;
		this.website = website;
		this.biography = biography;
		this.profilepic = profilepic;
		this.tnc = tnc;
		this.address = address;
		this.interests = interests;
		this.rLang = rLang;
		this.wLang = wLang;
		this.userbadges = userbadges;
		this.userpoints = userpoints;
		this.userlevel = userlevel;
		this.usertype = usertype;
		// this.bookmarks = bookmarks;
	}

	public UserProfile(long userid, Name name,
			com.qpeka.db.Constants.GENDER gender, Date dob, short age,
			short nationality, String website, String biography, int profilepic, short tnc) {
		super();
		this.userid = userid;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.age = age;
		this.nationality = nationality;
		this.website = website;
		this.biography = biography;
		this.profilepic = profilepic;
		this.tnc = tnc;
	}

	public static UserProfile getInstance() {
		return new UserProfile(); //(instance == null ? (instance = new UserProfile()) : instance);
	}

	/*
	 * Getters and setters for attributes
	 */
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
		this.useridModified = true;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
		this.nameModified = true;
	}

	public GENDER getGender() {
		return gender;
	}

	public void setGender(GENDER gender) {
		this.gender = gender;
		this.genderModified = true;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
		this.dobModified = true;
	}

	public short getAge() {
		return age;
	}

	public void setAge(short age) {
		this.age = age;
		this.ageModified = true;
	}

	public short getNationality() {
		return nationality;
	}

	public void setNationality(short nationality) {
		this.nationality = nationality;
		this.nationalityModified = true;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
		this.websiteModified = true;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
		this.biographyModified = true;
	}

	public long getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(long profilepic) {
		this.profilepic = profilepic;
		this.profilepicNull = false;
		this.profilepicModified = true;
	}

	public short getTnc() {
		return tnc;
	}

	public void setTnc(short tnc) {
		this.tnc = tnc;
		this.tncModified = true;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
		this.addressModified = true;
	}

	public Set<Genre> getInterests() {
		return interests;
	}

	public void setInterests(Set<Genre> interests) {
		this.interests = interests;
		this.interestsModified = true;
	}

	public Set<Languages> getRLang() {
		return rLang;
	}

	public void setRLang(Set<Languages> rLang) {
		this.rLang = rLang;
		this.rLangModified = true;
	}

	public Set<Languages> getWLang() {
		return wLang;
	}

	public void setWLang(Set<Languages> wLang) {
		this.wLang = wLang;
		this.wLangModified = true;
	}

	public Set<Badges> getUserbadges() {
		return userbadges;
	}

	public void setUserbadges(Set<Badges> userbadges) {
		this.userbadges = userbadges;
		this.userbadgesModified = true;
	}

	public Map<String, Integer> getUserpoints() {
		return userpoints;
	}

	public void setUserpoints(Map<String, Integer> userpoints) {
		this.userpoints = userpoints;
		this.userpointsModified = true;
	}

	public USERLEVEL getUserlevel() {
		return userlevel;
	}

	public void setUserlevel(USERLEVEL userlevel) {
		this.userlevel = userlevel;
		this.userlevelModified = true;
	}

	public USERTYPE getUsertype() {
		return usertype;
	}

	public void setUsertype(USERTYPE usertype) {
		this.usertype = usertype;
		this.usertypeModified = true;
	}

	// public List<BookMark> getBookmarks() {
	// return bookmarks;
	// }
	//
	// public void setBookmarks(List<BookMark> bookmarks) {
	// this.bookmarks = bookmarks;
	// this.bookmarksModified = true;
	// }

	/*
	 * Getters and setters for attribute modified status
	 */
	public boolean isUseridModified() {
		return useridModified;
	}

	public void setUseridModified(boolean useridModified) {
		this.useridModified = useridModified;
	}

	public boolean isNameModified() {
		return nameModified;
	}

	public void setNameModified(boolean nameModified) {
		this.nameModified = nameModified;
	}

	public boolean isGenderModified() {
		return genderModified;
	}

	public void setGenderModified(boolean genderModified) {
		this.genderModified = genderModified;
	}

	public boolean isDobModified() {
		return dobModified;
	}

	public void setDobModified(boolean dobModified) {
		this.dobModified = dobModified;
	}

	public boolean isAgeModified() {
		return ageModified;
	}

	public void setAgeModified(boolean ageModified) {
		this.ageModified = ageModified;
	}

	public boolean isNationalityModified() {
		return nationalityModified;
	}

	public void setNationalityModified(boolean nationalityModified) {
		this.nationalityModified = nationalityModified;
	}

	public boolean isWebsiteModified() {
		return websiteModified;
	}

	public void setWebsiteModified(boolean websiteModified) {
		this.websiteModified = websiteModified;
	}

	public boolean isBiographyModified() {
		return biographyModified;
	}

	public void setBiographyModified(boolean biographyModified) {
		this.biographyModified = biographyModified;
	}

	public boolean isProfilepicModified() {
		return profilepicModified;
	}

	public void setProfilepicModified(boolean profilepicModified) {
		this.profilepicModified = profilepicModified;
	}

	public boolean isProfilepicNull() {
		return profilepicNull;
	}

	public void setProfilepicNull(boolean value) {
		this.profilepicNull = value;
		this.profilepicModified = true;
	}
	

	public boolean isTncModified() {
		return tncModified;
	}

	public void setTncModified(boolean tncModified) {
		this.tncModified = tncModified;
	}
	
	public boolean isAddressModified() {
		return addressModified;
	}

	public void setAddressModified(boolean addressModified) {
		this.addressModified = addressModified;
	}

	public boolean isInterestsModified() {
		return interestsModified;
	}

	public void setInterestsModified(boolean interestsModified) {
		this.interestsModified = interestsModified;
	}

	public boolean isrLangModified() {
		return rLangModified;
	}

	public void setrLangModified(boolean rLangModified) {
		this.rLangModified = rLangModified;
	}

	public boolean iswLangModified() {
		return wLangModified;
	}

	public void setwLangModified(boolean wLangModified) {
		this.wLangModified = wLangModified;
	}

	public boolean isUserbadgesModified() {
		return userbadgesModified;
	}

	public void setUserbadgesModified(boolean userbadgesModified) {
		this.userbadgesModified = userbadgesModified;
	}

	public boolean isUserpointsModified() {
		return userpointsModified;
	}

	public void setUserpointsModified(boolean userpointsModified) {
		this.userpointsModified = userpointsModified;
	}

	public boolean isUserlevelModified() {
		return userlevelModified;
	}

	public void setUserlevelModified(boolean userlevelModified) {
		this.userlevelModified = userlevelModified;
	}

	public boolean isUsertypeModified() {
		return usertypeModified;
	}

	public void setUsertypeModified(boolean usertypeModified) {
		this.usertypeModified = usertypeModified;
	}

	// public boolean isBookmarksModified() {
	// return bookmarksModified;
	// }
	//
	// public void setBookmarksModified(boolean bookmarksModified) {
	// this.bookmarksModified = bookmarksModified;
	// }

	/*
	 * Verify User type
	 */
	public boolean isWriter() {
		// TODO change this. this wont work. It should look for badges in set
		return this.userbadges.contains(BADGES.WRITER.toString());
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other) {
		if (_other == null) {
			return false;
		}

		if (_other == this) {
			return true;
		}

		if (!(_other instanceof UserProfile)) {
			return false;
		}

		final UserProfile _cast = (UserProfile) _other;
		if (userid != _cast.userid) {
			return false;
		}

		// if (useridModified != _cast.useridModified) {
		// return false;
		// }

		if (name == null ? _cast.name != name : !name.equals(_cast.name)) {
			return false;
		}

		if (nameModified != _cast.nameModified) {
			return false;
		}

		if (gender == null ? _cast.gender != gender : !gender
				.equals(_cast.gender)) {
			return false;
		}

		if (genderModified != _cast.genderModified) {
			return false;
		}

		if (dob != _cast.dob) {
			return false;
		}

		// if (dobNull != _cast.dobNull) {
		// return false;
		// }

		if (dobModified != _cast.dobModified) {
			return false;
		}

		if (age != _cast.age) {
			return false;
		}

		if (ageModified != _cast.ageModified) {
			return false;
		}

		if (_cast.nationality != nationality) {
			return false;
		}

		// if (nationalityNull != _cast.nationalityNull) {
		// return false;
		// }

		if (nationalityModified != _cast.nationalityModified) {
			return false;
		}

		if (website == null ? _cast.website != website : !website
				.equals(_cast.website)) {
			return false;
		}

		if (websiteModified != _cast.websiteModified) {
			return false;
		}

		if (biography == null ? _cast.biography != biography : !biography
				.equals(_cast.biography)) {
			return false;
		}

		if (biographyModified != _cast.biographyModified) {
			return false;
		}

		if (profilepic != _cast.profilepic) {
			return false;
		}

		if (profilepicNull != _cast.profilepicNull) {
			return false;
		}

		if (profilepicModified != _cast.profilepicModified) {
			return false;
		}

		if (tnc != _cast.tnc) {
			return false;
		}

		if (tncModified != _cast.tncModified) {
			return false;
		}

		
		if (address == null ? _cast.address != address : !address
				.equals(_cast.address)) {
			return false;
		}

		if (addressModified != _cast.addressModified) {
			return false;
		}

		if (interests == null ? _cast.interests != interests : !interests
				.equals(_cast.interests)) {
			return false;
		}

		if (interestsModified != _cast.interestsModified) {
			return false;
		}

		if (rLang == null ? _cast.rLang != rLang : !rLang.equals(_cast.rLang)) {
			return false;
		}

		if (rLangModified != _cast.rLangModified) {
			return false;
		}

		if (wLang == null ? _cast.wLang != wLang : !wLang.equals(_cast.wLang)) {
			return false;
		}

		if (wLangModified != _cast.wLangModified) {
			return false;
		}

		if (userbadges == null ? _cast.userbadges != userbadges : !userbadges
				.equals(_cast.userbadges)) {
			return false;
		}

		if (userbadgesModified != _cast.userbadgesModified) {
			return false;
		}

		if (usertype == null ? _cast.usertype != usertype : !usertype
				.equals(_cast.usertype)) {
			return false;
		}

		if (usertypeModified != _cast.usertypeModified) {
			return false;
		}

		if (userlevel == null ? _cast.userlevel != userlevel : !userlevel
				.equals(_cast.userlevel)) {
			return false;
		}

		if (userlevelModified != _cast.userlevelModified) {
			return false;
		}

		if (userpoints == null ? _cast.userpoints != userpoints : userpoints
				.equals(_cast.userpoints)) {
			return false;
		}

		if (userpointsModified != _cast.userpointsModified) {
			return false;
		}

		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode() {
		int _hashCode = 0;

		_hashCode = 29 * _hashCode + (int) userid;
		// _hashCode = 29 * _hashCode + (useridModified ? 1 : 0);
		if (name != null) {
			_hashCode = 29 * _hashCode + name.hashCode();
		}

		_hashCode = 29 * _hashCode + (nameModified ? 1 : 0);
		if (gender != null) {
			_hashCode = 29 * _hashCode + gender.hashCode();
		}

		_hashCode = 29 * _hashCode + (genderModified ? 1 : 0);
		_hashCode = 29 * _hashCode + dob.hashCode();
		// _hashCode = 29 * _hashCode + (dobNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (dobModified ? 1 : 0);
		_hashCode = 29 * _hashCode + age;
		_hashCode = 29 * _hashCode + (ageModified ? 1 : 0);
		_hashCode = 29 * _hashCode + nationality;
		// _hashCode = 29 * _hashCode + (nationalityNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (nationalityModified ? 1 : 0);
		if (website != null) {
			_hashCode = 29 * _hashCode + website.hashCode();
		}

		_hashCode = 29 * _hashCode + (websiteModified ? 1 : 0);
		if (biography != null) {
			_hashCode = 29 * _hashCode + biography.hashCode();
		}

		_hashCode = 29 * _hashCode + (biographyModified ? 1 : 0);
		_hashCode = (int) (29 * _hashCode + profilepic);
		_hashCode = 29 * _hashCode + (profilepicNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (profilepicModified ? 1 : 0);
		_hashCode = 29 * _hashCode + tnc;
		_hashCode = 29 * _hashCode + (tncModified ? 1 : 0);
		
		if (address != null) {
			_hashCode = 29 * _hashCode + address.hashCode();
		}

		_hashCode = 29 * _hashCode + (addressModified ? 1 : 0);
		if (interests != null) {
			_hashCode = 29 * _hashCode + interests.hashCode();
		}

		_hashCode = 29 * _hashCode + (interestsModified ? 1 : 0);
		if (rLang != null) {
			_hashCode = 29 * _hashCode + rLang.hashCode();
		}

		_hashCode = 29 * _hashCode + (rLangModified ? 1 : 0);
		if (wLang != null) {
			_hashCode = 29 * _hashCode + wLang.hashCode();
		}

		_hashCode = 29 * _hashCode + (wLangModified ? 1 : 0);
		if (userbadges != null) {
			_hashCode = 29 * _hashCode + userbadges.hashCode();
		}

		_hashCode = 29 * _hashCode + (userbadgesModified ? 1 : 0);
		if (usertype != null) {
			_hashCode = 29 * _hashCode + usertype.hashCode();
		}

		_hashCode = 29 * _hashCode + (usertypeModified ? 1 : 0);
		if (userlevel != null) {
			_hashCode = 29 * _hashCode + userlevel.hashCode();
		}

		_hashCode = 29 * _hashCode + (userlevelModified ? 1 : 0);
		if (userpoints != null) {
			_hashCode = 29 * _hashCode + userpoints.hashCode();
		}

		_hashCode = 29 * _hashCode + (userpointsModified ? 1 : 0);

		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer();

		ret.append("Userprofile: ");
		ret.append(USERID + "=" + userid);
		ret.append(", " + NAME + "=" + name.toString());
		ret.append(", " + GENDER + "=" + gender);
		ret.append(", " + DOB + "=" + dob.toString());
		ret.append(", " + AGE + "=" + age);
		ret.append(", " + NATIONALITY + "=" + nationality);
		ret.append(", " + WEBSITE + "=" + website);
		ret.append(", " + BIOGRAPHY + "=" + biography);
		ret.append(", " + PROFILEPIC + "=" + profilepic);
		ret.append(", " + TNC + "=" + tnc);
		ret.append(", " + ADDRESS + "=" + address.toString());
		ret.append(", " + INTERESTS + "" + interests.toString());
		ret.append(", " + RLANG + "=" + rLang.toString());
		ret.append(", " + WLANG + "=" + wLang.toString());
		ret.append(", " + USERBADGES + "=" + userbadges.toString());
		ret.append(", " + USERTYPE + "=" + usertype.toString());
		ret.append(", " + USERLEVEL + "=" + userlevel.toString());
		ret.append(", " + USERPOINTS + "=" + userpoints.toString());

		return ret.toString();
	}

	// public static void main(String[] args) {
	// BasicDBObject d = new BasicDBObject();
	// d.put("name", new Name("df", "nm", "ln").toDBObject());
	//
	// System.out.println(((BasicDBObject) d.get("name"))
	// .getString(Name.FIRSTNAME));
	//
	// }
	//
	// public DBObject toDBObject(boolean insert) {
	// BasicDBObject dbObj = new BasicDBObject();
	//
	// if (!insert)
	// dbObj.put(ID, new ObjectId(_id));
	// BasicDBList rbdl = new BasicDBList();
	// for (LANGUAGES l : rLang)
	// rbdl.add(l.toString());
	// BasicDBList wbdl = new BasicDBList();
	// for (LANGUAGES l : wLang)
	// wbdl.add(l.toString());
	// dbObj.put(USERNAME, this.userName);
	// dbObj.put(NAME, name.toDBObject());
	// dbObj.put(DOB, dob.getTime());
	// dbObj.put(USERTYPE, type.toString());
	// dbObj.put(USERLEVEL, userlevel.toString());
	// dbObj.put(GENDER, gender.toString());
	// dbObj.put(NATIONALITY, nationality);
	// dbObj.put(IMAGEFILE, imageFile);
	// dbObj.put(ADDRESS, address.toDBObject());
	// dbObj.put(INTERESTS, interests.toString());
	// dbObj.put(EMAIL, email);
	// dbObj.put(AGE, age);
	// dbObj.put(DESC, desc);
	// dbObj.put(RLANG, rbdl);
	// dbObj.put(WLANG, wbdl);
	// dbObj.put(PENNAME, penName);
	//
	// Set<BasicDBObject> bookMarkSet = new HashSet<BasicDBObject>();
	// for (BookMark mark : bookMarks) {
	// bookMarkSet.add((BasicDBObject) mark.toDBObject());
	// }
	//
	// dbObj.put(BOOKMARKS, bookMarkSet);
	// dbObj.put(PHONE, phone);
	//
	// return dbObj;
	// }
	//
	// public static User getUserfromDBObject(BasicDBObject obj) {
	// // return new User(obj.getString(ID),
	// // Name.getNamefromDBObject((BasicDBObject)obj.get(NAME)),
	// //
	// com.qpeka.db.book.store.tuples.Constants.GENDER.valueOf(obj.getString(GENDER)),
	// // identities, address, interests, type, bookMarks, age, dob,
	// // nationality, imageFile)
	// BasicDBObject name = (BasicDBObject) obj.get("name");
	// Set<LANGUAGES> rlang = new HashSet<Constants.LANGUAGES>();
	// Set<LANGUAGES> wlang = new HashSet<Constants.LANGUAGES>();
	//
	// BasicDBObject addr = (BasicDBObject) obj.get(User.ADDRESS);
	// try {
	// JSONArray ja = new JSONArray(obj.getString(RLANG));
	// for (int i = 0; i < ja.length(); i++) {
	// rlang.add(LANGUAGES.valueOf(ja.getString(i)));
	// }
	//
	// ja = new JSONArray(obj.getString(WLANG));
	// for (int i = 0; i < ja.length(); i++) {
	// wlang.add(LANGUAGES.valueOf(ja.getString(i)));
	// }
	//
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return new User(obj.getString(USERNAME), obj.getString(ID),
	// new Name(name.getString(Name.FIRSTNAME), name
	// .getString(Name.MIDDLENAME), name
	// .getString(Name.LASTNAME)),
	// com.qpeka.db.book.store.tuples.Constants.GENDER.valueOf(obj
	// .getString(GENDER)), new Address(
	// addr.getString(Address.CITY),
	// addr.getString(Address.STATE),
	// addr.getString(Address.ADDRESSLINE1),
	// addr.getString(Address.ADDRESSLINE2),
	// addr.getString(Address.ADDRESSLINE3),
	// addr.getString(Address.PINCODE)),
	// new HashSet<CATEGORY>(),
	// com.qpeka.db.book.store.tuples.Constants.USERLEVEL.valueOf(obj
	// .getString(USERLEVEL)),
	// com.qpeka.db.book.store.tuples.Constants.USERTYPE.valueOf(obj
	// .getString(USERTYPE)), new ArrayList(),
	// obj.getInt(AGE), new Date(obj.getLong(DOB)),
	// obj.getString(NATIONALITY), obj.getString(IMAGEFILE),
	// obj.getString(EMAIL), obj.getString(PHONE),
	// obj.getString(PENNAME), rlang, wlang, obj.getString(DESC));
	// }
}
