<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title style="font-family:cookierun;">HOWL's MOHAGEE</title>

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
            background-color: blue; 
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
                            <h1 class="title" style="font-family:cookierun;">EDITOR HOWL's GYM BOARD 수정하기</h1>       
                        </div>
                    </div>
                </div>
            </div>
        </div>
   </section>

<section>
<div class="mb-2" align="center">
   <form id="insertForm" 
         action="${ pageContext.request.contextPath }/gymBoard/gymBoardUpdateEnd.do"  
         method="post" enctype="multipart/form-data">
      <input type="hidden" name="userNo"  value="${member.userNo}"/>
      <input type="hidden" name="bNo"  value="${gymBoard.bNo}"/>


      <!-- 제목 입력 칸 -->
      <div class="input-group mb-3" style="width:900px" >
         <br />
              <input type="text" class="form-control" aria-label="Text input with dropdown button" 
              id = "title" name="bTitle" placeholder="제목 입력" value="${gymBoard.bTitle}" required>
      </div>

     
      <!-- 태그 입력 칸 -->
      <div class="input-group mb-3" style="display: table-cell; text-align: center; vertical-align: middle; width:900px;"><br />
              <input value="${ gymBoard.bTag }" type="text" class="form-control" aria-label="Text input with dropdown button" 
              id = "tag" name="bTag" placeholder="최대 5개" required>
      </div>
      
           <!-- URL 입력 칸 -->
      <div class="input-group mb-3" style="width:900px;">
         <br />
              <input type="text" class="form-control" 
              aria-label="Text input with dropdown button" 
              id = "bUrl" name="bUrl" placeholder="유튜브-공유-퍼가기-ifram코드 넣기" value="${gymBoard.bUrl }">
      </div>

      <!-- 카테고리선택 칸 -->
      <select id = "bCategory" name="bCategory" class="input-group mb-3 required" style="width:900px" value="${gymBoard.bCategory }" required>
      	<option value="" style="font-family:cookierun;"disabled selected >카테고리를 선택해주세요 (필수!)</option>
	<option value="1" style="font-family:cookierun;">쇠질</option>
	<option value="2" style="font-family:cookierun;">홈트레이닝</option>
	<option value="3" style="font-family:cookierun;">요가&필라테스</option>
	<option value="4" style="font-family:cookierun;">철인삼종</option>
	<option value="5" style="font-family:cookierun;">식단</option> 
      </select>
      
      
      <br />

   <div>
        <div class="imgs_wrap" style="width:900px">
        <c:forEach items="${GymAttachmentList}" var="att">
            <img onclick="fileDelete(this, ${att.bFileNo}, '${att.bFileName}');" id="img" src="${ pageContext.request.contextPath }/resources/upload/${att.bFileName}"/>
            <!--  이미지 개별 삭제  -->
            <!-- <button type="button" class="btn btn-danger" >X</button> -->
        </c:forEach>
        </div>
    </div> 
      
 
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





<!--       <div class="form-group">
          <textarea class="form-control" type="textarea" id="tbcontent" name="tbContent" placeholder="글 내용" maxlength="1000" rows="7"></textarea>
          <span class="help-block"><p id="characterLeft" class="help-block ">더 이상 작성할 수 없습니다.</p></span>
      </div>
 -->
   <!-- 내용 입력칸 -->
      <div class="editorArea"  style="margin-top:5px;">
              <textarea id="summernote" name="bContent" placeholder="글 내용" maxlength="1000" rows="7" required>${gymBoard.bContent }"</textarea>
              <span class="help-block"><p id="characterLeft" class="help-block " >더 이상 작성할 수 없습니다.</p></span>
      </div>
            

<div align="center">
<button type="reset" class="btn btn-danger" onclick="cancelbtn();">수정취소</button>
&nbsp;&nbsp;&nbsp;
<button type="submit" class="btn btn-primary" id="btnSubmit">수정완료</button>
</div>
</form>
            
</div>
</section>
<br ><br ><br ><br ><br >


<c:import url="../common/footer.jsp" />

    <!-- 최대글 작성 한도 스크립트 구현해야함@ -->
<script>

$("#tag").tagsinput({
	   maxTags: 5,
	   
	   itemText: function(item) {
		   return "#" + item;
	   },
	   
	   cancelConfirmKeysOnEmpty: false
	   
	});
	
	
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
		  url : '${pageContext.request.contextPath}/board/gymFileDelete.do',
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
   
</script>




    <c:import url="../common/footer.jsp"/>
</body>
</html>