package com.kh.mohagee.showBoard.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mohagee.showBoard.exception.ShowBoardException;
import com.kh.mohagee.showBoard.model.dao.ShowBoardDAO;
import com.kh.mohagee.showBoard.model.vo.ShowAttachment;
import com.kh.mohagee.showBoard.model.vo.ShowBoard;
import com.kh.mohagee.travelBoard.model.vo.TravelAttachment;

@Service
public class ShowBoardService {

	@Autowired
	ShowBoardDAO showBoardDAO;

	public List<ShowBoard> selectList() {
		return showBoardDAO.selectList();
	}

	public int selectTotalContents() {
		return showBoardDAO.selectTotalContents();
	}

	// 게시글 작성
	public int insertShowBoard(ShowBoard showBoard, List<ShowAttachment> list) {
		
		  int result = 0;
		  
		  // 1. 게시글 추가 
		  result = showBoardDAO.insertShowBoard(showBoard); 
		  
		  if(result < 1) {
			  
			  throw new ShowBoardException("게시글 추가 중 에러 발생!");
		  }
		  
		  
		/*
		 * String content = request.getParameter("bContent");
		 * 
		 * contents = contents.replace("\r\n","<br>");
		 */

		  // 2. 추가된 게시글의 번호를 가져와 // 첨부파일 추가하기 
		  
		  if(list.size() > 0) { 
			  for(int i = 0; i < list.size(); i++) {
				  ShowAttachment a = list.get(i);
				  
				  // 파일 레벨 0, 1 나누기
				  if(i== 0) a.setbFileLevel(0);
				  else  a.setbFileLevel(1);
				  
				 result = showBoardDAO.insertShowAttachment(a);
		  				  
		  
		  if(result < 1) 
			  throw new ShowBoardException("첨부파일 추가 중 에러 발생!"); 
		  } 
	 }
		 
		return result;
	}

	// 게시글 선택
	public ShowBoard selectOneShowBoard(int bNo) {
		return showBoardDAO.selectOneShowBoard(bNo);
	}

	public List<ShowAttachment> selectAttachment(int bNo) {
		return showBoardDAO.selectShowAttachment(bNo);
	}

	// 게시글 수정하기
	   public int updateShowBoard(ShowBoard showBoard, List<ShowAttachment> list) {
	      
	      int result = 0;

	      List<ShowAttachment> originList = showBoardDAO.selectShowAttachment(showBoard.getbNo());
	      result = showBoardDAO.updateShowBoard(showBoard);

	      if (result > 0) {
	         if (originList.size() > 0 && list.size() > 0) { // 원본도 있고, 변경하려는 이미지도 있을 때
	            result = showBoardDAO.deleteShowAttachment(showBoard.getbNo());
	            
	            // i 가 0일 때 대표이미지를, 1이면 서브이미지를 구현하기 위한 대표 사진 번호를 i로 먼저 지정하기
	            int i = 0; // 이렇게!
	            
	            for(ShowAttachment a : list) {
	               
	               if(i == 0) a.setbFileLevel(0); // 대표 이미지로 설정할려고 0을 set함 
	                 else a.setbFileLevel(1);  // 서브 이미지로 설정할려고 1을 set함
	               
	               result = showBoardDAO.updateShowAttachment(a);
	               
	               i++; // i++ 로 대표와 서브 구분                
	            }
	         } else if ( list.size() > 0) { // 원본은 없고 변경 이미지만 있을 때

	            int i = 0; // 이렇게!
	         
	            for(ShowAttachment a : list) {
	               if(i == 0) a.setbFileLevel(0); // 대표 이미지로 설정할려고 0을 set함 
	                 else a.setbFileLevel(1);  // 서브 이미지로 설정할려고 1을 set함
	               
	               result = showBoardDAO.updateShowAttachment(a);
	               
	               i++; // i++ 로 대표와 서브 구분       
	            }
	         }
	      }
	      return result;
	   }

	// 게시글 삭제하기
	public int deleteShowBoard(int bNo) {

		return showBoardDAO.deleteShowBoard(bNo);
	}

	public int deleteFile(int attNo) {
		// 만약 지우려는 파일이 메인 사진 일 경우
		// 메인 사진을 지우면 다음의 사진이 메인 역할을 해야 한다.
		ShowAttachment ta = showBoardDAO.getAttachmentOne(attNo);
		if(ta.getbFileLevel() == 0 ) {
			showBoardDAO.setNextTitleImage(ta.getbNo());
		}
		
		// 그렇지 않을 경우
		return showBoardDAO.deleteFile(attNo);
	}



}
