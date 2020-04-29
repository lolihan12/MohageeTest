package com.kh.mohagee.nickName.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mohagee.nickName.model.dao.NickNameDAO;

@Service
public class NickNameService {
	
	@Autowired
	NickNameDAO nickNameDao;

	public int checkNick(String nick) {
		return nickNameDao.checkNick(nick);
	}

}
