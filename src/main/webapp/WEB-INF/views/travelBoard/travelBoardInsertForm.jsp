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
         height:300px; 
         border : 1px solid lightgrey;
      }
/*       #input-group mb-3{
         
      }
       */
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
          

/* named upload */
/* .filebox .upload-name {
    display: inline-block;
    padding: .5em .75em;
    font-size: inherit;
    font-family: inherit;
    line-height: normal;
    vertical-align: middle;
    background-color: #f5f5f5;
  border: 1px solid #ebebeb;
  border-bottom-color: #e2e2e2;
  border-radius: .25em;
  -webkit-appearance: none; /* 네이티브 외형 감추기 
  -moz-appearance: none;
  appearance: none;
} */

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
                            <h1 class="title" style="font-family:cookierun;">EDITOR  JJUUU's Travel BOARD </h1>       
                        </div>
                    </div>
                </div>
            </div>
        </div>
   </section>

<section>
<div class="mb-2" align="center">
   <form id="insertForm" 
         action="${ pageContext.request.contextPath }/travelBoard/travelBoardInsertEnd.do"  
         method="post" enctype="multipart/form-data">
      <input type="hidden" name="userNo"  value="${member.userNo}"/>
      <input type="hidden" name="bKind" value="T"/>
      
      <!-- 카테고리선택 칸 -->
      <select id = "bCategory" name="bCategory" class="input-group mb-3" style="width:900px; border:1px solid lightgrey;" required >
         <option value="" disabled selected >카테고리를 선택</option>
         <option value="korea" >국내</option>
         <option value="asia" >아시아</option>
         <option value="america" >아메리카</option>
         <option value="europe" >유럽</option>
         <option value="oceania" >오세아니아</option>
         <option value="africa" >아프리카</option>
      </select>
      
      <!-- 위치 선택  -->
	  <select id = "mNo" name="mNo" class="input-group mb-3" style="width:900px; border:1px solid lightgrey; color:gray;" required>
         <option disabled selected >위치선택</option>
         <option value="1" >Seoul</option>
         <option value="2" >Suwon</option>
         <option value="3" >Busan</option>
         <option value="4" >Jeonju</option>
         <option value="5">Jeju</option>
         <option value="6">Las Vegas</option>
         <option value="7">Valley of Fire</option>
         <option value="8">Death Valley</option>
         <option value="9">Grand Canyon National Park</option>
         <option value="10">Antelope Canyon</option>
         <option value="11">Los Angeles</option>
         <option value="12">San Francisco</option>
         <option value="13">San Diego</option>
         <option value="14">Seattle</option>
         <option value="15">Chicago</option>
         <option value="16">NewYork</option>
         <option value="17">Singapore</option>
         <option value="18">Bali</option>
         <option value="19">Taipei</option>
         <option value="20">Osaka</option>
	</select>
            
      
      <!-- 제목 입력 칸 -->
      <div class="input-group mb-3" style="width:900px" ><br />
          <input type="text" class="form-control" aria-label="Text input with dropdown button" 
              id = "title" name="bTitle" placeholder="제목 입력" required>
      </div>      

      <!-- 태그 입력 칸 -->
      <div class="input-group mb-3" style="display: table-cell; text-align: center; vertical-align: middle; width:900px;"><br />
              <input  type="text" class="form-control" aria-label="Text input with dropdown button" 
              id = "tag" name="bTag" placeholder="태그를 입력하세요 (최대 5개)" required>
      </div>
           <!-- URL 입력 칸 -->
      <div class="input-group mb-3" style="width:900px;"><br />
              <input type="url" class="form-control" aria-label="Text input with dropdown button" 
              id = "bUrl" name="bUrl" placeholder="link입력 ex) www.naver.com">
      </div>
      
<!--  업로드 사진 미리보기 칸 -->
    <div>
        <div class="imgs_wrap" style="width:900px">
            <img id="img" />
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
       <textarea  id="summernote" name="bContent" placeholder="글 내용" maxlength="1000" rows="7" required></textarea>
      <span class="help-block"><p id="characterLeft" class="help-block ">더 이상 작성할 수 없습니다.</p></span>
      </div>

   <div align="center">
      <a href="${ pageContext.request.contextPath }/travelBoard/travelBoardList.do">
      <button type="button" class="btn btn-warning" id="listBtn" style="width:200px;">목록</button></a>&nbsp;
      <button type="submit" class="btn btn-primary" id="btnSubmit" style="width:200px;">작성</button>&nbsp;
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
	    return '#' + item;
	},
	
	cancelConfirmKeysOnEmpty: false
	
});

$('#tag').on('itemAddedOnInit', function(event) {
	  return '#' + event.item.label;
});

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