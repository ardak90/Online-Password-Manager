package com.mydomain.OnPass.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher; 
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Parameter;
import org.jasypt.hibernate.type.EncryptedStringType;


@Entity
@Table(name="Passwords")

public class Password {
	


	private int pass_ID;
	private String pass_name;
	private Date pass_start;
	private Date pass_end;
	private String pass_content;
	private String pass_username;
	private String icon;
	private User user;
	private String url;
	private String strenght;
	
	
	
    @Id
    @GeneratedValue
	public int getPass_ID() {
		return pass_ID;
	}
	public void setPass_ID(int pass_ID) {
		this.pass_ID = pass_ID;
	}

	public String getPass_name() {
		if(pass_name!=null){
			return pass_name.toUpperCase();
		} return pass_name;
	}
	public void setPass_name(String pass_name) {
		this.pass_name = pass_name;
	}
	public Date getPass_start() {
		return pass_start;
	}
	public void setPass_start(Date pass_start) {
		this.pass_start = pass_start;
	}
	public Date getPass_end() {
		return pass_end;
	}
	public void setPass_end(Date pass_end) {
		this.pass_end = pass_end;
	}

	public String getPass_content() {
		return pass_content;
	}
	public void setPass_content(String pass_content) {	
     this.pass_content = pass_content;
	}
	@ManyToOne
	@JoinColumn(name ="EMAIL_")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Long DiffDate(){

        Calendar calendar = new GregorianCalendar();
        Calendar c2 = new GregorianCalendar();
        c2.set(2011, getPass_end().getMonth(), getPass_end().getDate());
        System.out.println(calendar.getTime());
        System.out.println(c2.getTime());
        Long diff = c2.getTimeInMillis() - calendar.getTimeInMillis();
        Long answer = diff/(1000*60*60*24);	
	   System.out.println(answer);
		return answer;
	}
	
    public String styleForDays(){
    String style;
    
    if(DiffDate()<=3){
    style = "days_red";	
    } else{
    	style = "days";
    }
    return style;
    }
    
    public String DaysOrDay(){
    	String daysorday;
    	if(DiffDate()==1){
    	daysorday="day  left";	
    	} else {daysorday="days left";}
    	return daysorday;
    }
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStrenght() {
		return strenght;
	}
	public void setStrenght(String strenght) {
		this.strenght = strenght;
	}
	public String getPass_username() {
		return pass_username;
	}
	public void setPass_username(String pass_username) {
		this.pass_username = pass_username;
	}


	
    
	
}
