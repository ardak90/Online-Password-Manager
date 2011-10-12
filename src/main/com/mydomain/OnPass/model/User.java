package com.mydomain.OnPass.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.Email;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.security.management.PasswordSalt;
import org.jboss.seam.annotations.security.management.UserPassword;
import org.jboss.seam.annotations.security.management.UserPrincipal;

@Entity
@Table(name = "Users")
public class User {

	private String fullname;
	private String email;
	private String password;
	private List<Password> pass;
	private String number;
	private List<History> hist;
	private File file;
	private boolean expiryNotify = false;
	private boolean loginNotify = false;

	@NotNull(message = "Esnter your name")
	@Column(name = "NAME_")
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Id
	@NotNull
	@Email(message = "Invalid email address")
	@UserPrincipal
	@Column(name = "EMAIL_")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@UserPassword(hash = "md5")
	@NotNull(message = "Enter your password")
	@Column(name = "PASSWORD_HASH_")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(mappedBy = "user")
	public List<Password> getPass() {
		return pass;
	}

	public void setPass(List<Password> pass) {
		this.pass = pass;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	@OneToMany(mappedBy = "user")
	public List<History> getHist() {
		return hist;
	}
	
	
	public void setHist(List<History> hist) {
		this.hist = hist;
	}

	public boolean isExpiryNotify() {
		return expiryNotify;
	}

	public void setExpiryNotify(boolean expiryNotify) {
		this.expiryNotify = expiryNotify;
	}

	public boolean isLoginNotify() {
		return loginNotify;
	}

	public void setLoginNotify(boolean loginNotify) {
		this.loginNotify = loginNotify;
	}
	@OneToOne(mappedBy = "user")
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}    
	
}
