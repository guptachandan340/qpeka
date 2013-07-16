package com.qpeka.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qpeka.db.Country;
import com.qpeka.db.exceptions.CountryException;
import com.qpeka.db.handler.CountryHandler;

public class CountryManager {
	public static CountryManager instance =null;
	Country country = new Country();
	List<Country> countries = null;

	public CountryManager() {
		super();
	}
 
	public CountryManager getInstance() {
		return (instance == null ? instance = new CountryManager() : instance);
	}
 
	public Country createCountry(short countryId, String iso2,
			String shortName, String longName, String iso3, String nameCode,
			String unMember, String callingCode, String cctld) {
		
		country.setCountryid(countryId);
		country.setIso2(iso2);
		country.setShortname(shortName);
		country.setLongname(longName);
		country.setIso3(iso3);
		country.setNumcode(nameCode);
		country.setUnmember(unMember);
		country.setCallingcode(callingCode);
		country.setCctld(cctld);
		try {
			CountryHandler.getInstance().insert(country);
		} catch (CountryException e) {
			e.printStackTrace();
		}
		return country;
	}
	
	//Deleting Country
	public boolean deleteCountry(short countryid) {
		  try {
			  CountryHandler.getInstance().delete(countryid);
			  return true;
		} catch (CountryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	  }
 
	// Updating country through CountryID
	public short updateCountry(Map<String, Object> updatecountry) {
		short counter = 0;
		List<Country> existingCountry = null;
		if (updatecountry.get(Country.COUNTRYID) != null) {
			try {
				existingCountry = CountryHandler.getInstance()
						.findWhereCountryidEquals(
								Short.parseShort(updatecountry.get(
										Country.COUNTRYID).toString()));
			} catch (CountryException e) {
				e.printStackTrace();
			}
			if (existingCountry != null) {
				for (Country country : existingCountry) {
					if (updatecountry.get(Country.ISO2) != null) {
						country.setIso2(updatecountry.get(Country.ISO2)
								.toString());
					}

					if (updatecountry.get(Country.SHORTNAME) != null) {
						country.setShortname(updatecountry.get(
								Country.SHORTNAME).toString());
					}

					if (updatecountry.get(Country.LONGNAME) != null) {
						country.setLongname(updatecountry.get(Country.LONGNAME)
								.toString());
					}

					if (updatecountry.get(Country.ISO3) != null) {
						country.setIso3(updatecountry.get(Country.ISO3)
								.toString());
					} else {
						country.setIso3(existingCountry.get(0).getIso3());
					}

					if (updatecountry.get(Country.NUMCODE) != null) {
						country.setNumcode(updatecountry.get(Country.NUMCODE)
								.toString());
					}

					if (updatecountry.get(Country.UNMEMBER) != null) {
						country.setUnmember(updatecountry.get(Country.UNMEMBER)
								.toString());
					}

					if (updatecountry.get(Country.CALLINGCODE) != null) {
						country.setCallingcode(updatecountry.get(
								Country.CALLINGCODE).toString());
					}

					if (updatecountry.get(Country.CCTLD) != null) {
						country.setCctld(updatecountry.get(Country.CCTLD)
								.toString());
					}
					try {
						counter += CountryHandler
								.getInstance()
								.update(Short.parseShort(updatecountry.get(
										Country.COUNTRYID).toString()), country);
					} catch (CountryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return counter;
	}
 
	//Reading All the countries in database
	public List<Country> readCountry() {
		try {
			countries = CountryHandler.getInstance().findAll();
		} catch (CountryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countries;
	}
	
	//@Overloading
	//Reading countries through Countryid
	public List<Country> readCountry(short countryid) {
		try {
			countries = CountryHandler.getInstance().findWhereCountryidEquals(
					countryid);
		} catch (CountryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countries;
	}

	// @overloading
	// Reading Countries through iso2 or iso3 or shortname or longname or
	// numcode or callingcode or cctld
	public List<Country> readCountry(String countryfield, String countryField) {
	
		// Read through ISO2
		if (countryField.equalsIgnoreCase("iso2")) {
			try {
				countries = CountryHandler.getInstance().findWhereIso2Equals(
						countryfield);
			} catch (CountryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Read through Language Shortname 
		else if (countryField.equalsIgnoreCase("shortname")) {
			try {
				countries = CountryHandler.getInstance()
						.findWhereShortnameEquals(countryfield);
			} catch (CountryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Read through ISO3
		else if (countryField.equalsIgnoreCase("iso3")) {
			try {
				countries = CountryHandler.getInstance().findWhereIso3Equals(
						countryfield);
			} catch (CountryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Read through Longname
		else if (countryField.equalsIgnoreCase("longname")) {
			try {
				countries = CountryHandler.getInstance().findWhereLongnameEquals(
						countryfield);
			} catch (CountryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		//Read through NUMCODE
		else if (countryField.equalsIgnoreCase("numcode")) {
			try {
				countries = CountryHandler.getInstance().findWhereNumcodeEquals(
						countryfield);
			} catch (CountryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		// Read through CallingCode
		else if (countryField.equalsIgnoreCase("callingcode")) {
			try {
				countries = CountryHandler.getInstance()
						.findWhereCallingcodeEquals(countryfield);
			} catch (CountryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		// Read through cctld
		else {
			try {
				countries = CountryHandler.getInstance().findWhereCctldEquals(
						countryfield);
			} catch (CountryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return countries;
	}
	
 
 /**
	 * @param args
	 */
	public static void main(String[] args) {
		CountryManager countryManager = new CountryManager();
		countryManager.deleteCountry((short)251);
		countryManager.createCountry((short)251,"MI", "MY INDIA", "I love My India", "MLI", "444", "yes", "91", ".ILI");
		Map<String, Object> updateCountry = new HashMap<String, Object>();
		updateCountry.put("countryid",2);
		updateCountry.put("iso2","AX");
		countryManager.updateCountry(updateCountry);
		System.out.println(countryManager.readCountry("248","numcode"));
		
	}

}
