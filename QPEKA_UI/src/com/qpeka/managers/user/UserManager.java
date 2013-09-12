package com.qpeka.managers.user;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import com.qpeka.db.Category;
import com.qpeka.db.Constants.GENDER;
import com.qpeka.db.Constants.STATUS;
import com.qpeka.db.Constants.TYPE;
import com.qpeka.db.Constants.USERLEVEL;
import com.qpeka.db.Constants.USERTYPE;
import com.qpeka.db.Files;
import com.qpeka.db.Genre;
import com.qpeka.db.Languages;
import com.qpeka.db.UserFieldVisibility;
import com.qpeka.db.exceptions.CategoryException;
import com.qpeka.db.exceptions.CountryException;
import com.qpeka.db.exceptions.FileException;
import com.qpeka.db.exceptions.GenreException;
import com.qpeka.db.exceptions.LanguagesException;
import com.qpeka.db.exceptions.QpekaException;
import com.qpeka.db.exceptions.user.AddressException;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.exceptions.user.UserInterestsException;
import com.qpeka.db.exceptions.user.UserLanguageException;
import com.qpeka.db.exceptions.user.UserProfileException;
import com.qpeka.db.handler.AbstractHandler;
import com.qpeka.db.handler.CategoryHandler;
import com.qpeka.db.handler.CountryHandler;
import com.qpeka.db.handler.FilesHandler;
import com.qpeka.db.handler.GenreHandler;
import com.qpeka.db.handler.LanguagesHandler;
import com.qpeka.db.handler.user.AddressHandler;
import com.qpeka.db.handler.user.UserFieldVisibilityHandler;
import com.qpeka.db.handler.user.UserHandler;
import com.qpeka.db.handler.user.UserInterestsHandler;
import com.qpeka.db.handler.user.UserLanguageHandler;
import com.qpeka.db.handler.user.UserProfileHandler;
import com.qpeka.db.user.User;
import com.qpeka.db.user.profile.Address;
import com.qpeka.db.user.profile.Name;
import com.qpeka.db.user.profile.UserInterests;
import com.qpeka.db.user.profile.UserLanguage;
import com.qpeka.db.user.profile.UserProfile;
import com.qpeka.managers.FilesManager;
import com.qpeka.security.bcrypt.BCrypt;
import com.qpeka.services.Response.ServiceResponseManager;
import com.qpeka.utils.Utils;

public class UserManager {
	private static UserManager instance = null;
	UserHandler userHandler = new UserHandler();

	public UserManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static UserManager getInstance() {
		return (instance == null ? (instance = new UserManager()) : instance);
	}
	
	protected static final Logger logger = Logger
			.getLogger(UserManager.class);

	/***************************** REGISTRATION MODULE ********************************/

	/*
	 * @param firstName
	 * 
	 * @param lastName
	 * 
	 * @param email
	 * 
	 * @param username
	 * 
	 * @param password
	 * 
	 * @param gender
	 * 
	 * @param dob
	 * 
	 * @param langugaes
	 * 
	 * @return
	 */

	public Map<String, Object> registerUser(
			MultivaluedMap<String, String> formParams) throws UserException {	
		// Create User
		User user = User.getInstance();
		// Create User Profile
		UserProfile userProfile = UserProfile.getInstance();
		if (userProfile.getName() == null) {
			userProfile.setName(Name.getInstance());
		}
		user.setCreated(System.currentTimeMillis() / 1000);
		user.setLastaccess(0);
		user.setLastlogin(0);
		user.setStatus((short) STATUS.DEFAULT.ordinal());
		user.setType((short) TYPE.AUTHENTIC.ordinal());
	
		//registerUserInfo(formParams, user, userProfile);
		Set<String> keySet = formParams.keySet();
		for (String key : keySet) {
			if (!key.equalsIgnoreCase(Languages.LANGUAGE)) {
				List<String> userInfo = formParams.get(key);
				if (!userInfo.isEmpty()) {
					for (String userInfoValue : userInfo) {
						if (userInfoValue != null
								&& !userInfoValue.equalsIgnoreCase("")) {
							registerUserInfo(key, userInfoValue, user,
									userProfile);
						}
					}
				}
			}
		}
		createPenName(userProfile, user);
		short responseStatus = 0;
		try {
			// Insert user to database;
			Long userid = UserHandler.getInstance().insert(user);
			if (userid > 0) {
				responseStatus = 200;
				userProfile.setUserid(userid);
				if (UserProfileHandler.getInstance().insert(userProfile) > 0) {
					responseStatus = 200;
					// store languages in user language table
					List<String> languages = formParams.get(Languages.LANGUAGE);
						updateUserLanguages(userid, "read",
								languages);
				} else {
					responseStatus = 215;
				}
			} else {
				responseStatus = 500;
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ServiceResponseManager.getInstance().readServiceResponse(responseStatus);
	}// end of registeruser() 

	/**
	 * Being called by registerUser module
	 * 
	 * @param key
	 * @param value
	 * @param user
	 * @param userProfile
	 */
	
	/* Will be used in future during usage of guava library
	 * 
	 * 
	 * private void registerUserInfo1(MultiValueMap<String, Object> formParams,
			User user, UserProfile userProfile) {
		System.out.println("hello");
		
		if(formParams.get(User.EMAIL) != null) {
			System.out.println(formParams.get(User.EMAIL));
		}
		long t1 = System.currentTimeMillis() / 1000;
		if(!formParams.getCollection(User.EMAIL).isEmpty()) {
			while (formParams.getCollection(User.EMAIL).iterator().hasNext()
					&& formParams.getCollection(User.EMAIL).iterator().next() != null) {
				System.out.println(formParams.getCollection(User.EMAIL)
						.iterator().next());
				Collection<Object> c = formParams.getCollection(User.EMAIL);
				System.out.println(c);
				Iterator<Object> it = c.iterator();
				while(it.hasNext()) {
					System.out.println(it.next());
				}
		
		}
		long t2 = System.currentTimeMillis() / 1000;
		System.out.println(t2 - t1);
	}*/
	
	
	public void registerUserInfo(String key, String value, User user,
			UserProfile userProfile) {
		if (key.equalsIgnoreCase(Name.FIRSTNAME)) {
			userProfile.getName().setFirstname(value);
			// name.setFirstname(value);
		} else if (key.equalsIgnoreCase(Name.LASTNAME)) {
			userProfile.getName().setLastname(value);
			// name.setLastname(value);
		} else if (key.equalsIgnoreCase(User.EMAIL)) {
			user.setEmail(value);
		} else if (key.equalsIgnoreCase(User.PASSWORD)) {
			user.setPassword(BCrypt.hashpw(value, BCrypt.gensalt()));
		} else if(key.equalsIgnoreCase(User.TYPE)) {
			 user.setType((short) TYPE.valueOf(value.toUpperCase()).ordinal());
		} else if (key.equalsIgnoreCase(UserProfile.GENDER)) {
			userProfile.setGender(GENDER.valueOf(value.toUpperCase()));
		} else if (key.equalsIgnoreCase(UserProfile.DOB)) {
			try {
				Date dateOfBirth = (Date) Utils.getFormatedDate().parse(value);
				userProfile.setDob(dateOfBirth);
				userProfile.setAge(deriveAge(dateOfBirth));
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				
				e.printStackTrace();
			}
		} else if(key.equalsIgnoreCase(UserProfile.TNC)) {
			userProfile.setTnc(Short.parseShort(value));
		} 
	}

	/**
	 * Create penName
	 * 
	 * @param userProfile
	 * @param user
	 * @throws UserException
	 *  
	 */
	public void createPenName(UserProfile userProfile, User user) throws UserException {
		char[] patternChar;
		patternChar = "._".toCharArray();
		List<Object> penNameComb = new ArrayList<Object>();

		try {
			short index = (short) user.getEmail().indexOf("@");
			String email = user.getEmail().substring(0, (index != -1 ? index : user.getEmail().length()));
			if (!UserManager.getInstance().userExists(email, false)) {
				user.setPenname(email);
			} else {
				// Create pennameList with Combination of fname and lname
				penNameComb.add(userProfile.getName().getFirstname()
						+ userProfile.getName().getLastname());
				penNameComb.add(userProfile.getName().getLastname()
						+ userProfile.getName().getFirstname());
				for (int i = 0; i < patternChar.length; i++) {
					penNameComb.add(userProfile.getName().getFirstname()
							+ patternChar[i]
							+ userProfile.getName().getLastname());
					penNameComb.add(userProfile.getName().getLastname()
							+ patternChar[i]
							+ userProfile.getName().getFirstname());
				}

				// TODO use findbyDynamicSelect() to find only penname.gave
				// error getLong() not matching with String

				// retrieve user object from databases to check whether
				// penNameExist

				List<User> penNameDbList = UserHandler.getInstance()
						.findByDynamicWhere(
								buildQuery("penname", penNameComb.size()),
								penNameComb);
		
				if (!penNameDbList.isEmpty() && penNameDbList != null) {
					for (User userList : penNameDbList) {
						// Use A - ( A AND B) i.e remove common elements
						penNameComb.remove(userList.getPenname());
					}
				}

				// Set first uncommon element to user object
				if (!penNameComb.isEmpty()) {
					user.setPenname(penNameComb.iterator().next().toString());
				}
			}
		} catch (UserException e) {
			throw new UserException(" Creating Pen Name Exception : ");
		}

	}

	/**
	 * METHOD FOR REGISTER AND EDIT PROFILE MODULE
	 * 
	 * Email or penname exists?
	 * 
	 * isAuthExist -> true if email isAUthExist -> false if penname
	 * 
	 * @throws UserException 
	 */

	public boolean userExists(String username, boolean isUserExist)
			throws UserException {

		// Returns false when userlist is empty else true (Email or penname
		// exists)
		// false -> email OR penname does not exists, true -> email or penname
		// exists
		return (!(!isUserExist ? UserHandler.getInstance()
				.findWherePennameEquals(username).isEmpty() : UserHandler.getInstance()
				.findWhereEmailEquals(username).isEmpty()));
	}// end of emailExists()

	
	/******************************* AUTHENTICATION MODULE ********************************/
	
	/**
	 * Authenticate User
	 * 
	 * isEmail -> true if email isEmail -> false if penname
	 * 
	 * @throws UserException
	 * @throws UserProfileException 
	 */
	public Map<String, Object> authenticateUser(String username,
			String password, boolean isEmail) throws UserException, UserProfileException {
		short responseStatus = 0;
		List<User> userList = null;
		try {
			userList = (!isEmail) ? UserHandler.getInstance()
					.findWherePennameEquals(username) : UserHandler
					.getInstance().findWhereEmailEquals(username);
		} catch (UserException _e) {
			throw new UserException("User Authentication Exception: "
					+ _e.getMessage(), _e);
		}

		if (!userList.isEmpty() && userList != null) {
			for (User user : userList) {
				if (user.getStatus() != 4 && user.getStatus() != 3) {
					if (BCrypt.checkpw(password, user.getPassword())) {
						// No need to set response for updateLastActivity
						// Reason : We shouldn't let the user that we are
						// tracking their lastaccess and lastlogin record.
						updateLastActivity(user.getUserid(), true);
						return createUserInfoMap(user);
					} else {
						responseStatus = 215;
					}
				} else {
					if (user.getStatus() == 3) {
						// TODO Set ErrorCode for informing user that he/ she is
						// blocked
						responseStatus = 64;
					} else if (user.getStatus() == 4) {
						// TODO set ErrorCode for informing user that he is
						// deleted
						responseStatus = 64;
					}
				}
			}
		} else {
			responseStatus = 215;
		}
		return ServiceResponseManager.getInstance()
				.readServiceResponse(responseStatus);
	}

	/**
	 * creating UserInfoMap from user and userprofile data
	 * @throws UserProfileException 
	 */
	public MultiValueMap<String, String> createUserInfoMap(User user)
			throws UserProfileException {
		MultiValueMap<String, String> userInfo = new MultiValueMap<String, String>();
		List<UserProfile> userprofile = null;
		if (UserProfile.getInstance().getName() == null) {
			UserProfile.getInstance().setName(Name.getInstance());
		}
		try {
			userprofile = UserProfileHandler.getInstance()
					.findWhereUseridEquals(user.getUserid());
		} catch (UserProfileException e) {
			throw new UserProfileException(
					"Authentication Map generation Exception at findByUserid : "
							+ e.getMessage(), e);
		}
		if (user != null) {
			userInfo.put((User.PROFILEID), user.getUserid());
			// Set PenName
			userInfo.put(User.PENNAME,
					user.getPenname() != null ? user.getPenname() : "");
			// Set Email
			userInfo.put(User.EMAIL,
					user.getEmail() != null ? user.getEmail() : "");
		}
		// TODO Set errorcode if obj or list is empty
		if (!userprofile.isEmpty() && userprofile != null) {
			for (UserProfile userProfile : userprofile) {
				// get first and last name
				retrieveFullName(userInfo, userProfile.getName());
				// get Gender
				userInfo.put(
						UserProfile.GENDER, userProfile
								.getGender());
				// get profilepic
				if (userProfile.getProfilepic() > 0) {
					userInfo.put(UserProfile.PROFILEPIC,
							retrieveProfilePic(userProfile.getProfilepic()));
				}
			}
		}
		return userInfo;
	}
	
	
	/**
	 * Needed by Authenticate, getProfile and viewOwnProfile Module
	 * (createInfoMap() and ViewOwnProfile()
	 * 
	 * @param profilepic
	 * @return 
	 */ 
	private String retrieveProfilePic(long profilepic) throws UserProfileException {
		String filePath = "";
		List<Files> files = null;
		try {
			files = FilesHandler.getInstance()
					.findWhereFileidEquals(profilepic);
			if (!files.isEmpty() && files != null) {
				filePath = files.iterator().next().getFilepath();
			}
		} catch (FileException e) {
			throw new UserProfileException(
					" Map generation Exception at profilepic : "
							+ e.getMessage(), e);
		}
		return filePath;
	}

	/**
	 * Needed by Authenticate, getProfile and viewOwnProfile Module
	 * (createInfoMap() and ViewOwnProfile()
	 * 
	 * @param userInfo
	 * @param userProfile
	 */
	private void retrieveFullName(MultiValueMap<String, String> userInfo,
			Name name) {
		userInfo.put(Name.FIRSTNAME, name
				.getFirstname());
		// get Last Name
		userInfo.put(Name.LASTNAME, name
				.getLastname());
		// Get MiddleName
		userInfo.put(Name.MIDDLENAME, (name.getMiddlename() != null ? name
				.getMiddlename() : ""));
	}

	/**
	 * update lastlogin or lastaccess
	 * 
	 * @throws UserException
	 */
	public void updateLastActivity(long userid, boolean isLastLogin)
			throws UserException {
		User user = User.getInstance();
		// isLastLogin = true when incoming data is LastLogin
		// isLastLogin = false when incoming data is LastAccess		
		if (!isLastLogin) {
			user.setLastaccess(System.currentTimeMillis() / 1000);
		} else {
			user.setLastlogin(System.currentTimeMillis() / 1000);
		}
		try {
			UserHandler.getInstance().update(userid, user);
		} catch (UserException e) {
			throw new UserException("Update Last Activity Exception : "
					+ e.getMessage(), e);
		}
	}
	

	
	/***************************** PASSWORD MODULE ********************************/
	
	/***
	 * VERIFY PASSWORD
	 *
	 * @param userid
	 * @throws UserException 
	 * 
	 */
	public boolean verifyPassword(long userid, String password)
			throws UserException {
		List<User> userList = null;
		boolean verifyresult = false;
		try {
			userList = UserHandler.getInstance().findWhereUseridEquals(userid);
		} catch (UserException _e) {
			throw new UserException("Verify password Exception: "
					+ _e.getMessage(), _e);
		}
		if (!userList.isEmpty() && userList != null) {
			for (User user : userList) {
				verifyresult = BCrypt.checkpw(password, user.getPassword());
			}
		}
		return verifyresult;
	}

	/**
	 * UPDATE PASSWORD
	 * 
	 * Change account password -
	 * 
	 * @throws UserException
	 */
	public Map<String, Object> changePassword(long userid,
			String currentPassword, String newPassword) throws UserException {
		short responseStatus = 0;
		if (verifyPassword(userid, currentPassword)) {
			try {
				User user = User.getInstance();
				user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
				UserHandler.getInstance().update(userid, user);
				responseStatus = 200;
			} catch (UserException _e) {
				throw new UserException("Update User Password Exception: "
						+ _e.getMessage(), _e);
			}
		} else {
			responseStatus = 215;
		}
		return ServiceResponseManager.getInstance().readServiceResponse(
				responseStatus);
	}// end of changePassword()

	/**
	 * 
	 * RESET PASSWORD
	 * 
	 * @throws UserException
	 */
	public String resetPassword(String authName, boolean isEmail)
			throws UserException {
		List<User> userList = null;
		String newPassword = "";
		try {
			userList = (!isEmail) ? UserHandler.getInstance()
					.findWherePennameEquals(authName) : UserHandler
					.getInstance().findWhereEmailEquals(authName);
		} catch (UserException _e) {
			throw new UserException("Reset user Password Exception: "
					+ _e.getMessage(), _e);
		}

		if (!userList.isEmpty() && userList != null) {
			for (User user : userList) {
				newPassword = RandomStringUtils.random(8, true, true);
				user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
				UserHandler.getInstance().update(user.getUserid(), user);
			}
		}
		return newPassword;
	} // end of reset password()
	
	
	/***************************** DELETE ACCOUNT MODULE ********************************/

	/**
	 * 
	 * @param userid
	 * @throws UserException 
	 */
	
	public Object deleteUser(long userid) throws UserException {
		User user = User.getInstance();
		user.setStatus((short) STATUS.DELETED.ordinal());
		try {
			UserHandler.getInstance().update(userid, user);
			return ServiceResponseManager.getInstance().readServiceResponse(200);
			// TODO Close the session and logout the user and redirecting to home page
		} catch (UserException e) {
			throw new UserException("Delete User Exception : ");
		}
	}
	/***************************** GET PROFILE MODULE ********************************/
	
	/**
	 * 
	 * @param userid
	 * @return
	 * @throws UserProfileException
	 * @throws LanguagesException 
	 * @throws UserLanguageException 
	 * @throws GenreException 
	 * @throws UserInterestsException 
	 * @throws CountryException 
	 * @throws AddressException 
	 * 
	 */
	
	public Object getProfile(long userid) throws UserProfileException,
			AddressException, CountryException, UserInterestsException,
			GenreException, UserLanguageException, LanguagesException {
		try {
			User user = UserHandler.getInstance().findByPrimaryKey(userid);
			if (user != null) {
				return createProfileInfoMap(user);
			} else {
				return ServiceResponseManager.getInstance()
						.readServiceResponse(215);
			}
		} catch (UserException _e) {
			throw new UserProfileException("Get profile exception: "
					+ _e.getMessage(), _e);
		}
	}

	/**
	 * 
	 * @param userid
	 * @return
	 * @throws UserProfileException
	 */
	
	public Map<String, Object> viewOwnProfile(long userid)
			throws UserProfileException {
		MultiValueMap<String, String> profileInfo = new MultiValueMap<String, String>();
		UserProfile userProfile;
		try {
			userProfile = UserProfileHandler.getInstance().findByPrimaryKey(
					userid);
		} catch (UserProfileException e) {
			throw new UserProfileException("View Own Profile Exception : ");
		}
		if (userProfile != null) {
			// Set Full Name
			retrieveFullName(profileInfo, userProfile.getName());
			// Set ProfilePic
			if (userProfile.getProfilepic() > 0) {
				profileInfo.put(UserProfile.PROFILEPIC,
						retrieveProfilePic(userProfile.getProfilepic()));
			}
			// Set Biography
			profileInfo.put(UserProfile.BIOGRAPHY,
					userProfile.getBiography() != null ? userProfile.getBiography()
							: "");
			// TODO Set stats (earned points, read points)

		} else {
			return ServiceResponseManager.getInstance()
					.readServiceResponse(215);
		}
		return profileInfo;
	}

	/*
	 * Create EditedInfo Map dob nationality website biography level tnc
	 */
	public MultiValueMap<String, String> createProfileInfoMap(User user)
			throws UserProfileException, AddressException, CountryException,
			UserInterestsException, GenreException, UserLanguageException,
			LanguagesException {
		
		MultiValueMap<String, String> userInfo = createUserInfoMap(user);
		List<UserProfile> userprofile = null;
		List<Address> useraddress = null;
		
		if (UserProfile.getInstance().getAddress() == null) {
			UserProfile.getInstance().setAddress(Address.getInstance());
		}
		try {
			userprofile = UserProfileHandler.getInstance()
					.findWhereUseridEquals(user.getUserid());
			useraddress = AddressHandler.getInstance().findWhereUseridEquals(
					user.getUserid());
		} catch (UserProfileException e) {
			throw new UserProfileException("create Profile InfoMap UserProfile Exception : "
					+ e.getMessage(), e);
		} catch (AddressException e) {
			throw new AddressException("create Profile InfoMap Address Exception : "
					+ e.getMessage(), e);
		}
		
		if (!userprofile.isEmpty() && userprofile != null) {
			for(UserProfile userProfile : userprofile) { 
			// Set DOB
			userInfo.put(UserProfile.DOB,
						userProfile.getDob() != null ? Utils.getFormatedDate()
								.format(userProfile.getDob()) : "");
			// Set Nationality
			userInfo.put(
						UserProfile.NATIONALITY, userProfile.getNationality() > 0 ? 
						getCountryIdentifiers(userProfile
								.getNationality(), "countryid") : "");
			
			if (!useraddress.isEmpty() && useraddress != null) {
				for( Address userAddress : useraddress) {
				// Set Line1
				userInfo.put(Address.ADDRESSLINE1, userAddress.getAddressLine1() != null ?
							userAddress.getAddressLine1() : "");
				// Set line2
				userInfo.put(Address.ADDRESSLINE2, userAddress.getAddressLine2() != null ?
							userAddress.getAddressLine2() : "");
				// Set line3
				userInfo.put(Address.ADDRESSLINE3, userAddress.getAddressLine3() != null ?
							userAddress.getAddressLine3() : "");
				// Set City
				userInfo.put(Address.CITY, userAddress.getCity() != null ?
							userAddress.getCity() : "");
				// Set State
				userInfo.put(Address.STATE, userAddress.getState() != null ?
							userAddress.getState() : "");
				// Set country
				userInfo.put(Address.COUNTRY, userAddress.getCountry() > 0 ? 
							getCountryIdentifiers(userAddress.getCountry(), "countryid") : "");
				// Set pincode
				userInfo.put(Address.PINCODE, userAddress.getPincode() >= 0 ? 
							userAddress.getPincode() : "");
				}
			}
			// Set biography
			userInfo.put(UserProfile.BIOGRAPHY,
					userProfile.getBiography() != null ? userProfile.getBiography()
							: "");
			
			
			// Set Website
			userInfo.put(
						UserProfile.WEBSITE,
						userProfile.getWebsite() != null ? userProfile
								.getWebsite() : "");
				// Set UserInterests
			userInfo.put(UserProfile.INTERESTS, retrieveUserInterests(user.getUserid()));
			
			// Set RLang and WLang
			userInfo.put(Languages.LANGUAGE, retieveUserLanguages(user.getUserid()));
			}
		}
		return userInfo;
	}

	private Set<String> retieveUserLanguages(long userid)
			throws UserLanguageException, LanguagesException {
		Set<String> language = new HashSet<String>();
		List<Object> editedLanguages = new ArrayList<Object>();
		try {
			List<UserLanguage> userLanguages = UserLanguageHandler
					.getInstance().findWhereUseridEquals(userid);
			if (!userLanguages.isEmpty() && userLanguages != null) {
				for (UserLanguage userLanguage : userLanguages) {
					editedLanguages.add(userLanguage.getLanguageid());
				}
				
				List<Languages> languages = LanguagesHandler.getInstance().findByDynamicWhere(
						buildQuery("languageid", userLanguages.size()), editedLanguages);
				
				if (!languages.isEmpty() && languages != null) {
						for (Languages lang : languages) {
							language.add(lang.getLanguage());
						}
				}  
			}
		} catch (UserLanguageException e) {
			throw new UserLanguageException(" Get UserLanguage Exception : "
					+ e.getMessage(), e);
		} catch (LanguagesException e) {
			throw new LanguagesException(" Get Languages Exception : "
					+ e.getMessage(), e);
		}
		return language;
	}

	
	private List<String> retrieveUserInterests(long userid) throws UserInterestsException, GenreException {
		Set<String> genreSet = new HashSet<String>();
		try {
			List<UserInterests> userInterests = UserInterestsHandler
					.getInstance().findWhereUseridEquals(userid);
			List<Object> editedGenre = new ArrayList<Object>();
			if (!userInterests.isEmpty() && userInterests != null) {
				for (UserInterests userinterests : userInterests) {
						editedGenre.add(userinterests.getGenreid());
				}
				List<Genre> genres = GenreHandler.getInstance().findByDynamicWhere(
							buildQuery("genreid", userInterests.size()), editedGenre);
				
				if (!genres.isEmpty() && genres != null) {
						for (Genre genre : genres) {
							// Add genre to the Set to remove duplicates
							genreSet.add(genre.getGenre());
						}
					}
				}
		} catch (UserInterestsException e) {
			throw new UserInterestsException("Get User Interests Exception : "
					+ e.getMessage(), e);
		} catch (GenreException e) {
			throw new GenreException(" Get Genres Exception : "
					+ e.getMessage(), e);
		}
		return new ArrayList<String>(genreSet);
	}

	/*
	 * Being called by createEditedInfoMap method Read Nationality for
	 * userprofile or Country for useraddress And return country name from
	 * countryid
	 * 
	 * Parameter : countryid
	 */
	private Object getCountryIdentifiers(Object countryIdentifier, String type)
			throws CountryException {
		try {
			// TODO ask whether to chk that list is empty or not.
			if (type.equalsIgnoreCase("countryid")) {
				return CountryHandler.getInstance()
						.findWhereCountryidEquals((Short) countryIdentifier)
						.iterator().next().getShortname();
			} else {
				// if(type.equalsIgnoreCase("countryName")

				// TODO using short name, ideal it should be
				// iso2 or iso3 (preferred). Change it accordingly
				return CountryHandler.getInstance()
						.findWhereShortnameEquals((String) countryIdentifier)
						.iterator().next().getCountryid();

			}
		} catch (CountryException e) {
			throw new CountryException(" get Country Identifiers Exception : "
					+ e.getMessage(), e);
		}
	}		

	/***************************** EDIT PROFILE MODULE ********************************/
	
	/**
	 * Edit profile
	 * 
	 * throws UserException
	 * @throws CountryException 
	 * @throws NumberFormatException 
	 * @throws UserException 
	 */

	// TODO Edit profile for work published
	
	// TODO userlevel, usertype, status

	public Map<String, Object> editProfile(
			MultivaluedMap<String, String> formParams) throws FileException, NumberFormatException, CountryException, UserException {
		long userid = 0;
		short responseStatus = 0;
		List<UserProfile> userList = null;
		List<String> formUserid = formParams.get(User.PROFILEID);
		for (String value : formUserid) {
			if (value != null && !value.equals("")) {
				userid = Long.parseLong(value);
				try {
					userList = UserProfileHandler.getInstance()
								.findWhereUseridEquals(userid);
				} catch (UserProfileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!userList.isEmpty() && userList != null) {
					responseStatus = setEditedInfo(userid, formParams);
				}
			}
		}
		return ServiceResponseManager.getInstance().readServiceResponse(responseStatus);
	}

	public short setEditedInfo(long userid,
			MultivaluedMap<String, String> formParams) throws NumberFormatException, CountryException, UserException {
		short responseStatus = 0;
		UserProfile userProfile = UserProfile.getInstance();
		UserFieldVisibility userFieldVisibility = UserFieldVisibility.getInstance();
		if (userid != 0) {
				if (userProfile.getName() == null) {
					userProfile.setName(Name.getInstance());
				}
				if (userProfile.getAddress() == null) {
					userProfile.setAddress(Address.getInstance());
				}
				userProfile.setUserid(userid);
				Set<String> keySet = formParams.keySet();
				for (String key : keySet) {
					List<String> userInfo = formParams.get(key);
					if (!(key.equalsIgnoreCase(UserProfile.RLANG)
							&& key.equalsIgnoreCase(UserProfile.WLANG) && key
								.equalsIgnoreCase(UserProfile.INTERESTS))) {
						for (String userInfoValue : userInfo) {
							if (userInfoValue != null
									&& !userInfoValue.equalsIgnoreCase("")) {
								// Set/Update User Name
								if (key.equalsIgnoreCase(User.PENNAME)) {
									User user = User.getInstance();
									try {
										if (!UserManager.getInstance().userExists(userInfoValue, false)) {
											user.setPenname(userInfoValue);
											UserHandler.getInstance().update(userid, user);
											SetUserVisibility();
										} else {
											responseStatus = 84;
										}
									} catch (UserException e) {
										throw new UserException("PenName Creation Exception : ");
									}	
								}
								// Update first name
								else if (key.equalsIgnoreCase(Name.FIRSTNAME)) {
									userProfile.getName().setFirstname(
											userInfoValue);
									SetUserVisibility();
								}
								// Set/Update middle name
								else if (key.equalsIgnoreCase(Name.MIDDLENAME)) {
									userProfile.getName().setMiddlename(
											(userInfoValue));
									SetUserVisibility();
								}
								// Update last name
								else if (key.equalsIgnoreCase(Name.LASTNAME)) {
									userProfile.getName().setLastname(
											userInfoValue);
									SetUserVisibility();
								}
								// Update Gender
								else if (key
										.equalsIgnoreCase(UserProfile.GENDER)) {
									userProfile.setGender(GENDER
											.valueOf(userInfoValue
													.toUpperCase()));
									SetUserVisibility();
								}
								// update Date of Birth
								else if (key.equalsIgnoreCase(UserProfile.DOB)) {
									try {
										Date dateOfBirth = (Date) Utils.getFormatedDate()
												.parse(userInfoValue);
										userProfile.setDob(dateOfBirth);
										userProfile
												.setAge(deriveAge(dateOfBirth));
										SetUserVisibility();
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								// Set/Update nationality
								// Passing countryname to get country id
								else if (key
										.equalsIgnoreCase(UserProfile.NATIONALITY)) {
									userProfile.setNationality(Short
											.parseShort(getCountryIdentifiers(
													userInfoValue,
													"countryName").toString()));
								}
								// Set/Update Website
								else if (key
										.equalsIgnoreCase(UserProfile.WEBSITE)) {
									userProfile.setWebsite(userInfoValue);
								}
								// Set/Update biography
								else if (key
										.equalsIgnoreCase(UserProfile.BIOGRAPHY)) {
									userProfile.setBiography(userInfoValue);
								}
								// Set/Update profilepic
								else if (key
										.equalsIgnoreCase(UserProfile.PROFILEPIC)) {
									long fileid = setProfilePic(userid, userInfoValue);
									if(fileid > 0){
										userProfile.setProfilepic(fileid);
									}
								}
								// Set/Update AddressLine1
								else if (key
										.equalsIgnoreCase(Address.ADDRESSLINE1)) {
									userProfile.getAddress().setAddressLine1(
											userInfoValue);
								}
								// Set/Update AddressLine2
								else if (key
										.equalsIgnoreCase(Address.ADDRESSLINE2)) {
									userProfile.getAddress().setAddressLine2(
											userInfoValue);
								}
								// Set/Update AddressLine3
								else if (key
										.equalsIgnoreCase(Address.ADDRESSLINE3)) {
									userProfile.getAddress().setAddressLine3(
											userInfoValue);
								}
								// Set/Update city
								else if (key.equalsIgnoreCase(Address.CITY)) {
									userProfile.getAddress().setCity(
											userInfoValue);
								}
								// Set/Update pincode
								else if (key.equalsIgnoreCase(Address.PINCODE)) {
									userProfile.getAddress().setPincode(
											Integer.parseInt(userInfoValue));
								}
								// Set/Update state
								else if (key.equalsIgnoreCase(Address.STATE)) {
									userProfile.getAddress().setState(
											userInfoValue);
								}
								// Set/Update country
								else if (key.equalsIgnoreCase(Address.COUNTRY)) {
									userProfile
											.getAddress()
											.setCountry(
													Short.parseShort(getCountryIdentifiers(
															userInfoValue,
															"countryName")
															.toString()));
								}
							}
						}
					}
					// Update User Interests
					else if (key.equalsIgnoreCase(UserProfile.INTERESTS)) {
							updateUserInterests(userInfo, userid, userProfile);
					} else if (key.equalsIgnoreCase(UserProfile.RLANG)) {
						// update RLang
						updateUserLanguages(userid,
								"read", userInfo);
					} else if (key.equalsIgnoreCase(UserProfile.WLANG)) {
						// update WLang
						updateUserLanguages(userid,
								"write", userInfo);
					}
				}
				// User Points if (profile.get(UserProfile.USERPOINTS) != null)
				// {
				// userProfile.getUserpoints();
				// User Level userProfile.getUserlevel();
				// User Type userProfile.getUsertype();
				try {
					UserProfileHandler.getInstance().update(userid,userProfile);
					updateUserAddress(userid, userProfile.getAddress());
					responseStatus = 200;
				} catch (UserProfileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return responseStatus;
	}

	
	private void SetUserVisibility() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method to be called by setEditInfo method for editProfile
	 * 
	 * @param userid
	 * @param address
	 * @return
	 */
	public long setProfilePic(long userid, String userInfoValue) {
		Files file = new Files();
		long fileid = 0;
		Map<String, Entry<String, String>> userPicExist = FilesManager.getInstance().readFiles(userid,
				"profilepic", Files.FILETYPE);
	
		if (!userPicExist.isEmpty()) {
			fileid = Long.parseLong(userPicExist.get(Files.FILEID).toString());
			FilesManager.getInstance().updateFiles(fileid, userInfoValue);
		} else {
			file = FilesManager.getInstance().createFiles(userid, "profilepic",
					userInfoValue);
			if (file != null) {
				fileid = file.getFileid();
			}
		}
		return fileid;
	}

	/**
	 * Method to be called by setEditInfo method for editProfile
	 * 
	 * @param userid
	 * @param address
	 */
	public void updateUserAddress(long userid, Address address) {
		List<Address> useridExist = null;
		List<Object> readUseridObj = new ArrayList<Object>();
		readUseridObj.add(userid);
		address.setTimestamp(System.currentTimeMillis() / 1000L);
		try {
			useridExist = AddressHandler.getInstance().findByDynamicWhere(
					"userid IN (?)", readUseridObj);
			if (!useridExist.isEmpty() && useridExist != null) {
				 AddressHandler.getInstance().update(
						(long) useridExist.get(0).getAddressid(), address);
			} else {
				address.setUserid(userid);
				AddressHandler.getInstance().insert(address);
			}
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateUserInterests(List<String> userInfo,
			long userid, UserProfile userProfile) {
		List<Object> editedInfoList = new ArrayList<Object>();
		List<Object> categoryidList = new ArrayList<Object>();
		List<Category> categories = null;
		Set<Genre> genreDbSet = null;
		for (String uInfo : userInfo) {
			editedInfoList.add(uInfo);
		}
		try {
			genreDbSet = new HashSet<Genre>(GenreHandler.getInstance().findByDynamicWhere(
					buildQuery("genre", userInfo.size()), editedInfoList));

			categories = CategoryHandler.getInstance().findByDynamicWhere(
					buildQuery("category", userInfo.size()), editedInfoList);
			
			
			// Constructing unique category set
			for (Category category : categories) {
				categoryidList.add(category.getCategoryid());
			}
			
			genreDbSet.addAll(GenreHandler.getInstance().findByDynamicWhere(
					buildQuery("categoryid", categoryidList.size()), categoryidList));
			
			if(!genreDbSet.isEmpty() && genreDbSet != null) {
				UserInterests userInterests = UserInterests.getInstance();
				for (Genre genre : genreDbSet) {
					userInterests.setUserid(userid);
					userInterests.setGenreid(genre.getGenreid());
					UserInterestsHandler.getInstance().insert(
							userInterests);
				}
			}
		} catch (GenreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserInterestsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Updates user languages
	 * 
	 * @param userid
	 * @param languageType
	 * @param languageObj
	 * @return Set of languages used by user
	 */
	public Set<Languages> updateUserLanguages(long userid, String languageType,
			List<String> userInfo) {
		List<Object> editedLanguages = new ArrayList<Object>();
		List<Languages> languageList = null;
		for (String lang : userInfo) {	
			editedLanguages.add(lang.toLowerCase());
		}
		try {
			languageList = LanguagesHandler.getInstance().findByDynamicWhere(
					buildQuery("language", userInfo.size()), editedLanguages);

			if (!languageList.isEmpty() && languageList != null) {
				UserLanguage userlang = UserLanguage.getInstance();
				for (Languages lang : languageList) {
					if (lang.getLanguageid() > 0) {
						userlang.setUserid(userid);
						userlang.setType(languageType);
						userlang.setLanguageid(lang.getLanguageid());
						userlang = UserLanguageHandler.getInstance().insert(
								userlang);
					}
				}
				if (userlang != null) {
					return (new HashSet<Languages>(languageList));
				}
			} else {
				// TODO if no languages exists in database then ?
			} 
		} catch (UserLanguageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LanguagesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/***************************** POINTS AND LEVEL MODULE ********************************/

	/**
	 * Update user points
	 * 
	 * @param object
	 * @return
	 */
	public UserProfile updateUserPoints(Map<String, Integer> userPoints) {
		Map<String, Integer> userpoints = null;

		// userProfile.setUserpoints(userpoints);

		return null;
	}

	public UserProfile updateUserLevel(long userid, USERLEVEL userlevel) {
		List<UserProfile> userProfileList = null;

		try {
			userProfileList = UserProfileHandler.getInstance().findByUser(
					userid);
		} catch (UserProfileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (userProfileList != null) {
			for (UserProfile userProfile : userProfileList) {
				userProfile.setUserlevel(userlevel);
				try {
					UserProfileHandler.getInstance()
							.update(userid, userProfile);
				} catch (UserProfileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return userProfileList.get(0);
	}

	
	/***************************** USERTYPE MODULE ********************************/
	
	public UserProfile updateUserType(long userid, USERTYPE usertype) {
		List<UserProfile> userProfileList = null;

		try {
			userProfileList = UserProfileHandler.getInstance().findByUser(
					userid);
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (userProfileList != null) {
			for (UserProfile userProfile : userProfileList) {
				userProfile.setUsertype(usertype);

				try {
					UserProfileHandler.getInstance()
							.update(userid, userProfile);
				} catch (UserProfileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return userProfileList.get(0);
	}
	
	// TODO
	public short getUserType(String userType) {
		return userType.equalsIgnoreCase(USERTYPE.READER.toString())
				|| userType.equalsIgnoreCase(USERTYPE.WRITER.toString())
				|| userType.equalsIgnoreCase(USERTYPE.PUBLISHER.toString()) || userType
					.equalsIgnoreCase(USERTYPE.SERIVCEPROVIDER.toString()) ? (short) USERTYPE
				.valueOf(userType.toUpperCase()).ordinal()
				: (short) com.qpeka.db.Constants.USERTYPE.UNSPECIFIED.ordinal();
		
			
			/*short usertype = 0;
			if (userType.equalsIgnoreCase(USERTYPE.READER.toString())
					|| userType.equalsIgnoreCase(USERTYPE.WRITER.toString())
					|| userType.equalsIgnoreCase(USERTYPE.PUBLISHER.toString())
					|| userType.equalsIgnoreCase(USERTYPE.SERIVCEPROVIDER
							.toString())) {
				usertype = (short) USERTYPE.valueOf(userType.toUpperCase())
						.ordinal();
			} else {
				usertype = (short) com.qpeka.db.Constants.USERTYPE.UNSPECIFIED
						.ordinal();
			}
			return usertype;*/
		
	}
	/***************************** DATE and AGE MODULE ********************************/

	
	/**
	 * Compute age of a person
	 */
	public short deriveAge(Date dob) {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.setTime(dob);
		Calendar today = Calendar.getInstance();

		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		if (today.get(Calendar.MONDAY) < dateOfBirth.get(Calendar.MONTH)) {
			age--;
		} else if (today.get(Calendar.MONTH) == dateOfBirth.get(Calendar.MONTH)
				&& today.get(Calendar.DAY_OF_MONTH) < dateOfBirth
						.get(Calendar.DAY_OF_MONTH)) {
			age--;
		}
		return (short) age;
	}
	
	/***************************** QUERY BUILDING MODULE ********************************/

	/**
	 * Create query for multiple list objects (comma separated)
	 * 
	 * @param args
	 */
	private String buildQuery(String field, int size) {
		StringBuilder queryString = new StringBuilder();
		queryString.append(field + " IN (?");
		for (int i = 1; i < size; i++) {
			if (queryString.length() > 0) {
				queryString.append(", ");
			}
			queryString.append("?");
		}
		queryString.append(")");
		return queryString.toString();
	}

	/***************************** GENERALIZED MODULE ********************************/
	
	/**
	 * Get profile preferences
	 * 
	 * METHOD FOR RETRIEVING RESULT; BY PASSING LIST OF OBJECTS & CONVERTING THEM
	 * INTO SINGLE QUERY
	 * 
	 * @param tableName
	 * @param preferencesObj
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public Object getProfilePreferences(String whereSql, Object preferencesObj,
			AbstractHandler abstractHandler) {
		List<String> preferenceList = new ArrayList<String>();
		preferenceList.addAll((Collection<? extends String>) preferencesObj);

		Iterator<String> preferencesIt = preferenceList.iterator();
		List<Object> preferencesObjList = new ArrayList<Object>();

		preferencesObjList.add(convertCollectionToString(preferencesIt));
		System.out.println(preferencesObjList);
		Object profilePreferences = null;
		try {
			profilePreferences = abstractHandler.findByDynamicWhere(whereSql
					+ " IN (?)", preferencesObjList);
			System.out.println(profilePreferences);

		} catch (QpekaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profilePreferences;
	}

	/**
	 * Accepts collection and converts to string
	 * 
	 * @param languageIt
	 * @return
	 */
	public String convertCollectionToString(Iterator<String> collectionIt) {
		StringBuilder collectionString = new StringBuilder();
		// Create string of collection objects (comma separated)
		while (collectionIt.hasNext()) {
			if (collectionString.length() > 0) {
				collectionString.append(", ");
			}
			collectionString.append(collectionIt.next().toString());
		}
		return collectionString.toString();
	}

}// End of class UserManager.java
