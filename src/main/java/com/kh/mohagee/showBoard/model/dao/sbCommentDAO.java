package com.kh.mohagee.showBoard.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mohagee.showBoard.model.vo.ShowBoard;
import com.kh.mohagee.showBoard.model.vo.sbComment;

@Repository
public class sbCommentDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	
	public int insertsbComment(sbComment sbComment) {
		return sqlSession.insert("sbComment-mapper.insertsbComment", sbComment);
	}

	public List<sbComment> selectListsbComment(int bNo) {
		return sqlSession.selectList("sbComment-mapper.selectListsbComment", bNo);
	}

	public int updatesbComment(sbComment sbComment) {
		return sqlSession.update("sbComment-mapper.updatesbComment", sbComment);
	}

	public int deletesbComment(int bcNo) {
		return sqlSession.update("sbComment-mapper.deletesbComment", bcNo);
	}

	public int selectLastsbComment(int bNo) {
		
		return sqlSession.selectOne("sbComment-mapper.selectLastComment", bNo);
	}

}
