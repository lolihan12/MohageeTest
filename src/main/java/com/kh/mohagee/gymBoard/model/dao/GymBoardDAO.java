package com.kh.mohagee.gymBoard.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mohagee.gymBoard.model.vo.GymBoard;
import com.kh.mohagee.gymBoard.model.vo.GymAttachment;

@Repository
public class GymBoardDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	
	public List<GymBoard> selectList() {
	// "매퍼의 실행할 SQL 명", 파라미터로 받을 값, RowBounds 전용 공간 
		return sqlSession.selectList("GymBoard-mapper.selectList");
	}

	public int insertGymBoard(GymBoard board) {
		
		return sqlSession.insert("GymBoard-mapper.insertGymBoard", board);
	}
	
	public int insertGymAttachment(GymAttachment a) {
		return sqlSession.insert("GymBoard-mapper.insertGymAttachment", a);
	}
	
	public GymBoard selectOneGymBoard(int bNo) {
		return sqlSession.selectOne("GymBoard-mapper.selectGymBoard", bNo);
	}

	public List<GymAttachment> selectGymAttachment(int bNo) {
		return sqlSession.selectList("GymBoard-mapper.selectGymAttachment", bNo);
	}

	public int updateGymBoard(GymBoard gymBoard) {
		
		return sqlSession.update("GymBoard-mapper.updateGymBoard", gymBoard);
	}
	
	public int updateGymAttachment(GymAttachment a) {
		return sqlSession.insert("GymBoard-mapper.updateGymAttachment", a);
	}

	public int deleteGymBoard(int bNo) {
		return sqlSession.update("GymBoard-mapper.deleteGymBoard", bNo);
	}

	public int deleteGymAttachment(int bNo) {
		return sqlSession.update("GymBoard-mapper.deleteGymAttachment", bNo);
	}

	
// 댓글관련 
	
	public int insertGymBoardComment(int bcNo) {
		return sqlSession.insert("GymBoard-mapper.insertGymBoardComment", bcNo);
	}
	
	public int updateGymBoardComment(int bcNo) {
		return sqlSession.insert("GymBoard-mapper.updateGymBoardComment", bcNo);
	}	
	
	public int deleteGymBoardComment(int bcNo) {
		return sqlSession.insert("GymBoard-mapper.deleteGymBoardComment", bcNo);
	}

	public GymAttachment getAttachmentOne(int attNo) {
		return sqlSession.selectOne("GymBoard-mapper.selectOne", attNo);
	}

	public int setNextTitleImage(int getbNo) {
		return sqlSession.update("GymBoard-mapper.updateTitleImage", getbNo);
	}

	public int deleteFile(int attNo) {
		return sqlSession.update("GymBoard-mapper.deleteGymFile", attNo);
	}
	
	
}
















