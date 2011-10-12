package com.mydomain.OnPass.action;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.validator.Valid;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.management.IdentityStore;
import org.jboss.seam.security.management.JpaIdentityStore;
import org.richfaces.event.UploadEvent;

import com.mydomain.OnPass.model.File;
import com.mydomain.OnPass.model.User;

@Name("sign")
@Scope(ScopeType.SESSION)
public class RegisterAction {

	User user;
	private String repass;
	private String phrase;
	private String password;
	FileUploadBean fileUploadBean = (FileUploadBean)Component.getInstance("fileUploadBean");

	public User getUser() {
		if (user == null) {
			user = new User();
		}
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	String email = "E-mail";
	String status;
	md5encryption hash;

	@In("entityManager")
	EntityManager em;

	@Logger
	Log log;

	public String register() {

		List<User> existing = em
				.createQuery("select u from User u where u.email=:email")
				.setParameter("email", user.getEmail()).getResultList();

		if ((existing.size() == 0) && (checkPasswords() == true)) {

			IdentityStore s = (IdentityStore) Component
					.getInstance("org.jboss.seam.security.identityStore");
			log.info(getPassword());
			hash = new md5encryption();
			user.setPassword(hash.HashMd5(getPassword()));
			em.persist(user);
			setStatus("Successfully Registered. You can login now by entering your email and password");
            log.info(user.getEmail());
			setRepass("");
			return "cont_reg";

		} else {
			setStatus("Passwords are not the same");
			user.setPassword("");
			setRepass("");
			return null;
		}

	}
	public void addPhishingImage(UploadEvent event){
		
		try {
			fileUploadBean.listener(event, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	public void check() {

		List<User> existing = em
				.createQuery("select u from User u where u.email=:email")
				.setParameter("email", user.getEmail()).getResultList();

		if (existing.size() == 0) {
			setStatus("");
		} else {
			setStatus(user.getEmail() + " is already registered");

		}

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void sayHello() {

	}

	public String test() {
		User user = (User) Component
				.getInstance("org.jboss.seam.security.management.authenticatedUser");
		return user.getEmail();
	}

	public String getRepass() {
		return repass;
	}

	public void setRepass(String repass) {
		this.repass = repass;
	}

	public boolean checkPasswords() {
		if (getPassword().equals(getRepass())) {
			log.info("Passwords are equal");
			return true;
		} else {
			log.info("Passwords are not equal");
			return false;
		}

	}
	public String updateImage(){
		List<User> existing = em
		.createQuery("select u from User u where u.email=:email")
		.setParameter("email", user.getEmail()).getResultList();
		user = existing.get(0);
		File toUpdate = em.find(File.class, user.getFile().getId());
		toUpdate.setPhrase(getPhrase());
		user = null;
		return "updatedImage";
		
	}

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
   
}
