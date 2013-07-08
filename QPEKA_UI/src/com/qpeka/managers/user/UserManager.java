package com.qpeka.managers.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
	public User authenticateByEmail(String email, String password)
			throws UserException {
		List<User> user = new ArrayList<User>();
		try {
			user = UserHandler.getInstance().findWhereEmailEquals(email);
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
	public User authenticateByUsername(String username, String password)
			throws UserException {
		List<User> user = new ArrayList<User>();
		try {
			user = UserHandler.getInstance().findWhereUsernameEquals(username);
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

	}// end of authenticateByUsername()

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

		if (!userList.isEmpty()) {
			return true;
		} else {
			return false;
		}
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

		if (!userList.isEmpty()) {
			return true;
		} else {
			return false;
		}
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
	public User resetPassword(String username, boolean usernameStatus)
			throws UserException {
		List<User> user = new ArrayList<User>();
		if (!usernameStatus) {
			try {
				user = UserHandler.getInstance().findWhereUsernameEquals(
						username);
			} catch (UserException _e) {
				throw new UserException("Reset user Password Exception: "
						+ _e.getMessage(), _e);
			}
		}
		return user.get(0);
	} // end of resetpassword()

	/**
	 * Edit profile
	 * 
	 * throws UserException
	 */
	public UserProfile editProfile(Map<String, String> profile) {
		UserProfile userProfile = UserProfile.getInstance();
		long userid = 0;
		if (profile.get(UserProfile.USERID) != null) {
			userid = Long.parseLong(UserProfile.USERID);
			
			// Set Pen name 
			if (profile.get(UserProfile.PENNAME) != null) {
				userProfile.setPenname(profile.get(UserProfile.PENNAME));
			}
			
			// set firstName, LastName and MiddleName for UserProfile
			if (profile.get(UserProfile.NAME) != null) {
				Name name = UserProfile.getInstance().getName().getInstance();

				if (profile.get(Name.FIRSTNAME) != null) {
					name.setFirstname(profile.get(Name.FIRSTNAME));
				}

				if (profile.get(Name.MIDDLENAME) != null) {
					name.setMiddlename(profile.get(Name.MIDDLENAME));
				}

				if (profile.get(Name.LASTNAME) != null) {
					name.setLastname(profile.get(Name.LASTNAME));
				}

				userProfile.setName(name);
			}
			
			// check and set Gender for UserProfile
			if (profile.get(UserProfile.GENDER) != null) {
				userProfile.setGender(GENDER.valueOf(profile
						.get(UserProfile.GENDER)));
			}
			//Check and set Date of birth and age for userProfile.
			if (profile.get(UserProfile.DOB) != null) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date dob;
				try {
					dob = (Date) df.parse(profile.get(UserProfile.DOB));
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
									profile.get(UserProfile.NATIONALITY));
				} catch (CountryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();						

				}
				userProfile.setNationality((short) nation.get(0).getCountryid());
			}
			
			// set WEbSite for UserProfile
			if (profile.get(UserProfile.WEBSITE) != null) {
				userProfile.setWebsite(profile.get(UserProfile.WEBSITE));
			}
			
			//Set Biography for userProfile
			if (profile.get(UserProfile.BIOGRAPHY) != null) {
				userProfile.setBiography(profile.get(UserProfile.BIOGRAPHY));
			}
			
			//Set ProfilePic for UserProfile
			if (profile.get(UserProfile.PROFILEPIC) != null) {
				Files file = new Files();
				file.setFilepath(profile.get(UserProfile.PROFILEPIC));

				userProfile.setProfilepic(file.getFileid());
			    }
		    
			// Set Address, Country, State and Pin code To Addess Object
			if(profile.get(UserProfile.ADDRESS)!=null) {
				Address address = Address.getInstance();
				
				// Set AddressLine1
				if(profile.get(Address.ADDRESSLINE1)!=null) {
				    address.setAddressLine1(profile.get(Address.ADDRESSLINE1));
				}
			     
				//Set addressLine2
				if(profile.get(Address.ADDRESSLINE2)!=null) {
					address.setAddressLine2(profile.get(Address.ADDRESSLINE2));
				}
				
				//Set AddressLine3
				if(profile.get(Address.ADDRESSLINE3)!= null) {
			            address.setAddressLine3(profile.get(Address.ADDRESSLINE3));
				}
				
				//Set City
				if(profile.get(Address.CITY)!=null) {
					address.setCity(profile.get(Address.CITY));
				}
				//Set Country
				if(profile.get(Address.COUNTRY)!=null) {
					List<Country> country = null;
					try {
						// TODO using short name, ideal it should be iso2 or iso3
						// (preferred). Change it accordingly
						country = CountryHandler.getInstance()
								.findWhereShortnameEquals(
										profile.get(Address.COUNTRY));
					} catch (CountryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				address.setCountry((short) country.get(0).getCountryid());
				}
				
				//Set PinCode
				if(profile.get(Address.PINCODE)!=null) {
					address.setPincode(Short.parseShort(profile.get(Address.PINCODE)));
				}
				// Set State
				if(profile.get(Address.STATE)!=null) {
					address.setState(profile.get(Address.STATE));
				}
				userProfile.setAddress(address);  //Set Address for UserProfile
			}
			
	
					//Set<LANGUAGES> rLang1 = UserProfile.getInstance().getrLang();
		//Iterator it1 = UserProfile.getInstance().getrLang().iterator();
		Iterator<LANGUAGES> it = UserProfile.getInstance().getrLang().iterator();
			while(it.hasNext()) {
			
				if(profile.get(UserProfile.RLANG)!= null) {
					List<Languages> rLang1 = null;
					try {
						// TODO using short name, ideal it should be iso2 or iso3
						// (preferred). Change it accordingly
							
						rLang1 = LanguagesHandler.getInstance().findWhereLanguageidEquals(Short.parseShort(profile.get(UserProfile.RLANG)));
						
						it1.set(rLang1.get(0).getLanguageid());
						
					} catch (LanguagesException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					userProfile.setrLang(it.next().valueOf(profile.get(UserProfile.RLANG)));
					//userProfile.setNationality((short) nation.get(0).getCountryid());
				}
				
				if(profile.get(UserProfile.USERPOINTS)!=null) {
					Map<String, Integer> userpoints;
					
					userProfile.setUserpoints(userpoints);
				}
				
				if(profile.get(UserProfile.USERLEVEL)!=null) {
					userProfile.setUserlevel(USERLEVEL.valueOf(profile.get(UserProfile.USERLEVEL)));}
				}
			
			if(profile.get(UserProfile.USERTYPE)!= null) {
				userProfile.setUsertype(USERTYPE.valueOf(profile.get(UserProfile.USERTYPE)));
			}
				
				
					
			
			
			
		            
		   
			
			try {
				UserProfileHandler.getInstance().update(userid, userProfile);
			} catch (UserProfileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return null;
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

}// End of class UserManager.java
