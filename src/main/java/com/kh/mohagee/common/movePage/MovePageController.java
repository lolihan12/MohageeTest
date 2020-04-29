package com.kh.mohagee.common.movePage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.mohagee.common.movePage.model.service.IndexService;

@Controller
public class MovePageController {
	
	@Autowired
	IndexService indexService;
	
	
	@RequestMapping("/index.do")
	public String gotoIndexMain(Model model) {
			
		int total = indexService.totalBoard();
		int showBoard = indexService.showBoard();
		int travelBoard = indexService.travelBoard();
		int gymBoard = indexService.gymBoard();
		
		model.addAttribute("total", total).addAttribute("showBoard", showBoard)
		.addAttribute("travelBoard", travelBoard).addAttribute("gymBoard", gymBoard);
		
		return "index";
	}
	
	@RequestMapping("/gotoIndex.do")
	public String gotoIndex() {

		return "redirect:/";
	}

	@RequestMapping("/goto404.do")
	public String goto404() {
		return "common/404";
	}

	
	@RequestMapping("/gotoTravelBoardList.do")
	public String gotoTravelBoardList() {
		return "travelBoard/travelBoardList";
	}
	
	@RequestMapping("/gotoGymBoardInsertForm.do")
    public String gotoGymBoardInsertForm() {
       return "gymBoard/gymBoardInsertForm";
    }
   
    @RequestMapping("/gotoGymBoardList.do")
    public String gotoGymBoardList() {
       return "gymBoard/gymBoardList";
    }
   
    @RequestMapping("/gotoGymBoardDetail.do")
    public String gotoGymBoardDetail() {
       return "gymBoard/gymBoardDetail";
    }
    
    @RequestMapping("/TalkBoardList.do")
    public String TalkBoardList() {
       return "talkBoard/talkBoardList";
    }
    
    @RequestMapping("/TalkBoardDetail.do")
    public String TalkBoardDetail() {
       return "travelBoard/travelBoardList";
    }
    
    @RequestMapping("/member/findIdPage.do")
	public String findIdPage() {
		return "member/findId";
	}
    
    @RequestMapping("/member/findPwPage.do")
	public String findPwPage() {
		return "member/findPassword";
	}
    
    @RequestMapping("/member/memberUpdatePage.do")
    public String memberUpdatePage() {
    	return "member/updateProfile";
    }
    
    @RequestMapping("/Chatting.do")
    public String Chatting() {
    	return "chat/chatting";
    }
}
