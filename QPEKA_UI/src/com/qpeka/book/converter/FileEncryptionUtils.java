package com.qpeka.book.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

public class FileEncryptionUtils {

	public static final String AES_ALGO = "AES/ECB/PKCS5Padding"; //16bytes key length
	
	public static String generateKey(String id)
	{
		String ts = Long.toString(System.currentTimeMillis());
		int len = Math.max(ts.length(), id.length());
		StringBuffer buf = new StringBuffer();
		for(int i = 0 ; i < len;i++)
		{
			if(i%2 == 0)
				buf.append(id.charAt(i));
			else
				buf.append(ts.charAt(i));
			
			if(buf.length() == 16)
				break;
		}
		return buf.toString();
	}
	
	public static void encrypt(File file, String algo, String keyString) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		file = new File(file.getAbsolutePath() + ".enc");
		FileOutputStream fos = new FileOutputStream(file);
		byte k[] = keyString.getBytes();
		SecretKeySpec key = new SecretKeySpec(k, algo.split("/")[0]);
		Cipher encrypt = Cipher.getInstance(algo);
		encrypt.init(Cipher.ENCRYPT_MODE, key);
		CipherOutputStream cout = new CipherOutputStream(fos, encrypt);

		byte[] buf = new byte[1024];
		int read;
		while ((read = fis.read(buf)) != -1)
			cout.write(buf, 0, read); 
		fis.close();
		cout.flush();
		cout.close();
	}

	public static void decrypt(File file, String algo, String keyString) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		String[] arr = file.getAbsolutePath().split("\\.");
		file = new File(arr[0]+"."+arr[1]);
		FileOutputStream fos = new FileOutputStream(file);
		byte k[] = keyString.getBytes();
		SecretKeySpec key = new SecretKeySpec(k, algo.split("/")[0]);
		Cipher decrypt = Cipher.getInstance(algo);
		decrypt.init(Cipher.DECRYPT_MODE, key);
		CipherInputStream cin = new CipherInputStream(fis, decrypt);

		byte[] buf = new byte[1024];
		int read = 0;
		while ((read = cin.read(buf)) != -1)
			fos.write(buf, 0, read); 
		cin.close();
		fos.flush();
		fos.close();
	}

	public static void main(String[] args) throws Exception {
//		new FileEncryptionUtils("DES/ECB/PKCS5Padding", "sample.txt").encrypt();
//		new FileEncryptionUtils("DES/ECB/PKCS5Padding", "sample.txt.enc")
//				.decrypt();
		String k = "HignDlPsHignDlPs";
		//encrypt(new File("/home/manoj/Alice in Wonderland.epub"), "AES/ECB/PKCS5Padding", k);
		decrypt(new File("/home/manoj/Alice in Wonderland.epub.enc"), "AES/ECB/PKCS5Padding", k);
	}
}
