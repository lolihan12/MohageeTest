package com.kh.mohagee.member.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mohagee.email.model.service.EmailService;
import com.kh.mohagee.favorite.model.service.FavoriteService;
import com.kh.mohagee.member.model.service.MemberService;
import com.kh.mohagee.member.model.vo.FavoriteBoard;
import com.kh.mohagee.member.model.vo.Member;
import com.kh.mohagee.member.model.vo.Profile;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;

	@Autowired
	EmailService emailService;
	
	@Autowired
	FavoriteService favoriteService;

	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;

	@RequestMapping("/member/signUp.do")
	public String memberSignUp(Member member, Model model) throws Exception {

		String password = member.getPassword();

		String encPassword = bcryptPasswordEncoder.encode(password);

		member.setPassword(encPassword);

		int result = memberService.insertMember(member);

		emailService.createRandNum(member.getUserId());

		String msg = "";
		String loc = "";

		if (result > 0) {
			msg = "회원가입이 완료되었습니다. \\n이메일인증후부터 로그인이 가능합니다.";
			loc = "/";
		} else {
			msg = "회원가입이 실패 했습니다.";
			loc = "/";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("loc", loc);

		return "common/util";
	}

	@RequestMapping("/member/memberLogin.do")
	public ModelAndView memberLogin(HttpServletRequest context, Member member, HttpSession session) throws Exception {

		ModelAndView mv = new ModelAndView();
		
		String msg = "";
		String loc = "/";
		
		if(context.getAttribute(member.getUserId()) == null) {
			
			try {
				
				Member m = memberService.selectOneMember(member);
				Profile profile = memberService.selectProfile(m.getUserNo());
				
				
				
				if (m != null && bcryptPasswordEncoder.matches(member.getPassword(), m.getPassword())
						&& m.getEmailCheck() == 0) {
					msg = "이메일 인증을 진행하고 로그인을 해주시기 바랍니다";
				} else if (m != null && bcryptPasswordEncoder.matches(member.getPassword(), m.getPassword())
						&& m.getEmailCheck() == 1) {
					
					
					if (profile == null || profile.getpOriginalFileName() == "") {
						
						profile = new Profile();
						profile.setpRenamedFileName("profile.png");
						
					}
					
					context.setAttribute(m.getUserId(), m.getNickName());
					
					msg = m.getNickName() + "님, 환영합니다.";
					
					session.setAttribute("profile", profile);
					session.setAttribute("member", m);
					
					mv.addObject("member", m);
					
				} else if (m != null) {
					
					msg = "비밀번호가 틀렸습니다.";
					
				}
				
				mv.addObject("msg", msg).addObject("loc", loc);
				mv.setViewName("common/util");
				
			} catch (Exception e) {
				throw new Exception("로그인 시도 중 에러 발생!");
			}
			
		} else {
			
			msg = "이미로그인한 아이디 입니다";
			mv.addObject("msg", msg).addObject("loc", loc);
			mv.setViewName("common/util");
			
		}


		return mv;
	}

	@RequestMapping("/member/logOut.do")
	public String logOut(HttpSession session) {

		session.invalidate();

		return "redirect:/";
	}

	@RequestMapping("/member/findId.do")
	public String findId(@RequestParam("name") String name, @RequestParam("phone") String phone, Model model)
			throws Exception {

		Member member = new Member();

		member.setUserName(name);
		member.setPhone(phone);

		String email = memberService.findEmail(member);

		String msg = "";
		String loc = "";

		if (email == null || email == "") {

			msg = "존재하지 않는 이름과 전화번호 입니다";
			loc = "/member/findIdPage.do";

		} else {

			int check = memberService.updateConfirm(email);

			if (check == 0) {
				System.out.println("멤버 updateConfirm함수 실행 실패");
			}

			emailService.createRandNum(email);

			msg = "회원님의 아이디인 \\n" + email + "로 인증메일을 발송했습니다. \\n" + "인증 후부터 로그인이 가능합니다.";
			loc = "/";

		}

		model.addAttribute("msg", msg).addAttribute("loc", loc);

		return "common/util";
	}

	@RequestMapping("/member/findPw.do")
	public String findPw(@RequestParam("userId") String userId, @RequestParam("name") String name,
			@RequestParam("phone") String phone, Model model) throws Exception {

		Member member = new Member();

		member.setUserId(userId);
		member.setUserName(name);
		member.setPhone(phone);

		int result = memberService.findPassword(member);

		String msg = "";
		String loc = "";

		if (result == 0) {

			msg = "존재하지 않는 회원입니다";
			loc = "/member/findPwPage.do";

		} else {

			String TemPass = emailService.TemPass(userId);

			String encTemPass = bcryptPasswordEncoder.encode(TemPass);

			emailService.findPassword(userId, TemPass, encTemPass);

			msg = "회원님의 이메일로 비밀번호를 전달 해드렸습니다.";
			loc = "/";
		}

		model.addAttribute("msg", msg).addAttribute("loc", loc);

		return "common/util";

	}

	@RequestMapping("/member/myPage.do")
	public String myPage(@RequestParam("userNo") int userNo, Model model, HttpSession session) {

		Member m = memberService.selectMyPage(userNo);

		Profile profile = memberService.selectProfile(userNo);
		
		if (profile == null || profile.getpOriginalFileName() == "") {
			
			profile = new Profile();
			profile.setpRenamedFileName("profile.png");
			
		}
		
		List<FavoriteBoard> favoriteList = favoriteService.selectFavoriteList(userNo);
		
		for(FavoriteBoard f : favoriteList) {
			if(f.getTitleFilename() == null) {
				f.setTitleFilename("defaultImage.png");
			}
		}
		
		List<FavoriteBoard> myBoardList = memberService.selectMyBoardList(userNo);
		
		for(FavoriteBoard f : myBoardList) {
			if(f.getTitleFilename() == null) {
				f.setTitleFilename("defaultImage.png");
			}
		}
		
		String msg = "";
		String loc = "";

		if (m == null) {
			msg = "존재하지 않는 사용자 입니다.";
			loc = "/";

			model.addAttribute("msg", msg).addAttribute("myPageProfile", profile).addAttribute("loc", loc);

			return "common/util";

		}

		session.setAttribute("myPageMember", m);
		session.setAttribute("myPageProfile", profile);
		session.setAttribute("favoriteList", favoriteList);
		session.setAttribute("myBoardList", myBoardList);

		return "member/myPage";
	}

	@RequestMapping(value = "/member/memberUpdate.do", method = RequestMethod.POST)
	public String memberUpdate(@RequestParam("profilePhotoCheck") int checkNum, @RequestParam(name = "profilePhoto") MultipartFile profilePhoto, Member member,
			HttpSession session, Model model, HttpServletRequest request) throws Exception {

		// ----------- originalMember Setting ----------- //
		Member originalMember = memberService.selectOneMember(member);

		originalMember.setUserNo(member.getUserNo());

		originalMember.setUserId(member.getUserId());

		if (member.getPassword().length() > 0) {

			String encPassword = bcryptPasswordEncoder.encode(member.getPassword());

			originalMember.setPassword(encPassword);

		} else {
			originalMember.setPassword(null);
		}

		originalMember.setUserName(member.getUserName());

		if (member.getNickName() != null || member.getNickName().length() != 0) {

			originalMember.setNickName(member.getNickName());

		} else if (member.getNickName().length() == 0) {

			member.setNickName(null);

		}

		if (member.getPhone() != null || member.getPhone().length() != 0) {

			originalMember.setPhone(member.getPhone());

		} else if (member.getPhone().length() == 0) {

			member.setPhone(null);

		}
		
		if (member.getIntroduce() != null) {

			originalMember.setIntroduce(member.getIntroduce());

		} 

		// ----------- originalMember Setting ----------- //

		String profilePath = request.getSession().getServletContext().getRealPath("/resources/profile");

		Profile profile = memberService.selectProfile(member.getUserNo());
		;

		File dir = new File(profilePath);

		if (dir.exists() == false) {
			dir.mkdirs();
		}

	
		/*
		 * boolean test1 = profilePhoto.getOriginalFilename() ==
		 * profile.getpOriginalFileName(); boolean test2 = profilePhoto.getName() ==
		 * profile.getpOriginalFileName(); boolean test3 =
		 * profilePhoto.getOriginalFilename() == profile.getpRenamedFileName(); boolean
		 * test4 = profilePhoto.getName() == profile.getpRenamedFileName();
		 * 
		 * System.out.println("test1" + test1); System.out.println("test2" + test2);
		 * System.out.println("test3" + test3); System.out.println("test4" + test4);
		 */
		 

		if (checkNum == 1) {

			if (profile != null) {
				new File(profilePath + "/" + profile.getpRenamedFileName()).delete();
			}

			String originalFileName = profilePhoto.getOriginalFilename();

			if (profile != null && (originalFileName == null || originalFileName == "")) {
				originalFileName = profile.getpOriginalFileName();
			}
			String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmss");

			int rNum = (int) (Math.random() * 1000);

			String renamedFileName = sdf.format(new Date(System.currentTimeMillis())) + "_" + rNum + "." + ext;

			try {
				profilePhoto.transferTo(new File(profilePath + "/" + renamedFileName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(profile == null) {
				profile = new Profile();
			}
			profile.setUserNo(originalMember.getUserNo());
			profile.setpOriginalFileName(originalFileName);
			profile.setpRenamedFileName(renamedFileName);


		}

		int result = 0;

		try {

			result = memberService.updateMember(originalMember, profile);

		} catch (Exception e) {

			if (profilePhoto != null) {
				File delFile = new File(profilePath + "/" + profile.getpRenamedFileName());
				delFile.delete();
			}

			throw e;
		}

		String msg = "";
		String loc = "/";

		if (result > 0) {
			msg = "회원정보 수정이 완료되었습니다.";
		} else {
			msg = "회원정보 수정에 실패 했습니다.";
		}

		session.invalidate();

		model.addAttribute("msg", msg).addAttribute("loc", loc);

		return "common/util";
	}

}
