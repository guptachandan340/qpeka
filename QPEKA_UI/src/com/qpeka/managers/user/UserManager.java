package com.qpeka.managers.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qpeka.db.Constants.GENDER;
import com.qpeka.db.Constants.LANGUAGES;
import com.qpeka.db.Constants.USERLEVEL;
import com.qpeka.db.Constants.USERSTATUS;
import com.qpeka.db.Constants.USERTYPE;
import com.qpeka.db.Country;
import com.qpeka.db.Files;
import com.qpeka.db.Languages;
import com.qpeka.db.exceptions.CountryException;
import com.qpeka.db.exceptions.LanguagesException;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.exceptions.user.UserProfileException;
import com.qpeka.db.handler.CountryHandler;
import com.qpeka.db.handler.LanguagesHandler;
import com.qpeka.db.handler.user.AddressHandler;
import com.qpeka.db.handler.user.UserHandler;
import com.qpeka.db.handler.user.UserLanguageHandler;
import com.qpeka.db.handler.user.UserProfileHandler;
import com.qpeka.db.user.User;
import com.qpeka.db.user.profile.Address;
import com.qpeka.db.user.profile.Name;
import com.qpeka.db.user.profile.UserLanguage;
import com.qpeka.db.user.profile.UserPoints;
import com.qpeka.db.user.profile.UserProfile;
import com.qpeka.security.bcrypt.BCrypt;

public class UserManager {
	private static UserManager instance = null;

	UserHandler userHandler = new UserHandler();

	public UserManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserManager getInstance() {
		return (instance == null ? (instance = new UserManager()) : instance);
	}

	/*
	 * public static UserHandler getInstance() { return (instance == null ?
	 * (instance = new UserHandler()) : instance); }
	 */
	/**
	 * Register User to Qpeka
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
			String nationality) {
		// Create User
		User user = new User();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
		user.setCreated(System.currentTimeMillis() / 1000);
		user.setLastaccess(0);
		user.setLastlogin(0);
		user.setStatus((short) USERSTATUS.DEFAULT.ordinal());

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
			UserHandler.getInstance().insert(user);
			UserProfileHandler.getInstance().insert(userprofile);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	 * Authenticate User
	 * 
	 * @throws UserException
	 */
	/*
	 * public User authenticateByUsername(String username, String password)
	 * throws UserException { List<User> user = new ArrayList<User>(); try {
	 * 
	 * } catch (UserException _e) { throw new
	 * UserException("User Authentication Exception: " + _e.getMessage(), _e); }
	 * 
	 * if (!user.isEmpty()) { return (BCrypt.checkpw(password,
	 * user.get(0).getPassword()) ? user .get(0) : null); } else { return null;
	 * }
	 * 
	 * }// end of authenticateByUsername()
	 */
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

		/*
		 * if (!userList.isEmpty()) { return true; } else { return false; }
		 */

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

		/*
		 * if (!userList.isEmpty()) { return true; } else { return false; }
		 */

		// Returns false when userlist is empty else true (Email exists)
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
	public User changePassword(long userid, String password)
			throws UserException {
		User user = new User();
		try {
			user = UserHandler.getInstance().findByPrimaryKey(userid);
			user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

			UserHandler.getInstance().update(userid, user);
		} catch (UserException _e) {
			throw new UserException("Update User Password Exception: "
					+ _e.getMessage(), _e);
		}

		return user;
	}// end of changePassword()

	/**
	 * Reset users password
	 * 
	 * @throws UserException
	 */
	public String resetPassword(String authName, String newPassword,
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
			throw new UserException("Reset user Password Exception: "
					+ _e.getMessage(), _e);
		}

		if (!user.isEmpty()) {
			user.get(0).setPassword(
					BCrypt.hashpw(newPassword, BCrypt.gensalt()));

			UserHandler.getInstance().update(user.get(0).getUserid(),
					user.get(0));
		}

		return user.get(0).getEmail();
	} // end of resetpassword()

	/**
	 * Edit profile
	 * 
	 * throws UserException
	 */
	public UserProfile editProfile(Map<String, Object> profile) {
		UserProfile userProfile = UserProfile.getInstance();
		long userid = 0;

		if (profile.get(UserProfile.USERID) != null) {
			userid = Long.parseLong(UserProfile.USERID);

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

			// Read Language
			if(profile.get(UserProfile.RLANG) != null) {
				@SuppressWarnings("unchecked")
				Iterator<String> languageIt = ((Set<String>) profile.get(UserProfile.RLANG)).iterator();
				List<Object> rLangObj = new ArrayList<Object>();
				rLangObj.add(convertCollectionToString(languageIt));
						
				List<Languages> rLang = null;
						
				try {
					// TODO using short name, ideal it should be iso2 or
					// iso3 (preferred). Change it accordingly
					rLang = LanguagesHandler.getInstance().findByDynamicWhere("language IN (?)", rLangObj);
				} catch (LanguagesException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				
				UserLanguage userLang = UserLanguage.getInstance();
				userLang.setUserid(userid);
				
				for(Languages language : rLang) {
					userLang.setLanguageid(language.getLanguageid());
					UserLanguageHandler.getInstance().insert(userLang);
				}
			}

				if (profile.get(UserProfile.USERPOINTS) != null) {
					Map<String, Integer> userpoints;

					userProfile.setUserpoints(userpoints);
				}

				if (profile.get(UserProfile.USERLEVEL) != null) {
					userProfile.setUserlevel(USERLEVEL.valueOf(profile
							.get(UserProfile.USERLEVEL)));
				}
			}

			if (profile.get(UserProfile.USERTYPE) != null) {
				userProfile.setUsertype(USERTYPE.valueOf(profile
						.get(UserProfile.USERTYPE)));
			}

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
	 * @param languageIt
	 * @return
	 */
	public String convertCollectionToString(Iterator<String> languageIt) {
		
		StringBuilder languageString = new StringBuilder();
		while (languageIt.hasNext()) {
			if(languageString.length() > 0) {
				languageString.append(", ");
			}
			languageString.append(languageIt.next().toString());
		}
		
		languageString.toString();
	}

}// End of class UserManager.java
