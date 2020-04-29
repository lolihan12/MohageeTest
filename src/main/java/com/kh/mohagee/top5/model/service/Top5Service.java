package com.kh.mohagee.top5.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mohagee.member.model.vo.FavoriteBoard;
import com.kh.mohagee.top5.model.dao.Top5DAO;

@Service
public class Top5Service {

	@Autowired
	Top5DAO top5DAO;
	
	public List<FavoriteBoard> selectList() {
		
		return top5DAO.selectList();
	}

}
