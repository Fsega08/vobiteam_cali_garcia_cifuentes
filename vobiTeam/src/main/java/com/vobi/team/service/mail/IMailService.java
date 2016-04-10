package com.vobi.team.service.mail;

import javax.mail.MessagingException;

public interface IMailService {
	
	public void send(String para, String asunto, String cuerpo) throws MessagingException;
}
