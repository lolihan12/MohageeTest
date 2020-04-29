package com.kh.mohagee.talkBoard.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mohagee.talkBoard.model.vo.Tcomment;

@Repository
public class tCommentDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;

	public int inserttComment(Tcomment tComment) {
		return sqlSession.insert("tcomment-mapper.inserttComment", tComment);
	}

	public List<Tcomment> selectListtComment(int tno) {
		return sqlSession.selectList("tcomment-mapper.selectListtComment", tno);
	}

	public int updatetComment(Tcomment tComment) {
		return sqlSession.update("tcomment-mapper.updatetComment", tComment);
	}

	public int deletetComment(int tno) {
		return sqlSession.update("tcomment-mapper.deletetComment", tno);
	}

	public int selectLasttComment(int tno) {
		return sqlSession.selectOne("tcomment-mapper.selectLastComment", tno);
	}

}
