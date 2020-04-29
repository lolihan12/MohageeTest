package com.kh.mohagee.gymBoard.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mohagee.gymBoard.model.vo.GymBoard;
import com.kh.mohagee.gymBoard.model.vo.gbComment;

@Repository
public class gbCommentDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	
	public int insertgbComment(gbComment gbComment) {
		return sqlSession.insert("gbComment-mapper.insertgbComment", gbComment);
	}

	public List<gbComment> selectListgbComment(int bNo) {
		return sqlSession.selectList("gbComment-mapper.selectListgbComment", bNo);
	}

	public int updategbComment(gbComment gbComment) {
		return sqlSession.update("gbComment-mapper.updategbComment", gbComment);
	}

	public int deletegbComment(int bcNo) {
		return sqlSession.update("gbComment-mapper.deletegbComment", bcNo);
	}

	public int selectLastgbComment(int bNo) {
		
		return sqlSession.selectOne("gbComment-mapper.selectLastComment", bNo);
	}

}
