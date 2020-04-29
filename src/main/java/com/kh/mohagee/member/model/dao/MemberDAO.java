package com.kh.mohagee.member.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mohagee.favorite.model.vo.Favorite;
import com.kh.mohagee.member.model.vo.FavoriteBoard;
import com.kh.mohagee.member.model.vo.Member;
import com.kh.mohagee.member.model.vo.Profile;

@Repository
public class MemberDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int insertMember(Member member) {
		return sqlSession.insert("member-mapper.insertMember", member);
	}

	public Member selectNickName(String userId) {
		return sqlSession.selectOne("member-mapper.selectNickName", userId);
	}

	public Member selectOneMember(Member member) {
		return sqlSession.selectOne("member-mapper.selectOneMember", member);
	}

	public String findEmail(Member member) {
		return sqlSession.selectOne("member-mapper.selectFindEmail", member);
	}

	public int updateConfirm(String email) {
		return sqlSession.update("member-mapper.updateConfirm", email);
	}

	public int findPassword(Member member) {
		return sqlSession.selectOne("member-mapper.selectFindPassword", member);
	}

	public int updateTemPass(Member member) {
		return sqlSession.update("member-mapper.updateTemPass", member);
	}

	public Member selectMyPage(int userNo) {
		return sqlSession.selectOne("member-mapper.selectMyPage", userNo);
	}

	public int updateMember(Member member) {
		return sqlSession.update("member-mapper.updateMember", member);
	}

	public int insertProfilePhoto(Profile profile) {
		return sqlSession.insert("member-mapper.insertProfile");
	}

	public Profile selectProfile(int userNo) {
		return sqlSession.selectOne("member-mapper.selectProfile", userNo);
	}

	public int deleteProfile(int userNo) {
		return sqlSession.delete("member-mapper.deleteProfile", userNo);
	}

	public int updateProfile(Profile profile) {
		return sqlSession.insert("member-mapper.updateProfile", profile);
	}

	public List<FavoriteBoard> selectMyBoardList(int userNo) {
		return sqlSession.selectList("member-mapper.selectMyBoardList", userNo);
	}

}
