package com.qpeka.managers.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.RandomStringUtils;

import com.qpeka.db.Category;
import com.qpeka.db.Constants.GENDER;
import com.qpeka.db.Constants.STATUS;
import com.qpeka.db.Constants.USERLEVEL;
import com.qpeka.db.Constants.USERTYPE;
import com.qpeka.db.Country;
import com.qpeka.db.Files;
import com.qpeka.db.Languages;
import com.qpeka.db.exceptions.CategoryException;
import com.qpeka.db.exceptions.CountryException;
import com.qpeka.db.exceptions.FileException;
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
import com.qpeka.db.handler.LanguagesHandler;
import com.qpeka.db.handler.user.AddressHandler;
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
import org.apache.commons.collections4.map.MultiValueMap;

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

	public User registerUser(MultivaluedMap<String, String> formParams) {

		// Create User
		User user = User.getInstance();
		// Create User Profile
		UserProfile userProfile = UserProfile.getInstance();
		if (userProfile.getName() == null) {
			userProfile.setName(Name.getInstance());
		}
		Set<String> keySet = formParams.keySet();
		for (String key : keySet) {
			List<String> userInfo = formParams.get(key);
			for (String userInfoValue : userInfo) {
				if (userInfoValue != null
						&& !userInfoValue.equalsIgnoreCase("")) {
					setUserInfo(key, userInfoValue, user, userProfile);
				}
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
			// store languages in user language table
			for (String key : keySet) {
				if (key.equalsIgnoreCase(Languages.LANGUAGE)) {
					List<String> languages = formParams.get(key);
					updateUserLanguages(userid, "read", languages,userProfile);
					/*if (!languages.isEmpty()) {
						for (String language : languages) {
							short languageid = languageExist(language);
							if(languageid > 0) {
								UserLanguage userlanguage = new UserLanguage();
								userlanguage.setUserid(userid);
								userlanguage.setLanguageid(languageid);
								UserLanguageHandler.getInstance().insert(
									userlanguage);
							}
						}
					}*/
				}
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}// end of registeruser()

	/*private short languageExist(String language) {
		// TODO Auto-generated method stub
		List<Languages> languages = null;
		try {
			languages = LanguagesHandler.getInstance().findWhereLanguageEquals(language);
		} catch (LanguagesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!languages.isEmpty() && languages != null) {
			return languages.get(0).getLanguageid();
		} else {
			return 0;
		}
	}
*/
	/**
	 * 
	 * @param key
	 * @param value
	 * @param user
	 * @param userProfile
	 */
	public void setUserInfo(String key, String value, User user,
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
		} else if (key.equalsIgnoreCase(UserProfile.GENDER)) {
			userProfile.setGender(GENDER.valueOf(value.toUpperCase()));
		} else if (key.equalsIgnoreCase(UserProfile.DOB)) {
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			try {
				formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
				Date dateOfBirth = (Date) formatter.parse(value);
				userProfile.setDob(dateOfBirth);
				userProfile.setAge(deriveAge(dateOfBirth));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (key.equalsIgnoreCase(UserProfile.TNC)) {
			userProfile.setTnc(Short.parseShort(value));
		}
	}

	/*
	 * // Get nationality List<Country> nation = null;
	 * 
	 * /*try { // TODO using short name, ideal it should be iso2 or iso3 //
	 * (preferred). Change it accordingly nation =
	 * CountryHandler.getInstance().findWhereShortnameEquals( nationality); }
	 * catch (CountryException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * // update nationality if (nation != null) {
	 * userprofile.setNationality(nation.get(0).getCountryid()); }
	 */

	/**
	 * Authenticate User
	 * 
	 * isEmail -> true if email isEmail -> false if username
	 * 
	 * @throws UserException
	 */
	public Map<String, Object> authenticateUser(String authName,
			String password, boolean isEmail) throws UserException {

		Map<String, Object> loginresponse = new HashMap<String, Object>();
		List<User> user = new ArrayList<User>();
		try {
			user = (!isEmail) ? UserHandler.getInstance()
					.findWhereUsernameEquals(authName) : UserHandler
					.getInstance().findWhereEmailEquals(authName);
		} catch (UserException _e) {
			throw new UserException("User Authentication Exception: "
					+ _e.getMessage(), _e);
		}

		if (!user.isEmpty()) {
			if (user.get(0).getStatus() != 3 || user.get(0).getStatus() != 4) {
				if (BCrypt.checkpw(password, user.get(0).getPassword())) {
					// check whether counter is > 0 or not; if not then set
					// error code.
					updateLastActivity(user.get(0).getUserid(), true);
					return createUserInfoMap(user.get(0));
				} else {
					loginresponse.put("Error", "215");
					return loginresponse;
				}
			} else {
				loginresponse.put("Error", "64");
				return loginresponse;
			}
		} else {
			loginresponse.put("Error", "215");
			return loginresponse;
		}
	}

	/**
	 * creating UserInfoMap from user and userprofile data
	 */
	public MultiValueMap<String, Object> createUserInfoMap(User user) {
		MultiValueMap<String, Object> userInfo = new MultiValueMap<String, Object>();
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
		if (!userProfile.isEmpty()) {
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
				userInfo.put(UserProfile.PENNAME.toLowerCase(), userProfile
						.get(0).getPenname());
			} else {
				userInfo.put(UserProfile.PENNAME.toLowerCase(), "");
			}

			if (userProfile.get(0).getGender() != null) {
				userInfo.put(UserProfile.GENDER.toLowerCase(),
						userProfile.get(0).getGender().name().toLowerCase());
			} else {
				userInfo.put(UserProfile.GENDER.toLowerCase(), "");
			}
			if (userProfile.get(0).getProfilepic() > 0) {
				List<Files> file;
				try {
					file = FilesHandler.getInstance().findWhereFileidEquals(
							userProfile.get(0).getProfilepic());
					if (!file.isEmpty()) {
						String filepath = file.get(0).getFilepath() + "/"
								+ file.get(0).getFilename()
								+ file.get(0).getExtension();
						userInfo.put(UserProfile.PROFILEPIC.toLowerCase(),
								filepath);
					}
				} catch (FileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				userInfo.put(UserProfile.PROFILEPIC.toLowerCase(), "");
			}
		}
		return userInfo;
	}

	/*
	 * Create EditedInfo Map dob nationality website biography level tnc
	 */
	public MultiValueMap<String, Object> createEditedInfoMap(User user) {
		MultiValueMap<String, Object> userInfo = createUserInfoMap(user);
		List<UserProfile> userProfile = null;
		List<Address> userAddress = null;
		if (UserProfile.getInstance().getName() == null) {
			UserProfile.getInstance().setName(Name.getInstance());
		}

		try {
			userProfile = UserProfileHandler.getInstance()
					.findWhereUseridEquals(user.getUserid());
			userAddress = AddressHandler.getInstance().findWhereUseridEquals(
					user.getUserid());
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!userProfile.isEmpty() && userProfile != null) {
			if (userProfile.get(0).getName().getMiddlename() != null) {
				userInfo.put(Name.MIDDLENAME.toLowerCase(), userProfile.get(0)
						.getName().getMiddlename());
			} else {
				userInfo.put(Name.MIDDLENAME.toLowerCase(), "");
			}

			if (userProfile.get(0).getDob() != null) {
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				format.setTimeZone(TimeZone.getTimeZone("UTC"));
				String formatted = format.format(userProfile.get(0).getDob());
				userInfo.put(UserProfile.DOB.toLowerCase(), formatted);
			} else {
				userInfo.put(UserProfile.DOB.toLowerCase(), "");
			}
			if (userProfile.get(0).getNationality() > 0) {
				userInfo.put(UserProfile.NATIONALITY,
						getCountryIdentifiers(userProfile.get(0).getNationality(),"countryid"));
			} else {
				userInfo.put(UserProfile.NATIONALITY.toLowerCase(), "");
			}
			if (!userAddress.isEmpty() && userAddress != null) {
				if (userAddress.get(0).getAddressLine1() != null) {
					userInfo.put(Address.ADDRESSLINE1.toLowerCase(),
							userAddress.get(0).getAddressLine1());
				} else {
					userInfo.put(Address.ADDRESSLINE1.toLowerCase(), "");
				}

				if (userAddress.get(0).getAddressLine2() != null) {
					userInfo.put(Address.ADDRESSLINE2.toLowerCase(),
							userAddress.get(0).getAddressLine2());
				} else {
					userInfo.put(Address.ADDRESSLINE2.toLowerCase(), "");
				}

				if (userAddress.get(0).getAddressLine3() != null) {
					userInfo.put(Address.ADDRESSLINE3.toLowerCase(),
							userAddress.get(0).getAddressLine3());
				} else {
					userInfo.put(Address.ADDRESSLINE3.toLowerCase(), "");
				}

				if (userAddress.get(0).getCity() != null) {
					userInfo.put(Address.CITY.toLowerCase(), userAddress.get(0)
							.getCity());
				} else {
					userInfo.put(Address.CITY.toLowerCase(), "");
				}

				if (userAddress.get(0).getState() != null) {
					userInfo.put(Address.STATE.toLowerCase(), userAddress
							.get(0).getState());
				} else {
					userInfo.put(Address.STATE.toLowerCase(), "");
				}

				if (userAddress.get(0).getCountry() > 0) {
					userInfo.put(Address.COUNTRY.toLowerCase(),
							getCountryIdentifiers(userAddress.get(0).getCountry(),"countryid"));
					;
				} else {
					userInfo.put(Address.COUNTRY.toLowerCase(), "");
				}

				if (userAddress.get(0).getPincode() >= 0) {
					userInfo.put(Address.PINCODE.toLowerCase(), userAddress
							.get(0).getPincode());
				} else {
					userInfo.put(Address.PINCODE.toLowerCase(), "");
				}
			}
			if (userProfile.get(0).getBiography() != null) {
				userInfo.put(UserProfile.BIOGRAPHY.toLowerCase(), userProfile
						.get(0).getBiography());
			} else {
				userInfo.put(UserProfile.BIOGRAPHY.toLowerCase(), "");
			}
			if (userProfile.get(0).getWebsite() != null) {
				userInfo.put(UserProfile.WEBSITE.toLowerCase(), userProfile
						.get(0).getWebsite());
			} else {
				userInfo.put(UserProfile.WEBSITE.toLowerCase(), "");
			}
			if (userProfile.get(0).getUserlevel() != null) {
				userInfo.put(UserProfile.USERLEVEL.toLowerCase(), userProfile
						.get(0).getUserlevel());
			} else {
				userInfo.put(UserProfile.USERLEVEL.toLowerCase(), "");
			}
			if (userProfile.get(0).getTnc() != -1) {
				userInfo.put(UserProfile.TNC.toLowerCase(), userProfile.get(0)
						.getTnc());
			} else {
				userInfo.put(UserProfile.TNC.toLowerCase(), "");
			}
		} 
		return userInfo;
	}

	/*
	 * Being called by createEditedInfoMap method
	 * Read Nationality for userprofile or Country for useraddress 
	 * And return country name from countryid
	 * 
	 * Parameter : countryid
	 */
	private Object getCountryIdentifiers(Object countryIdentifier, String type) {
		List<Country> nation = null;
		try {
			if(type.equalsIgnoreCase("countryid")) {
				nation = CountryHandler.getInstance()
						.findWhereCountryidEquals((Short) countryIdentifier);
			} else if(type.equalsIgnoreCase("countryName")) {
				try {
					// TODO using short name, ideal it
					// should be
					// iso2 or iso3
					// (preferred). Change it accordingly
					nation = CountryHandler.getInstance()
							.findWhereShortnameEquals(
									(String) countryIdentifier);
				} catch (CountryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (CountryException e) {
			e.printStackTrace();
		}
		return nation.get(0).getShortname();
	}

	/**
	 * update lastlogin or lastaccess
	 */
	public short updateLastActivity(long userid, boolean isLastLogin) {
		long lastActivity = System.currentTimeMillis() / 1000;
		List<User> existingUser = new ArrayList<User>();
		short counter = 0;
		try {
			existingUser = UserHandler.getInstance().findWhereUseridEquals(
					userid);
		} catch (UserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// isLastLogin = true when incoming data is LastLogin
		// isLastLogin = false when incoming data is LastAccess
		for (User user : existingUser) {
			if (!isLastLogin) {
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
	 * Email or Username exists?
	 * 
	 * isAuthExist -> true if email isAUthExist -> false if username
	 * 
	 * @throws UserException
	 */

	public boolean userExists(String authName, boolean isAuthExist)
			throws UserException {

		List<User> userList = new ArrayList<User>();
		try {
			userList = (!isAuthExist) ? UserHandler.getInstance()
					.findWhereUsernameEquals(authName) : UserHandler
					.getInstance().findWhereEmailEquals(authName);
		} catch (UserException _e) {
			throw new UserException("User Authentication Exception: "
					+ _e.getMessage(), _e);
		}

		// Returns false when userlist is empty else true (Email or Username
		// exists)
		// false -> email OR Username does not exists, true -> email or Username
		// exists
		return (!userList.isEmpty());
	}// end of emailExists()

	/**
	 * Change account password -
	 * 
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
	public String resetPassword(String authName, boolean isEmail)
			throws UserException {
		List<User> user = new ArrayList<User>();
		Object error = null;
		String newPassword = RandomStringUtils.random(8, true, true);
		try {
			user = (!isEmail) ? UserHandler.getInstance()
					.findWhereUsernameEquals(authName) : UserHandler
					.getInstance().findWhereEmailEquals(authName);
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
	 * 
	 * @param userid
	 * @return
	 * @throws UserProfileException
	 * 
	 */
	public MultiValueMap<String, Object> getProfile(long userid)
			throws UserProfileException {
		User user = User.getInstance();
		MultiValueMap<String, Object> profileInfo = new MultiValueMap<String, Object>();
		try {
			user = UserHandler.getInstance().findByPrimaryKey(userid);
			profileInfo = createEditedInfoMap(user);
			if (profileInfo.isEmpty()) {
				profileInfo.put("error", 215);
			}
		} catch (UserException _e) {
			// TODO Auto-generated catch block
			throw new UserProfileException("Get profile exception: "
					+ _e.getMessage(), _e);
		}
		return profileInfo;
	}

	/**
	 * Edit profile
	 * 
	 * throws UserException
	 */

	// TODO userlevel, usertype, status

	public String editProfile(MultivaluedMap<String, String> formParams)
			throws FileException {
		long userid = 0;
		List<UserProfile> userList = null;
		String editResult =  null;
		Set<String> keySet = formParams.keySet();
		for (String key : keySet) {
			if (key.equalsIgnoreCase(UserProfile.USERID)) {
				List<String> formUserid = formParams.get(key);
				for (String value : formUserid) {
					if (value != null && !value.equals("")) {
						userid = Long.parseLong(value);
						 try { 
							 userList = UserProfileHandler.getInstance().findWhereUseridEquals(userid); System.out.println(userList); 
					     } catch (UserProfileException e) { 
					    	 // TODO Auto-generated catch block 
					    	 e.printStackTrace(); 
					     }
						editResult = setEditedInfo(userid, formParams, userList);
					}
				}
			}
		}
		return editResult;
	}

	public String setEditedInfo(long userid,
			MultivaluedMap<String, String> formParams,
			List<UserProfile> userList) {
			short counter = 0;
			String userProfileResult = null;
			String userAddressResult = null;
			String userInterestResult = null;
			String userLanguageResult = null;
			String profilepicResult = null;
			String userResult = null;
			String result = null;
		if (userid != 0) {
			for (UserProfile userProfile : userList) {
				if (userProfile.getName() == null) {
					userProfile.setName(Name.getInstance());
				}
				if (userProfile.getAddress() == null) {
					userProfile.setAddress(Address.getInstance());
				}
				userProfile.setUserid(userid);
				short usertype = 0;
				List<Country> nation = null;
				Set<String> keySet = formParams.keySet();
				for (String key : keySet) {
					List<String> userInfo = formParams.get(key);
					if (!(key.equalsIgnoreCase(UserProfile.RLANG)
							|| key.equalsIgnoreCase(UserProfile.WLANG) || key
								.equalsIgnoreCase(UserProfile.INTERESTS))) {
						for (String userInfoValue : userInfo) {
							if (userInfoValue != null
									&& !userInfoValue.equalsIgnoreCase("")) {
								// Set userType
								if (key.equalsIgnoreCase(UserProfile.USERTYPE)) {
									if (userInfoValue
											.equalsIgnoreCase(USERTYPE.READER
													.toString())
											|| userInfoValue
													.equalsIgnoreCase(USERTYPE.WRITER
															.toString())
											|| userInfoValue
													.equalsIgnoreCase(USERTYPE.PUBLISHER
															.toString())
											|| userInfoValue
													.equalsIgnoreCase(USERTYPE.SERIVCEPROVIDER
															.toString())) {
										usertype = (short) USERTYPE.valueOf(
												userInfoValue.toUpperCase())
												.ordinal();
									}
								}
								// Set/Update  User Name
								else if (key
										.equalsIgnoreCase(UserProfile.USERNAME)) {
									userResult = UpdateUserName(userInfoValue, userid);
								}
								// Set/Update Pen name
								else if (key
										.equalsIgnoreCase(UserProfile.PENNAME)) {
									userProfile.setPenname(userInfoValue);
								} 
								// Update first name 
								else if (key.equalsIgnoreCase(Name.FIRSTNAME)) {
									userProfile.getName().setFirstname(
											userInfoValue);
								} 
								// Set/Update middle name
								else if (key
										.equalsIgnoreCase(Name.MIDDLENAME)) {
									userProfile.getName().setMiddlename(
											(userInfoValue));
								} 
								// Update last name
								else if (key.equalsIgnoreCase(Name.LASTNAME)) {
									userProfile.getName().setLastname(
											userInfoValue);
								} 
								// Update Gender
								else if (key
										.equalsIgnoreCase(UserProfile.GENDER)) {
									userProfile.setGender(GENDER
											.valueOf(userInfoValue
													.toUpperCase()));
								} 
								// update Date of Birth
								else if (key
										.equalsIgnoreCase(UserProfile.DOB)) {
									DateFormat formatter = new SimpleDateFormat(
											"MM/dd/yyyy");
									try {
										Date dateOfBirth = (Date) formatter
												.parse(userInfoValue);
										userProfile.setDob(dateOfBirth);
										userProfile
												.setAge(deriveAge(dateOfBirth));
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} 
								// Set/Update nationality
								else if (key
										.equalsIgnoreCase(UserProfile.NATIONALITY)) {
									userProfile.setNationality((Short) getCountryIdentifiers(userInfoValue, "countryName"));
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
									profilepicResult = hasProfilePic(userid,
											userInfoValue, userProfile);
									
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
								else if(key.equalsIgnoreCase(Address.ADDRESSLINE3)) { 
									userProfile.getAddress().setAddressLine3(userInfoValue);
								} 
								// Set/Update city
								else if (key.equalsIgnoreCase(Address.CITY)) {
									userProfile.getAddress().setCity(
											userInfoValue);
								} 
								// Set/Update pincode
								else if (key
										.equalsIgnoreCase(Address.PINCODE)) {
									userProfile.getAddress().setPincode(
											Integer.parseInt(userInfoValue));
								} 
								// Set/Update state
								else if (key.equalsIgnoreCase(Address.STATE)) {
									userProfile.getAddress().setState(
											userInfoValue);
								} 
								// Set/Update country
								else if (key
										.equalsIgnoreCase(Address.COUNTRY)) {
									userProfile.getAddress().setCountry(
											(Short) getCountryIdentifiers(userInfoValue, "countryName"));
								}
							} else {
								usertype = (short) com.qpeka.db.Constants.USERTYPE.UNSPECIFIED
										.ordinal();
							}
						}
					}

					// Update User Interests
					else if (key.equalsIgnoreCase(UserProfile.INTERESTS)) {
						userInterestResult = updateUserInterests(userInfo,
								userid, userProfile);
					} else if (key.equalsIgnoreCase(UserProfile.RLANG)) {
						// Read Language if language is rLang {
						userLanguageResult = updateUserLanguages(userid,
								"read", userInfo, userProfile);
					} else if (key.equalsIgnoreCase(UserProfile.WLANG)) {
						// Read Language if language is wLang {
						userLanguageResult = updateUserLanguages(userid,
								"write", userInfo, userProfile);
					}
				}
				// User Points if (profile.get(UserProfile.USERPOINTS) != null)
				// {
				// userProfile.getUserpoints();
				// User Level userProfile.getUserlevel();
				// User Type userProfile.getUsertype();
				try {
					if(UserProfileHandler.getInstance()
							.update(userid, userProfile) > 0) {
						userProfileResult = "success : 200";
					} else {
						userProfileResult = "error : 215";
				  }
				  userAddressResult = setUserAddress(userid, userProfile.getAddress());
				} catch (UserProfileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if ((userProfileResult.equalsIgnoreCase("error : 215") || userProfileResult != null)
				&& (userAddressResult.equalsIgnoreCase("error : 215") || userAddressResult != null)
				&& (userInterestResult.equalsIgnoreCase("error : 215") || userInterestResult != null)
				&& (userLanguageResult.equalsIgnoreCase("error : 215") || userLanguageResult != null)
				&& (profilepicResult.equalsIgnoreCase("error : 215") || profilepicResult != null)
				&& (userResult.equalsIgnoreCase("error : 215") || userResult != null)) {

			result = "success : 200";
		} else {
			result = "error : 215";
		}
		return result;
	}

	private String updateUserInterests(List<String> userInfo, long userid, UserProfile userProfile) {
		String result = null;
		List<Object> editedInfoList = new ArrayList<Object>();
		List<Category> categories = null;
		List<Category> genre = null;
		
		// Constructing unique category set
		Set<Category> uniqueSet = new HashSet<Category>();
		for (String category : userInfo) {
			editedInfoList.add(category);
		}
		try {
			genre = CategoryHandler.getInstance().findByDynamicWhere(
					buildQuery("genre", userInfo.size()), editedInfoList);
			categories = CategoryHandler.getInstance().findByDynamicWhere(
					buildQuery("category", userInfo.size()), editedInfoList);

			uniqueSet.addAll(genre);
			uniqueSet.addAll(categories);
			if (!uniqueSet.isEmpty() && uniqueSet != null) {
				UserInterests userInterests = UserInterests.getInstance();
				
				for (Category category : uniqueSet) {
					userInterests.setUserid(userid);
					userInterests.setCategoryid(category.getCategoryid());
					userInterests = UserInterestsHandler.getInstance().insert(userInterests);
				}	
				if(userInterests != null) {
					userProfile.setInterests(uniqueSet);
					result = "success : 200";
				} else {
					result = "error : 215";
				}
			} 			 		 
		} catch (CategoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserInterestsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Updates user languages
	 * 
	 * @param userid
	 * @param languageType
	 * @param languageObj
	 * @return Set of languages used by user
	 */
	public String updateUserLanguages(long userid, String languageType, List<String> userInfo, UserProfile userProfile) {
		String result = null;
		List<Object> editedLanguages = new ArrayList<Object>();
		List<Languages> languageList = null;
		for (String lang : userInfo) {
			editedLanguages.add(lang);
		}
		try {
			languageList = LanguagesHandler.getInstance().findByDynamicWhere(
					buildQuery("language", userInfo.size()), editedLanguages);
			
			if (!languageList.isEmpty() && languageList != null) {
				UserLanguage userlang = UserLanguage.getInstance();
				for (Languages lang : languageList) {
					if(lang.getLanguageid() > 0) {
						userlang.setUserid(userid);
						userlang.setType(languageType);
						userlang.setLanguageid(lang.getLanguageid());
						userlang = UserLanguageHandler.getInstance().insert(userlang);
					}
				}
				if(userlang != null) {
					if(languageType.equalsIgnoreCase("read")) {
						userProfile.setrLang(new HashSet<Languages>(languageList));
					} else if (languageType.equalsIgnoreCase("write")) {
						userProfile.setwLang(new HashSet<Languages>(languageList));
					}	
					result = "success : 200";
				} else {
				    result = "error : 215";
				}
			}
		} catch (UserLanguageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LanguagesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * @SuppressWarnings("unchecked") private Set<Category>
	 * updateUserInterests(long userid, Object categoryObj) { Set<String>
	 * allCategorySet = new HashSet<String>();
	 * allCategorySet.addAll((Collection<? extends String>) categoryObj);
	 * Set<String> constants = new HashSet<String>();
	 * 
	 * Iterator<String> categoryIt = allCategorySet.iterator();
	 * 
	 * //List<Category> interestList = new ArrayList<Category>();
	 * 
	 * while(categoryIt.hasNext()) { short constantOrdinal = (short)
	 * CATEGORY.valueOf(categoryIt.next()).ordinal();
	 * System.out.println(constantOrdinal);
	 * System.out.println(CATEGORY.valueOf(categoryIt.next()).name());
	 * if(constantOrdinal >= 0){
	 * constants.add(CATEGORY.valueOf(categoryIt.next()).name())allCategorySet;
	 * } }
	 * 
	 * System.out.println(constants); //interestList = (List<Category>)
	 * getProfilePreferences("category", categoryObj,
	 * CategoryHandler.getInstance()); return null;
	 * 
	 * }
	 */

	private String UpdateUserName(String userInfoValue, long userid) {
		String userResult = null;
		User user = User.getInstance();
		try {
			if (!UserManager.getInstance().userExists(userInfoValue, false)) {
				user.setUsername(userInfoValue);
				if(UserHandler.getInstance().update(userid, user) > 0) {
					userResult = "success : 200";
				} else {
					userResult = "error : 215";
				}
			} else {
				userResult = "error : 34";
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userResult;
	}

	/**
	 * Method to be called by setEditInfo method for editProfile
	 * 
	 * @param userid
	 * @param address
	 */
	public String setUserAddress(long userid, Address address) {
		String result = null;
		short counter = 0;
		List<Address> useridExist = null;
		List<Object> readUseridObj = new ArrayList<Object>();
		readUseridObj.add(userid);
		address.setTimestamp(System.currentTimeMillis() / 1000L);
		try {
			useridExist = AddressHandler.getInstance().findByDynamicWhere(
					"userid IN (?)", readUseridObj);
			if (!useridExist.isEmpty()) {
				counter += AddressHandler.getInstance().update(
						(long) useridExist.get(0).getAddressid(), address);
			} else {
				address.setUserid(userid);
				AddressHandler.getInstance().insert(address);
			}
			if(counter > 0) {
				result = "success : 200";
			} else {
				result = "error = 215";
			}
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method to be called by setEditInfo method for editProfile
	 * 
	 * @param userid
	 * @param address
	 * @return
	 */
	public String hasProfilePic(long userid, String userInfoValue, UserProfile userProfile) {
		Files file = Files.getInstance();
		long fileid = 0;
		String profileResult = null;
		List<Files> userPicExist = null;
		List<Object> readUseridObj = new ArrayList<Object>();
		readUseridObj.add(userid);
		try {
			userPicExist = FilesManager.getInstance().readFiles(userid,
					"profilepic", Files.FILETYPE);
			if (!userPicExist.isEmpty()) {
				if(FilesHandler.getInstance().update(
						userPicExist.get(0).getFileid(), file) > 0) {
					fileid = userPicExist.get(0).getFileid();
				}
			} else {
				file = FilesManager.getInstance().createFiles(userid, "profilepic", userInfoValue);
				if(file != null) { 
					fileid = file.getFileid();
				}
			}
			if (fileid > 0) {
				profileResult = "success : 200";
				userProfile.setProfilepic(fileid);
			} else {
				profileResult = "error : 215";
			}
		} catch (FileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profileResult;
	}

	/*
	 * if (formParams.get(UserProfile.USERID) != null) { userid =
	 * Long.parseLong(profile.get(UserProfile.USERID).toString()); usertype =
	 * (short) ((profile.get(UserProfile.USERTYPE) != null) ?
	 * Short.parseShort(profile.get(UserProfile.USERTYPE).toString()) :
	 * com.qpeka.db.Constants.USERTYPE.READER.ordinal());
	 * 
	 * if (profile.get(UserProfile.PENNAME) != null) {
	 * userProfile.setPenname(profile.get(UserProfile.PENNAME) .toString()); }
	 * 
	 * // set firstName, LastName and MiddleName for UserProfile if
	 * (profile.get(UserProfile.NAME) != null) { Name name = Name.getInstance();
	 * 
	 * if (profile.get(Name.FIRSTNAME) != null) {
	 * name.setFirstname(profile.get(Name.FIRSTNAME).toString()); }
	 * 
	 * if (profile.get(Name.MIDDLENAME) != null) {
	 * name.setMiddlename(profile.get(Name.MIDDLENAME).toString()); }
	 * 
	 * if (profile.get(Name.LASTNAME) != null) {
	 * name.setLastname(profile.get(Name.LASTNAME).toString()); }
	 * 
	 * userProfile.setName(name); } // check and set Gender for UserProfile if
	 * (profile.get(UserProfile.GENDER) != null) {
	 * userProfile.setGender(GENDER.valueOf(profile.get(
	 * UserProfile.GENDER).toString())); } // Check and set Date of birth and
	 * age for userProfile. if (profile.get(UserProfile.DOB) != null) {
	 * DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); Date dob; try { dob =
	 * (Date) df.parse(profile.get(UserProfile.DOB) .toString());
	 * userProfile.setDob(dob); userProfile.setAge(deriveAge(dob)); } catch
	 * (ParseException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 * 
	 * // Check and set Nationality for userProfile if
	 * (profile.get(UserProfile.NATIONALITY) != null) { List<Country> nation =
	 * null; try { // TODO using short name, ideal it should be iso2 or iso3 //
	 * (preferred). Change it accordingly nation = CountryHandler.getInstance()
	 * .findWhereShortnameEquals( profile.get(UserProfile.NATIONALITY)
	 * .toString()); } catch (CountryException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } userProfile .setNationality((short)
	 * nation.get(0).getCountryid()); }
	 * 
	 * // set WEbSite for UserProfile if (profile.get(UserProfile.WEBSITE) !=
	 * null) { userProfile.setWebsite(profile.get(UserProfile.WEBSITE)
	 * .toString()); }
	 * 
	 * // Set Biography for userProfile if (profile.get(UserProfile.BIOGRAPHY)
	 * != nul userProfile.setNationality(nation.get(0).getCountryid()); } } {
	 * userProfile.setBiography(profile.get(UserProfile.BIOGRAPHY) .toString());
	 * }
	 * 
	 * // Set ProfilePic for UserProfile if (profile.get(UserProfile.PROFILEPIC)
	 * != null) { Files file = new Files(); file =
	 * FilesManager.getInstance().createFiles(userid,
	 * profile.get(UserProfile.PROFILEPIC).toString());
	 * userProfile.setProfilepic(file.getFileid()); }
	 * 
	 * // Set Address, Country, State and Pin code To Addess Object if
	 * (profile.get(UserProfile.ADDRESS) != null) { Address address =
	 * Address.getInstance();
	 * 
	 * // Set AddressLine1 if (profile.get(Address.ADDRESSLINE1) != null) {
	 * address.setAddressLine1(profile.get(Address.ADDRESSLINE1) .toString()); }
	 * 
	 * // Set addressLine2 if (profile.get(Address.ADDRESSLINE2) != null) {
	 * address.setAddressLine2(profile.get(Address.ADDRESSLINE2) .toString()); }
	 * 
	 * // Set AddressLine3 if (profile.get(Address.ADDRESSLINE3) != null) {
	 * address.setAddressLine3(profile.get(Address.ADDRESSLINE3) .toString()); }
	 * 
	 * // Set City if (profile.get(Address.CITY) != null) {
	 * address.setCity(profile.get(Address.CITY).toString()); } // Set Country
	 * if (profile.get(Address.COUNTRY) != null) { List<Country> country = null;
	 * try { // TODO using short name, ideal it should be iso2 or // iso3 //
	 * (preferred). Change it accordingly country = CountryHandler
	 * .getInstance() .findWhereShortnameEquals(
	 * profile.get(Address.COUNTRY).toString()); } catch (CountryException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); }
	 * address.setCountry((short) country.get(0).getCountryid()); }
	 * 
	 * // Set PinCode if (profile.get(Address.PINCODE) != null) {
	 * address.setPincode(Short.parseShort(profile.get(
	 * Address.PINCODE).toString())); } // Set State if
	 * (profile.get(Address.STATE) != null) {
	 * address.setState(profile.get(Address.STATE).toString()); }
	 * userProfile.setAddress(address); // Set Address for UserProfile }
	 * 
	 * // User Interests if (profile.get(UserProfile.INTERESTS) != null) {
	 * 
	 * @SuppressWarnings("unchecked") List<Category> interestsList =
	 * (List<Category>) getProfilePreferences( "category",
	 * profile.get(UserProfile.INTERESTS), CategoryHandler.getInstance(),
	 * (short) 0);
	 * 
	 * UserInterests userInterests = UserInterests.getInstance();
	 * userInterests.setUserid(userid);
	 * 
	 * for (Category category : interestsList) {
	 * userInterests.setCategoryid(category.getCategoryid()); try {
	 * UserInterestsHandler.getInstance() .insert(userInterests); } catch
	 * 
	 * (UserInterestsException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } } userProfile.setInterests(new
	 * HashSet<Category>(interestsList)); }
	 * 
	 * // Read Language if (profile.get(UserProfile.RLANG) != null) {
	 * Set<Languages> userLanguages =
	 * updateUserLanguages(userid,"read",profile.get(UserProfile.RLANG));
	 * userProfile.setrLang(userLanguages); }
	 * 
	 * // Written Language if (profile.get(UserProfile.WLANG) != null) {
	 * Set<Languages> userLanguages =
	 * updateUserLanguages(userid,"write",profile.get(UserProfile.WLANG));
	 * userProfile.setrLang(userLanguages); }
	 * 
	 * // User Badges if (profile.get(UserProfile.USERBADGES) != null) {
	 * 
	 * @SuppressWarnings("unchecked") List<Badges> userBadgesList =
	 * (List<Badges>) getProfilePreferences( "typeid = ? AND badge",
	 * profile.get(UserProfile.USERBADGES), BadgesHandler.getInstance(),
	 * usertype);
	 * 
	 * UserBadges userBadges = UserBadges.getInstance();
	 * userBadges.setUserid(userid); for (Badges badges : userBadgesList) {
	 * userBadges.setBadgeid(badges.getBadgeid());
	 * 
	 * try { UserBadgesHandler.getInstance().insert(userBadges); } catch
	 * (UserBadgesException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 * 
	 * userProfile.setUserbadges(new HashSet<Badges>()); }
	 * 
	 * // User Points if (profile.get(UserProfile.USERPOINTS) != null) {
	 * 
	 * } userProfile.getUserpoints();
	 * 
	 * // User Level userProfile.getUserlevel();
	 * 
	 * // User Type userProfile.getUsertype();
	 * 
	 * try { UserProfileHandler.getInstance().update(userid, userProfile); }
	 * catch (UserProfileException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */
	/*
	 * return userProfile; }// end of edit Profile()
	 */

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
	 * Get profile preferences
	 * 
	 * @param tableName
	 * @param preferencesObj
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	public Object getProfilePreferences(String whereSql, Object preferencesObj,
			AbstractHandler abstractHandler) {
		List<String> preferenceList = new ArrayList<String>();
		preferenceList.addAll((Collection<? extends String>) preferencesObj);

		Iterator<String> preferencesIt = preferenceList.iterator();
		List<Object> preferencesObjList = new ArrayList<Object>();
		
		 * if (userType > 0) { preferencesObjList.add(userType); }
		 
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
	}*/
	
	
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
		System.out.println("iterator to be converted " + collectionIt);
		StringBuilder collectionString = new StringBuilder();
		// Create string of collection objects (comma separated)
		while (collectionIt.hasNext()) {
			if (collectionString.length() > 0) {
				collectionString.append(", ");
				collectionString.append("\'");
			}
			collectionString.append(collectionIt.next().toString());
			collectionString.append("\'");
		}
		System.out.println("converted string : " + collectionString.toString());
		return collectionString.toString();
	}
	
	/**
	 * Create query for multiple list objects (comma separated)
	 * @param args
	 */
	private String buildQuery(String field, int size) {
		StringBuilder queryString = new StringBuilder();
		queryString.append(field +" IN (?");
		for(int i=1;i< size;i++) {
			if (queryString.length() > 0) {
				queryString.append(", ");
			}
			queryString.append("?");
		}
		queryString.append(")");
		return queryString.toString();
	}
	
	public static void main(String[] args) {
		UserManager usermgr = new UserManager();
		List<String> interests = new ArrayList<String>();
		UserProfile userProfile = UserProfile.getInstance();
		interests.add("Adult");
		interests.add("Classic");
		interests.add("Fiction");
		interests.add("Children Learning");
		System.out.println(usermgr.updateUserInterests(interests,(long)1, userProfile));
		
		/*List<String> lang = new ArrayList<String>();
	lang.add("MARATHI");
	lang.add("HINDI");
	lang.add("ENGLISH");
 usermgr.updateUserLanguages((long) 1, "write", lang);
		*/
		/*
		 * User user = new User("rahul", BCrypt.hashpw("rahul",
		 * BCrypt.gensalt()),
		 * "srahul07.qpeka@gmail.com",(System.currentTimeMillis()) / 1000,
		 * "East"); user.setUsernameModified(true);
		 * user.setPasswordModified(true); user.setEmailModified(true);
		 * user.setCreatedModified(true); user.setTimezoneModified(true); try {
		 * UserHandler.getInstance().insert(user); } catch (UserException e1) {
		 * // TODO Auto-generated catch block e1.printStackTrace(); } try {
		 * Map<String, Object> usr = userman.authenticateUser("rahul", "rahul",
		 * false); System.out.println(usr.toString()); } catch (UserException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		
	}

}// End of class UserManager.java
