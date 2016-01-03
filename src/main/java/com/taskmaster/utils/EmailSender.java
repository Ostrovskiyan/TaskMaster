package com.taskmaster.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.taskmaster.entity.User;

public class EmailSender {

	private EmailSender() {
	}

	private static final String SENDER_EMAIL = "ostrovskiyan15@gmail.com";
	private static final String SENDER_PASSWORD = "183796254Ayliko!";

	//private static final String HIGH_PRIORITY = "1";
	private static final String COMMON_PRIORITY = "3";
	
	private static void sendEmail(String emailTo, String subject, String message, String xPriority) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
			}
		});
		try {
			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(SENDER_EMAIL));
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			mimeMessage.setSubject(subject);
			mimeMessage.setContent(message, "text/html");
			mimeMessage.setHeader("X-Priority", xPriority);
			Transport.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void sendRegistrationConfirmEmail(User user, String url, String hash) {
		String subject = "Activated accout";
		String siteAddress = url.substring(0, url.indexOf('/', url.indexOf("/register")));
		StringBuilder message = new StringBuilder();
		message.append("<div style='font-size:12pt'>Hello, ").append(user.getName()).append("!<br/>")
				.append("Congratulations with the registration in <a href='").append(siteAddress).append("'>TaskMaster</a><br/>")
				.append("To complete your registration, please visit this URL: </div>")
				.append("<div style='font-size:12pt'>").append(url).append("?login=").append(user.getLogin()).append("&hash=").append(hash).append("</div><br/>")
				.append("<div style='font-size:10pt'>Attention!<br/>")
				.append("Account activation only at this link. <br/>")
				.append("You mustn't responding on this message <br/>")
				.append("You received this message because your e-mail address has been registered on the site <a href='").append(siteAddress).append("'>TaskMaster</a><br/>") 
				.append("If you are not registered on this site, please ignore this letter. <br/>")
				.append("Best wishes, the site administration <a href='").append(siteAddress).append("'>TaskMaster</a></div><br/>");
		sendEmail(user.getLogin(), subject, message.toString(), COMMON_PRIORITY);
	}
	
	public static void sendRestorePasswordConfirmingCode(String email, String confirmingCode, String url){
		String subject = "Restore password";
		String message = "<div style='color:#000000'><div>Recently it received a request to restore your password in <a href='" + url + "'>TaskMaster.</a></div>"
				+ "<br/><div>Input this code for restore password:</div>"
				+ "<div style='background-color:#F8F8FF; border:solid 1px black; font-size:20pt; display: inline-block' align='center'>" + confirmingCode + "</div></div>";
		sendEmail(email, subject, message, COMMON_PRIORITY);
	}
	
	public static void sendNotificationaAboutNewTask(String email, String taskName){
		String subject = "New task";
		String message = "<div style='color:#000000'>You got new task "+ taskName + "</div>";
		sendEmail(email, subject, message, COMMON_PRIORITY);
	}
}