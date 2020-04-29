package com.kh.mohagee.favorite.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mohagee.favorite.model.dao.FavoriteDAO;
import com.kh.mohagee.favorite.model.vo.Favorite;
import com.kh.mohagee.member.model.vo.FavoriteBoard;

@Service
public class FavoriteService {
	
	@Autowired
	FavoriteDAO favoriteDao;

	public int favoriteCount(int bNo) {
		return favoriteDao.favoriteCount(bNo);
	}

	public Favorite selectFavorite(Favorite favorite) {
		return favoriteDao.selectFavorite(favorite);
	}

	public int insertFavorite(Favorite favorite) {
		return favoriteDao.insertFavorite(favorite);
	}

	public int updateFavorite(Favorite favorite) {
		return favoriteDao.updateFavorite(favorite);
	}

	public int cancelFavorite(Favorite favorite) {
		return favoriteDao.cancelFavorite(favorite);
	}

	public List<FavoriteBoard> selectFavoriteList(int userNo) {
		return favoriteDao.selectFavoriteList(userNo);
	}

}
