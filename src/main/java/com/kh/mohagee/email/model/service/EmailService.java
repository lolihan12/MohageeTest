package com.kh.mohagee.email.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kh.mohagee.email.RandomNumber;
import com.kh.mohagee.email.emailHandler.EmailHandler;
import com.kh.mohagee.email.model.dao.EmailDAO;
import com.kh.mohagee.email.model.vo.Email;
import com.kh.mohagee.member.model.service.MemberService;
import com.kh.mohagee.member.model.vo.Member;

@Service
public class EmailService {
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	EmailDAO emailDao;
	
	@Autowired
	Email email;
	
	@Autowired
	MemberService memberService;

	public int checkEmail(String email) {
		return emailDao.checkEmail(email);
	}
	
	public void createRandNum(String userId) throws Exception {
		
		RandomNumber randomNumber = new RandomNumber();
		
		String key = randomNumber.getKey(50, false);
		
		email.setEmail(userId);
		email.setKey(key);
		
		emailDao.createRandNum(email);
		
		EmailHandler sendMail = new EmailHandler(mailSender);
		
		sendMail.setSubject("뭐하지? 이메일 인증");
		sendMail.setText("<h1>메일인증</h1>" +
				"<a href='http://192.168.20.182:8088/mohagee/email/emailConfirm?userId=" + userId +
				"&key=" + key +
				"' target='_blenk'>이메일 인증 확인</a>");
		sendMail.setFrom("cainchel357@naver.com", "뭐하지?");
		sendMail.setTo(userId);
		sendMail.send();
	}

	public int emailConfirm(String userId, String key) {
		
		email.setEmail(userId);
		email.setKey(key);
		
		return emailDao.emailConfirm(email);
	}

	public int emailConfirmDelete(String userId, String key) {
		
		email.setEmail(userId);
		email.setKey(key);
		
		return emailDao.emailConfirmDelete(email);
		
	}
	
	public int findPassword(String userId, String TemPass, String encTemPass) throws Exception {
		
		int result = 0;
		
		Member member = new Member();
		
		member.setUserId(userId);
		member.setPassword(encTemPass);
		
		result = memberService.updateTemPass(member);
		
		if(result > 0) {
			
			EmailHandler sendMail = new EmailHandler(mailSender);
			
			sendMail.setSubject("뭐하지? 비밀번호 찾기");
			sendMail.setText("<h1>비밀번호 찾기</h1>"
					+ "회원님의 비밀번호는 "  + TemPass + "입니다.");
			sendMail.setFrom("cainchel357@naver.com", "뭐하지?");
			sendMail.setTo(userId);
			sendMail.send();
			
		}
		
		return result;
		
	}

	public String TemPass(String userId) {
		RandomNumber randomNumber = new RandomNumber();
		
		String TemPass = randomNumber.getKey(8, false);
		
		return TemPass;
	}

}






