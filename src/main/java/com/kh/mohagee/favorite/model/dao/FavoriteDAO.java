package com.kh.mohagee.favorite.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mohagee.favorite.model.vo.Favorite;
import com.kh.mohagee.member.model.vo.FavoriteBoard;

@Repository
public class FavoriteDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;

	public int favoriteCount(int bNo) {
		return sqlSession.selectOne("favorite-mapper.favoriteCount", bNo);
	}

	public Favorite selectFavorite(Favorite favorite) {
		return sqlSession.selectOne("favorite-mapper.selectFavorite", favorite);
	}

	public int insertFavorite(Favorite favorite) {
		return sqlSession.insert("favorite-mapper.insertFavorite", favorite);
	}

	public int updateFavorite(Favorite favorite) {
		return sqlSession.update("favorite-mapper.updateFavorite", favorite);
	}

	public int cancelFavorite(Favorite favorite) {
		return sqlSession.update("favorite-mapper.cancelFavorite", favorite);
	}

	public List<FavoriteBoard> selectFavoriteList(int userNo) {
		return sqlSession.selectList("favorite-mapper.selectFavoriteList", userNo);
	}
	
}
