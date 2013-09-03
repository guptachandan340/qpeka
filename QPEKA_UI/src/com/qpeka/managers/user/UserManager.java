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

import com.mongodb.util.Hash;
import com.qpeka.db.Category;
import com.qpeka.db.Constants.GENDER;
import com.qpeka.db.Constants.STATUS;
import com.qpeka.db.Constants.TYPE;
import com.qpeka.db.Constants.USERLEVEL;
import com.qpeka.db.Constants.USERTYPE;
import com.qpeka.db.Country;
import com.qpeka.db.Files;
import com.qpeka.db.Languages;
import com.qpeka.db.conf.ResourceManager;
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
import com.qpeka.managers.ServiceResponseManager;
import com.qpeka.security.bcrypt.BCrypt;
import com.qpeka.services.Errors.ServiceResponse;

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
			MultivaluedMap<String, String> formParams) {
		ServiceResponse sResponse = ServiceResponse.getInstance();
		// Create User
		User user = User.getInstance();
		// Create User Profile
		UserProfile userProfile = UserProfile.getInstance();
		if (userProfile.getName() == null) {
			userProfile.setName(Name.getInstance());
		}
		user.setType((short) TYPE.AUTHENTIC.ordinal());
		//user.setType((short) TYPE.valueOf("AUTHENTIC".toUpperCase()).ordinal());
		
		Set<String> keySet = formParams.keySet();
		for (String key : keySet) {
			List<String> userInfo = formParams.get(key);
			if (!userInfo.isEmpty()) {
				for (String userInfoValue : userInfo) {
					if (userInfoValue != null
							&& !userInfoValue.equalsIgnoreCase("")) {
						registerUserInfo(key, userInfoValue, user, userProfile);
						//createPenName(userProfile);
					}
				}
			}
		}
		createPenName(userProfile, user);
		user.setCreated(System.currentTimeMillis() / 1000);
		user.setLastaccess(0);
		user.setLastlogin(0);
		user.setStatus((short) STATUS.DEFAULT.ordinal());
		try {
			// Insert user to database;
			Long userid = UserHandler.getInstance().insert(user);
			if (userid > 0) {
				sResponse.setStatus(200);
				userProfile.setUserid(userid);
				if (UserProfileHandler.getInstance().insert(userProfile) > 0) {
					sResponse.setStatus(200);
					// store languages in user language table
					for (String key : keySet) {
						if (key.equalsIgnoreCase(Languages.LANGUAGE)) {
							List<String> languages = formParams.get(key);
							userProfile.setrLang(updateUserLanguages(userid,
									"read", languages, sResponse));
						}
					}
				} else {
					sResponse.setStatus(215);
				}
			} else {
				sResponse.setStatus(215);
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ServiceResponseManager.getInstance().readServiceResponse(sResponse.getStatus());
	}// end of registeruser()

	/*
	 * private short languageExist(String language) { // TODO Auto-generated
	 * method stub List<Languages> languages = null; try { languages =
	 * LanguagesHandler.getInstance().findWhereLanguageEquals(language); } catch
	 * (LanguagesException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } if(!languages.isEmpty() && languages != null) {
	 * return languages.get(0).getLanguageid(); } else { return 0; } }
	 */
	/**
	 * Being called by registerUser module
	 * 
	 * @param key
	 * @param value
	 * @param user
	 * @param userProfile
	 */
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
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			try {
				formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
				Date dateOfBirth = (Date) formatter.parse(value);
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
	
	private void createPenName(UserProfile userProfile, User user) {
		
		char[] patternChar;
		patternChar = "._".toCharArray();
		List<User> users = new ArrayList<User>();
		List<Object> penNameComb = new ArrayList<Object>();
		Set<String> penNamedbSet = new HashSet<String>();
		
		// Create pennameList with Combination of fname and lname
		
		penNameComb.add(userProfile.getName().getFirstname()
				+ userProfile.getName().getLastname());
		penNameComb.add(userProfile.getName().getLastname()
				+ userProfile.getName().getFirstname());
		for (int i = 0; i < patternChar.length; i++) {
			penNameComb.add(userProfile.getName().getFirstname()
					+ patternChar[i] + userProfile.getName().getLastname());
			penNameComb.add(userProfile.getName().getLastname()
					+ patternChar[i] + userProfile.getName().getFirstname());
		}
		// Convert penNameList to hashSet
		Set<Object> penNameCombSet = new HashSet<Object>(penNameComb);
	
		// TODO findbyDynamicSelect() to find only penname.gave error getLong() not matching with String
		
		// retrieve user object from databases to check whether penExist
		try {
			users = UserHandler.getInstance().findByDynamicWhere(
					buildQuery("penname", penNameComb.size()), penNameComb);
					
			// Adding only penname to HashSet
			for (User userList : users) {
				penNamedbSet.add(userList.getPenname());
			}
			
			// Use A - ( A AND B) i.e remove common elements
			penNameCombSet.removeAll(penNamedbSet);
			
			// Set first uncommon element to user object
			user.setPenname((penNameCombSet.iterator().next()).toString());
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * METHOD FOR REGISTER AND EDIT PROFILE MODULE
	 * 
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
					.findWherePennameEquals(authName) : UserHandler
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

	
	/***************************** AUTHENTICATION MODULE ********************************/
	
	/**
	 * Authenticate User
	 * 
	 * isEmail -> true if email isEmail -> false if username
	 * 
	 * @throws UserException
	 */
	public Map<String, Object> authenticateUser(String authName,
			String password, boolean isEmail) throws UserException {

		ServiceResponse sResponse = ServiceResponse.getInstance();
		Map<String, Object> loginresponse = new HashMap<String, Object>();
		List<User> user = new ArrayList<User>();
		try {
			user = (!isEmail) ? UserHandler.getInstance()
					.findWherePennameEquals(authName) : UserHandler
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
					sResponse.setStatus(215);
				}
			} else {
				sResponse.setStatus(64);
			}
		} else {
			sResponse.setStatus(215);
		}
		loginresponse.put("Service Error: ", ServiceResponseManager.getInstance()
				.readServiceResponse(sResponse.getStatus()));
		return loginresponse;
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
			if (user.getPenname() != null) {
				userInfo.put(User.PENNAME.toLowerCase(), user.getPenname());
			} else {
				userInfo.put(User.PENNAME.toLowerCase(), "");
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
					if (!file.isEmpty() && file != null) {
						userInfo.put(UserProfile.PROFILEPIC.toLowerCase(), ResourceManager.getFILE_PROFILEPIC()+file
								.get(0).getFilepath());
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

	/**
	 * update lastlogin or lastaccess
	 */
	public Map<String, Object> updateLastActivity(long userid,
			boolean isLastLogin) {
		long lastActivity = System.currentTimeMillis() / 1000;
		List<User> existingUser = new ArrayList<User>();
		ServiceResponse sResponse = ServiceResponse.getInstance();
		try {
			existingUser = UserHandler.getInstance().findWhereUseridEquals(
					userid);
		} catch (UserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// isLastLogin = true when incoming data is LastLogin
		// isLastLogin = false when incoming data is LastAccess
		if (!existingUser.isEmpty()) {
			for (User user : existingUser) {
				if (!isLastLogin) {
					user.setLastaccess(lastActivity);
				} else {
					user.setLastlogin(lastActivity);
				}
				try {
					if (UserHandler.getInstance().update(userid, user) != -1) {
						sResponse.setStatus(200);
					} else {
						sResponse.setStatus(215);
					}
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ServiceResponseManager.getInstance().readServiceResponse(sResponse.getStatus());
	}

	/***************************** UPDATE PASSWORD MODULE ********************************/
	
	/**
	 * Change account password -
	 * 
	 * @throws UserException
	 */
	public Map<String, Object> changePassword(long userid,
			String currentPassword, String newPassword) throws UserException {
		ServiceResponse sResponse = ServiceResponse.getInstance();
		List<User> userInfoList = new ArrayList<User>();
		userInfoList = UserHandler.getInstance().findWhereUseridEquals(userid);
		for (User user : userInfoList) {
			if (BCrypt.checkpw(currentPassword, user.getPassword())) {
				try {
					user.setPassword(BCrypt.hashpw(newPassword,
							BCrypt.gensalt()));
					UserHandler.getInstance().update(userid, user);
					sResponse.setStatus(200);
				} catch (UserException _e) {
					throw new UserException("Update User Password Exception: "
							+ _e.getMessage(), _e);
				}
			} else {
				sResponse.setStatus(215);
			}
		}
		return ServiceResponseManager.getInstance().readServiceResponse(sResponse.getStatus());
	}// end of changePassword()

	/**
	 * Reset users password
	 * 
	 * @throws UserException
	 */
	public String resetPassword(String authName, boolean isEmail)
			throws UserException {
		List<User> user = new ArrayList<User>();
		String newPassword = RandomStringUtils.random(8, true, true);
		try {
			user = (!isEmail) ? UserHandler.getInstance()
					.findWherePennameEquals(authName) : UserHandler
					.getInstance().findWhereEmailEquals(authName);
		} catch (UserException _e) {
			throw new UserException("Reset user Password Exception: "
					+ _e.getMessage(), _e);
		}

		if (!user.isEmpty()) {
			user.get(0).setPassword(
					BCrypt.hashpw(newPassword, BCrypt.gensalt()));
			if (UserHandler.getInstance().update(user.get(0).getUserid(),
					user.get(0)) != -1) {
				return newPassword;
			}
		}
		return null;
	} // end of reset password()

	/***************************** GET PROFILE MODULE ********************************/
	
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
		ServiceResponse sResponse = ServiceResponse.getInstance();
		MultiValueMap<String, Object> profileInfo = new MultiValueMap<String, Object>();
		try {
			user = UserHandler.getInstance().findByPrimaryKey(userid);
			profileInfo = createProfileInfoMap(user);
			if (profileInfo.isEmpty()) {
				sResponse.setStatus(215);
				profileInfo.put("Error", ServiceResponseManager.getInstance()
						.readServiceResponse(sResponse.getStatus()));
			}
		} catch (UserException _e) {
			// TODO Auto-generated catch block
			throw new UserProfileException("Get profile exception: "
					+ _e.getMessage(), _e);
		}
		return profileInfo;
	}

	/*
	 * Create EditedInfo Map dob nationality website biography level tnc
	 */
	public MultiValueMap<String, Object> createProfileInfoMap(User user) {
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
				userInfo.put(
						UserProfile.NATIONALITY,
						getCountryIdentifiers(userProfile.get(0)
								.getNationality(), "countryid"));
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
					userInfo.put(
							Address.COUNTRY.toLowerCase(),
							getCountryIdentifiers(userAddress.get(0)
									.getCountry(), "countryid"));
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
			/*if (userProfile.get(0).getUserlevel() != null) {
				userInfo.put(UserProfile.USERLEVEL.toLowerCase(), userProfile
						.get(0).getUserlevel());
			} else {
				userInfo.put(UserProfile.USERLEVEL.toLowerCase(), "");
			}*/
		}
		return userInfo;
	}

	/*
	 * Being called by createEditedInfoMap method Read Nationality for
	 * userprofile or Country for useraddress And return country name from
	 * countryid
	 * 
	 * Parameter : countryid
	 */
	private Object getCountryIdentifiers(Object countryIdentifier, String type) {
		List<Country> nation = null;
		try {
			if (type.equalsIgnoreCase("countryid")) {
				nation = CountryHandler.getInstance().findWhereCountryidEquals(
						(Short) countryIdentifier);
				return nation.get(0).getShortname();
			}
			// if(type.equalsIgnoreCase("countryName")
			else {
				try {
					// TODO using short name, ideal it
					// should be
					// iso2 or iso3
					// (preferred). Change it accordingly
					nation = CountryHandler.getInstance()
							.findWhereShortnameEquals(
									(String) countryIdentifier);
					return nation.get(0).getCountryid();
				} catch (CountryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (CountryException e) {
			e.printStackTrace();
		}
		return null;

	}

	/***************************** EDIT PROFILE MODULE ********************************/
	
	/**
	 * Edit profile
	 * 
	 * throws UserException
	 */

	// TODO userlevel, usertype, status

	public Map<String, Object> editProfile(
			MultivaluedMap<String, String> formParams) throws FileException {
		long userid = 0;
		ServiceResponse sResponse = ServiceResponse.getInstance();
		List<UserProfile> userList = null;
		Set<String> keySet = formParams.keySet();
		for (String key : keySet) {
			if (key.equalsIgnoreCase(UserProfile.USERID)) {
				List<String> formUserid = formParams.get(key);
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
						if (!userList.isEmpty()) {
							setEditedInfo(userid, formParams, userList, sResponse);
						}
					}
				}
			}
		}
		return ServiceResponseManager.getInstance().readServiceResponse(sResponse.getStatus());

	}

	public ServiceResponse setEditedInfo(long userid,
			MultivaluedMap<String, String> formParams,
			List<UserProfile> userList, ServiceResponse sResponse) {
		if (userid != 0) {
			for (UserProfile userProfile : userList) {
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
							|| key.equalsIgnoreCase(UserProfile.WLANG) || key
								.equalsIgnoreCase(UserProfile.INTERESTS))) {
						for (String userInfoValue : userInfo) {
							if (userInfoValue != null
									&& !userInfoValue.equalsIgnoreCase("")) {
								// Set/Update User Name
								if (key.equalsIgnoreCase(User.PENNAME)) {
									UpdatePenName(userInfoValue, userid, sResponse);
								}
								// Set/Update Pen name
								/*
								 * else if (key
								 * .equalsIgnoreCase(UserProfile.PENNAME)) {
								 * userProfile.setPenname(userInfoValue); }
								 */
								// Update first name
								else if (key.equalsIgnoreCase(Name.FIRSTNAME)) {
									userProfile.getName().setFirstname(
											userInfoValue);
								}
								// Set/Update middle name
								else if (key.equalsIgnoreCase(Name.MIDDLENAME)) {
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
								else if (key.equalsIgnoreCase(UserProfile.DOB)) {
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
									hasProfilePic(userid, userInfoValue,
											userProfile, sResponse);

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
						userProfile.setInterests(updateUserInterests(userInfo,
								userid, sResponse));
					} else if (key.equalsIgnoreCase(UserProfile.RLANG)) {
						// Read Language if language is rLang {
						userProfile.setrLang(updateUserLanguages(userid,
								"read", userInfo, sResponse));
					} else if (key.equalsIgnoreCase(UserProfile.WLANG)) {
						// Read Language if language is wLang {
						userProfile.setwLang(updateUserLanguages(userid,
								"write", userInfo, sResponse));
					}
				}
				// User Points if (profile.get(UserProfile.USERPOINTS) != null)
				// {
				// userProfile.getUserpoints();
				// User Level userProfile.getUserlevel();
				// User Type userProfile.getUsertype();
				try {
						if (UserProfileHandler.getInstance().update(userid,
								userProfile) > 0) {
							sResponse.setStatus(200);
						} else {
							sResponse.setStatus(215);
						}
						updateUserAddress(userid, userProfile.getAddress(), sResponse);
				} catch (UserProfileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return sResponse;
	}

	/**
	 * Method to be called by setEditInfo method for editProfile
	 * 
	 * @param userid
	 * @param address
	 * @return
	 */
	public void hasProfilePic(long userid, String userInfoValue,
			UserProfile userProfile, ServiceResponse sResponse) {
		Files file = Files.getInstance();
		long fileid = 0;
		List<Files> userPicExist = null;
		userPicExist = FilesManager.getInstance().readFiles(userid,
				"profilepic", Files.FILETYPE);
		if (!userPicExist.isEmpty()) {
			if (FilesManager.getInstance().updateFiles(
					userPicExist.get(0).getFileid(), userInfoValue) != -1) {
				fileid = userPicExist.get(0).getFileid();
			}
		} else {
			file = FilesManager.getInstance().InsertFiles(userid, "profilepic", userInfoValue);
			if(file != null) {
				fileid = file.getFileid();
			}
		}
		if (fileid > 0) {
			sResponse.setStatus(200);
			userProfile.setProfilepic(fileid);
		} else {
			sResponse.setStatus(215);
		}
	}

	private void UpdatePenName(String userInfoValue, long userid,
			ServiceResponse sResponse) {
		User user = User.getInstance();
		try {
			if (!UserManager.getInstance().userExists(userInfoValue, false)) {
				user.setPenname(userInfoValue);
				if (UserHandler.getInstance().update(userid, user) >= 0) {
					sResponse.setStatus(200);
				} else {
					sResponse.setStatus(215);
				}
			} else {
				sResponse.setStatus(34);
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to be called by setEditInfo method for editProfile
	 * 
	 * @param userid
	 * @param address
	 */
	public void updateUserAddress(long userid, Address address,
			ServiceResponse sResponse) {
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
			if (counter >= 0) {
				sResponse.setStatus(200);
			} else {
				sResponse.setStatus(215);
			}
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Set<Category> updateUserInterests(List<String> userInfo,
			long userid, ServiceResponse sResponse) {
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
					userInterests = UserInterestsHandler.getInstance().insert(
							userInterests);
				}
				if (userInterests != null) {
					sResponse.setStatus(200);
					return uniqueSet;
				} else {
					sResponse.setStatus(215);
				}
			}
		} catch (CategoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserInterestsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uniqueSet;
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
			List<String> userInfo, ServiceResponse sResponse) {
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
					if (lang.getLanguageid() > 0) {
						userlang.setUserid(userid);
						userlang.setType(languageType);
						userlang.setLanguageid(lang.getLanguageid());
						userlang = UserLanguageHandler.getInstance().insert(
								userlang);
					}
				}
				if (userlang != null) {
					sResponse.setStatus(200);
					return (new HashSet<Languages>(languageList));
				} else {
					sResponse.setStatus(215);
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
		return (new HashSet<Languages>(languageList));
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
			short usertype = 0;
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
			return usertype;
		}

	/***************************** AGE MODULE ********************************/

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

	/***************************** MAIN MODULE ********************************/
	
	public static void main(String[] args) {
		UserManager usermgr = UserManager.getInstance();
	/*	User user = User.getInstance();
		UserProfile userProfile = UserProfile.getInstance();
		if (userProfile.getName() == null) {
			userProfile.setName(Name.getInstance());
		}
		userProfile.getName().setFirstname("mehul");
		userProfile.getName().setLastname("malani");
		user.setEmail("mehulmalani16@yahoo.com ");
		usermgr.createPenName(userProfile, user);*/
		/*List<String> interests = new ArrayList<String>();
		ServiceResponse sResponse = ServiceResponse.getInstance();
		interests.add("Adult");
		interests.add("Classic");
		interests.add("Fiction");
		interests.add("Children Learning");
		System.out.println(usermgr.updateUserInterests(interests, (long) 1,
				sResponse));

		List<String> lang = new ArrayList<String>();
		lang.add("MARATHI");
		lang.add("HINDI");
		lang.add("ENGLISH");
		usermgr.updateUserLanguages((long) 1, "write", lang, sResponse);*/
	}
}// End of class UserManager.java
