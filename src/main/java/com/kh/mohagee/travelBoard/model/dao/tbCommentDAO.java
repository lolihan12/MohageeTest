package com.kh.mohagee.travelBoard.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mohagee.travelBoard.model.vo.TravelBoard;
import com.kh.mohagee.travelBoard.model.vo.tbComment;

@Repository
public class tbCommentDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	
	public int inserttbComment(tbComment tbComment) {
		return sqlSession.insert("tbComment-mapper.inserttbComment", tbComment);
	}

	public List<tbComment> selectListtbComment(int bNo) {
		return sqlSession.selectList("tbComment-mapper.selectListtbComment", bNo);
	}

	public int updatetbComment(tbComment tbComment) {
		return sqlSession.update("tbComment-mapper.updatetbComment", tbComment);
	}

	public int deletetbComment(int bcNo) {
		return sqlSession.update("tbComment-mapper.deletetbComment", bcNo);
	}

	public int selectLasttbComment(int bNo) {
		
		return sqlSession.selectOne("tbComment-mapper.selectLastComment", bNo);
	}

}
