package poly.service.impl;

import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.MailDTO;
import poly.service.IMailService;
import poly.util.CmmUtil;


@Service("MailService")
public class MailService implements IMailService {
	
	//메일발송을 위한 자바 겍체 가져옴
	@Resource(name="MailService")
	private IMailService mailService;
	
	private Logger log = Logger.getLogger(this.getClass());
	final String host = "smtp.naver.com";
	final String user ="rnrnck78";
	final String fulluser ="rnrnck78@naver.com";
	final String password = "jh189189189";
	final int port=465; //포트번호  
	



	
	
	@Override
	public int doSendMail(MailDTO pDTO){
		log.info(this.getClass().getName() + ".doSendMail start!");
		
		int res= 1;
		
		if(pDTO == null) {
			pDTO = new MailDTO();
		}
		
		String toMail = CmmUtil.nvl(pDTO.getToMail());
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);  

		props.put("mail.smtp.port", port);  

		props.put("mail.smtp.auth", "true");  

		props.put("mail.smtp.ssl.enable", "true");  

		props.put("mail.smtp.ssl.trust", host);
		
		//구글 발송을 위한 포트설정
		props.put("mail.smtp.port", "465");  
		props.put("mail.debug", "true");  
		props.put("mail.smtp.socketFactory.port", "465");  
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
		props.put("mail.smtp.socketFactory.fallback", "false");  
		
		Session sesstion = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
			protected  javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(user, password);
			}
		});
		
		
		
		try {
			MimeMessage message = new MimeMessage(sesstion);
			message.setFrom(new InternetAddress(fulluser));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
			
			message.setSubject(CmmUtil.nvl(pDTO.getTitle()));
			message.setText(CmmUtil.nvl(pDTO.getContents()));
			
			
			Transport.send(message);
		}
		catch(MessagingException e){
			res =0;
			log.info("[ERROR]"+this.getClass().getName()+".dooSendMail:"+ e);
		}catch (Exception e) {
			res= 0;
			log.info("[ERROR]"+this.getClass().getName()+".dooSendMail:"+ e);
		}
		
		log.info(getClass().getName() + ".doSendMail end!");
		return res;
	}

}
