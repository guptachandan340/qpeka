package com.qpeka.managers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qpeka.db.Country;
import com.qpeka.db.exceptions.CountryException;
import com.qpeka.db.handler.CountryHandler;


public class CountryManager {
	public static CountryManager instance = null;

	public CountryManager() {
		super();
	}

	public static CountryManager getInstance() {
		return (instance == null ? (instance = new CountryManager()) : instance);
	}

	public Country createCountry(short countryId, String iso2,
			String shortName, String longName, String iso3, String nameCode,
			String unMember, String callingCode, String cctld) {

		Country country = Country.getInstance();
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

	// Deleting Country
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
	public short updateCountry(Map<String, Object> updateCountryMap) {
		short counter = 0;
		List<Country> existingCountry = null;
		if (updateCountryMap.get(Country.COUNTRYID) != null) {
			try {
				existingCountry = CountryHandler.getInstance()
						.findWhereCountryidEquals(
								Short.parseShort(updateCountryMap.get(
										Country.COUNTRYID).toString()));
			} catch (CountryException e) {
				e.printStackTrace();
			}
			if (existingCountry != null) {
				for (Country country : existingCountry) {
					if (updateCountryMap.get(Country.ISO2) != null) {
						country.setIso2(updateCountryMap.get(Country.ISO2)
								.toString());
					}

					if (updateCountryMap.get(Country.SHORTNAME) != null) {
						country.setShortname(updateCountryMap.get(
								Country.SHORTNAME).toString());
					}

					if (updateCountryMap.get(Country.LONGNAME) != null) {
						country.setLongname(updateCountryMap.get(
								Country.LONGNAME).toString());
					}

					if (updateCountryMap.get(Country.ISO3) != null) {
						country.setIso3(updateCountryMap.get(Country.ISO3)
								.toString());
					}

					if (updateCountryMap.get(Country.NUMCODE) != null) {
						country.setNumcode(updateCountryMap
								.get(Country.NUMCODE).toString());
					}

					if (updateCountryMap.get(Country.UNMEMBER) != null) {
						country.setUnmember(updateCountryMap.get(
								Country.UNMEMBER).toString());
					}

					if (updateCountryMap.get(Country.CALLINGCODE) != null) {
						country.setCallingcode(updateCountryMap.get(
								Country.CALLINGCODE).toString());
					}

					if (updateCountryMap.get(Country.CCTLD) != null) {
						country.setCctld(updateCountryMap.get(Country.CCTLD)
								.toString());
					}
					try {
						counter += CountryHandler
								.getInstance()
								.update(Short.parseShort(updateCountryMap.get(
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

	// Reading All the countries in database
	public List<Country> readCountry() {
		List<Country> countries = null;
		try {
			countries = CountryHandler.getInstance().findAll();
		} catch (CountryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countries;
	}
	
	// read ISO2 and Shortname of all countries
	public Map<String, String> retrieveCountry() {
			List<Country> existingCountry = null;
			Map<String, String> countryIdentifierMap = new HashMap<String, String>();
				try {
					existingCountry = CountryHandler.getInstance().findAll();
					for(Country country : existingCountry) {
							countryIdentifierMap.put(country.getIso2(), country.getShortname());	
					}
				} catch (CountryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return countryIdentifierMap;
		}
		
	

	// @Overloading
	// Reading countries through Countryid
	public List<Country> readCountry(short countryid) {
		List<Country> countries = null;
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
	public List<Country> readCountry(String countryIdentifier,
			String countryIdentifierString) {
		List<Country> countries = null;
		// Read through ISO2
		if (countryIdentifierString.equalsIgnoreCase("iso2")) {
			try {
				countries = CountryHandler.getInstance().findWhereIso2Equals(
						countryIdentifier);
			} catch (CountryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Read through Language Shortname
		else if (countryIdentifierString.equalsIgnoreCase("shortname")) {
			try {
				countries = CountryHandler.getInstance()
						.findWhereShortnameEquals(countryIdentifier);
			} catch (CountryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Read through ISO3
		else if (countryIdentifierString.equalsIgnoreCase("iso3")) {
			try {
				countries = CountryHandler.getInstance().findWhereIso3Equals(
						countryIdentifier);
			} catch (CountryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Read through Longname
		else if (countryIdentifierString.equalsIgnoreCase("longname")) {
			try {
				countries = CountryHandler.getInstance()
						.findWhereLongnameEquals(countryIdentifier);
			} catch (CountryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Read through NUMCODE
		else if (countryIdentifierString.equalsIgnoreCase("numcode")) {
			try {
				countries = CountryHandler.getInstance()
						.findWhereNumcodeEquals(countryIdentifier);
			} catch (CountryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Read through CallingCode
		else if (countryIdentifierString.equalsIgnoreCase("callingcode")) {
			try {
				countries = CountryHandler.getInstance()
						.findWhereCallingcodeEquals(countryIdentifier);
			} catch (CountryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Read through cctld
		else if (countryIdentifierString.equalsIgnoreCase("cctld")) {
			try {
				countries = CountryHandler.getInstance().findWhereCctldEquals(
						countryIdentifier);
			} catch (CountryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return countries;
		}
		return countries;
	}

	/**
	 * @param args
	 */
/*
	public static void main(String[] args) {
		CountryManager countryManager = new CountryManager();
		System.out.println(countryManager.deleteCountry((short) 251));
		System.out.println(countryManager.createCountry((short) 251, "MI", "MY INDIA",
				"I love My India", "MLI", "444", "yes", "91", ".ILI"));
		System.out.println(countryManager.retrieveCountry());
		Map<String, Object> updateCountry = new HashMap<String, Object>();
		updateCountry.put("countryid", 2);
		updateCountry.put("iso2", "AX");
		countryManager.updateCountry(updateCountry);
		System.out.println(countryManager.readCountry("248", "numcode"));

	}
*/
}
