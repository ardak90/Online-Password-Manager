package com.mydomain.OnPass.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "History")
public class History {
	private Date time;
	private String comment;
	private User user;
	private int hist_ID;

	@Id
	@GeneratedValue
	public int getHist_ID() {
		return hist_ID;
	}

	public void setHist_ID(int hist_ID) {
		this.hist_ID = hist_ID;
	}



	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
    
	@ManyToOne
	@JoinColumn(name ="EMAIL_")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String FormattedDate(){
	SimpleDateFormat  sdf = new SimpleDateFormat ("[dd.MM.yyyy]");	
	Calendar c = Calendar.getInstance();
	c.setTime(getTime());
	return sdf.format(c.getTime());
	}
	public String FormattedTime(){
		SimpleDateFormat  sdf = new SimpleDateFormat ("[KK:mm aa]");	
		Calendar c = Calendar.getInstance();
		c.setTime(getTime());
	return sdf.format(c.getTime());
	}

}
