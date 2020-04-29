package com.kh.mohagee.travelBoard.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mohagee.travelBoard.model.vo.TravelBoard;
import com.kh.mohagee.travelBoard.model.vo.tbComment;
import com.kh.mohagee.travelBoard.exception.TravelBoardException;
import com.kh.mohagee.travelBoard.model.dao.tbCommentDAO;


@Service
public class tbCommentService {

	@Autowired
	tbCommentDAO tbCommentDAO;
	
	public int inserttbComment(tbComment tbComment) throws TravelBoardException{
		int result = 0;
		
		System.out.println("서비스에tbComment 는 : " + tbComment);
		result = tbCommentDAO.inserttbComment(tbComment);
		if(result < 1) throw new TravelBoardException("게시글 추가 중 에러 발생!");
		
		return result;
	}

	public List<tbComment> selectListtbComment(int bNo) {
		
		return tbCommentDAO.selectListtbComment(bNo);
	}

	public int updatetbComment(tbComment tbComment) {
		int result = 0;
		
		result = tbCommentDAO.updatetbComment(tbComment);
		if(result < 1) throw new TravelBoardException("댓글 수정 실패");
		
		return result;
	}

	public int deletetbComment(int bcNo) {
		int result = 0;
		
		result = tbCommentDAO.deletetbComment(bcNo);
		if(result < 1) throw new TravelBoardException("댓글 삭제 실패");
		
		return result;
	}

	public int selectLasttbComment(int bNo) {
		
		return tbCommentDAO.selectLasttbComment(bNo);
	}
}
