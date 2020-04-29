package com.kh.mohagee.email.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mohagee.email.model.vo.Email;

@Repository
public class EmailDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;

	public int checkEmail(String email) {
		return sqlSession.selectOne("email-mapper.checkEmail", email);
	}

	public void createRandNum(Email email) {
		sqlSession.insert("email-mapper.insertCheckNumber", email);
	}

	public int emailConfirm(Email email) {
		return sqlSession.update("email-mapper.emailConfirm", email);
	}

	public int emailConfirmDelete(Email email) {
		return sqlSession.delete("email-mapper.emailConfirmDelete", email);
	}

	

}
