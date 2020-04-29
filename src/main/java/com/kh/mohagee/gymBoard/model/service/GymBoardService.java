package com.kh.mohagee.gymBoard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mohagee.gymBoard.exception.GymBoardException;
import com.kh.mohagee.gymBoard.model.dao.GymBoardDAO;

import com.kh.mohagee.gymBoard.model.vo.GymAttachment;
import com.kh.mohagee.gymBoard.model.vo.GymBoard;
import com.kh.mohagee.gymBoard.model.vo.gbComment;
import com.kh.mohagee.travelBoard.model.vo.TravelAttachment;

@Service
public class GymBoardService {

   @Autowired
   GymBoardDAO GymBoardDAO;
   gbComment gbComment;
   
   
    public List<GymBoard> selectList(){
       return GymBoardDAO.selectList(); 
    }
    
   
   public int insertGymBoard(GymBoard board, List<GymAttachment> list) { 
      int result = 0;
      
      // 1. 게시글 추가
      result = GymBoardDAO.insertGymBoard(board);
      if(result < 1) {
         throw new GymBoardException("게시글 추가 중 에러 발생!");
      }
      // 2. 추가된 게시글의 번호를 가져와
      //    첨부파일 추가하기
      if(list.size() > 0) {
           for(int i = 0; i < list.size(); i++) {
              GymAttachment a = list.get(i);
              
              if(i== 0) {
            	  a.setbFileLevel(0);
              }
              else  {
            	  a.setbFileLevel(1);
              }
              
              System.out.println("check : "+a);
            result = GymBoardDAO.insertGymAttachment(a);
            
            System.out.println("result 확인 : " + result);

            // 확인용 result 값 변경하기
            // result = 0;
            if(result < 1)
               throw new GymBoardException("첨부파일 추가 중 에러 발생!");
         }
      }
      return result;
   }   
   public GymBoard selectOneGymBoard(int bNo) {
      return GymBoardDAO.selectOneGymBoard(bNo);
   }

   public List<GymAttachment> selectAttachment(int bNo) {
      return GymBoardDAO.selectGymAttachment(bNo);
   }

      // 게시글 수정하기
   public int updateBoard(GymBoard gymBoard, List<GymAttachment> list) {
      
      int result = 0;
      
      List<GymAttachment> originList
         = GymBoardDAO.selectGymAttachment(gymBoard.getbNo());
      
      result = GymBoardDAO.updateGymBoard(gymBoard);
      
      if(result > 0) {
         if(originList.size() > 0 && list.size() > 0) { // 원본도 있고, 변경하려는 이미지도 있을 때
            result = GymBoardDAO.deleteGymAttachment(gymBoard.getbNo());
            
            // i 가 0일 때 대표이미지를, 1이면 서브이미지를 구현하기 위한 대표 사진 번호를 i로 먼저 지정하기            
            int i = 0;
            
            for(GymAttachment a : list) {

               if(i == 0) a.setbFileLevel(0); // 대표 이미지로 설정할려고 0을 set함 
                 else a.setbFileLevel(1);  // 서브 이미지로 설정할려고 1을 set함
      
               result = GymBoardDAO.updateGymAttachment(a);

               i++; // i++ 로 대표와 서브 구분                
            }
         } else if ( list.size() > 0) {  // 원본은 없고 변경 이미지만 있을 때
            int i = 0;    

            for(GymAttachment a : list) {
               
               if(i == 0) a.setbFileLevel(0); // 대표 이미지로 설정할려고 0을 set함 
                 else a.setbFileLevel(1);  // 서브 이미지로 설정할려고 1을 set함
               
               
               result = GymBoardDAO.updateGymAttachment(a);
               i++; // i++ 로 대표와 서브 구분               
            }
         }
      }
      
      return result;
   }

   public int deleteGymBoard(int bNo) {
      
      return GymBoardDAO.deleteGymBoard(bNo);
   }


public int deleteFile(int attNo) {
	// 만약 지우려는 파일이 메인 사진 일 경우
	// 메인 사진을 지우면 다음의 사진이 메인 역할을 해야 한다.
	GymAttachment ta = GymBoardDAO.getAttachmentOne(attNo);
	if(ta.getbFileLevel() == 0 ) {
		GymBoardDAO.setNextTitleImage(ta.getbNo());
	}
	
	// 그렇지 않을 경우
	return GymBoardDAO.deleteFile(attNo);
}
   
}