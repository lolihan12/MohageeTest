package com.kh.mohagee.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.mohagee.email.model.service.EmailService;
import com.kh.mohagee.member.model.service.MemberService;
import com.kh.mohagee.member.model.vo.Member;

@Controller
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/email/checkEmail.do")
	@ResponseBody
	public int checkEmail(@RequestParam String email) {
		int result = 0;
		
		result = emailService.checkEmail(email);
		
		return result;
	}
	
	@RequestMapping("/email/emailConfirm")
	public String emailConfirm(@RequestParam String userId, @RequestParam String key, Model model) {
		
		int result = emailService.emailConfirm(userId, key);
		
		String msg = "";
		String loc = "/";
		
		if(result > 0) {
			
			Member member = memberService.selectNickName(userId);;
			
			msg = "이메일인증에 성공하였습니다. \\n" + member.getNickName() + "님 환영합니다.";
			
			int result2 = emailService.emailConfirmDelete(userId, key);
		} else {
			msg = "이메일인증에 실패했습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("loc", loc);
		
		return "common/util";
	}

}










