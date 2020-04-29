package com.kh.mohagee.common.movePage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mohagee.common.movePage.model.dao.IndexDAO;


@Service
public class IndexService {
	@Autowired
	IndexDAO indexDao;

	public int totalBoard() {
		return indexDao.totalBoard();
	}

	public int showBoard() {
		return indexDao.showBoard();
	}

	public int travelBoard() {
		return indexDao.travelBoard();
	}

	public int gymBoard() {
		return indexDao.gymBoard();
	}

}
