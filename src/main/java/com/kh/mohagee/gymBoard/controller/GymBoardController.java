package com.kh.mohagee.gymBoard.controller;

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
import com.kh.mohagee.gymBoard.model.service.GymBoardService;
import com.kh.mohagee.gymBoard.model.service.gbCommentService;
import com.kh.mohagee.gymBoard.model.vo.GymAttachment;
import com.kh.mohagee.gymBoard.model.vo.GymBoard;

@Controller
public class GymBoardController {
	
    private String[] imgExt = {"jpg", "png", "PNG", "gif", "GIF", "bmp", "svg", "jpeg", "webp", "jfif"};
    private String[] videoExt = {"mp4", "avi", "mkv", "wmv", "flv", "asf", "ts", "mpg"};
    private String[] audioExt = {"mp3", "ogg", "wav", "flac"};

    public List<String> imgExtList = Arrays.asList(imgExt);
    public List<String> videoExtList = Arrays.asList(videoExt);
    public List<String> audioExtList = Arrays.asList(audioExt);
	
	
	@Autowired
	GymBoardService GymBoardService;
	
	@Autowired
	FavoriteService favoriteService;
	
	@Autowired
	gbCommentService gbCommentService;


	   @RequestMapping("/gymBoard/gymBoardList.do")
	   public String GymBoardList(Model model) {
	      
	      List<GymBoard> list = GymBoardService.selectList();
	            
	      model.addAttribute("list", list);
	      
	      return "gymBoard/gymBoardList";
	   }	

	   
	// 글쓰기 버튼 함수가 이동할곳
	@RequestMapping("/gymBoard/gymBoardInsertForm.do")
	public String gymBoardInsertForm() {
		return "gymBoard/gymBoardInsertForm";
	}	

	// 글쓰기 폼에서 등록버튼 눌렀을때 이동할 곳
	@RequestMapping("gymBoard/gymBoardInsertEnd.do")
	public String InsertGymBoard(
			GymBoard board, Model model,
			@RequestParam(value="upFile", required=false) MultipartFile[] upFiles,
			HttpServletRequest request) {
		System.out.println(board);
		
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
		List<GymAttachment> list = new ArrayList();
		
		// 3. 만약 저장할 폴더가 없다면 직접 만들기
		File dir = new File(savePath);
		if(dir.exists()==false) dir.mkdirs();
		
		/********** Multipart 파일 업로드 시작 **********/

	       for(MultipartFile f : upFiles) { 
	           if(!f.isEmpty()) {
	              String originalFileName = f.getOriginalFilename();
	              String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
	                
	              try {
	                 
	                f.transferTo(new File(savePath + "/" + originalFileName));
	             } catch (IllegalStateException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	             } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	             }
	              GymAttachment att = new GymAttachment();
	        
	              System.out.println("INSERT FILENAME : " + originalFileName);
	              
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
		/********** Multipart 파일 업로드 끝   **********/
		
		int result = 0;
		  System.out.println(list);		
		try {
			result = GymBoardService.insertGymBoard(board, list);
		}catch(Exception e) {
			if(list.size() > 0) {
				for(GymAttachment a : list) {
					File delFile = new File(savePath + "/" + a.getbFileName());
					
					boolean isDelete = delFile.delete();
					
					System.out.println("파일 삭제 여부 확인 : " + isDelete);
				}
			}
			
			throw e; // 스프링이 처리할 꺼라서 그냥 던져도 됩니다.
		}
		
 
		
		String msg = "";
		String loc = "/gymBoard/gymBoardList.do";//운동 게시판으로 가기
		
		if(result > 0) {
			msg = "게시글 추가 성공해버렸네?";
		}else {
			msg = "게시글 추가 실패해버렸네?";
		}
		
		model.addAttribute("msg", msg).addAttribute("loc", loc);
		
	return "common/util";
	}
	
	// 건하 운동 게시판 상세보기
	@RequestMapping("/gymBoard/gymBoardDetail.do")
	public String selectOne(@RequestParam("bNo") int bNo,
																		Model model) {
		
		GymBoard gymBoard = GymBoardService.selectOneGymBoard(bNo);
		
		if(gymBoard.getpRenamedFileName() == null) {
			gymBoard.setpRenamedFileName("profile.png");
	     }
		
		List<GymAttachment> list = GymBoardService.selectAttachment(bNo);
		
		int favoriteCount = favoriteService.favoriteCount(bNo);
		
		// gymBoard라는 이름에(키) gb에 담긴 값을 담는다(값)
		model.addAttribute("gymBoard", gymBoard)
		.addAttribute("GymAttachmentList", list)
		.addAttribute("favoriteCount", favoriteCount)
		.addAttribute("gbcList",gbCommentService.selectListgbComment(bNo));
	
	return "gymBoard/gymBoardDetail";
	}
	
	// 건하 게시판 수정하러가기 (수정버튼에 넣기)
	@RequestMapping("/gymBoard/gymBoardUpdateView.do")
	public String gymBoardUpdateView(@RequestParam int bNo, Model model) {
		
		GymBoard gb = GymBoardService.selectOneGymBoard(bNo);
		
		String bTags = gb.getbTag().replaceAll("#", "");
		   
		String bTag = bTags.replaceAll(" ", "");
		      
		gb.setbTag(bTag);
		
		model.addAttribute("gymBoard", gb)
		     .addAttribute("GymAttachmentList", GymBoardService.selectAttachment(bNo));
		
		return "gymBoard/gymBoardUpdateView";
	}
	
	// 건하 게시판 수정완료하기 (수정완료버튼에 넣기)
	
	@RequestMapping("/gymBoard/gymBoardUpdateEnd.do")
	public String GymBoardUpdate(GymBoard board, Model model,
					@RequestParam(value="upFile", required=false) 
	  					MultipartFile[] upFiles,
					HttpServletRequest request) {
		
		int bNo = board.getbNo();
		
		String[] tagArray = board.getbTag().split(",");
		
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
		
		board.setbTag(tag);
		
		// 원본 게시글 수정 부분
		GymBoard originBoard
		   = GymBoardService.selectOneGymBoard(bNo);
		
		originBoard.setbTitle(board.getbTitle());
		originBoard.setbContent(board.getbContent());
		originBoard.setbTag(board.getbTag());
		originBoard.setbUrl(board.getbUrl());
		originBoard.setbCategory(board.getbCategory());
		
		
		// 첨부파일 내용을 수정하는 부분
		// 1. 파일을 저장할 경로 생성
		String savePath
		 = request.getSession().getServletContext()
		          .getRealPath("/resources/upload");
		
		// 2. 변경을 위해 알아야 할 예전 첨부파일 정보
		List<GymAttachment> list
		   = GymBoardService.selectAttachment(bNo);
		// ** 만약 첨부파일이 이전에 없었다면...?
		if(list == null) list = new ArrayList();
		
		// 3. 첨부파일을 저장할 위치 존재 확인
		File dir = new File(savePath);
		
		// 현재 폴더가 생성되었는지 확인
		if(dir.exists() == false) dir.mkdirs();
		
		// 4. 파일 업로드 시작!
		int idx = 0;
		for(MultipartFile f : upFiles) {
			GymAttachment att = null;
			
			if( ! f.isEmpty() ) {
					              
				// 원본 파일 삭제
				if(list.size() > idx) {
					boolean isDelete
					  = new File(savePath + "/" + list.get(idx).getbFileName()).delete();
					
					System.out.println("원본 파일 삭제 되었나요? : " + isDelete);
					
					att = list.get(idx);
				} else {
					att = new GymAttachment();
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
				
				att.setbFileName(originalFileName);
				
				list.set(idx, att);
			}
			
			idx++;
		}
		
		int result = GymBoardService.updateBoard(originBoard, list);
		
		String msg = "";
		String loc = "/gymBoard/gymBoardList.do";
		
		if(result > 0)	{
			msg = "게시글 수정 성공!";
		} else {
			msg = "게시글 수정 실패!";
		}
		
		model.addAttribute("msg", msg)
		     .addAttribute("loc", loc);
		
		return "common/util";
	}	
	
	

	
	
	
	
	
	// 건하 게시판 삭제하기 (삭제버튼에 넣기)
	@RequestMapping("/gymBoard/gymBoardDelete.do")
	public String gymBoardDelete(@RequestParam int bNo, Model model, HttpSession session) {
		
		// 게시글 삭제 시 게시글에 담긴 첨부파일도 삭제해야 한다.
		String savePath
		    = session.getServletContext().getRealPath("/resources/upload");
		
		List<GymAttachment> list = GymBoardService.selectAttachment(bNo);
		
		for(GymAttachment a : list) {
			new File(savePath + "/" + a.getbFileName()).delete();
		}
		
		int result = GymBoardService.deleteGymBoard(bNo);
		
		String msg = "";
		String loc = "/gymBoard/gymBoardList.do";
		
		if(result > 0) {
			msg = "게시글 삭제 성공!";
		} else {
			msg = "게시글 삭제 실패!";
		}
		
		model.addAttribute("msg", msg)
		     .addAttribute("loc", loc);
		
		return "common/util";
	}
	
	@RequestMapping("/board/gymFileDelete.do")
	@ResponseBody
	public boolean fileDelete(int attNo, String attFile, HttpSession session) {
		
		// 첨부파일 한 개 삭제해야 한다.
		String savePath
		    = session.getServletContext().getRealPath("/resources/upload");
		
		boolean result = GymBoardService.deleteFile(attNo) != 0 ? true : false;
		
		if(result) new File(savePath + "/" + attFile).delete();
		
		return result;
	}
	
}






