package com.qpeka.managers.user;

import java.util.Date;
import java.util.List;

import com.qpeka.db.Constants.GENDER;
import com.qpeka.db.Constants.USERSTATUS;
import com.qpeka.db.Country;
import com.qpeka.db.exceptions.CountryException;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.exceptions.user.UserProfileException;
import com.qpeka.db.handler.CountryHandler;
import com.qpeka.db.handler.user.UserHandler;
import com.qpeka.db.handler.user.UserProfileHandler;
import com.qpeka.db.user.User;
import com.qpeka.db.user.profile.Name;
import com.qpeka.db.user.profile.UserProfile;
import com.qpeka.security.bcrypt.BCrypt;

public class UserManager {

	private static UserManager instance = null;

	public UserManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserManager getInstance() {
		return (instance == null ? (instance = new UserManager()) : instance);
	}

	/**
	 * Register User to Qpeka
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
	}

}
