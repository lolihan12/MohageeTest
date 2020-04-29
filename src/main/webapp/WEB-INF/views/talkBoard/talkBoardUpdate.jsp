<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>

   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">   
   <c:import url="../common/commonUtil.jsp"/>
   <style>
      #summernote{
         margin-top:1px; 
         width:900px; 
         height:500px; 
         border : 1px solid lightgrey;
      }
      #input-group mb-3{
         
      }
      
      #board_img{
      	
      	background : rgb(8, 187, 104);
      
      }
      
      #board_video{
         
         background : rgb(223, 67, 152);
      
      }
      
      #board_audio{
         
         background : rgb(8, 143, 233);
      
      }
      
      
      .filebox label { 
      		display: inline-block; 
      		padding: .5em .75em; 
      		color: white; 
      		font-size: inherit; 
      		line-height: normal; 
      		vertical-align: middle; 
      		cursor: pointer; 
      		border: 1px solid #ebebeb; 
      		border-bottom-color: #e2e2e2; 
      		border-radius: .25em; 
      	}
  	
      	
       .filebox input[type="file"] {
       		 position: absolute; 
       		 width: 1px; 
       		 height: 1px; 
       		 padding: 0; 
       		 margin: -1px; 
       		 overflow: hidden; 
       		 clip:rect(0,0,0,0); 
       		 border: 0; 
       	}
       	
       	input[type=file]{
	   display: none;
	}
   
   </style>
   
</head>
<body>   
   <c:import url="../common/header.jsp"/>

    <section id="page-breadcrumb">
        <div class="vertical-center sun">
             <div class="container">
                <div class="row">
                    <div class="action">
                        <div class="col-sm-12">
                            <h1 class="title" style="font-family:cookierun;"> 수다글 수정 </h1>       
                        </div>
                    </div>
                </div>
            </div>
        </div>
   </section>
	<section>
			<form id="updateTalkBoard" action="${ pageContext.request.contextPath }/talkBoard/talkBoardUpdate.do"
					  method="post" enctype="multipart/form-data">
		<div class="mb-2" align="center">
				<input type="hidden" name="tWriter" value="${member.userNo}" />
				<input type="hidden" name="tno" value="${talkBoard.tno}" />

				<!-- 제목 입력 칸 -->
				<div class="input-group mb-3" style="width: 900px"><br /> 
					<input type="text" class="form-control" aria-label="Text input with dropdown button" id="title"
																	name="tTitle" placeholder="제목 입력" value="${talkBoard.tTitle}" >
				</div>
				
		
		<!--  업로드 사진 미리보기 칸 -->
	    <div>
	        <div class="imgs_wrap" style="width:900px">
	            <img id="img" />
	        </div>
	    </div> 
	     
	    
	    <div style="margin-bottom:3px;">
	         <div class="filebox">
	            <label id="board_img" for="ex_file_img" name="upFile">
	               <a href="javascript:" onclick="fileUploadAction();" class="my_button">
	               <i class="fas fa-image"></i>&nbsp;&nbsp;&nbsp;사진 업로드</a></label>
	            <input type="file" id="ex_file_img" name="upFile"  multiple>&nbsp;&nbsp;     
	                     
	          </div>
	   </div>
	    
   </div>

				<!-- 내용 입력칸 -->
				<div class="editorArea" style="margin-top: 5px; text-align: center">
					<textarea id="summernote" name="tContent"
						required placeholder="글 내용" maxlength="1000" rows="7">${talkBoard.tContent}</textarea>
					<span class="help-block"><p id="characterLeft" class="help-block ">더 이상 작성할 수 없습니다.</p></span>
				</div>

				<div align="center">
					<button type="submit" class="btn btn-danger" id="btnSubmit">수정완료</button>
			
					<button type="reset" class="btn btn-danger" onclick="cancelbtn();">수정취소</button>
					
				 </div>
				 <br />
				 
			</form>
		
	</section>
	<br>
	<br>
	<br>
	<br>
	<br>

	<c:import url="../common/footer.jsp" />

    <!-- 최대글 작성 한도 스크립트 구현해야함@ -->
<script>
	
	//이미지 정보들을 담을 배열
	var sel_files = [];
	
	
	$(document).ready(function() {
	    $("#ex_file_img").on("change", handleImgFileSelect);
	}); 
	
	function fileUploadAction() {
	    console.log("fileUploadAction");
	    $("#ex_file_img").trigger('click');
	}
	
	function handleImgFileSelect(e) {
	
	    // 이미지 정보들을 초기화
	    sel_files = [];
	    $(".imgs_wrap").empty();
	
	    var files = e.target.files;
	    var filesArr = Array.prototype.slice.call(files);
	
	    var index = 0;
	    filesArr.forEach(function(f) {
	        if(!f.type.match("image.*")) {
	            alert("확장자는 이미지 확장자만 가능합니다.");
	            return;
	        }
	
	        sel_files.push(f);
	
	        var reader = new FileReader();
	        reader.onload = function(e) {
	            var html = "<a href=\"javascript:void(0);\" onclick=\"deleteImageAction(" + index + ")\" id=\"img_id_" + 
	                           index + "\"><img src=\"" + e.target.result + "\" data-file='" + 
	                           f.name + "' class='selProductFile' title='Click to remove'></a>";
	            $(".imgs_wrap").append(html);
	            index++;
	
	        }
	        reader.readAsDataURL(f);
	        
	    });
	}
	
	
	
	/**************************************************************************************/
	   $(document).ready(function(){
	       $('#characterLeft').text('1000 자 작성가능');
	       $('#summernote').keydown(function () {
	           var max = 1000;
	           var len = $(this).val().length;
	           if (len >= max) {
	               $('#characterLeft').text('더 이상 작성할 수 없습니다.');
	               $('#characterLeft').addClass('red');
	               $('#btnSubmit').addClass('disabled');
	           }
	           else {
	               var ch = max - len;
	               $('#characterLeft').text(ch + ' 자 작성가능');
	               $('#btnSubmit').removeClass('disabled');
	               $('#characterLeft').removeClass('red');
	           }
	       });
	   });
	   /*-----------------------------------------------------------------------------------------------------------*/
	
	      
	   /* ----------------------------------------------------------------- */
	      /* function LoadImg(value) {
	         
	          if(value.files) {
	             
	             var reader = new FileReader();
	             
	             reader.onload = function(e){
	               var fileValue = $("#ex_file_img").val().split("\\");
	                var fileName = fileValue[fileValue.length-1]; // 파일명
	                
	               $("#fileName").attr("value", fileName);
	                
	        console.log(fileName); 
	             }
	             
	             reader.readAsDataURL(value.files[0]);
	          }
	          
	          if(value.files && value.files[0]) {
	            
	            var reader = new FileReader();
	            reader.onload = function(e){
	               $('#titleImg').attr('src', e.target.result);
	            }
	            reader.readAsDataURL(value.files[0]);
	         }
	       } */
	   /* 업로드 이미지 미리보기  */
	
	</script>


    <c:import url="../common/footer.jsp"/>
</body>
</html>