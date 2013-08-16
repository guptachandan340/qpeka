package com.qpeka.db;

public class Constants {

	public enum STATUS {
		DEFAULT, ACTIVE, PASSIVE, BLOCKED, DELETED
	}
	
	public enum TYPE {
		AUTHENTIC, QPEKAADMIN, QPEKAMANAGER
	}
	
	public enum GENDER {
		UNSPECIFIED, MALE, FEMALE
	}
	
	public enum USERTYPE {
		UNSPECIFIED, READER, WRITER, PUBLISHER, SERIVCEPROVIDER
	}

	public enum READER {
		NEW, AMATEUR, REGULAR, STEADY, EXTRAORDINARY
	}
	
	public enum AUTHOR {
		FRESH, AMATEUR, POPULAR, EXPERT, LEGEND
	}

	public enum SERVICEPROVIDER {
		NEW, AMATEUR, TRUSTWORTHY, FABULOUS, LEGEND
	}
	
	public enum USERLEVEL {
		PREMIUM, FREE
	}

	public enum WORKTYPE {
		BOOK, SHORTSTORY, POEM, ARTICLE
	}

	public enum SECTION {
		POPULAR, HIGHESTREAD, LATEST
	}
	
	public enum BOOK {
		HORRIBLE, BORING, GOOD, BEST, AWESOME
	}
	
	public enum BADGES {
		READER, WRITER, PUBLISHER, EDITOR, COVERDESIGNER, REVIEWER, COMMENTATOR, GROUPMANAGER, PRINTER, MARKETING, PUBLICRELATION, COPYRIGHTREGISTRATION
	}

	public enum LANGUAGES {
		ASSAMESE, BENGALI, BODO, DOGRI, GUJARATI, HINDI, KANNADA, KASHMIRI, KONKANI, MAITHILI, MALAYALAM, MANIPURI, MARATHI, NEPALI, ORIYA, PUNJABI, SANSKRIT, SANTHALI, SINDHI, TAMIL, TELUGU, URDU, ENGLISH
	}
	
	public enum CATEGORY {
		//UNSPECIFIED, FICTION, SUSPENSE, LOVE, COMEDY, MYSTERY, MOTIVATIONAL, THRILLERS, ROMANCE, FANTASIES, EROTIC, HORROR, BIOGRAPHIES, SPIRITUAL, SELFHELP, EDUCATIONAL, CHILDREN, SCIFI, HISTORICAL, PATRIOTIC, HISTORY, GEOGRAPHY, SCIENCE, LANGUAGES, ART, FACTS, NATURE, EMOTIONAL, SITUATIONAL, CURRENTAFFAIRS, SHAYARI, SONGS, GHAZALS, ALL
		UNSPECIFIED, FICTION, NONFICTION, CHILDREN, EDUCATIONAL
	}
	
}