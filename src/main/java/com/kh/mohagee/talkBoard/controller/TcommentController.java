package com.kh.mohagee.talkBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.mohagee.talkBoard.model.service.TalkBoardService;
import com.kh.mohagee.talkBoard.model.service.tCommentService;
import com.kh.mohagee.talkBoard.model.vo.Tcomment;

@Controller
public class TcommentController {
	
	@Autowired
	tCommentService tCommentService;
	
	@Autowired
	TalkBoardService talkBoardService;
	
	// 댓글 등록
		@RequestMapping("/tComment/tCommentInsert.do")
		@ResponseBody
		public int tCommentInsert (
				@RequestParam int tno ,
				@RequestParam int userNo,
				@RequestParam String tcContent,
				@RequestParam(value="ttcNo", defaultValue = "0" ) int ttcNo
				//@RequestParam Date bcDate,
				//@RequestParam int bcLevel,
				//@RequestParam String bcStatus,
				){
			
			System.out.println("tno : " + tno);
			System.out.println("userNo :" + userNo);
			System.out.println("tcCentent : " + tcContent);
			System.out.println("ttcNo : " + ttcNo);
			
			Tcomment tComment = null;
			// insert
			if(ttcNo == 0) {
				tComment = new Tcomment(tno, userNo, tcContent);
			} else {
				tComment = new Tcomment(tno, userNo, tcContent, ttcNo);
			}
			
			int result = tCommentService.inserttComment(tComment);
			int data = 0;
			if(result>0) {
				// selectList
				data = tCommentService.selectLasttComment(tno);
				System.out.println(data);
			}
			return data;
		}
		
		// 댓글 수정
		@RequestMapping("/tComment/tCommentUpdate.do")
		@ResponseBody
		public int tCommentUpdate(@RequestParam int tno ,
									@RequestParam int userNo,
									@RequestParam String tcContent){

			Tcomment tc = new Tcomment();
			
			tc.setTno(tno);
			tc.setUserNo(userNo);
			tc.setTcContent(tcContent);
			
			int result = tCommentService.updatetComment(tc);
			
			return result;
		}	
		
		// 댓글 삭제
		@RequestMapping("/tComment/tCommentDelete.do")
		@ResponseBody
		public int tCommentDeleteSelect (@RequestParam int tno){
		
			int result = tCommentService.deletetComment(tno);
			
			return result;
		}	

}
