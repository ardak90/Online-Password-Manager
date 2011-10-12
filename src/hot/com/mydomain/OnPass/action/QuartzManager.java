package com.mydomain.OnPass.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.async.QuartzTriggerHandle;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.log.Log;
import org.quartz.SchedulerException;
import org.quartz.core.QuartzScheduler;
import org.quartz.impl.QuartzServer;

@Name("quartzManager")
public class QuartzManager {
	@In
	QuartzTimer quartzTimer;

	@Logger
	Log log;

	QuartzTriggerHandle handle;

	public void startTimer() {
		log.info("Starting timer...");
		Calendar cld = Calendar.getInstance();
		cld.add(Calendar.DAY_OF_MONTH, 2);
		// handle = quartzTimer.createTimer(new Date(),10000L,cld.getTime());
		handle = quartzTimer.startJob1(new Date(), "0/20 * * * * ?",
				cld.getTime());
		log.info("Started timer");
		Contexts.getApplicationContext().set("quartzHandler", handle);

	}

	public void resumeTimer() {
		try {
			log.info("Resuming timer...");
			Object obj = Contexts.getApplicationContext().get("quartzHandler");
			if (obj != null) {
				handle = (QuartzTriggerHandle) obj;
				handle.resume();
				log.info("Resumed timer");
			} else {
				log.info("No timer found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void stopTimer() {
		try {
			handle = (QuartzTriggerHandle) Contexts.getApplicationContext()
					.get("quartzHandler");
			log.info("Stopping timer...");
			handle.cancel();
			log.info("Stopped timer");
		} catch (Exception e) {
			log.error(e);
		}
	}
}
