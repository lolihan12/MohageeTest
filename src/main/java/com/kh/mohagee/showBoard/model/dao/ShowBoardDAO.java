package com.kh.mohagee.showBoard.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mohagee.showBoard.model.vo.ShowAttachment;
import com.kh.mohagee.showBoard.model.vo.ShowBoard;

@Repository
public class ShowBoardDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	
	public List<ShowBoard> selectList() {
	// "매퍼의 실행할 SQL 명", 파라미터로 받을 값, RowBounds 전용 공간 
		return sqlSession.selectList("ShowBoard-mapper.selectList");
	}

	public int selectTotalContents() {
		return sqlSession.selectOne("ShowBoard-mapper.selectTotalContents");
	}
	
	// 게시글 작성
	public int insertShowBoard(ShowBoard showBoard) {
		
		return sqlSession.insert("ShowBoard-mapper.insertShowBoard", showBoard);
	}
	
	public int insertShowAttachment(ShowAttachment a) {
		return sqlSession.insert("ShowBoard-mapper.insertShowAttachment", a);
	}

	// 게시글 선택
	public ShowBoard selectOneShowBoard(int bNo) {
		return sqlSession.selectOne("ShowBoard-mapper.selectShowBoard", bNo);
	}

	public List<ShowAttachment> selectShowAttachment(int bNo) {
		return sqlSession.selectList("ShowBoard-mapper.selectShowAttachment", bNo);
	}

	// 게시글 수정
	public int updateShowBoard(ShowBoard originBoard) {
		
		return sqlSession.update("ShowBoard-mapper.updateShowBoard", originBoard);
	}
	
	public int updateShowAttachment(ShowAttachment a) {
		return sqlSession.insert("ShowBoard-mapper.updateShowAttachment", a);
	}
	
	
	// 게시글 삭제
	public int deleteShowBoard(int bNo) {
		return sqlSession.update("ShowBoard-mapper.deleteShowBoard", bNo);
	}

	public int deleteShowAttachment(int bNo) {
		return sqlSession.update("ShowBoard-mapper.deleteShowAttachment", bNo);
	}

	public ShowAttachment getAttachmentOne(int attNo) {
		return sqlSession.selectOne("ShowBoard-mapper.selectOne", attNo);
	}

	public int setNextTitleImage(int getbNo) {
		return sqlSession.update("ShowBoard-mapper.updateTitleImage", getbNo);
	}

	public int deleteFile(int attNo) {
		return sqlSession.update("ShowBoard-mapper.deleteShowFile", attNo);
	}


	
	
}
