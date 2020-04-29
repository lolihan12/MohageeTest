package com.kh.mohagee.showBoard.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.mohagee.favorite.model.service.FavoriteService;
import com.kh.mohagee.showBoard.model.service.ShowBoardService;
import com.kh.mohagee.showBoard.model.service.sbCommentService;
import com.kh.mohagee.showBoard.model.vo.ShowAttachment;
import com.kh.mohagee.showBoard.model.vo.ShowBoard;

@Controller
public class ShowBoardController {
	 private String[] imgExt = {"jpg", "png","PNG", "gif", "bmp", "svg", "jpeg", "webp"};
	 private String[] videoExt = {"mp4", "avi", "mkv", "wmv", "flv", "asf", "ts", "mpg"};
	 private String[] audioExt = {"mp3", "ogg", "wav", "flac"};

	 public List<String> imgExtList = Arrays.asList(imgExt);
	 public List<String> videoExtList = Arrays.asList(videoExt);
	 public List<String> audioExtList = Arrays.asList(audioExt);
	 
	@Autowired
	ShowBoardService showBoardService;
	
	@Autowired
	FavoriteService favoriteService;
	
	@Autowired
	sbCommentService sbCommentService;

	@RequestMapping("/showBoard/showBoardList.do")
	public String showBoardList(Model model) {
		
		// 게시글 목록
		List<ShowBoard> list = showBoardService.selectList();
				
		model.addAttribute("list", list);
		
		return "showBoard/showBoardList";
	}

	// 글쓰기 버튼 함수가 이동할곳
	@RequestMapping("/showBoard/showBoardInsert.do")
	public String showBoardInsert() {
		return "showBoard/showBoardInsert";
	}

	// 글쓰기 폼에서 등록버튼 눌렀을때 이동할 곳
	@RequestMapping("/showBoard/showBoardInsertEnd.do")
	public String InsertShowBoard(ShowBoard board, Model model,
			@RequestParam(value="upFile", required=false) MultipartFile[] upFiles,
			HttpServletRequest request) {
		
		String[] tagArray = board.getbTag().split(",");
		
		for(int i = 0; i < tagArray.length; i++) {
			tagArray[i] = "#" + tagArray[i];
		}
		
		String tagArrayToString = Arrays.toString(tagArray);

		String tag = tagArrayToString.substring(1, tagArrayToString.lastIndexOf(']'));

		System.out.println("tag : " + tag);
		
		board.setbTag(tag);

		 // 1. 저장할 폴더 설정 
	      String savePath = request.getSession().getServletContext().getRealPath("resources/upload");
		 
		 // 2. DB에 전달할 파일 정보를 담을 list 준비하기 
		  List<ShowAttachment> list = new ArrayList();
		 
		 // 3. 만약 저장할 폴더가 없다면 직접 만들기 
		  File dir = new File(savePath);
		 if(dir.exists()==false) dir.mkdirs();
		 
		 
		 	//********** Multipart 파일 업로드 시작 **********/
		
		 for(MultipartFile f : upFiles) { 
			 
			 if(!f.isEmpty()) {
				 
				 String originalFileName = f.getOriginalFilename();
				 String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
					
				 try {
					 
					f.transferTo(new File(savePath + "/" + originalFileName));
					
				} catch (IllegalStateException e) {
					e.printStackTrace();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				 
				 
				 ShowAttachment att = new ShowAttachment();
		 
				 att.setbFileName(originalFileName);
				 
				 att.setbFilePath(savePath);
				 
				 if(imgExtList.contains(ext)) {
					 
					 att.setbFileType("I");
					 
				 } else if(videoExtList.contains(ext) ) {
					 
					 att.setbFileType("V");
					 
				 } else if(audioExtList.contains(ext)) {
					 
					 att.setbFileType("A");
					 
				 } else {
					 
					 att.setbFileType("E");
				 }
				 
				 list.add(att); 
	   } 
	}
		 
		 /********** Multipart 파일 업로드 끝 **********/
														 
		  int result = 0;
		  
		  System.out.println(list);
		 try {
		 
		 result = showBoardService.insertShowBoard(board, list);
		 
		 }catch(Exception e) {
			 if(list.size() > 0) {
				 for(ShowAttachment a : list) { 
					 File delFile = new File(savePath + "/" + a.getbFileName());
		 
		 boolean isDelete = delFile.delete();
		 
		  System.out.println("파일 삭제 여부 확인 : " + isDelete); 
		  } 
		}
		 
		 throw e; // 스프링이 처리할 꺼라서 그냥 던져도 됩니다. 
	 }
												
		String msg = "";
		String loc = "/showBoard/showBoardList.do";// 게시판으로 가기

		if (result > 0) {
			msg = "게시글 추가 성공했다";
		} else {
			msg = "게시글 추가 실패했다";
		}

		model.addAttribute("msg", msg).addAttribute("loc", loc);

		return "common/util";
	}

	// 게시판 상세보기
	@RequestMapping("/showBoard/showBoardDetail.do")
	public String selectOne(@RequestParam("bNo") int bNo, Model model) {

		ShowBoard sb = showBoardService.selectOneShowBoard(bNo);
		
		if(sb.getpRenamedFileName() == null) {
			sb.setpRenamedFileName("profile.png");
		}

		List<ShowAttachment> list = showBoardService.selectAttachment(bNo);
		
		int favoriteCount = favoriteService.favoriteCount(bNo);
		
		System.out.println(sb);
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println(list.size());
		
		model.addAttribute("ShowBoard", sb)
		.addAttribute("ShowAttachment", list)
		.addAttribute("favoriteCount", favoriteCount)
		.addAttribute("sbcList",sbCommentService.selectListsbComment(bNo));

		return "showBoard/showBoardDetail";
	}


	// 수정페이지로 이동하기
	@RequestMapping("/showBoard/showBoardUpdateForm.do")
	public String showBoardUpdateForm(@RequestParam int bNo, Model model) {
		
		ShowBoard sb = showBoardService.selectOneShowBoard(bNo);
		
		String bTags = sb.getbTag().replaceAll("#", "");
		   
		String bTag = bTags.replaceAll(" ", "");
		      
		sb.setbTag(bTag);

		model.addAttribute("ShowBoard", sb)
			.addAttribute("attachmentList", showBoardService.selectAttachment(bNo));

		return "showBoard/showBoardUpdate";
	}
	
	// 게시글 수정하기
	@RequestMapping("/showBoard/showBoardUpdate.do")
	public String updateShowBoard(ShowBoard showBoard, Model model,
			@RequestParam(value = "upFile", required = false) MultipartFile[] upFiles, HttpServletRequest request) {

		int bNo = showBoard.getbNo();
		
		String[] tagArray = showBoard.getbTag().split(",");
		
		for(int i = 0; i < tagArray.length; i++) {
			
			if(tagArray[i].charAt(1) != '#') {
				
				tagArray[i] = "#" + tagArray[i];	
				
				if(i == 0) {
					tagArray[i].replaceAll(" ", "");
				}
				
			}
			
			
		}
		
		String tagArrayToString = Arrays.toString(tagArray);

		String tag = tagArrayToString.substring(1, tagArrayToString.lastIndexOf(']'));

		System.out.println("tag : " + tag);
		
		showBoard.setbTag(tag);

		// 원본 게시글 수정 부분
		ShowBoard originBoard = showBoardService.selectOneShowBoard(bNo);
		originBoard.setbTitle(showBoard.getbTitle());
		originBoard.setbContent(showBoard.getbContent());
		originBoard.setbTag(showBoard.getbTag());
		originBoard.setbUrl(showBoard.getbUrl());
		originBoard.setbCategory(showBoard.getbCategory());
		originBoard.setcNo(showBoard.getcNo());

		// 첨부 파일 수정 부분
		// 1. 파일을 저장할 경로 생성
		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload");

		// 2. 변경을 위해 알아야 할 예전 첨부파일 정보
		List<ShowAttachment> list = showBoardService.selectAttachment(bNo);

		// *** 만약 첨부파일이 이전에 없었다면...?
		if (list == null)
			list = new ArrayList();

		// 3. 저장할 첨부파일을 저장할 위치 존재 확인
		File dir = new File(savePath);

		// 현재 폴더가 생성되었는지 확인
		if (dir.exists() == false)
			dir.mkdirs(); // mkdirs()는 존재하는 파일들까지 전부 생성해줌

		// 4. 파일 업로드 시작!
		int idx = 0;
		for (MultipartFile f : upFiles) {
			ShowAttachment att = null;

			if (!f.isEmpty()) {
				
				// 원본파일 삭제
				if (list.size() > idx) {
					
					for(int i = 0 ; i < list.size(); i ++) {
						
						boolean isDelete = new File(savePath + "/" + list.get(i).getbFileName()).delete(); // 파일 삭제
						// 명령어
						
						System.out.println("원본파일 삭제 되었나? :" + isDelete);
						
					}
					att = list.get(idx);

				} else { // 첨부파일이 없는 게시글일때 기존 첨부파일을 삭제할 필요 없어서 추가만 한다.
					att = new ShowAttachment();
					att.setbNo(bNo);
					
					String originalFileName = f.getOriginalFilename();
					String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
					
					 if(imgExtList.contains(ext)) {
						 
						 att.setbFileType("I");
						 
					 } else if(videoExtList.contains(ext) ) {
						 
						 att.setbFileType("V");
						 
					 } else if(audioExtList.contains(ext)) {
						 
						 att.setbFileType("A");
						 
					 } else {
						 
						 att.setbFileType("E");
					 }
					
					list.add(att);
				}

				// 정상적인 파일 추가 과정
				// 원본 파일 이름 가져오기
				String originalFileName = f.getOriginalFilename();
								
				att.setbFileName(originalFileName);
				att.setbFilePath(savePath + "/" + originalFileName);
				
				try {

					f.transferTo(new File(savePath + "/" + originalFileName));

				} catch (IllegalStateException e) {
					e.printStackTrace();

				} catch (IOException e) {
					e.printStackTrace();
				}

				list.set(idx, att);
			}

			idx++;
		}

		int result = showBoardService.updateShowBoard(originBoard, list);

		String msg = "";
		String loc = "/showBoard/showBoardList.do";

		if (result > 0) {
			msg = "게시글 수정 성공! +_+ ";

		} else {
			msg = "게시글 수정 실패 ㅜ_ㅜ";
		}

		model.addAttribute("msg", msg).addAttribute("loc", loc);

		return "common/util";
	}

	
	
	// 게시글 삭제
	@RequestMapping("/showBoard/showBoardDelete.do")
	public String boardDelete(int bNo, Model model, HttpSession session) {

		// 게시글에 담긴 첨부파일도 삭제해야한다.
		String savePath = session.getServletContext().getRealPath("/resources/upload");

		List<ShowAttachment> list = showBoardService.selectAttachment(bNo);

		for (ShowAttachment a : list) {

			new File(savePath + "/" + a.getbFileName()).delete();
		}

		int result = showBoardService.deleteShowBoard(bNo);

		String msg = "";
		String loc = "/showBoard/showBoardList.do";

		if (result > 0) {
			msg = "게시글 삭제 성공!";

		} else {
			msg = "게시글 삭제 실패";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("loc", loc);

		return "common/util";
	}
	
	@RequestMapping("/board/showFileDelete.do")
	@ResponseBody
	public boolean fileDelete(int attNo, String attFile, HttpSession session) {
		
		// 첨부파일 한 개 삭제해야 한다.
		String savePath
		    = session.getServletContext().getRealPath("/resources/upload");
		
		boolean result = showBoardService.deleteFile(attNo) != 0 ? true : false;
		
		if(result) new File(savePath + "/" + attFile).delete();
		
		return result;
	}

	
}
