/**
 * 
 */
package com.everis.service.business.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.everis.service.business.ServiceUtilsBusiness;
import com.everis.service.domain.MessageTO;

/**
 * @author gregorio.de.chaves
 * 
 */
@Service
public class ServiceUtilsBusinessImpl implements ServiceUtilsBusiness {
	
	/**
	 * TheLOGGER
	 */
	static final Logger LOGGER = LoggerFactory.getLogger(ServiceUtilsBusinessImpl.class);
	
	@Value("#{prop['places']}")
	private String places;
	
	@Value("#{prop['emails']}")
	private String emails;

	@Override
	public MessageTO shuffleAndSendEmail() {
		
		LOGGER.info("Entering in the method shuffleAndSendEmail");
		
		MessageTO returnTO = new MessageTO();
		
		List<String> listPlaces = Arrays.asList(places.split(";"));
		
		shuffleList(listPlaces);
		
		String finalPlace = listPlaces.get(0);
		
		sendEmail(finalPlace, emails);
		
		LOGGER.info("Returning: " + returnTO);
		
		return returnTO;
	}

	private void shuffleList(List<String> list){
		Collections.shuffle(list);
	}
	
	private boolean sendEmail(String finalPlace, String emails){
		
		LOGGER.info("Place selected: " + finalPlace);
		
		if (LOGGER.isDebugEnabled()){
			LOGGER.info("Sending e-mail to: " +emails);
		}
		
		boolean isOK = true;
		
		final String username = "gchavesacc@gmail.com";
		final String password = "pedrolizzie2428";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("anonymous@everis.com"));
			InternetAddress[] parse = InternetAddress.parse(emails , true);
			
			message.setRecipients(Message.RecipientType.TO,	parse);
			
			message.setSubject("Lugar onde iremos almoçar");
			message.setText("Iremos almoçar no: " + finalPlace + "!!");

			Transport.send(message);

			LOGGER.info("The email was sent!!");

		} catch (MessagingException e) {
			LOGGER.error("Error senting the email!");
			isOK = false;
		}
		
		return isOK;
	}
	
}