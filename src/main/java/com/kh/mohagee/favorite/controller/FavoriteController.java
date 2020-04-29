package com.kh.mohagee.favorite.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.mohagee.favorite.model.service.FavoriteService;
import com.kh.mohagee.favorite.model.vo.Favorite;

@Controller
public class FavoriteController {

	@Autowired
	FavoriteService favoriteService;
	
	@RequestMapping("/favorite/checkFavorite")
	@ResponseBody
	public Map<String, Object> checkFavorite(Favorite favorite) {
		Favorite checkFavorite = favoriteService.selectFavorite(favorite);
		
		if(checkFavorite == null) {
			checkFavorite = new Favorite();
		}
		
		Map<String, Object> map = new HashMap();
		
		map.put("Favorite", checkFavorite);
		
		return map;
	}
	
	@RequestMapping("/favorite/doFavorite")
	@ResponseBody
	public int doFavorite(@RequestParam String fStatus, 
								@RequestParam int userNo, @RequestParam int bNo) {
		
		int result = 0;
		int insertFavorite = 0;
		int updateFavorite = 0;
		
		Favorite favorite = new Favorite();
		
		favorite.setbNo(bNo);
		favorite.setUserNo(userNo);
		
		if(fStatus == "") {
			fStatus = null;
		}
		
		if(fStatus == null) {
			
			insertFavorite = favoriteService.insertFavorite(favorite);
			
		} else {
			
			favorite.setfStatus(fStatus);
			updateFavorite = favoriteService.updateFavorite(favorite);
			
		}
		
		if(insertFavorite == 1 || updateFavorite == 1) {
			result = 1;
		}
		
		return result;
	}
	
	@RequestMapping("/favorite/favoriteNumber")
	@ResponseBody
	public int favoriteNumber(@RequestParam int bNo) {
		int result = favoriteService.favoriteCount(bNo);
		
		return result;
	}
	
	@RequestMapping("/favorite/cancelFavorite")
	@ResponseBody
	public int cancelFavorite(Favorite favorite) {
		int result = favoriteService.cancelFavorite(favorite);
		
		return result;
	}
	
}













