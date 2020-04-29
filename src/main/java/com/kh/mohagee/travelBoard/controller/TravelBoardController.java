package com.kh.mohagee.travelBoard.controller;

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
import com.kh.mohagee.travelBoard.model.service.TravelBoardService;
import com.kh.mohagee.travelBoard.model.service.tbCommentService;
import com.kh.mohagee.travelBoard.model.vo.TravelAttachment;
import com.kh.mohagee.travelBoard.model.vo.TravelBoard;

@Controller
public class TravelBoardController {
    private String[] imgExt = {"jpg", "png","PNG", "gif", "bmp", "svg", "jpeg", "webp"};
    private String[] videoExt = {"mp4", "avi", "mkv", "wmv", "flv", "asf", "ts", "mpg"};
    private String[] audioExt = {"mp3", "ogg", "wav", "flac"};

    public List<String> imgExtList = Arrays.asList(imgExt);
    public List<String> videoExtList = Arrays.asList(videoExt);
    public List<String> audioExtList = Arrays.asList(audioExt);
    
   @Autowired
   TravelBoardService travelBoardService;
   
   @Autowired
   FavoriteService favoriteService;
   
   @Autowired
   tbCommentService tbCommentService;

   @RequestMapping("/travelBoard/travelBoardList.do")
   public String travelBoardList(Model model) {
      
      // 게시글 목록
      List<TravelBoard> list = travelBoardService.selectList();
            
      model.addAttribute("list", list);
      
      return "travelBoard/travelBoardList";
   }

   // 글쓰기 버튼 함수가 이동할곳
   @RequestMapping("/travelBoard/travelBoardInsertForm.do")
   public String travelBoardInsert() {
      return "travelBoard/travelBoardInsertForm";
   }

   // 글쓰기 폼에서 등록버튼 눌렀을때 이동할 곳
   @RequestMapping("/travelBoard/travelBoardInsertEnd.do")
   public String InsertTravelBoard(TravelBoard board, Model model,
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
        List<TravelAttachment> list = new ArrayList();
       
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
             
             
             TravelAttachment att = new TravelAttachment();
       
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
        
       try {
       
       result = travelBoardService.insertTravelBoard(board, list);
       
       }catch(Exception e) {
          if(list.size() > 0) {
             for(TravelAttachment a : list) { 
                File delFile = new File(savePath + "/" + a.getbFileName());
       
       boolean isDelete = delFile.delete();
       
        System.out.println("파일 삭제 여부 확인 : " + isDelete); 
        } 
      }
       
       throw e; // 스프링이 처리할 꺼라서 그냥 던져도 됩니다. 
    }
                                    
      
      
      String msg = "";
      String loc = "/travelBoard/travelBoardList.do";// 게시판으로 가기

      if (result > 0) {
         msg = "게시글 추가 성공했다";
      } else {
         msg = "게시글 추가 실패했다";
      }

      model.addAttribute("msg", msg).addAttribute("loc", loc);

      return "common/util";
   }

   // 게시판 상세보기
   @RequestMapping("/travelBoard/travelBoardDetail.do")
   public String selectOne(@RequestParam("bNo") int bNo, Model model) {

      TravelBoard tb = travelBoardService.selectOne(bNo);
      
      if(tb.getpRenamedFileName() == null) {
          tb.setpRenamedFileName("profile.png");
      }

      List<TravelAttachment> list = travelBoardService.selectAttachment(bNo);
      
      int favoriteCount = favoriteService.favoriteCount(bNo);
      
      for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
      
   // travelBoard라는 이름에(키) gb에 담긴 값을 담는다(값)
      model.addAttribute("TravelBoard", tb)
      .addAttribute("TravelAttachment", list)
      .addAttribute("favoriteCount", favoriteCount)
      .addAttribute("tbcList",tbCommentService.selectListtbComment(bNo));

      return "travelBoard/travelBoardDetail";
   }


   // 게시판 수정하기 (수정하기 버튼)
   @RequestMapping("/travelBoard/travelBoardUpdateView.do")
	public String boardUpdateView(@RequestParam int bNo, Model model) {
	   
	   TravelBoard tb = travelBoardService.selectOne(bNo);
	   

	   String bTags = tb.getbTag().replaceAll("#", "");
	   
	   String bTag = bTags.replaceAll(" ", "");
	      
	   tb.setbTag(bTag);
		
		model.addAttribute("TravelBoard", tb)
		     .addAttribute("TravelAttachment", travelBoardService.selectAttachment(bNo));
		
		return "travelBoard/travelBoardUpdateForm";
	}
	
   // 게시판 수정완료하기 (수정완료 버튼)
	@RequestMapping("/travelBoard/travelBoardUpdate.do")
	public String travelBoardUpdate(TravelBoard board, Model model, 
			@RequestParam(value="upFile", required=false) MultipartFile[] upFiles, HttpServletRequest request) {
		
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
		TravelBoard originBoard = travelBoardService.selectOne(bNo);
		originBoard.setbTitle(board.getbTitle());
		originBoard.setbContent(board.getbContent());
		originBoard.setbTag(board.getbTag());
		originBoard.setbUrl(board.getbUrl());
		originBoard.setbCategory(board.getbCategory());
		originBoard.setmNo(board.getmNo());
		
		// 첨부파일 내용을 수정하는 부분
		// 1.파일을 저장할 경로 생성
		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload");
		
		// 2. 변경을 위해 알아야 할 예전 첨부파일 정보
		List<TravelAttachment>list = travelBoardService.selectAttachment(bNo);
		// ** 만약 첨부파일이 이전에 없었다면
		if(list == null) list = new ArrayList();
		
		// 3. 첨부파일을 저장할 위치 존재 확인
		File dir = new File(savePath);
		
		// 현재 폴더가 생성되었는지 확인
		// mkdir에 s를 붙이면 계단식으로 경로생성까지 해준다 ex)resources/upload/...
		if(dir.exists() == false) dir.mkdirs();
		
		// 4. 파일 업로드 시작
		int idx = 0;
		for(MultipartFile f : upFiles) {
			TravelAttachment att = null;
			
			if( ! f.isEmpty()) {
				String originalFileName = f.getOriginalFilename();
				String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
				System.out.println("UPDATE FILENAME : " + originalFileName);
				
				// 원본 파일 삭제
				if(list.size() > idx) {
					
					boolean isDelete 
					 = new File(savePath + "/" + list.get(idx).getbFileName()).delete();
					
					System.out.println("원본 파일 삭제 되었나요? : " + isDelete);
					
					att = list.get(idx);
				} else {
					att = new TravelAttachment();
					att.setbNo(bNo);
//////////////////////////////////////////////////////////////////////////////////////
				att.setbFileName(originalFileName);
				att.setbFilePath(savePath);
				
				if(imgExtList.contains(ext)) {
					att.setbFileType("I");
				} else if(videoExtList.contains(ext)) {
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
				try {
					f.transferTo(new File(savePath + "/" + originalFileName));
				} catch(IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				att.setbFileName(originalFileName);
				
				list.set(idx, att);
			}
			
			idx++;
		}
//////////////////////////////////////////////////////////////////////////////////////
		int result = travelBoardService.updateBoard(originBoard, list);
		
		String msg = "";
		String loc = "/travelBoard/travelBoardList.do";
		if(result > 0) {
			
			msg = "게시글 수정 성공!";
		}else {
			msg = "게시글 수정 실패!";
		}
		
		model.addAttribute("msg", msg)
			 .addAttribute("loc", loc);
		
		return "common/util";
	}
	
	// 게시판 삭제하기 (삭제버튼에 넣기)
		@RequestMapping("/travelBoard/travelBoardDelete.do")
		public String travelBoardDelete(@RequestParam int bNo, Model model, HttpSession session) {
			
			System.out.println("bno확인 : " + bNo);
			
			// 게시글 삭제 시 게시글에 담긴 첨부파일도 삭제해야 한다.
			String savePath
			    = session.getServletContext().getRealPath("/resources/upload");
			
			List<TravelAttachment> list = travelBoardService.selectAttachment(bNo);
			
			for(TravelAttachment a : list) {
				new File(savePath + "/" + a.getbFileName()).delete();
			}
			
			int result = travelBoardService.deleteTravelBoard(bNo);
			
			String msg = "";
			String loc = "/travelBoard/travelBoardList.do";
			
			if(result > 0) {
				msg = "게시글 삭제 성공!";
			} else {
				msg = "게시글 삭제 실패!";
			}
			
			model.addAttribute("msg", msg)
			     .addAttribute("loc", loc);
			
			return "common/util";
		}
		
		@RequestMapping("/board/travelFileDelete.do")
		@ResponseBody
		public boolean fileDelete(int attNo, String attFile, HttpSession session) {
			
			// 첨부파일 한 개 삭제해야 한다.
			String savePath
			    = session.getServletContext().getRealPath("/resources/upload");
			
			boolean result = travelBoardService.deleteFile(attNo) != 0 ? true : false;
			
			if(result) new File(savePath + "/" + attFile).delete();
			
			return result;
		}
		
}