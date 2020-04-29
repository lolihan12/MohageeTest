package com.kh.mohagee.talkBoard.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.kh.mohagee.talkBoard.model.service.TalkBoardService;
import com.kh.mohagee.talkBoard.model.vo.TalkAttachment;
import com.kh.mohagee.talkBoard.model.vo.TalkBoard;

@Controller
public class TalkBoardController {
	
	private String[] imgExt = {"jpg", "png","PNG", "gif", "bmp", "svg", "jpeg", "webp"};
	private String[] videoExt = {"mp4", "avi", "mkv", "wmv", "flv", "asf", "ts", "mpg"};
    private String[] audioExt = {"mp3", "ogg", "wav", "flac"};
    
    public List<String> imgExtList = Arrays.asList(imgExt);
    public List<String> videoExtList = Arrays.asList(videoExt);
    public List<String> audioExtList = Arrays.asList(audioExt);
	
	@Autowired
	TalkBoardService talkBoardService;
	
	@RequestMapping("/talkBoard/talkBoardList.do")
	public String talkBoardList(Model model) {
		
		// 게시글 목록
		List<TalkBoard> list = talkBoardService.selectList();
				
		model.addAttribute("list", list);
		
		return "talkBoard/talkBoardList";
	}
	
		// insertForm 이동
		@RequestMapping("/talkBoard/talkInsert.do")
		public String talkBoardInsert() {
			return "talkBoard/talkBoardInsert";
		}

		@RequestMapping("/talkBoard/talkBoardInsert.do")
		public String InsertTalkBoard(TalkBoard board, Model model,
			@RequestParam(value="upFile", required=false) MultipartFile[] upFiles,
			HttpServletRequest request) {
			System.out.println(board);
			
			 // 1. 저장할 폴더 설정 
			String savePath = request.getSession().getServletContext().getRealPath("resources/talkUpload");
			 
			 // 2. DB에 전달할 파일 정보를 담을 list 준비하기 
			  List<TalkAttachment> list = new ArrayList();
			 
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
					 
					 
					 TalkAttachment att = new TalkAttachment();
			 
					 att.settFileName(originalFileName);
					 
					 att.settFilePath(savePath);
					 
					 if(imgExtList.contains(ext)) {
		                 att.settFileType("I");
		              } else if(videoExtList.contains(ext) ) {
		                 att.settFileType("V");
		              } else if(audioExtList.contains(ext)) {
		                 att.settFileType("A");
		              } else {
		                 att.settFileType("E");
		              }
					 
					 
					 list.add(att); 
		   } 
		}
			 
			 /********** Multipart 파일 업로드 끝 **********/
															 
			  int result = 0;
			  
			  System.out.println(list);
			 try {
			 
			 result = talkBoardService.insertTalkBoard(board, list);
			 
			 }catch(Exception e) {
				 if(list.size() > 0) {
					 for(TalkAttachment a : list) { 
						 File delFile = new File(savePath + "/" + a.gettFileName());
			 
			 boolean isDelete = delFile.delete();
			 
			  System.out.println("파일 삭제 여부 확인 : " + isDelete); 
			  } 
			}
			 
			 throw e; // 스프링이 처리할 꺼라서 그냥 던져도 됩니다. 
		 }
													
			String msg = "";
			String loc = "/talkBoard/talkBoardList.do";// 게시판으로 가기

			if (result > 0) {
				msg = "수다글 게시 성공!";
			} else {
				msg = "오류 발생!";
			}

			model.addAttribute("msg", msg).addAttribute("loc", loc);

			return "common/util";
		}

		// 게시판 상세보기
		@RequestMapping("/talkBoard/talkBoardDetail.do")
		public String selectOne(@RequestParam("tno") int tno, Model model) {

			TalkBoard tb = talkBoardService.selectOneTalkBoard(tno);

			List<TalkAttachment> list = talkBoardService.selectAttachment(tno);
			
			// talkBoard라는 이름에(키) tb에 담긴 값을 담는다(값)
			model.addAttribute("talkBoard", tb)
				.addAttribute("talkAttachmentList", list);
			
			return "talkBoard/talkBoardDetail";
		}


		// 수정페이지로 이동하기
		@RequestMapping("/talkBoard/talkUpdate.do")
		public String talkBoardUpdateForm(@RequestParam int tno, Model model) {

			model.addAttribute("talkBoard", talkBoardService.selectOneTalkBoard(tno))
				.addAttribute("talkattachmentList", talkBoardService.selectAttachment(tno));

			return "talkBoard/talkBoardUpdate";
		}
		
		// 게시글 수정하기
		@RequestMapping("/talkBoard/talkBoardUpdate.do")
		public String updateTalkBoard(TalkBoard talkBoard, Model model,
				@RequestParam(value = "upFile", required = false) MultipartFile[] upFiles, HttpServletRequest request) {

			int tno = talkBoard.getTno();

			// 원본 게시글 수정 부분
			TalkBoard originBoard = talkBoardService.selectOneTalkBoard(tno);
			originBoard.settTitle(talkBoard.gettTitle());
			originBoard.settContent(talkBoard.gettContent());
			originBoard.settTag(talkBoard.gettTag());

			// 첨부 파일 수정 부분
			// 1. 파일을 저장할 경로 생성
			String savePath = request.getSession()
					.getServletContext().getRealPath("/resources/talkUpload");

			// 2. 변경을 위해 알아야 할 예전 첨부파일 정보
			List<TalkAttachment> list = talkBoardService.selectAttachment(tno);

			// *** 만약 첨부파일이 이전에 없었다면...?
			if (list == null) list = new ArrayList();

			// 3. 저장할 첨부파일을 저장할 위치 존재 확인
			File dir = new File(savePath);

			// 현재 폴더가 생성되었는지 확인
			if (dir.exists() == false)
				dir.mkdirs(); // mkdirs()는 존재하는 파일들까지 전부 생성해줌

			// 4. 파일 업로드 시작!
			int idx = 0;
			for (MultipartFile f : upFiles) {
				TalkAttachment att = null;

				if (!f.isEmpty()) {
					String originalFileName = f.getOriginalFilename();
		            String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
					
					// 원본파일 삭제
					if (list.size() > idx) {
						boolean isDelete 
							= new File(savePath + "/" + list.get(idx).gettFileName()).delete(); // 파일 삭제
																								// 명령어

						System.out.println("원본파일 삭제 되었나? :" + isDelete);

						att = list.get(idx);

					} else { // 첨부파일이 없는 게시글일때 기존 첨부파일을 삭제할 필요 없어서 추가만 한다.
						att = new TalkAttachment();
						att.setTno(tno);
						
						att.settFileName(originalFileName);
			              att.settFilePath(savePath);
			              
			              if(imgExtList.contains(ext)) {
			                 att.settFileType("I");
			              } else if(videoExtList.contains(ext) ) {
			                 att.settFileType("V");
			              } else if(audioExtList.contains(ext)) {
			                 att.settFileType("A");
			              } else {
			                 att.settFileType("E");
			              }
						
						list.add(att);
					}

					// 정상적인 파일 추가 과정
					// 원본 파일 이름 가져오기
					
					try {
						
						f.transferTo(new File(savePath + "/" + originalFileName));
						
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					att.settFileName(originalFileName);
					
					list.set(idx, att);
				}

				idx++;
			}

			int result = talkBoardService.updateTalkBoard(originBoard, list);

			String msg = "";
			String loc = "/talkBoard/talkBoardList.do";

			if (result > 0) {
				msg = "게시글 수정 성공! +_+ ";

			} else {
				msg = "게시글 수정 실패 ㅜ_ㅜ";
			}

			model.addAttribute("msg", msg).addAttribute("loc", loc);

			return "common/util";
		}

		
		
		// 게시글 삭제
		@RequestMapping("/talkBoard/talkBoardDelete.do")
		public String boardDelete(int tno, Model model, HttpSession session) {

			// 게시글에 담긴 첨부파일도 삭제해야한다.
			String savePath = session.getServletContext().getRealPath("/resources/talkUpload");

			List<TalkAttachment> list = talkBoardService.selectAttachment(tno);

			for (TalkAttachment a : list) {

				new File(savePath + "/" + a.gettFileName()).delete();
			}

			int result = talkBoardService.deleteTalkBoard(tno);

			String msg = "";
			String loc = "/talkBoard/talkBoardList.do";

			if (result > 0) {
				msg = "게시글 삭제 성공!";

			} else {
				msg = "게시글 삭제 실패";
			}

			model.addAttribute("msg", msg);
			model.addAttribute("loc", loc);

			return "common/util";
		}
	

}
