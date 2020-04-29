package com.kh.mohagee.talkBoard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mohagee.showBoard.exception.ShowBoardException;
import com.kh.mohagee.showBoard.model.vo.ShowAttachment;
import com.kh.mohagee.talkBoard.exception.TalkBoardException;
import com.kh.mohagee.talkBoard.model.dao.TalkBoardDAO;
import com.kh.mohagee.talkBoard.model.vo.TalkAttachment;
import com.kh.mohagee.talkBoard.model.vo.TalkBoard;

@Service
public class TalkBoardService {
	
	@Autowired
	TalkBoardDAO talkBoardDAO;

	public List<TalkBoard> selectList() {
		return talkBoardDAO.selectList();
	}

	public int insertTalkBoard(TalkBoard board, List<TalkAttachment> list) {
			int result = 0;
		  
		  // 1. 게시글 추가 
		  result = talkBoardDAO.insertTalkBoard(board); 
		  
		  if(result < 1) {
			  
			  throw new TalkBoardException("게시글 추가 중 에러 발생!");
		  }
		  
		  
		  // 2. 추가된 게시글의 번호를 가져와 // 첨부파일 추가하기 
		  
		  if(list.size() > 0) { 
			  for(int i = 0; i < list.size(); i++) {
				  TalkAttachment a = list.get(i);
				  
				  if(i== 0) a.settFileLevel(0);
				  else  a.settFileLevel(1);
				  
				  System.out.println("check : "+a);
				 result = talkBoardDAO.insertTalkAttachment(a);
		  
				  System.out.println("result 확인 : " + result); // 확인용 result 값 변경하기 // 
				  
		  		// result = 0; 
		  
		  if(result < 1) 
			  throw new TalkBoardException("첨부파일 추가 중 에러 발생!"); 
		  } 
	 }
		 
		return result;
	}

	public TalkBoard selectOneTalkBoard(int tno) {
		return talkBoardDAO.selectOne(tno);
	}

	public List<TalkAttachment> selectAttachment(int tno) {
		
		return talkBoardDAO.selectAttachment(tno);
	}

	public int updateTalkBoard(TalkBoard originBoard, List<TalkAttachment> list) {
		
		int result = 0;
		
		List<TalkAttachment> originList
		 = talkBoardDAO.selectAttachment(originBoard.getTno());
		
		result = talkBoardDAO.updateTalkBoard(originBoard);
		
		if(result > 0) {
			if(originList.size() > 0) {
				result = talkBoardDAO.deleteAttachment(originBoard.getTno());
			}
			
			if(list.size() > 0) {
				for(TalkAttachment t : list) {
					result = talkBoardDAO.updateAttachment(t);
				}
			}
		}
		
		return result;
	}

	public int deleteTalkBoard(int tno) {
		
		return talkBoardDAO.deleteTalkBoard(tno);
	}

	
	

}
