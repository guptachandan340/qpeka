package com.qpeka.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.qpeka.db.Category;
import com.qpeka.db.Constants.INVITESTATUS;
import com.qpeka.db.Constants.VISIBILITY;
import com.qpeka.db.exceptions.CountryException;
import com.qpeka.db.exceptions.FileException;
import com.qpeka.db.exceptions.GenreException;
import com.qpeka.db.exceptions.LanguagesException;
import com.qpeka.db.exceptions.QpekaException;
import com.qpeka.db.exceptions.user.AddressException;
import com.qpeka.db.exceptions.user.UserException;
import com.qpeka.db.exceptions.user.UserFieldVisibilityException;
import com.qpeka.db.exceptions.user.UserInterestsException;
import com.qpeka.db.exceptions.user.UserLanguageException;
import com.qpeka.db.exceptions.user.UserProfileException;
import com.qpeka.db.handler.CategoryHandler;
import com.qpeka.db.user.User;
import com.qpeka.db.user.profile.Name;
import com.qpeka.db.user.profile.UserInvites;
import com.qpeka.db.user.profile.UserProfile;
import com.qpeka.managers.CategoryManager;
import com.qpeka.managers.FilesManager;
import com.qpeka.managers.SessionsManager;
import com.qpeka.managers.user.UserInvitesManager;
import com.qpeka.managers.user.UserManager;
import com.qpeka.security.bcrypt.BCrypt;
import com.sun.research.ws.wadl.Request;

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

		//**************** Test Interests Update module ****************************/

	/*	List<String> interests = new ArrayList<String>();
		UserProfile up = UserProfile.getInstance();
		interests.add("Adult");
		interests.add("Classic");
		interests.add("Children Learning");
		UserManager.getInstance().updateUserInterests(interests, (long) 1, up);*/

		/**************** Test Language Update module ****************************//*

		List<String> lang = new ArrayList<String>();
		lang.add("MARATHI");
		lang.add("HINDI");
		lang.add("ENGLISH");
		System.out.println(UserManager.getInstance().updateUserLanguages(
				(long) 1, "write", lang));

		//**************** Test getProfile module ***************************

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
		*/
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
		
		/************************ Test SetUserVisibility File ***********************/
		
		/*try {
			UserManager.getInstance().SetUserVisibility(UserProfile.GENDER, VISIBILITY.PUBLIC, (long)43);
		} catch (UserFieldVisibilityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*UserManager.getInstance().setProfilePic((long)1, "/home/ankita/Desktop/ankita pics/IMG-20130814-WA0000.jpg");*/
		
		/*********************** Test what getInstance returns **************************/
		
		/*UserProfile up = UserProfile.getInstance();
		if( up == null) {
			System.out.println(up);
		} else {
			System.out.println("hello");
		}*/
		
/*		Multimap<String, Object> m = HashMultimap.create();
		
		m.put("ankita", "malani");
		m.put("ankita", "malani123");
		m.put("hello", "anki");
		System.out.println(m);
		System.out.println("hello");
		
		Map<String, Object> a = Maps.newHashMap();
		a.put("hii", "fine");
		a.put("hii", "hello");
		a.put("ok", "okay");
		Multimap<String, Object> ok = Multimaps.forMap(a);
		ok.put("okay", 1);
		System.out.println(ok);*/
		
		/****************************** Test UserInviteSent *********************************//*
		
		List<String> li = new ArrayList<String>();
		li.add("$2a$10$A2OTK.17tp2ULFNuV8DQ0OjA7cX5cJXYiuSQX5n3YWv1hKdpps8Ey");
		li.add("$2a$10$UheA8e/tThiTXP.oBsAeiuEPlNlA8br8.HoyAhn4.AWOGuiYhqXUu");
		li.add("$2a$10$0XJpx8/ZuG6BYL4Do./96e0SIaUJ1qgpH5bWT5z9yWJ2WPP7OoO1a");
		try {
			System.out.println(UserInvitesManager.getInstance().setInviteSent(li));
		} catch (QpekaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*//***************************Test User Accepted ******************************//*
		try {
			System.out.println(UserInvitesManager.getInstance().setInviteAccepted("$2a$10$FHhyrBkws95Y6d6qhtmym.BeNXKgFO95NcWZIMMHbJDSoafjCwp42"));
		} catch (QpekaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/***********************Test Nonce**********************************/
		
		/*
			long l = System.currentTimeMillis();
			byte b = (byte) l;
			int a = b ^ 0xDE;
			BCrypt.hashpw(Integer.toString(a),BCrypt.gensalt());
			System.out.println(nonce);
			rand = SecureRandom.getInstance("SHA1PRNG");
			rand.nextBytes(nonce); 
			System.out.println();		*/
		
		/*********************** Test hostname **************************/
		
		try {
			System.out.println(InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SessionsManager.getInstance().createSession((long) 1 , "anki546.malani@gmail.com", "ankita");
		
	}
}
