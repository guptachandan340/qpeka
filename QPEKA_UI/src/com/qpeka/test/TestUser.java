package com.qpeka.test;

import java.util.ArrayList;
import java.util.List;

import com.qpeka.db.exceptions.CountryException;
import com.qpeka.db.exceptions.FileException;
import com.qpeka.db.exceptions.GenreException;
import com.qpeka.db.exceptions.LanguagesException;
import com.qpeka.db.exceptions.user.AddressException;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.exceptions.user.UserInterestsException;
import com.qpeka.db.exceptions.user.UserLanguageException;
import com.qpeka.db.exceptions.user.UserProfileException;
import com.qpeka.db.user.User;
import com.qpeka.db.user.profile.Name;
import com.qpeka.db.user.profile.UserProfile;
import com.qpeka.managers.FilesManager;
import com.qpeka.managers.user.UserManager;

public class TestUser {

	/***************************** MAIN MODULE ********************************/

	public static void main(String[] args) {

		/*
		 * MultiValueMap<String, Object> m = new MultiValueMap<String,
		 * Object>(); m.put(User.EMAIL, "anki@sffzvz.com"); m.put(User.EMAIL,
		 * null); UserManager.getInstance().registerUser(m);
		 */

		/************* Test for create Pen Name ***************/

		/*if (UserProfile.getInstance().getName() == null) {
			UserProfile.getInstance().setName(Name.getInstance());
		}
		UserProfile.getInstance().getName().setFirstname("mehul");
		UserProfile.getInstance().getName().setLastname("malani");
		User.getInstance().setEmail("mehulmalani@yahoo.com ");
		try {
			UserManager.getInstance().createPenName(UserProfile.getInstance(),
					User.getInstance());
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(User.getInstance().getPenname());

		*//**************** Test Interests Update module ****************************//*

		List<String> interests = new ArrayList<String>();
		UserProfile up = UserProfile.getInstance();
		interests.add("Adult");
		interests.add("Classic");
		interests.add("Fiction");
		interests.add("Children Learning");
		UserManager.getInstance().updateUserInterests(interests, (long) 1, up);

		*//**************** Test Language Update module ****************************//*

		List<String> lang = new ArrayList<String>();
		lang.add("MARATHI");
		lang.add("HINDI");
		lang.add("ENGLISH");
		System.out.println(UserManager.getInstance().updateUserLanguages(
				(long) 1, "write", lang));

		//**************** Test getProfile module ****************************/

		try {
			System.out.println(UserManager.getInstance().getProfile((long) 12));
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CountryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserInterestsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GenreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserLanguageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LanguagesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//************************Test Delete User Module *************************************//*
		
		/*try {
			System.out.println(UserManager.getInstance().deleteUser(10));
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//***********************Test login module *************************//*
		
		try {
			System.out.println(UserManager.getInstance().authenticateUser(
					"malanimehul", "mehul", false));
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		*//********************* Test Files Deleted module *************************//*
		
		try {
			System.out.println(FilesManager.getInstance().SetFilesDeleted(4));
		} catch (FileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
