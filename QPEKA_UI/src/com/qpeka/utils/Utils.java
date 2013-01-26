package com.qpeka.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class Utils {

	public static void  createImageFile(String srcFile , String destFile)
	{
		FileInputStream in = null;
		FileOutputStream out = null;
		try{
			  File f1 = new File(srcFile);
			  File f2 = new File(destFile);
			  in = new FileInputStream(f1);
			  out = new FileOutputStream(f2);

			  byte[] buf = new byte[1024];
			  int len;
			  while ((len = in.read(buf)) > 0)
			  {
				  out.write(buf, 0, len);
			  }
			 
			  }
			  catch(FileNotFoundException ex){
			  System.out.println(ex.getMessage() + " in the specified directory.");
			  }
			  catch(Exception e){
			  System.out.println(e.getMessage());  
			  }
			  finally
			  {
				  try
				  {
					  in.close();
					  out.close();
				  }
				  catch (Exception e) {
					// TODO: handle exception
				}
				  System.out.println("File copied.");
			  }
	}
}
