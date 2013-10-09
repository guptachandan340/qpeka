package com.qpeka.managers.user;

import java.io.FileReader;

import au.com.bytecode.opencsv.CSVReader;

import com.qpeka.managers.LanguagesManager;
 

/*load data local infile '/home/ankita/Downloads/languagesCsv.csv' into 
table languages fields terminated by ',' enclosed by '"' escaped by '\\' 
lines terminated by '\n' (language,native,script,enabled,direction);*/

public class CSVLoader {

    private char seprator;
 
    public CSVLoader() {
    	this.seprator = ',';
    }
 
    /**
     * Parse CSV file using OpenCSV library and load in 
     * given database table. 
     * @param csvFile Input CSV file
     * @param tableName Database table name to import data
     * @PARAM truncate table before inserting new values based on true or false
     * @throws Exception
     */
    public void loadCSV(String csvFile, String tableName,
            boolean truncateBeforeLoad) throws Exception {
 
        CSVReader csvReader = null;
      try {
            csvReader = new CSVReader(new FileReader(csvFile), this.seprator);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occured while executing file. "
                    + e.getMessage());
        }
 
        /* CODE FOR FOR READING HEADERS FROM CSV FILE AND FORM QUERY BASED ON IT.  
         *
    private static final String SQL_INSERT = "INSERT INTO ${table}(${keys}) VALUE(${values})";
    private static final String TABLE_REGEX = "\\$\\{table\\}";
    private static final String KEYS_REGEX = "\\$\\{keys\\}";
    private static final String VALUES_REGEX = "\\$\\{values\\}";
 
    String[] headerRow = csvReader.readNext();
 
        if (null == headerRow) {
            throw new FileNotFoundException(
                    "No columns defined in given CSV file." +
                    "Please check the CSV file format.");
        }
 
        String questionmarks = StringUtils.repeat("?,", headerRow.length);
        questionmarks = (String) questionmarks.subSequence(0, questionmarks
                .length() - 1);
 
        String query = SQL_INSERT.replaceFirst(TABLE_REGEX, tableName);
        query = query
                .replaceFirst(KEYS_REGEX, StringUtils.join(headerRow, ","));
        query = query.replaceFirst(VALUES_REGEX, questionmarks);
         System.out.println("Query: " + query);
 */
        String[] nextLine;
        try {
                  
            while ((nextLine = csvReader.readNext()) != null) {           
            
            	short enabled = (short) (nextLine[3].equalsIgnoreCase("No") ? 0 : 1); 
                short direction = (short) (nextLine[4].equalsIgnoreCase("LR") ? 0 : 1);
                LanguagesManager.getInstance().createLanguages(nextLine[0], nextLine[1], nextLine[2], direction, enabled);
            }    
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(
                    "Error occured while loading data from file to database."
                            + e.getMessage());
        } 
    }
   }

