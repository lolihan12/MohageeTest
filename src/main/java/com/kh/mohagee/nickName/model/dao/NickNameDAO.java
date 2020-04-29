package com.kh.mohagee.nickName.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NickNameDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public int checkNick(String nick) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("nickName-mapper.checkNick", nick);
	}

}
