package com.kh.mohagee.talkBoard.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mohagee.talkBoard.model.vo.TalkAttachment;
import com.kh.mohagee.talkBoard.model.vo.TalkBoard;

@Repository
public class TalkBoardDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;

	public List<TalkBoard> selectList() {
		
		return sqlSession.selectList("talkBoard-mapper.selectList");
	}

	public int insertTalkAttachment(TalkAttachment a) {
		
		return sqlSession.insert("talkBoard-mapper.insertAttachment", a);
	}

	public TalkBoard selectOne(int tno) {
		return sqlSession.selectOne("talkBoard-mapper.selectTalk", tno);
	}

	public int insertTalkBoard(TalkBoard board) {
		return sqlSession.insert("talkBoard-mapper.insertTalk", board);
	}

	public List<TalkAttachment> selectAttachment(int tno) {
		return sqlSession.selectList("talkBoard-mapper.selectAttachment", tno);
	}

	public int updateTalkBoard(TalkBoard originBoard) {
		return sqlSession.update("talkBoard-mapper.updateBoard", originBoard);
	}

	public int deleteAttachment(int tno) {
		return sqlSession.delete("talkBoard-mapper.deleteTalkAttachment", tno);
	}

	public int updateAttachment(TalkAttachment t) {
		return sqlSession.update("talkBoard-mapper.updateAttachment", t);
	}

	public int deleteTalkBoard(int tno) {
		return sqlSession.delete("talkBoard-mapper.deleteTalk", tno);
	}
	
	

}
