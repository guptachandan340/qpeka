package com.qpeka.managers.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.RandomStringUtils;
import com.qpeka.db.Constants.GENDER;
import com.qpeka.db.Constants.STATUS;
import com.qpeka.db.Constants.USERLEVEL;
import com.qpeka.db.Constants.USERTYPE;
import com.qpeka.db.Languages;
import com.qpeka.db.exceptions.FileException;
import com.qpeka.db.exceptions.QpekaException;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.exceptions.user.UserLanguageException;
import com.qpeka.db.exceptions.user.UserProfileException;
import com.qpeka.db.handler.AbstractHandler;
import com.qpeka.db.handler.LanguagesHandler;
import com.qpeka.db.handler.user.UserHandler;
import com.qpeka.db.handler.user.UserLanguageHandler;
import com.qpeka.db.handler.user.UserProfileHandler;
import com.qpeka.db.user.User;
import com.qpeka.db.user.profile.Name;
import com.qpeka.db.user.profile.UserLanguage;
import com.qpeka.db.user.profile.UserProfile;
import com.qpeka.security.bcrypt.BCrypt;

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

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param username
	 * @param password
	 * @param gender
	 * @param dob
	 * @param langugaes
	 * @return
	 */
	public User registerUser(MultivaluedMap<String, String> formParams) {
		
		// Create User
		User user = User.getInstance();
		// Create User Profile
		UserProfile userProfile = UserProfile.getInstance();
		if(userProfile.getName() == null) { 
			userProfile.setName(Name.getInstance());
		}
		Set<String> keySet = formParams.keySet();
		for(String key : keySet) {
			List<String> userInfo = formParams.get(key);
			for(String userInfoValue : userInfo) {
				setUserInfo(key, userInfoValue, user, userProfile);
			}
		}
			user.setCreated(System.currentTimeMillis() / 1000);
			user.setLastaccess(0);
			user.setLastlogin(0);
			user.setStatus((short) STATUS.DEFAULT.ordinal());
			try {
				// Insert user to database;
				Long userid = UserHandler.getInstance().insert(user);
				userProfile.setUserid(userid);
				UserProfileHandler.getInstance().insert(userProfile);
				//store languages in user language table
				for(String key : keySet) {
					if(key.equalsIgnoreCase(Languages.LANGUAGEID)) {
						List<String> userLanguageId = formParams.get(key);
						for(String languageid : userLanguageId) {
							UserLanguage userlanguage = new UserLanguage();
							userlanguage.setUserid(userid);
							userlanguage.setLanguageid(Short.parseShort(languageid));
							UserLanguageHandler.getInstance().insert(userlanguage);
						}
					}
				}
			}
			catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserProfileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserLanguageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return user;
	}// end of registeruser()
			
	public void setUserInfo(String key, String value, User user, UserProfile userProfile) {
			if(key.equalsIgnoreCase(Name.FIRSTNAME)) {
				   userProfile.getName().setFirstname(value);
					//name.setFirstname(value);
			} else if(key.equalsIgnoreCase(Name.LASTNAME)) {
					userProfile.getName().setLastname(value);
					//name.setLastname(value);
			} else if(key.equalsIgnoreCase(User.EMAIL)) {
					user.setEmail(value);
			} else if(key.equalsIgnoreCase(User.PASSWORD)) {
					user.setPassword(BCrypt.hashpw(value, BCrypt.gensalt()));
			} else if(key.equalsIgnoreCase(UserProfile.GENDER)) {
					userProfile.setGender(GENDER.valueOf(value));
			} else if(key.equalsIgnoreCase(UserProfile.DOB)) {
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date dateOfBirth = (Date)formatter.parse(value);
						userProfile.setDob(dateOfBirth);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			} else if(key.equalsIgnoreCase(UserProfile.TNC)) {
				   userProfile.setTnc(Short.parseShort(value));
			}
		}
		 
		
	

		
	/* // Get nationality
		List<Country> nation = null;

		/*try {
			// TODO using short name, ideal it should be iso2 or iso3
			// (preferred). Change it accordingly
			nation = CountryHandler.getInstance().findWhereShortnameEquals(
					nationality);
		} catch (CountryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// update nationality
		if (nation != null) {
			userprofile.setNationality(nation.get(0).getCountryid());
		}*/
	
	/**
	 * Authenticate User
	 * 
	 * @throws UserException
	 */
	
	public Map<String, Object> authenticateUser(String authName, String password,
			boolean isEmail) throws UserException {
		Map<String, Object> loginresponse = new HashMap<String, Object>();
		List<User> user = new ArrayList<User>();
		try {
			user = (!isEmail) ? UserHandler.getInstance().findWhereUsernameEquals(
					authName) : UserHandler.getInstance().findWhereEmailEquals(authName);
		} catch (UserException _e) {
			throw new UserException("User Authentication Exception: "
					+ _e.getMessage(), _e);
		}
		
		if (!user.isEmpty()) {
			if (user.get(0).getStatus() != 3 || user.get(0).getStatus() != 4) {
				if (BCrypt.checkpw(password, user.get(0).getPassword())) {
					// check whether counter is > 0 or not; if not then set error code.
					updateLastActivity(user.get(0)
							.getUserid(), true);
					return createUserInfoMap(user.get(0));
				} else {
					loginresponse.put("Error", "215");
					return loginresponse;
				}
			} else {
				loginresponse.put("Error", "64");
				return loginresponse;
			}
		}else {
			loginresponse.put("Error", "215");
			return loginresponse;
		}
	} 

	/**
	 * creating UserInfoMap from user and userprofile data
	 */
	public Map<String, Object> createUserInfoMap(User user) {
		Map<String, Object> userInfo = new HashMap<String, Object>();
		List<UserProfile> userProfile = new ArrayList<UserProfile>();
		try {
			userProfile = UserProfileHandler.getInstance()
					.findWhereUseridEquals(user.getUserid());
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null) {
			userInfo.put((User.PROFILEID).toLowerCase(), user.getUserid());
			if (user.getUsername() != null) {
				userInfo.put(User.USERNAME.toLowerCase(), user.getUsername());
			} else {
				userInfo.put(User.USERNAME.toLowerCase(), "");
			}
			if (user.getEmail() != null) {
				userInfo.put(User.EMAIL.toLowerCase(), user.getEmail());
			} else {
				userInfo.put(User.EMAIL.toLowerCase(), "");
			}
		}
		if (userProfile != null) {
			Name name = userProfile.get(0).getName();
			if (name.getFirstname() != null) {
				userInfo.put(Name.FIRSTNAME.toLowerCase(), name.getFirstname());
			} else {
				userInfo.put(Name.FIRSTNAME.toLowerCase(), "");
			}
			if (name.getFirstname() != null) {
				userInfo.put(Name.LASTNAME.toLowerCase(), name.getLastname());
			} else {
				userInfo.put(Name.LASTNAME.toLowerCase(), "");
			}
			if (userProfile.get(0).getPenname() != null) {
				userInfo.put(UserProfile.PENNAME.toLowerCase(), userProfile.get(0)
						.getPenname());
			} else {
				userInfo.put(UserProfile.PENNAME.toLowerCase(), "");
			}

			if (userProfile.get(0).getGender() != null) {
				userInfo.put(UserProfile.GENDER.toLowerCase(), userProfile.get(0).getGender());
			} else {
				userInfo.put(UserProfile.GENDER.toLowerCase(), "");
			}
			if (userProfile.get(0).getProfilepic() > 0) {
				userInfo.put(UserProfile.PROFILEPIC.toLowerCase(), userProfile.get(0)
						.getProfilepic());
			} else {
				userInfo.put(UserProfile.PROFILEPIC.toLowerCase(), "");
			}
		}
		return userInfo;
	}

	/**
	 * update lastlogin or lastaccess
	 */
	public short updateLastActivity(long userid, boolean isLastLogin) {
		long lastActivity = System
				.currentTimeMillis() / 1000;
		List<User> existingUser = new ArrayList<User>();
		short counter = 0;
		try {
			existingUser = UserHandler.getInstance().findWhereUseridEquals(userid);
		} catch (UserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// isLastLogin = true when incoming data is LastLogin
		// isLastLogin = false when incoming data is LastAccess
		for(User user : existingUser) {
			if(!isLastLogin) {
				user.setLastaccess(lastActivity);
			} else {
				user.setLastlogin(lastActivity);
			}
			try {
				counter += UserHandler.getInstance().update(userid, user);
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	return counter;
	}
	
	/**
	 * Username exists?
	 * 
	 * @throws UserException
	 */
	public boolean usernameExists(String username) throws UserException {
		List<User> userList = new ArrayList<User>();
		try {
			userList = UserHandler.getInstance().findWhereUsernameEquals(
					username);
		} catch (UserException _e) {
			throw new UserException("User Authentication Exception: "
					+ _e.getMessage(), _e);
		}
		// Returns false when userlist is empty else true (Username exists)
		return (!userList.isEmpty());
	}// end of usernameExists()

	/**
	 * Email exists?
	 * 
	 * @throws UserException
	 */
	public boolean emailExists(String email) throws UserException {
		List<User> userList = new ArrayList<User>();
		try {
			userList = UserHandler.getInstance().findWhereEmailEquals(email);
		} catch (UserException _e) {
			throw new UserException("User Authentication Exception: "
					+ _e.getMessage(), _e);
		}

		// Returns false when userlist is empty else true (Email exists)
		// false -> email does not exists, true -> email exists
		return (!userList.isEmpty());
	}// end of emailExists()

	/**
	 * Change account password
	 * -
	 * @throws UserException
	 */
	
	public Object changePassword(long userid, String currentPassword,
			String newPassword) throws UserException {
		Object result = null;
		List<User> userInfoList = new ArrayList<User>();
		userInfoList = UserHandler.getInstance().findWhereUseridEquals(userid);
		for (User user : userInfoList) {
			if (BCrypt.checkpw(currentPassword, user.getPassword())) {
				try {
					user.setPassword(BCrypt.hashpw(newPassword,
								BCrypt.gensalt()));
					UserHandler.getInstance().update(userid, user);
					result = "200"; 
					} catch (UserException _e) {
						throw new UserException("Update User Password Exception: "
								+ _e.getMessage(), _e);
					}
				} else {
					result = "215";
				}
			} 
		return result;
	}// end of changePassword()

	/**
	 * Reset users password
	 * 
	 * @throws UserException
	 */
	public String resetPassword(String authName, boolean isEmail) throws UserException {
		List<User> user = new ArrayList<User>();
		Object error = null;
		String newPassword = RandomStringUtils.random(8, true, true);
		try {
			user = (!isEmail) ? UserHandler.getInstance().findWhereUsernameEquals(authName) : UserHandler.getInstance().findWhereEmailEquals(authName);
		} catch (UserException _e) {
			throw new UserException("Reset user Password Exception: "
					+ _e.getMessage(), _e);
		}

		if (!user.isEmpty()) {
			user.get(0).setPassword(
					BCrypt.hashpw(newPassword, BCrypt.gensalt()));
			UserHandler.getInstance().update(user.get(0).getUserid(),
					user.get(0));
			return newPassword;
		} else {
			error = "215";
			return error.toString();
		}
	} // end of reset password()

	/**
	 * Edit profile
	 * 
	 * throws UserException
	 */
	
	// TODO userlevel, usertype,userlangugaes, status
		
	public UserProfile editProfile(MultivaluedMap<String, String> formParams) throws FileException {
		UserProfile userProfile = UserProfile.getInstance();
		short usertype = 0;
		long userid = 0;
		System.out.println(formParams.keySet());
	    System.out.println(formParams.entrySet());
	    Set<String> keySet = formParams.keySet();
	    for(String key : keySet) {
			if(key.equalsIgnoreCase(UserProfile.USERID)) {
				List<String> formValue = formParams.get(key);
				for(String value : formValue) {
					 userid = Long.parseLong(value);
					 System.out.println(userid);
				}
			}
	    }
				
		/*if (formParams.get(UserProfile.USERID) != null) {
			userid = Long.parseLong(profile.get(UserProfile.USERID).toString());
			usertype = (short) ((profile.get(UserProfile.USERTYPE) != null) 
					? Short.parseShort(profile.get(UserProfile.USERTYPE).toString())
					: com.qpeka.db.Constants.USERTYPE.READER.ordinal());

			// Set Pen name
			if (profile.get(UserProfile.PENNAME) != null) {
				userProfile.setPenname(profile.get(UserProfile.PENNAME)
						.toString());
			}

			// set firstName, LastName and MiddleName for UserProfile
			if (profile.get(UserProfile.NAME) != null) {
				Name name = Name.getInstance();

				if (profile.get(Name.FIRSTNAME) != null) {
					name.setFirstname(profile.get(Name.FIRSTNAME).toString());
				}

				if (profile.get(Name.MIDDLENAME) != null) {
					name.setMiddlename(profile.get(Name.MIDDLENAME).toString());
				}

				if (profile.get(Name.LASTNAME) != null) {
					name.setLastname(profile.get(Name.LASTNAME).toString());
				}

				userProfile.setName(name);
			}
			// check and set Gender for UserProfile
			if (profile.get(UserProfile.GENDER) != null) {
				userProfile.setGender(GENDER.valueOf(profile.get(
						UserProfile.GENDER).toString()));
			}
			// Check and set Date of birth and age for userProfile.
			if (profile.get(UserProfile.DOB) != null) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date dob;
				try {
					dob = (Date) df.parse(profile.get(UserProfile.DOB)
							.toString());
					userProfile.setDob(dob);
					userProfile.setAge(deriveAge(dob));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// Check and set Nationality for userProfile
			if (profile.get(UserProfile.NATIONALITY) != null) {
				List<Country> nation = null;
				try {
					// TODO using short name, ideal it should be iso2 or iso3
					// (preferred). Change it accordingly
					nation = CountryHandler.getInstance()
							.findWhereShortnameEquals(
									profile.get(UserProfile.NATIONALITY)
											.toString());
				} catch (CountryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				userProfile
						.setNationality((short) nation.get(0).getCountryid());
			}

			// set WEbSite for UserProfile
			if (profile.get(UserProfile.WEBSITE) != null) {
				userProfile.setWebsite(profile.get(UserProfile.WEBSITE)
						.toString());
			}

			// Set Biography for userProfile
			if (profile.get(UserProfile.BIOGRAPHY) != null) {
				userProfile.setBiography(profile.get(UserProfile.BIOGRAPHY)
						.toString());
			}

			// Set ProfilePic for UserProfile
			if (profile.get(UserProfile.PROFILEPIC) != null) {
				Files file = new Files();
				file = FilesManager.getInstance().createFiles(userid, profile.get(UserProfile.PROFILEPIC).toString());
				userProfile.setProfilepic(file.getFileid());
			}

			// Set Address, Country, State and Pin code To Addess Object
			if (profile.get(UserProfile.ADDRESS) != null) {
				Address address = Address.getInstance();

				// Set AddressLine1
				if (profile.get(Address.ADDRESSLINE1) != null) {
					address.setAddressLine1(profile.get(Address.ADDRESSLINE1)
							.toString());
				}

				// Set addressLine2
				if (profile.get(Address.ADDRESSLINE2) != null) {
					address.setAddressLine2(profile.get(Address.ADDRESSLINE2)
							.toString());
				}

				// Set AddressLine3
				if (profile.get(Address.ADDRESSLINE3) != null) {
					address.setAddressLine3(profile.get(Address.ADDRESSLINE3)
							.toString());
				}

				// Set City
				if (profile.get(Address.CITY) != null) {
					address.setCity(profile.get(Address.CITY).toString());
				}
				// Set Country
				if (profile.get(Address.COUNTRY) != null) {
					List<Country> country = null;
					try {
						// TODO using short name, ideal it should be iso2 or
						// iso3
						// (preferred). Change it accordingly
						country = CountryHandler
								.getInstance()
								.findWhereShortnameEquals(
										profile.get(Address.COUNTRY).toString());
					} catch (CountryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					address.setCountry((short) country.get(0).getCountryid());
				}

				// Set PinCode
				if (profile.get(Address.PINCODE) != null) {
					address.setPincode(Short.parseShort(profile.get(
							Address.PINCODE).toString()));
				}
				// Set State
				if (profile.get(Address.STATE) != null) {
					address.setState(profile.get(Address.STATE).toString());
				}
				userProfile.setAddress(address); // Set Address for UserProfile
			}

			// User Interests
			if (profile.get(UserProfile.INTERESTS) != null) {
				@SuppressWarnings("unchecked")
				List<Category> interestsList = (List<Category>) getProfilePreferences(
						"category", profile.get(UserProfile.INTERESTS),
						CategoryHandler.getInstance(), (short) 0);

				UserInterests userInterests = UserInterests.getInstance();
				userInterests.setUserid(userid);

				for (Category category : interestsList) {
					userInterests.setCategoryid(category.getCategoryid());
					try {
						UserInterestsHandler.getInstance()
								.insert(userInterests);
					} catch
					
					(UserInterestsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				userProfile.setInterests(new HashSet<Category>(interestsList));
			}

			// Read Language
			if (profile.get(UserProfile.RLANG) != null) {
				Set<Languages> userLanguages = updateUserLanguages(userid,"read",profile.get(UserProfile.RLANG));
				userProfile.setrLang(userLanguages);
			}

			// Written Language
			if (profile.get(UserProfile.WLANG) != null) {
				Set<Languages> userLanguages = updateUserLanguages(userid,"write",profile.get(UserProfile.WLANG));
				userProfile.setrLang(userLanguages);
			}

			// User Badges
			if (profile.get(UserProfile.USERBADGES) != null) {
				@SuppressWarnings("unchecked")
				List<Badges> userBadgesList = (List<Badges>) getProfilePreferences(
						"typeid = ? AND badge",
						profile.get(UserProfile.USERBADGES),
						BadgesHandler.getInstance(), usertype);

				UserBadges userBadges = UserBadges.getInstance();
				userBadges.setUserid(userid);
				for (Badges badges : userBadgesList) {
					userBadges.setBadgeid(badges.getBadgeid());

					try {
						UserBadgesHandler.getInstance().insert(userBadges);
					} catch (UserBadgesException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				userProfile.setUserbadges(new HashSet<Badges>());
			}

			// User Points
			if (profile.get(UserProfile.USERPOINTS) != null) {

			}
			userProfile.getUserpoints();

			// User Level
			userProfile.getUserlevel();

			// User Type
			userProfile.getUsertype();

			try {
				UserProfileHandler.getInstance().update(userid, userProfile);
			} catch (UserProfileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
*/
		return userProfile;
	}// end of edit Profile()
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

	/**
	 * Updates user languages
	 * 
	 * @param userid
	 * @param languageType
	 * @param languageObj
	 * @return Set of languages used by user
	 */
	public Set<Languages> updateUserLanguages(long userid, String languageType,
			Object languageObj) {
		@SuppressWarnings("unchecked")
		List<Languages> languageList = (List<Languages>) getProfilePreferences(
				"language", languageObj, LanguagesHandler.getInstance(),
				(short) 0);

		UserLanguage userLang = UserLanguage.getInstance();
		userLang.setUserid(userid);
		userLang.setType(languageType);
		for (Languages language : languageList) {
			userLang.setLanguageid(language.getLanguageid());
			try {
				UserLanguageHandler.getInstance().insert(userLang);
			} catch (UserLanguageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return (new HashSet<Languages>(languageList));
	}

	/**
	 * Get profile preferences
	 * 
	 * @param tableName
	 * @param preferencesObj
	 * @return
	 */
	public Object getProfilePreferences(String whereSql, Object preferencesObj,
			AbstractHandler abstractHandler, short userType) {
		@SuppressWarnings("unchecked")
		Iterator<String> preferencesIt = ((Set<String>) preferencesObj)
				.iterator();

		List<Object> preferencesObjList = new ArrayList<Object>();

		if (userType > 0) {
			preferencesObjList.add(userType);
		}

		preferencesObjList.add(convertCollectionToString(preferencesIt));

		Object profilePreferences = null;

		try {
			profilePreferences = abstractHandler.findByDynamicWhere(whereSql
					+ " IN (?)", preferencesObjList);
		} catch (QpekaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return profilePreferences;
	}

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


	/*
	public static void main(String [] args) {
		UserManager userman = new UserManager();
		User user = new User("rahul",
				BCrypt.hashpw("rahul", BCrypt.gensalt()),
				"srahul07.qpeka@gmail.com",
				(System.currentTimeMillis()) / 1000, "East");
		user.setUsernameModified(true);
		user.setPasswordModified(true);
		user.setEmailModified(true);
		user.setCreatedModified(true);
		user.setTimezoneModified(true);
		
		try {
			UserHandler.getInstance().insert(user);
		} catch (UserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			User usr = userman.authenticateUser("rahul", "rahul", false);
			System.out.println(usr.toString());
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	*/
	
}// End of class UserManager.java
