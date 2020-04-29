package com.kh.mohagee.search.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mohagee.member.model.vo.FavoriteBoard;

@Repository
public class SearchDAO {
	@Autowired
	SqlSessionTemplate sqlSession;

	public List<FavoriteBoard> indexSearch(String indexSearch) {
		return sqlSession.selectList("search-mapper.indexSearch", indexSearch);
	}

	public List<FavoriteBoard> searchTotal(String searchInput) {
		return sqlSession.selectList("search-mapper.searchTotal", searchInput);
	}
	
	public List<FavoriteBoard> searchTitle(String searchInput) {
		return sqlSession.selectList("search-mapper.searchTitle", searchInput);
	}

	public List<FavoriteBoard> searchContent(String searchInput) {
		return sqlSession.selectList("search-mapper.searchContent", searchInput);
	}

	public List<FavoriteBoard> searchTag(String searchInput) {
		return sqlSession.selectList("search-mapper.searchTag", searchInput);
	}
}
