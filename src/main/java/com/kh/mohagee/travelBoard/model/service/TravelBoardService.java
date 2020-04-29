package com.kh.mohagee.travelBoard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mohagee.travelBoard.exception.TravelBoardException;
import com.kh.mohagee.travelBoard.model.dao.TravelBoardDAO;
import com.kh.mohagee.travelBoard.model.vo.TravelAttachment;
import com.kh.mohagee.travelBoard.model.vo.TravelBoard;

@Service
public class TravelBoardService {

   @Autowired
   TravelBoardDAO travelBoardDAO;

   public List<TravelBoard> selectList() {
      return travelBoardDAO.selectList();
   }

   public int selectTotalContents() {
      return travelBoardDAO.selectTotalContents();
   }

   public int insertTravelBoard(TravelBoard travelBoard, List<TravelAttachment> list) {
      
        int result = 0;
        
        // 1. 게시글 추가 
        
        result = travelBoardDAO.insertTravelBoard(travelBoard); 
        
        if(result < 1) {
           
           throw new TravelBoardException("게시글 추가 중 에러 발생!");
        }
        
        
        // 2. 추가된 게시글의 번호를 가져와 // 첨부파일 추가하기 
        if(list.size() > 0) { 
           for(int i = 0; i < list.size(); i++) { 
              TravelAttachment a = list.get(i);
              
              if(i == 0) a.setbFileLevel(0);
              else a.setbFileLevel(1);
              result = travelBoardDAO.insertTravelAttachment(a);
        
              System.out.println("result 확인 : " + result); // 확인용 result 값 변경하기 // 
           
              // result =0; 
        
        if(result < 1) 
           throw new TravelBoardException("첨부파일 추가 중 에러 발생!"); 
        } 
    }
       
      return result;
   }

   public TravelBoard selectOne(int bNo) {
      return travelBoardDAO.selectOne(bNo);
   }

   public List<TravelAttachment> selectAttachment(int bNo) {
      return travelBoardDAO.selectTravelAttachment(bNo);
   }

   // 게시글 수정하기
   public int updateBoard(TravelBoard travelBoard, List<TravelAttachment> list) {
	   
		int result = 0;
		
		List<TravelAttachment> originList = travelBoardDAO.selectTravelAttachment(travelBoard.getbNo());
		result = travelBoardDAO.updateTravelBoard(travelBoard);
		
		if(result > 0) {
			if(originList.size() > 0 && list.size() > 0) { // 원본도 있고, 변경하려는 이미지도 있을 때
				result = travelBoardDAO.deleteTravelAttachment(travelBoard.getbNo());
				// i 가 0일 때 대표이미지를, 1이면 서브이미지를 구현하기 위한 대표 사진 번호를 i로 먼저 지정하기
				// 내가 추가함... 
				int i = 0; // 이렇게!
				
				for(TravelAttachment a : list) {
			              
					// 내가 추가함
					if(i == 0) a.setbFileLevel(0); // 대표 이미지로 설정할려고 0을 set함 
			        else a.setbFileLevel(1);  // 서브 이미지로 설정할려고 1을 set함
					
					result = travelBoardDAO.updateTravelAttachment(a);
					
					// 내가 추가함
					i++; // i++ 로 대표와 서브 구분 
				}
			} else if ( list.size() > 0) { // 원본은 없고 변경 이미지만 있을 때
				// 내가 추가함
				int i = 0; // 이렇게!
				
				for(TravelAttachment a : list) {
			              
					// 내가 추가함
					if(i == 0) a.setbFileLevel(0); // 대표 이미지로 설정할려고 0을 set함 
			        else a.setbFileLevel(1);  // 서브 이미지로 설정할려고 1을 set함
					
					result = travelBoardDAO.updateTravelAttachment(a);
					
					// 내가 추가함
					i++; // i++ 로 대표와 서브 구분
				}
			}
		}
		
		return result;
	}

   // 게시글 삭제하기
	public int deleteTravelBoard(int bNo) {
		
		return travelBoardDAO.deleteTravelBoard(bNo);
	}

	public int deleteFile(int attNo) {
		// 만약 지우려는 파일이 메인 사진 일 경우
		// 메인 사진을 지우면 다음의 사진이 메인 역할을 해야 한다.
		TravelAttachment ta = travelBoardDAO.getAttachmentOne(attNo);
		if(ta.getbFileLevel() == 0 ) {
			travelBoardDAO.setNextTitleImage(ta.getbNo());
		}
		
		// 그렇지 않을 경우
		return travelBoardDAO.deleteFile(attNo);
	}
	
}