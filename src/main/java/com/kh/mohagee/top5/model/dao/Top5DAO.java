package com.kh.mohagee.top5.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mohagee.member.model.vo.FavoriteBoard;

@Repository
public class Top5DAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public List<FavoriteBoard> selectList() {
		
		return sqlSession.selectList("top5-mapper.selectBoardTop5");
	}

}
