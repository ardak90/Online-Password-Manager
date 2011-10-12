package com.mydomain.OnPass.action;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

 public class md5encryption {
    	private StringBuffer hexString;
	    public String HashMd5(String content){
	    byte[] defaultBytes = content.getBytes();
	    try{
	    	MessageDigest algorithm = MessageDigest.getInstance("MD5");
	    	algorithm.reset();
	    	algorithm.update(defaultBytes);
	    	byte messageDigest[] = algorithm.digest();

	    	hexString = new StringBuffer();
	    	for (int i=0;i<messageDigest.length;i++) {
	    			String hex = Integer.toHexString(0xFF & messageDigest[i]); 
	    			if(hex.length()==1)
	    				hexString.append('0');
	    				hexString.append(hex);
	    		}
							    
	    	}
		catch(NoSuchAlgorithmException nsae){					                    
				}
	System.out.println(hexString.toString());
	return hexString.toString();	
	}     
 }

 
 