package com.qpeka.managers.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;

import com.qpeka.db.Badges;
import com.qpeka.db.Category;
import com.qpeka.db.Constants.GENDER;
import com.qpeka.db.Constants.STATUS;
import com.qpeka.db.Constants.USERLEVEL;
import com.qpeka.db.Constants.USERTYPE;
import com.qpeka.db.Country;
import com.qpeka.db.Files;
import com.qpeka.db.Languages;
import com.qpeka.db.exceptions.CountryException;
import com.qpeka.db.exceptions.QpekaException;
import com.qpeka.db.exceptions.user.UserBadgesException;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.exceptions.user.UserInterestsException;
import com.qpeka.db.exceptions.user.UserLanguageException;
import com.qpeka.db.exceptions.user.UserProfileException;
import com.qpeka.db.handler.AbstractHandler;
import com.qpeka.db.handler.BadgesHandler;
import com.qpeka.db.handler.CategoryHandler;
import com.qpeka.db.handler.CountryHandler;
import com.qpeka.db.handler.FilesHandler;
import com.qpeka.db.handler.LanguagesHandler;
import com.qpeka.db.handler.user.UserBadgesHandler;
import com.qpeka.db.handler.user.UserHandler;
import com.qpeka.db.handler.user.UserInterestsHandler;
import com.qpeka.db.handler.user.UserLanguageHandler;
import com.qpeka.db.handler.user.UserProfileHandler;
import com.qpeka.db.user.User;
import com.qpeka.db.user.profile.Address;
import com.qpeka.db.user.profile.Name;
import com.qpeka.db.user.profile.UserBadges;
import com.qpeka.db.user.profile.UserInterests;
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
	 * @param nationality
	 * @return
	 */
	public User registerUser(String firstName, String lastName, String email,
			String username, String password, String gender, Date dob,
			List<String> languages) {
		// Create User
		User user = new User();
		
		user.setUsername(username);
		user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
		user.setEmail(email);
		user.setCreated(System.currentTimeMillis() / 1000);
		// To Do : write statement for lastaccess and lastlogin
		user.setLastaccess(0);
		user.setLastlogin(0);
		user.setStatus((short) STATUS.DEFAULT.ordinal());

		// Create User Profile
		// Set name

		Name name = new Name();
		name.setFirstname(firstName);
		name.setLastname(lastName);

		UserProfile userprofile = new UserProfile();
		userprofile.setName(name);
		userprofile.setGender(GENDER.valueOf(gender));
		userprofile.setDob(dob);

		// Get nationality
		List<Country> nation = null;

		try {
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
		}

		// Insert user to database;
		try {
			Long userId = UserHandler.getInstance().insert(user);
	
			userprofile.setUserid(userId);
			UserProfileHandler.getInstance().insert(userprofile);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//store lang in userlangtable
	return user;
	}// end of registeruser()

	/**
	 * Authenticate User
	 * 
	 * @throws UserException
	 */
	public User authenticateUser(String authName, String password,
			boolean isEmail) throws UserException {
		List<User> user = new ArrayList<User>();
		try {
			if (!isEmail) {
				user = UserHandler.getInstance().findWhereUsernameEquals(
						authName);
			} else {
				user = UserHandler.getInstance().findWhereEmailEquals(authName);
			}
		} catch (UserException _e) {
			throw new UserException("User Authentication Exception: "
					+ _e.getMessage(), _e);
		}
		
		if (!user.isEmpty()) {
			return (BCrypt.checkpw(password, user.get(0).getPassword()) ? user
					.get(0) : null);
			
		} else {
			return null;
		}
	} // end of authenticateByEmail()

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
	 * Create user Account
	 * 
	 * @throws UserException
	 */

	/**
	 * Change account password
	 * 
	 * @throws UserException
	 */
	public User changePassword(User user, String currentPassword, String newPassword)
			throws UserException {
		boolean oldPasswordResult = false;
		if(BCrypt.checkpw(currentPassword, user.getPassword())) {
		try {
			oldPasswordResult = BCrypt.checkpw(newPassword, user.getPassword()) ? true : false;
			if(!oldPasswordResult) {
				user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
			} else {
				System.out.println("Old password is matching");
			}
			UserHandler.getInstance().update(user.getUserid(), user);
		} catch (UserException _e) {
			throw new UserException("Update User Password Exception: "
					+ _e.getMessage(), _e);
		}
		}
		return user;
	}// end of changePassword()

	/**
	 * Reset users password
	 * 
	 * @throws UserException
	 */
	public String resetPassword(String authName, boolean isEmail) throws UserException {
		List<User> user = new ArrayList<User>();
		String newPassword = RandomStringUtils.random(8, true, true);
		try {
			if (!isEmail) {
				user = UserHandler.getInstance().findWhereUsernameEquals(
						authName);
			} else {
				user = UserHandler.getInstance().findWhereEmailEquals(authName);
			}
		} catch (UserException _e) {
			throw new UserException("Reset user Password Exception: "
					+ _e.getMessage(), _e);
		}

		if (!user.isEmpty()) {
			user.get(0).setPassword(
					BCrypt.hashpw(newPassword, BCrypt.gensalt()));

			UserHandler.getInstance().update(user.get(0).getUserid(),
					user.get(0));
		}

		return newPassword;
	} // end of reset password()

	/**
	 * Edit profile
	 * 
	 * throws UserException
	 */
	public UserProfile editProfile(Map<String, Object> profile) {
		UserProfile userProfile = UserProfile.getInstance();
		long userid = 0;
		short usertype = 0;

		if (profile.get(UserProfile.USERID) != null) {
			userid = Long.parseLong(UserProfile.USERID);

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
				file.setFilepath(profile.get(UserProfile.PROFILEPIC).toString());

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
					} catch (UserInterestsException e) {
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

		return userProfile;
	}// end of edit Profile

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
