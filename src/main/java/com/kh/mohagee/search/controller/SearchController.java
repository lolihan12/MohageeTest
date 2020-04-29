package com.kh.mohagee.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.mohagee.member.model.vo.FavoriteBoard;
import com.kh.mohagee.search.model.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	@RequestMapping("/search/gotoSearchForm.do")
	public String gotoSearchForm() {
		return "search/searchForm";
	}
	
	@RequestMapping("/search/indexSearch.do")
	public String indexSearch(@RequestParam String indexSearch, Model model) {
		
		indexSearch = "%" + indexSearch + "%";
		
		List<FavoriteBoard> indexSearchList = searchService.indexSearch(indexSearch);
		
		model.addAttribute("indexSearchList", indexSearchList);
		
		return "search/searchForm";
	}
	
	@RequestMapping("/search/formSearch.do")
	public String formSearch(@RequestParam int select, @RequestParam String searchInput, Model model){
		List<FavoriteBoard> list = new ArrayList<>();
		
		String searchInput2 = "%" + searchInput + "%";
		
		if(select == 1) {
			list = searchService.searchTotal(searchInput2);
		} else if(select == 2) {
			list = searchService.indexSearch(searchInput2);
		} else if(select == 3) {
			list = searchService.searchTitle(searchInput2);
		} else if(select == 4) {
			list = searchService.searchContent(searchInput2);
		} else if(select == 5) {
			
			searchInput2 ="%#" + searchInput + ",%";
			
			list = searchService.searchTag(searchInput2);

		}
		
		model.addAttribute("indexSearchList", list);
		
		
		return "search/searchForm";
	}
	
}













