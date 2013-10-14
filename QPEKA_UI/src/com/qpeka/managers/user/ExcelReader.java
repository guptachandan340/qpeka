package com.qpeka.managers.user;

public class ExcelReader {
      
    public static void main(String[] args) {
        try {
            CSVLoader loader = new CSVLoader();
            loader.loadCSV("/home/ankita/Downloads/languagesCsv.csv","languages", true);
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
