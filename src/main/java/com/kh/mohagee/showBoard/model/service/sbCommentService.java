package com.kh.mohagee.showBoard.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mohagee.showBoard.model.vo.ShowBoard;
import com.kh.mohagee.showBoard.model.vo.sbComment;
import com.kh.mohagee.showBoard.exception.ShowBoardException;
import com.kh.mohagee.showBoard.model.dao.sbCommentDAO;



@Service
public class sbCommentService {

	@Autowired
	sbCommentDAO sbCommentDAO;
	
	public int insertsbComment(sbComment sbComment) throws ShowBoardException{
		int result = 0;
		
		System.out.println("서비스sbbComment 는 : " + sbComment);
		result = sbCommentDAO.insertsbComment(sbComment);
		if(result < 1) throw new ShowBoardException("게시글 추가 중 에러 발생!");
		
		return result;
	}

	public List<sbComment> selectListsbComment(int bNo) {
		
		return sbCommentDAO.selectListsbComment(bNo);
	}

	public int updatesbComment(sbComment sbComment) {
		int result = 0;
		
		result = sbCommentDAO.updatesbComment(sbComment);
		if(result < 1) throw new ShowBoardException("댓글 수정 실패");
		
		return result;
	}

	public int deletesbComment(int bcNo) {
		int result = 0;
		
		result = sbCommentDAO.deletesbComment(bcNo);
		if(result < 1) throw new ShowBoardException("댓글 삭제 실패");
		
		return result;
	}

	public int selectLastsbComment(int bNo) {
		
		return sbCommentDAO.selectLastsbComment(bNo);
	}
}
