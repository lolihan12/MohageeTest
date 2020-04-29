package com.kh.mohagee.top5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.mohagee.member.model.vo.FavoriteBoard;
import com.kh.mohagee.top5.model.service.Top5Service;

@Controller
public class Top5Controller {
	
	@Autowired
	Top5Service top5Service;
	
	@RequestMapping("/top5/top5List.do")
	@ResponseBody
	public List<FavoriteBoard> top5List(){
		
		List<FavoriteBoard> list = top5Service.selectList();
		
		
		for(FavoriteBoard f : list) {
			
			if(f.getbContent().length() > 300) {
				
				String splitContent = f.getbContent().substring(0, 300);
				
				String content = splitContent + "...";
				
				f.setbContent(content);
				
				System.out.println(f);
				
			}
			
		}
		
		return list;
	}

}
