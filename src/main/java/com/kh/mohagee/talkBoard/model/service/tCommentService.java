package com.kh.mohagee.talkBoard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mohagee.talkBoard.exception.TalkBoardException;
import com.kh.mohagee.talkBoard.model.dao.tCommentDAO;
import com.kh.mohagee.talkBoard.model.vo.Tcomment;

@Service
public class tCommentService {
	
	@Autowired
	tCommentDAO tCommentDAO;
	
	public int inserttComment(Tcomment tComment) throws TalkBoardException{
		int result = 0;
		
		System.out.println("서비스에tComment 는 : " + tComment);
		result = tCommentDAO.inserttComment(tComment);
		if(result < 1) throw new TalkBoardException("게시글 추가 중 에러 발생!");
		
		return result;
	}

	public List<Tcomment> selectListgbComment(int tno) {
		
		return tCommentDAO.selectListtComment(tno);
	}

	public int updatetComment(Tcomment tComment) {
		int result = 0;
		
		result = tCommentDAO.updatetComment(tComment);
		if(result < 1) throw new TalkBoardException("댓글 수정 실패");
		
		return result;
	}

	public int deletetComment(int tno) {
		int result = 0;
		
		result = tCommentDAO.deletetComment(tno);
		if(result < 1) throw new TalkBoardException("댓글 삭제 실패");
		
		return result;
	}

	public int selectLasttComment(int tno) {
		
		return tCommentDAO.selectLasttComment(tno);
	}

}
