package com.kh.mohagee.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mohagee.favorite.model.vo.Favorite;
import com.kh.mohagee.member.exception.MemberException;
import com.kh.mohagee.member.model.dao.MemberDAO;
import com.kh.mohagee.member.model.vo.FavoriteBoard;
import com.kh.mohagee.member.model.vo.Member;
import com.kh.mohagee.member.model.vo.Profile;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO memberDao;

	public int insertMember(Member member) {
		return memberDao.insertMember(member);
	}

	public Member selectNickName(String userId) {
		return memberDao.selectNickName(userId);
	}

	public Member selectOneMember(Member member) {
		return memberDao.selectOneMember(member);
	}

	public String findEmail(Member member) {
		return memberDao.findEmail(member);
	}

	public int updateConfirm(String email) {
		return memberDao.updateConfirm(email);
	}

	public int findPassword(Member member) {
		return memberDao.findPassword(member);
	}

	public int updateTemPass(Member member) {
		return memberDao.updateTemPass(member);
	}

	public Member selectMyPage(int userNo) {
		return memberDao.selectMyPage(userNo);
	}

	public int updateMember(Member member, Profile profile)  {
		
		int result = 0;
		
		Profile originProfile = memberDao.selectProfile(member.getUserNo());
		
		result = memberDao.updateMember(member);
		
		if (result > 0) {
			if(originProfile != null) { 
				result = memberDao.deleteProfile(member.getUserNo());
			}
			  
			if (profile != null) { 
				result = memberDao.updateProfile(profile); 
			} 
		}
		 
		return result;
	}

	public Profile selectProfile(int userNo) {
		return memberDao.selectProfile(userNo);
	}

	public List<FavoriteBoard> selectMyBoardList(int userNo) {
		return memberDao.selectMyBoardList(userNo);
	}

}












