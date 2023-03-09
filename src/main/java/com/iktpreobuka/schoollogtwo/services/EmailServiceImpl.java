package com.iktpreobuka.schoollogtwo.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.iktpreobuka.schoollogtwo.model.EmailObject;

public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender emailSender;

	@Override
	public void sendTemplateMessage(EmailObject object) throws Exception {
		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(object.getTo());
		helper.setSubject(object.getSubject());
		String text = "<html><body><table " + "style='border:2px solid black'>" + "<tr><td>" + object.getText()
				+ "</td></tr>" + "</table></body></html>";
		helper.setText(text, true);
		emailSender.send(mail);

	}

}
