package com.kh.mohagee.gymBoard.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mohagee.gymBoard.model.vo.GymBoard;
import com.kh.mohagee.gymBoard.model.vo.gbComment;
import com.kh.mohagee.gymBoard.exception.GymBoardException;
import com.kh.mohagee.gymBoard.model.dao.gbCommentDAO;


@Service
public class gbCommentService {

	@Autowired
	gbCommentDAO gbCommentDAO;
	
	public int insertgbComment(gbComment gbComment) throws GymBoardException{
		int result = 0;
		
		System.out.println("서비스에gbComment 는 : " + gbComment);
		result = gbCommentDAO.insertgbComment(gbComment);
		if(result < 1) throw new GymBoardException("게시글 추가 중 에러 발생!");
		
		return result;
	}

	public List<gbComment> selectListgbComment(int bNo) {
		
		return gbCommentDAO.selectListgbComment(bNo);
	}

	public int updategbComment(gbComment gbComment) {
		int result = 0;
		
		result = gbCommentDAO.updategbComment(gbComment);
		if(result < 1) throw new GymBoardException("댓글 수정 실패");
		
		return result;
	}

	public int deletegbComment(int bcNo) {
		int result = 0;
		
		result = gbCommentDAO.deletegbComment(bcNo);
		if(result < 1) throw new GymBoardException("댓글 삭제 실패");
		
		return result;
	}

	public int selectLastgbComment(int bNo) {
		
		return gbCommentDAO.selectLastgbComment(bNo);
	}
}
