package com.kh.mohagee.common.movePage.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IndexDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;

	public int totalBoard() {
		return sqlSession.selectOne("index-mapper.totalBoard");
	}

	public int showBoard() {
		return sqlSession.selectOne("index-mapper.showBoard");
	}

	public int travelBoard() {
		return sqlSession.selectOne("index-mapper.travelBoard");
	}

	public int gymBoard() {
		return sqlSession.selectOne("index-mapper.gymBoard");
	}

}
