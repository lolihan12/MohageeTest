package com.kh.mohagee.search.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mohagee.member.model.vo.FavoriteBoard;
import com.kh.mohagee.search.model.dao.SearchDAO;

@Service
public class SearchService {
	
	@Autowired
	SearchDAO searchDao;

	public List<FavoriteBoard> indexSearch(String indexSearch) {
		return searchDao.indexSearch(indexSearch);
	}

	public List<FavoriteBoard> searchTotal(String searchInput) {
		return searchDao.searchTotal(searchInput);
	}

	public List<FavoriteBoard> searchTitle(String searchInput) {
		return searchDao.searchTitle(searchInput);
	}

	public List<FavoriteBoard> searchContent(String searchInput) {
		return searchDao.searchContent(searchInput);
	}

	public List<FavoriteBoard> searchTag(String searchInput) {
		return searchDao.searchTag(searchInput);
	}
	
}
