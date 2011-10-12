package com.mydomain.OnPass.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

/**
*
* @author ardak90
*/
public class SMS {

	String xmlURL = "http://xml1.aspsms.com:5061/xmlsvr.asp";

	// insert required userkey,password and originator
	String userkey = "ZPVM4KEP9X0U";
	String password = "Slimshady1";
	String originator = "+60108009510";

	public SMS(){
		
	}

	public String send( String number,
	                    String message) {

	String content = "<?xml version='1.0' encoding='ISO-8859-1'?><aspsms><Userkey>"+userkey+"</Userkey><Password>"+password+"</Password>"
	        + "<Originator>"+ originator +"</Originator><Recipient><PhoneNumber>"+number +"</PhoneNumber>"
	        + "</Recipient><MessageData>"+message+"</MessageData><Action>SendTextSMS</Action></aspsms>";



	InetAddress inetAddr = null;
	String xmlResult = "";

	try {
	  URL aspsmsURL = new URL(xmlURL);
	  URLConnection aspsmsCon = aspsmsURL.openConnection();

	  aspsmsCon.setRequestProperty("Content-Type","text/xml");
	  aspsmsCon.setDoOutput(true);
	  aspsmsCon.setDoInput(true);

	  PrintWriter out = new PrintWriter(aspsmsCon.getOutputStream());

	  char[] buffer = new char[1024*10];
	  buffer = content.toCharArray();

	  out.write(buffer,0,content.length());
	  out.close();

	  BufferedReader in = new BufferedReader(
	                                         new InputStreamReader(
	                                         aspsmsCon.getInputStream()));

	  String inputLine = null;
	  while ((inputLine = in.readLine()) != null)
	  {
	    xmlResult = xmlResult + inputLine;
	    System.out.println(inputLine); }

	  in.close();
	} catch (Exception ex) {
	  System.out.println(ex.getMessage());
	}
	return xmlResult;
	}
	}
