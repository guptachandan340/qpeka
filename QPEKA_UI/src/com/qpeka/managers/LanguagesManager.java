package com.qpeka.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qpeka.db.Languages;
import com.qpeka.db.exceptions.LanguagesException;
import com.qpeka.db.handler.LanguagesHandler;


public class LanguagesManager {
	public static LanguagesManager instance = null;
	
	public LanguagesManager() {
		super();
	}

	public LanguagesManager getInstance() {
		return (instance == null ? (instance = new LanguagesManager()) : instance);
	}

	public Languages createLanguages(short languageid, String language,
			String name, String aNative, short direction, short enabled) {
		Languages languages = Languages.getInstance();
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
    	try {
			LanguagesHandler.getInstance().delete(languageid);
			return true;
		} catch (LanguagesException e) {
			e.printStackTrace();
			return false;
		}
	}
    
	// Updating Languages based on languageId
	public short updateLanguages(Map<String, Object> updatelanguageMap) {
		short counter = 0;
		if (updatelanguageMap.get(Languages.LANGUAGEID) != null) {
			List<Languages> existingLanguages = null;
			try {
				existingLanguages = LanguagesHandler.getInstance()
						.findWhereLanguageidEquals(
								Short.parseShort(updatelanguageMap.get(
										Languages.LANGUAGEID).toString()));
			} catch (LanguagesException e) {
				e.printStackTrace();
			}
			if (existingLanguages != null) {
				for (Languages languages : existingLanguages) {
					if (updatelanguageMap.get(Languages.LANGUAGE) != null) {
						languages.setLanguage(updatelanguageMap.get(
								Languages.LANGUAGE).toString());
					}

					if (updatelanguageMap.get(Languages.NAME) != null) {
						languages.setName(updatelanguageMap.get(Languages.NAME)
								.toString());
					}

					if (updatelanguageMap.get(Languages.ANATIVE) != null) {
						languages.setANative(updatelanguageMap.get(
								Languages.ANATIVE).toString());
					}

					if (updatelanguageMap.get(Languages.DIRECTION) != null) {
						languages.setDirection(Short.parseShort(updatelanguageMap
								.get(Languages.DIRECTION).toString()));
					}

					if (updatelanguageMap.get(Languages.ENABLED) != null) {
						languages.setEnabled(Short.parseShort(updatelanguageMap
								.get(Languages.ENABLED).toString()));
					}
					try {
						counter += LanguagesHandler.getInstance().update(
								Short.parseShort(updatelanguageMap.get(
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
		List<Languages> language = null;
		try {
			language = LanguagesHandler.getInstance().findAll();
		} catch (LanguagesException e) {
			e.printStackTrace();
		}
		return language;
	}
	
	//@Overloading 
	//Read languages through languageid or direction or enabled
	public List<Languages> readLanguages(short languagIdentifier, String languagIdentifierString) {
		List<Languages> language = null;
		//Read languages through their languageid
		if (languagIdentifierString.equalsIgnoreCase(Languages.LANGUAGEID)) {
			try {
				language = LanguagesHandler.getInstance()
						.findWhereLanguageidEquals(languagIdentifier);
			} catch (LanguagesException e) {
				e.printStackTrace();
			}
		} 
		//Read the languages by their directions 
		else if (languagIdentifierString.equalsIgnoreCase(Languages.DIRECTION)) {
			try {
				language = LanguagesHandler.getInstance().findWhereDirectionEquals(languagIdentifier);
			} catch (LanguagesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Read All Enabled Languages
		else if(languagIdentifierString.equalsIgnoreCase(Languages.ENABLED)){
			try {
				language = LanguagesHandler.getInstance().findWhereEnabledEquals(languagIdentifier);
			} catch (LanguagesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return language;
		}
		return language;
	}
	
	//@Overloading 
	//Read Language through Language, Name or Native
	public List<Languages> readLanguages(String languageAttribute,String languageAttributeString) {
		List<Languages> language = null;
		//To read Languages from language field
		if(languageAttributeString.equalsIgnoreCase(Languages.LANGUAGE)) {
			try {
				language = LanguagesHandler.getInstance().findWhereLanguageEquals(languageAttribute);
		
			} catch (LanguagesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		// To read Languages from Name Field
		else if (languageAttributeString.equalsIgnoreCase(Languages.NAME)) {
			try {
				language = LanguagesHandler.getInstance().findWhereNameEquals(languageAttribute);
				
			} catch (LanguagesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// To read Languages from Native Field
		else if(languageAttributeString.equalsIgnoreCase(Languages.ANATIVE)) {
			try {
				language = LanguagesHandler.getInstance().findWhereANativeEquals(languageAttribute);
				
			} catch (LanguagesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return language;
		}
		return language;
	}	
	
	public List<Languages> readLanguages(String language,
			String languageString, String languageNative, String nativeString) {
		List<Languages> languages = null;
		List<Object> readLangObj = new ArrayList<Object>();
		readLangObj.add(language);
		readLangObj.add(languageNative);
	    if (languageString.equalsIgnoreCase(Languages.LANGUAGE)
				&& nativeString.equalsIgnoreCase(Languages.ANATIVE)) {
			try {
				languages = LanguagesHandler.getInstance().findByDynamicWhere("language IN (?) AND native IN (?)", readLangObj);
			} catch (LanguagesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return languages;
	}

	/**
	 * @param args
	 */
	
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LanguagesManager languagesManager = new LanguagesManager();
		System.out.println(languagesManager.deleteLanguages((short) 6));
		System.out.println(languagesManager.createLanguages((short) 6, "HINDI", "DEVNAGRI",
				"INDIA", (short) 1, (short) 1));
		System.out.println(languagesManager.readLanguages());
		System.out.println(languagesManager.readLanguages((short) 3,
				"languageid"));
		System.out
				.println(languagesManager.readLanguages((short) 0, "enabled"));
		System.out.println(languagesManager.readLanguages(
				"ENGLISH SOUTH AMERICA", "name"));
		System.out.println(languagesManager.readLanguages("INDIA",Languages.ANATIVE)
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
