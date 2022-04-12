package poly.service;

import poly.dto.MailDTO;

public interface IMailService {
	
	//메일 발송
	int doSendMail(MailDTO pDTO);
	//에러처리의 주체가 controller가 아니라 service에서 하기 위해서 throws exception; 을 쓰지 않음
	
}
