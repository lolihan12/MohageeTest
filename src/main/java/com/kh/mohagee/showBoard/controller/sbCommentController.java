package com.kh.mohagee.showBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.mohagee.showBoard.model.service.ShowBoardService;
import com.kh.mohagee.showBoard.model.service.sbCommentService;
import com.kh.mohagee.showBoard.model.vo.sbComment;



@Controller
public class sbCommentController {	
	@Autowired
	sbCommentService sbCommentService;
	@Autowired
	ShowBoardService showBoardService;
	
	// 댓글 등록
	@RequestMapping("/sbComment/sbCommentInsert.do")
	@ResponseBody
	public int sbCommentInsert (
																			  @RequestParam int bNo ,
																			  @RequestParam int userNo,
																			  @RequestParam String bcContent,
																			  @RequestParam(value="bbcNo", defaultValue = "0" ) int bbcNo
																			  //@RequestParam Date bcDate,
																			  //@RequestParam int bcLevel,
																			  //@RequestParam String bcStatus,
																			  ){
		
		System.out.println("bNo : " + bNo);
		System.out.println("userNo :" + userNo);
		System.out.println("bcCentent : " + bcContent);
		System.out.println("bbcNo : " + bbcNo);
		
		sbComment sbComment = null;
		// insert
		if(bbcNo == 0) {
			sbComment = new sbComment(bNo, userNo, bcContent);
		} else {
			sbComment = new sbComment(bNo, userNo, bcContent, bbcNo);
		}
		
		int result = sbCommentService.insertsbComment(sbComment);
		int data = 0;
		if(result>0) {
			// selectList
			data = sbCommentService.selectLastsbComment(bNo);
			System.out.println(data);
		}
		return data;
	}
	
	// 댓글 수정
	@RequestMapping("/sbComment/sbCommentUpdate.do")
	@ResponseBody
	public int sbCommentUpdate(@RequestParam int bcNo ,
													 @RequestParam int userNo,
													 @RequestParam String bcContent){

		sbComment sbc = new sbComment();
		
		sbc.setBcNo(bcNo);
		sbc.setUserNo(userNo);
		sbc.setBcContent(bcContent);
		
		int result = sbCommentService.updatesbComment(sbc);
		
		return result;
	}	
	
	// 댓글 삭제
	@RequestMapping("/sbComment/sbCommentDelete.do")
	@ResponseBody
	public int sbCommentDeleteSelect (@RequestParam int bcNo){
	
		int result = sbCommentService.deletesbComment(bcNo);
		
		return result;
	}	
	
}
