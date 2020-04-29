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

.my_button{
   display: inline-block;
   text-align: center;
   color: #fff;
   text-decoration: none;
   border-radius: 5px;
}

.imgs_wrap{
   border: 1px solid lightgrey;
   margin-top: 30px;
   margin-bottom: 30px;
   padding-top: 10px;
   padding-bottom: 10px
}

.imgs_wrap img{
   max-width: 150px;
   margin-left: 10px;
   margin-right: 10px;
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
                            <h1 class="title" style="font-family:cookierun;">게시글 수정</h1>       
                        </div>
                    </div>
                </div>
            </div>
        </div>
   </section>

	<section>
		<div class="mb-2" align="center">
			<form id="updateShowBoard" action="${ pageContext.request.contextPath }/showBoard/showBoardUpdate.do"
					  method="post" enctype="multipart/form-data">
				<input type="hidden" name="userNo" value="${member.userNo}" />
				<input type="hidden" name="bNo" value="${ShowBoard.bNo}" />

    <!-- 카테고리선택 칸 -->
      <!-- 카테고리선택 칸 -->
      <select id = "bCategory" name="bCategory" class="input-group mb-3" style="width:900px; border:1px solid lightblue; font-family: bingrae;" required>
		   <option value="" disabled selected >카테고리를 선택해주세요 (필수!)</option>
		   <option value="musical" >라이센스</option>
		   <option value="create" >창작 뮤지컬</option>
		   <option value="original" >오리지널 내한</option>
		   <option value="act" >연극</option>
      </select><br />

	 <!-- 공연장 위치 선택  -->
	<select id = "cNo" name="cNo" class="input-group mb-3" style="width:900px; border:1px solid lightgrey; color:gray; font-family: bingrae;" >
         <option value="" disabled selected >공연장선택 (필수!)</option>
         <option value="1" >LG아트센터</option>
         <option value="2" >블루스퀘어 인터파크홀</option>
         <option value="3" >예술의전당</option>
         <option value="4" >세종문화회관</option>
         <option value="5">샤롯데씨어터</option>
         <option value="6">디큐브아트센터</option>
         <option value="7">충무아트센터</option>
         <option value="8">두산아트센터</option>
         <option value="9">대학로 유니플렉스</option>
         <option value="10">동양예술극장</option>
         <option value="11">한전아트센터</option>
         <option value="12">광림아트센터</option>
         <option value="13">대학로 틴틴홀</option>
         <option value="14">서경대학교 스콘1관</option>
         <option value="15">아트원씨어터</option>
	</select>

				<!-- 제목 입력 칸 -->
	<div class="input-group mb-3" style="width: 900px"><br /> 
		<input type="text" class="form-control" aria-label="Text input with dropdown button" id="title"
														name="bTitle" placeholder="제목 입력" value="${ShowBoard.bTitle}" >
	</div>

				<!-- 태그 입력 칸 -->
				<div class="input-group mb-3" style="display: table-cell; text-align: center; vertical-align: middle; width:900px;"><br />
              <input value="${ ShowBoard.bTag }" type="text" class="form-control" aria-label="Text input with dropdown button" 
              id = "tag" name="bTag" placeholder="최대 5개" required>
      </div>
				
				<!-- URL 입력 칸 -->
       <div class="input-group mb-3" style="width:900px;">
         <br />
              <input type="text" class="form-control" 
              aria-label="Text input with dropdown button" 
              id = "bUrl" name="bUrl" placeholder="link입력 ex) www.naver.com">
      </div><br />

				<!--  업로드 사진 미리보기 칸 -->
<div>
    <div class="imgs_wrap" style="width:900px">
    <c:forEach items="${attachmentList }" var="att">
        <img onclick="fileDelete(this, ${att.bFileNo}, '${att.bFileName}');" id="img" src="${ pageContext.request.contextPath }/resources/upload/${att.bFileName}"/>
        <!--  이미지 개별 삭제  -->
        <!-- <button type="button" class="btn btn-danger" >X</button> -->
    </c:forEach>
    </div>
</div>           
      
<!--  이미지 미리보기  -->
   <div style="margin-bottom:3px;">
         <div class="filebox">
            <label id="board_img" for="ex_file_img" name="upFile">
               <a href="javascript:" onclick="fileUploadAction();" class="my_button">
               <i class="fas fa-image"></i>&nbsp;&nbsp;&nbsp;사진 업로드</a></label>
            <input type="file" id="ex_file_img" name="upFile"  multiple>&nbsp;&nbsp;

            <label id="board_video" for="ex_file_video">
               <i class="fas fa-video"></i>&nbsp;&nbsp;&nbsp;영상 업로드</label>
            <input type="file" id="ex_file_video" name="upFile">&nbsp;&nbsp;         
                             
            <label id="board_audio" for="ex_file_audio">
               <i class="fas fa-headphones"></i>&nbsp;&nbsp;&nbsp;오디오 업로드</label>
            <input type="file" id="ex_file_audio" name="upFile">&nbsp;&nbsp;              
          </div>
   </div>

		<!-- 내용 입력칸 -->
      <div class="editorArea"  style="margin-top:5px;">
       <textarea  id="summernote" name="bContent" placeholder="글 내용" maxlength="1000" rows="7" required>${ShowBoard.bContent}</textarea>
      <span class="help-block"><p id="characterLeft" class="help-block ">더 이상 작성할 수 없습니다.</p></span>
      </div>

	<!--  수정, 삭제 버튼  -->
	<div align="center">
		<button type="submit" class="btn btn-danger" id="btnSubmit">수정완료</button>

	<input type="button" class="btn btn-warning" value="삭제하기" 
		onclick="location.href='${pageContext.request.contextPath}/showBoard/showBoardDelete.do?bNo=${ShowBoard.bNo}'"/>
	 </div>
	 <br />			 
			</form>
		</div>
	</section>
	<br>
	<br>
	<br>
	<br>
	<br>

	<c:import url="../common/footer.jsp" />

<script>

/* 태그 구현 */
$("#tag").tagsinput({
	   maxTags: 5,
	   
	   itemText: function(item) {
		   return "#" + item;
	   },
	   
	   cancelConfirmKeysOnEmpty: false
	   
});

/* -------------------------------------------------------------- */
 
 /* 이미지 수정 구현 */
 
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
function fileDelete(obj, attNo, attFile){
	  $.ajax({
		  url : '${pageContext.request.contextPath}/board/showFileDelete.do',
		  data : {attNo : attNo, attFile : attFile},
		  dataType : 'json',
		  success : function(data){
			  if(data ==true){
				  alert('삭제 완료!');
				  obj.remove();
				  obj.prev().remove();
			  }
		  }, error : function(data){
			  console.log(data);
		  }
	  });
}
   /* -------------------------------------------------------------- */   
 
 
 /* 게시글 1000자 이상 작성 금지 구현 */
   $(document).ready(function() {
      $('#characterLeft').text('1000 자 작성가능');
      $('#summernote').keydown(function() {
         var max = 1000;
         var len = $(this).val().length;
         if (len >= max) {
            $('#characterLeft').text('더 이상 작성할 수 없습니다.');
            $('#characterLeft').addClass('red');
            $('#btnSubmit').addClass('disabled');
         } else {
            var ch = max - len;
            $('#characterLeft').text(ch + ' 자 작성가능');
            $('#btnSubmit').removeClass('disabled');
            $('#characterLeft').removeClass('red');
         }
      });
   });
   /* -------------------------------------------------------------- */
</script>



    <c:import url="../common/footer.jsp"/>
</body>
</html>