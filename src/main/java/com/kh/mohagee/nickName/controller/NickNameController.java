package com.kh.mohagee.nickName.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.mohagee.nickName.model.service.NickNameService;

@Controller
public class NickNameController {
	@Autowired
	NickNameService nickNameService;
	
	@RequestMapping("/nick/checkNick.do")
	@ResponseBody
	public int checkNick(@RequestParam String nick) {
		int result = 0;
		
		result = nickNameService.checkNick(nick);
		
		return result;
	}
}









