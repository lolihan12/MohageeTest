package com.kh.mohagee.travelBoard.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.mohagee.travelBoard.model.service.tbCommentService;
import com.kh.mohagee.travelBoard.model.service.TravelBoardService;
import com.kh.mohagee.travelBoard.model.vo.TravelBoard;
import com.kh.mohagee.travelBoard.model.vo.tbComment;

@Controller
public class tbCommentController {	
	@Autowired
	tbCommentService tbCommentService;
	@Autowired
	TravelBoardService TravelBoardService;
	
	// 댓글 등록
	@RequestMapping("/tbComment/tbCommentInsert.do")
	@ResponseBody
	public int tbCommentInsert (
	@RequestParam int bNo ,
	@RequestParam int userNo,
	@RequestParam String bcContent,
	@RequestParam(value="bbcNo", defaultValue = "0" ) int bbcNo){
		
		System.out.println("bNo : " + bNo);
		System.out.println("userNo :" + userNo);
		System.out.println("bcCentent : " + bcContent);
		System.out.println("bbcNo : " + bbcNo);
		
		tbComment tbComment = null;
		
		// insert
		if(bbcNo == 0) {
			tbComment = new tbComment(bNo, userNo, bcContent);
		} else {
			tbComment = new tbComment(bNo, userNo, bcContent, bbcNo);
		}
		
		int result = tbCommentService.inserttbComment(tbComment);
		int data = 0;
		if(result>0) {
			// selectList
			data = tbCommentService.selectLasttbComment(bNo);
			System.out.println(data);
		}
		return data;
	}
	
	// 댓글 수정
	@RequestMapping("/tbComment/tbCommentUpdate.do")
	@ResponseBody
	public int tbCommentUpdate(@RequestParam int bcNo ,
	@RequestParam int userNo,
	@RequestParam String bcContent){

		tbComment tbc = new tbComment();
		
		tbc.setBcNo(bcNo);
		tbc.setUserNo(userNo);
		tbc.setBcContent(bcContent);
		
		int result = tbCommentService.updatetbComment(tbc);
		
		return result;
	}	
	
	// 댓글 삭제
	@RequestMapping("/tbComment/tbCommentDelete.do")
	@ResponseBody
	public int tbCommentDeleteSelect (@RequestParam int bcNo){
	
		int result = tbCommentService.deletetbComment(bcNo);
		
		return result;
	}	
	
}
