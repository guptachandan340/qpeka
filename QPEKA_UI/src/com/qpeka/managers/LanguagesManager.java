package com.qpeka.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.qpeka.db.Country;
import com.qpeka.db.Files;
import com.qpeka.db.Languages;
import com.qpeka.db.exceptions.LanguagesException;
import com.qpeka.db.handler.LanguagesHandler;


public class LanguagesManager {
	public static LanguagesManager instance = null;
	Languages languages = new Languages();
	List<Languages> language = null;
	
	public LanguagesManager() {
		super();
	}

	public LanguagesManager getInstance() {
		return (instance == null ? (instance = new LanguagesManager()) : instance);
	}

	public Languages createLanguages(short languageid, String language,
			String name, String aNative, short direction, short enabled) {
		//languages = null;
		languages.setLanguageid(languageid);
		languages.setLanguage(language);
		languages.setName(name);
		languages.setANative(aNative);
		languages.setDirection(direction);
		languages.setEnabled(enabled);
		try {
			LanguagesHandler.getInstance().insert(languages);
		} catch (LanguagesException e) {
			e.printStackTrace();
		}
		return languages;
	}
	
    public boolean deleteLanguages(short languageid) {
    	language = null;
    	try {
			LanguagesHandler.getInstance().delete(languageid);
			return true;
		} catch (LanguagesException e) {
			e.printStackTrace();
			return false;
		}
	}
    
	// Updating Languages based on languageId
	public short updateLanguages(Map<String, Object> updatelanguage) {
		language = null;
		short counter = 0;
		if (updatelanguage.get(Languages.LANGUAGEID) != null) {
			List<Languages> existingLanguages = null;
			try {
				existingLanguages = LanguagesHandler.getInstance()
						.findWhereLanguageidEquals(
								Short.parseShort(updatelanguage.get(
										Languages.LANGUAGEID).toString()));
			} catch (LanguagesException e) {
				e.printStackTrace();
			}
			if (existingLanguages != null) {
				for (Languages languages : existingLanguages) {
					if (updatelanguage.get(Languages.LANGUAGE) != null) {
						languages.setLanguage(updatelanguage.get(
								Languages.LANGUAGE).toString());
					}

					if (updatelanguage.get(Languages.NAME) != null) {
						languages.setName(updatelanguage.get(Languages.NAME)
								.toString());
					}

					if (updatelanguage.get(Languages.ANATIVE) != null) {
						languages.setANative(updatelanguage.get(
								Languages.ANATIVE).toString());
					}

					if (updatelanguage.get(Languages.DIRECTION) != null) {
						languages.setDirection(Integer.parseInt(updatelanguage
								.get(Languages.DIRECTION).toString()));
					}

					if (updatelanguage.get(Languages.ENABLED) != null) {
						languages.setEnabled(Integer.parseInt(updatelanguage
								.get(Languages.ENABLED).toString()));
					}
					try {
						counter += LanguagesHandler.getInstance().update(
								Short.parseShort(updatelanguage.get(
										Languages.LANGUAGEID).toString()),
								languages);
					} catch (LanguagesException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return counter;
	}
	
    //Read All languages;
	public List<Languages> readLanguages() {
		language = null;
		try {
			language = LanguagesHandler.getInstance().findAll();
		} catch (LanguagesException e) {
			e.printStackTrace();
		}
		return language;
	}
	
	//@Overloading 
	//Read languages through languageid or direction or enabled
	public List<Languages> readLanguages(short languagfield, String languageField) {
		language = null;
		//Read languages through their languageid
		if (languageField.equalsIgnoreCase("languageid")) {
			try {
				language = LanguagesHandler.getInstance()
						.findWhereLanguageidEquals(languagfield);
			} catch (LanguagesException e) {
				e.printStackTrace();
			}
		} 
		//Read the languages by their directions 
		else if (languageField.equalsIgnoreCase("direction")) {
			try {
				language = LanguagesHandler.getInstance().findWhereDirectionEquals(languagfield);
			} catch (LanguagesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Read All Enabled Languages
		else {
			try {
				language = LanguagesHandler.getInstance().findWhereEnabledEquals(languagfield);
			} catch (LanguagesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return language;
	}
	
	//@Overloading 
	//Read Language through Language, Name or Native
	public List<Languages> readLanguages(String languageNameNative,String languageFileds) {
		language = null; 
		//To read Languages from language field
		if(languageFileds.equalsIgnoreCase("language")) {
			try {
				language = LanguagesHandler.getInstance().findWhereLanguageEquals(languageNameNative);
			} catch (LanguagesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		// To read Languages from Name Field
		else if (languageFileds.equalsIgnoreCase("name")) {
			try {
				language = LanguagesHandler.getInstance().findWhereNameEquals(languageNameNative);
			} catch (LanguagesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// To read Languages from Native Field
		else {
			try {
				language = LanguagesHandler.getInstance().findWhereANativeEquals(languageNameNative);
			} catch (LanguagesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return language;
	}	
	
	public List<Languages> readLanguages(String languageField,
			String languageFieldString, String languageNative, String nativeString) {
		List<Object> readLangObj = new ArrayList<Object>();
		readLangObj.add(languageField);
		readLangObj.add(languageNative);
	    if (languageFieldString.equalsIgnoreCase("language")
				&& nativeString.equalsIgnoreCase("anative")) {
			try {
				language = LanguagesHandler.getInstance().findByDynamicWhere("language IN (?) AND native IN (?)", readLangObj);
			} catch (LanguagesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return language;
	}

	
	/**
	 * @param args
	 */
	
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LanguagesManager languagesManager = new LanguagesManager();
		languagesManager.deleteLanguages((short) 6);
		languagesManager.createLanguages((short) 6, "HINDI", "DEVNAGRI",
				"INDIA", (short) 1, (short) 1);
		System.out.println(languagesManager.readLanguages());
		System.out.println(languagesManager.readLanguages((short) 3,
				"languageid"));
		System.out
				.println(languagesManager.readLanguages((short) 0, "enabled"));
		System.out.println(languagesManager.readLanguages(
				"ENGLISH SOUTH AMERICA", "name"));
		System.out.println(languagesManager.readLanguages("INDIA", "anative")
				+ "\n");
		System.out.println(languagesManager.readLanguages("ENGLISH","language","INDIA","anative"));
		Map<String, Object> update = new HashMap<String, Object>();
		update.put("languageid", 2);
		update.put("language", "ENGLISH");
		update.put("name", "ENGLISH UK");
		update.put("anative", "LONDON");
		update.put("direction", (short) 1);
		update.put("enabled", (short) 0);
		System.out.println(languagesManager.updateLanguages(update));
	}
	*/
}
