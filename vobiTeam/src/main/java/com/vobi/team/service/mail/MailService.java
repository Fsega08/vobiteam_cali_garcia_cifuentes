package com.vobi.team.service.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vobi.team.test.testArtefacto;


@Service("mailService")
public class MailService implements IMailService {

	private final static Logger log=LoggerFactory.getLogger(MailService.class);
	

	private Properties mailServerProperties;
	
	private Session getMailSession;	
	
	private MimeMessage generateMailMessage;
	
	
	
	public Properties getMailServerProperties() {
		return mailServerProperties;
	}
	
	@Transactional(readOnly = true)
	public void setMailServerProperties(Properties mailServerProperties) {
		this.mailServerProperties = mailServerProperties;
	}

	public Session getGetMailSession() {
		return getMailSession;
	}

	public void setGetMailSession(Session getMailSession) {
		this.getMailSession = getMailSession;
	}

	public MimeMessage getGenerateMailMessage() {
		return generateMailMessage;
	}

	public void setGenerateMailMessage(MimeMessage generateMailMessage) {
		this.generateMailMessage = generateMailMessage;
	}

	public void send(String para, String asunto, String cuerpo) throws MessagingException {
		
		
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");

		// Step2
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
		generateMailMessage.setSubject(asunto);
		
		generateMailMessage.setText(cuerpo);

		// Step3
		Transport transport = getMailSession.getTransport("smtp");

		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", "vortbiteam@gmail.com", "txvamccxkmdflagx");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
	
}
