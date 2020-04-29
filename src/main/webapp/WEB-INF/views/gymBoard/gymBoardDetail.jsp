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
    <title style="font-family:cookierun;">HOWL's MOHAGEE</title>
    
    <c:import url="../common/commonUtil.jsp"/>
    
    
<style>
html, body {
  height: 100%;
}

.wrap {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.btn btn-success {
  width: 140px;
  height: 45px;
  font-family: 'Roboto', sans-serif;
  font-size: 11px;
  text-transform: uppercase;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: #000;
  background-color: #fff;
  border: none;
  border-radius: 45px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
  }

.btn btn-success:hover {
  background-color: #2EE59D;
  box-shadow: 0px 15px 20px rgba(46, 229, 157, 0.4);
  color: #fff;
  transform: translateY(-7px);
}    
    


.facebook-box .footer .write-comment input[type="text"] {
  background: #fff;
  border: 1px solid #dcdee3;
  padding: 7px 7px 7px 5px;
  min-height: 16px;
  width: calc(95% - 32px - 22px);
  float: left;
}
       #board_content{
          width : 1300px;
          border : none;
          font-size : 20px;
          height : auto;
       }
       
       #board_title{
          /* font-family : ; */
          font-size : 30px;
       }
       
	.bootstrap-tagsinput input{
			display: none;
		}
	
	li span[data-role = remove]{
		display: none;
	}
	
	.bootstrap-tagsinput{
		display: initial;
		border: none;
		box-shadow: none;
	}
	
	
	#favorite:hover{
		cursor: pointer;
	}
	.label{
	   font-size: 85%;
	}
	
/* 댓글 css */
#commentDivider {
  width: 120%;
}
#comments-container {
  overflow: auto;
  height: 350px;
}
#comments {
  text-align: -webkit-center;
  list-style-type: none;
  width: 120%;
  padding: 0;
}
#post {
    width: 50%;
    height: 300px;
    /* border: 1px solid; */
    border-radius: 20px;
    margin-left: 25%;
    display: flex;
   background-image: linear-gradient(45deg, #08bb81, #0300e08a);
  
  /* position: relative; */
}
#post h1 {
  margin: auto;
}
.commentButtons {
  display: flex;
  
}
#newCommentButton {
	box-shadow:inset -1px 0px 15px -12px #fce2c1;
	background-color : gold ;
	border-radius:6px;
	border:1px solid #eeb44f;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:cookierun;
	font-size:15px;
	padding:8px 5px;
	text-decoration:none;
	width : 70px;
}
#addComment {
  display: flex;
  justify-content: center;
  height: 40px;
  /* position: initial; */
  margin-top: 2%;
  width: 70%;
  margin-left: 15%;
 
}
#addComment input {
  width: 50%;
  height: 35px;
  border-radius: 20px;
  border:1px solid lightgray;
  background:#ffffffb0;
  padding-left: 10px;
  font-size: 15px;
  
}
/* 대댓글버튼 css */
.commentDiv {
  width: 50%;
  margin-top: 1%;
  display: flex;
  height: 25px;
  font-family:cookierun;
}

.commentDiv button {
  flex: 1;
  margin-left: 5px;
  border-radius: 10px;
  background-color:#faa700;
  border: 1px;
  color: white;
}
.commentDiv input {
  flex: 7;
  border:none;
  border-radius: 20px;
    background:#ffffffb0;
}
.commentContent {
  background: #F9C804;
  width: 60%;
  display: flex;
  justify-content: space-between;
  border-radius: 10px;
  margin-top: 1%;
}
.commentContent p {
  padding-left: 5px;
  font-family:cookierun;
  color : white;
}
.reply {
  background: border-box;
  border: none;
  cursor: pointer;
  color : white;
  font-weight:bold;
  font-family:cookierun;
}

textarea {
      width: 500px;
      height: auto;
      background: center center no-repeat; /* This ruins default border */
      border: none;
      resize: none;
      color: white;
      overflow: auto;
      font-size : 20px;
      font-family:배달의민족 주아;
   }

.post-nav > li {
	cursor : pointer;
}

.media-object{
	width : 120px;
	height : 150px;
	
	
} 
</style>
    
    
</head><!--/head-->

<body>
   
   <c:import url="../common/header.jsp"/>

    <section id="page-breadcrumb">
        <div class="vertical-center sun">
             <div class="container">
                <div class="row">
                    <div class="action">
                        <div class="col-sm-12">
                            <h1 class="title" style="font-family:cookierun;">EDITOR HOWL's GYM BOARD</h1>
                        </div>                                                                                
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--/#page-breadcrumb-->

    <section id="blog-details">
        <div class="container">
            <div class="row">
                <div class="col-md-9 col-sm-7">
                    <div class="row">
  
                    <!--  게시글  -->
                         <div class="col-md-12 col-sm-12">
                            <div class="single-blog blog-details two-column">
                            <h2 class="post-title bold" id="board_title"><a href="#" style="font-family:cookierun; font-size:30px;">${gymBoard.bTitle } <br/><br/></a></h2>


      <!-- 게시글 사진 슬라이드 영역 -->
   <div id="carousel-example-generic" class="carousel slide carousel-fade" data-ride="carousel">

        <ol class="carousel-indicators">
           <c:forEach var="att" items="${GymAttachmentList}" varStatus="status"> 
                 <c:if test="${status.index == 0}">
                     <li data-target="#carousel-example-generic" data-slide-to="${status.index}" class="active"></li>
                </c:if>
                <c:if test="${status.index != 0}">
                   <li data-target="#carousel-example-generic" data-slide-to="${status.index}"></li>
                 </c:if>
              </c:forEach>
        </ol>  
      
        <!-- Wrapper for slides -->
       <div class="carousel-inner" role="listbox">
         <c:forEach var="att" items="${GymAttachmentList}" varStatus="status"> 
              <c:if test="${status.index == 0}">
                   <div class="item active">
                     <img style="width:100%" class="d-block w-100" src="${ pageContext.request.contextPath }/resources/upload/${att.bFileName}" class="img-responsive" alt="slide${status.index }">
                </div>
           </c:if><c:if test="${status.index != 0}">
               <div class="item">
               <img style="width:100%" class="d-block w-100" src="${ pageContext.request.contextPath }/resources/upload/${att.bFileName}" class="img-responsive" alt="slide${status.index }">
        </div>
      </c:if>
        </c:forEach>
        </div>  
        
       
        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev" >
            <span class="icon-prev" aria-hidden="true" ></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="icon-next" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a> 

      </div><br />                               
                          
<%--                    <c:forEach var="GymAttachment" items="${GymAttachmentList}">
                         
                            <!--  게시글 상세보기 영역 -->
                                <div class="post-thumb">
                                    <!-- <a href="#"> -->
                                    <img src="${ pageContext.request.contextPath }/resources/upload/${GymAttachment.bFileName}" class="img-responsive" alt="">
                                    <!-- </a> -->
                                   
                                </div>
                                <hr />
                      </c:forEach>
 --%>                     
		    		<!-- 동영상 버튼 -->	
                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalVM">
                                <i class="fas fa-caret-square-right"></i>&nbsp;&nbsp; PlayVedio</button>
                             	
                             	<!-- 본문 내용 -->
                                <div class="post-content overflow">
                                    <pre class="form-control" id="board_content" style="font-family:cookierun; font-size:30px;">${gymBoard.bContent }</pre><br />
					                                
                                    <div class="post-bottom overflow">
                                        <ul class="nav navbar-nav post-nav">
                                            <li style="color: #0099AE"><i class="fas fa-clock"></i>&nbsp;&nbsp;${gymBoard.bDate}</li>
             
			                                <li style="color: #0099AE"><i class="fas fa-tags"></i>&nbsp;&nbsp;<input id="tag" type="text" data-role="tagsinput" value="${gymBoard.bTag}"/></li>
			                                	
			                                <li id="favorite" style="color: #0099AE"><i class="fas fa-heart"></i>&nbsp;&nbsp;${ favoriteCount }</li>
			                                  
			                                <li style="color: #0099AE"><i class="fas fa-comments"></i>&nbsp;&nbsp;${gymBoard.commentCount}</li>
                                        </ul>
                                    </div>
                                <!-- 게시글 수정 버튼  -->
				<div>
					<a href="${ pageContext.request.contextPath }/gymBoard/gymBoardList.do">
					   <button type="button" class="btn btn-warning" id="rewriteBtn">목록으로</button>
					</a>&nbsp;
					<c:set value="${member.userNo} " var="memberUserNo"/>
			                 <c:set value="${gymBoard.userNo} " var="gymBoardUserNo"/>
			                 <c:if test="${ memberUserNo eq gymBoardUserNo }">                  
					<a href="${ pageContext.request.contextPath }/gymBoard/gymBoardUpdateView.do?bNo=${gymBoard.bNo}">
					   <button type="button" class="btn btn-primary" id="rewriteBtn">수정하기</button>
					</a>&nbsp;
					<a href="${ pageContext.request.contextPath }/gymBoard/gymBoardDelete.do?bNo=${gymBoard.bNo}">
					   <button type="button" class="btn btn-danger" id="deleteBtn">삭제하기</button>
					</a>
					</c:if>
				 </div><br />
                               </div>
                               
                                    <div class="blog-share">                                  
                                        <span class='st_facebook_hcount'></span>
                                        <span class='st_twitter_hcount'></span>
                                        <span class='st_linkedin_hcount'></span>
                                        <span class='st_pinterest_hcount'></span>
                                        <span class='st_email_hcount'></span>                                      
                                    </div>
                                    
                                    <div class="author-profile padding">
                 <div class="row">
                     <div class="col-sm-2">
                         <img src="${ pageContext.request.contextPath }/resources/profile/${gymBoard.pRenamedFileName}">
                    </div>
                    <div class="col-sm-10" style="font-family:binggrae;">
                        <h3 style="font-family:cookierun;">EDITOR&nbsp;&nbsp;${ gymBoard.nickName }</h3>
                        <p style="font-family:cookierun;">${ gymBoard.introduce }</p>
                    </div>
                </div>
            </div>                                     

	    <div id="addComment">
		    <input type="text" placeholder="댓글 달고싶지?" style="font-family:cookierun;"></input>&nbsp;&nbsp;&nbsp;<button id="newCommentButton" onclick="submitNewComment(this)">&nbsp;댓 글&nbsp;</button>
	    </div><br />
	    
   <!--  <hr id="commentDivider"/> -->
        <div class="response-area">
               <ul class="media-list">
               <c:forEach var="gbc" items="${gbcList }">
                   <li class="reply${ gbc.bcLevel }" style="padding-left : ${ gbc.bcLevel * 7 }%" for="${ gbc.bbcNo }">
                       <div class="post-comment">
                           <a class="pull-left" href="${pageContext.request.contextPath}/member/myPage.do?userNo=${gbc.userNo }">
                               <img class="media-object" src="${pageContext.request.contextPath }/resources/profile/${gbc.pRenamedFileName}" alt="">
                           </a>
                           <div class="media-body">
                               <span style="font-family:cookierun;"><a href="${pageContext.request.contextPath}/member/myPage.do?userNo=${gbc.userNo }" style="font-family:cookierun;">${gbc.nickName }이</a>  ${gbc.bcDate }에 작성</span>
                               <br /><br>
                               <textarea class="contentUpdateForm" id="${ gbc.bcNo }" style="color: black;" readonly>${gbc.bcContent }</textarea>
                               <ul class="nav navbar-nav post-nav">
                                   <li style="font-family:cookierun;"><i class="fa fa-wrench" ></i>&nbsp;&nbsp;수정</li>
                                   <li style="font-family:cookierun;"><i class="fa fa-eraser" ></i>&nbsp;&nbsp;삭제</li>
                               </ul>
                           </div>
                       </div>
                       <!-- 대댓글 작성 창 -->
                       <div id="addComment"><input type="text" placeholder="대댓글 달고싶지?" style="font-family:cookierun;"></input>&nbsp;&nbsp;&nbsp;<button id="newCommentButton" onclick="submitNewReplyComment(this, ${ gbc.bcNo })">댓글</button></div>
                       
                   </li>
               </c:forEach>
               </ul>                   
               </div><!--/Response-area-->
               </div>
           </div>
       </div>
   </div>
</div>
</div>


</section>


<!-- ===========동영상 모달 시작 ===========-->
<!--Modal: modalVM-->
<div class="modal fade" id="modalVM" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
  aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <!--Content-->
    <div class="modal-content">
      <!--Body-->
      <div class="modal-body mb-0 p-0">
        <div class="embed-responsive embed-responsive-16by9 z-depth-1-half">
          ${gymBoard.bUrl }
        </div>
      </div>
      <!--Footer-->
      <div class="modal-footer justify-content-center flex-column flex-md-row">
        <span class="mr-4">공유해보라우!</span>
                                    <div class="blog-share">                                    
                                        <span class='st_facebook_hcount'></span>
                                        <span class='st_twitter_hcount'></span>
                                        <span class='st_linkedin_hcount'></span>
                                        <span class='st_pinterest_hcount'></span>
                                        <span class='st_email_hcount'></span>                                   
                                    </div>
        <button type="button" class="btn btn-outline-primary btn-rounded btn-md ml-4"
          data-dismiss="modal">Close</button>
      </div>
    </div>
    <!--/.Content-->
  </div>
</div>
<!--Modal: modalVM-->    
<!-- ===========동영상 모달 끝 ===========-->    
    
<script>
/* 댓글 만들기 스크립트 시작 */
 /**
 	obj : 클릭된 버튼 자신
 */
function submitNewComment(obj){
	$.ajax({
		url : '${ pageContext.request.contextPath }/gbComment/gbCommentInsert.do',
		  data : {
			  bNo : '${gymBoard.bNo}',
			  userNo : '${member.userNo}',
			  bcContent : $(obj).siblings('input').val()
		  }, success : function(data){
			  alert("댓글 추가 성공!");
			  location.href='${pageContext.request.contextPath}/gymBoard/gymBoardDetail.do?bNo=${gymBoard.bNo}';
		  }
	});
}

/**
	obj : 클릭된 버튼 자신
	parentBcNo : 누구의 대댓글인지 확인하기 위한 원본 댓글의 번호
*/
function submitNewReplyComment(obj, parentBcNo){
	$.ajax({
		url : '${ pageContext.request.contextPath }/gbComment/gbCommentInsert.do',
		  data : {
			  bNo : '${gymBoard.bNo}',
			  userNo : '${member.userNo}',
			  bcContent : $(obj).siblings('input').val(),
			  bbcNo : parentBcNo
		  }, success : function(data){
			  alert("댓글 추가 성공!");
			  location.href='${pageContext.request.contextPath}/gymBoard/gymBoardDetail.do?bNo=${gymBoard.bNo}';
		  }
	});
}

$('.fa-wrench').parent().each(function(){
	$(this).on('click', function(){
		var textArea = $(this).parent().parent().children('textarea');
		if(textArea.prop('readonly')){
			textArea.prop('readonly', false);
		} else {
			textArea.prop('readonly', true);
			$.ajax({
				url : '${ pageContext.request.contextPath }/gbComment/gbCommentUpdate.do',
				  data : {
					  bcNo : textArea.attr('id'),
					  userNo : '${member.userNo}',
					  bcContent : textArea.val()
				  }, success : function(data){
					  if(data != 0){
						  alert("댓글 변경 성공!");		
					  } else {
						  alert("댓글 수정 실패!");
					  }
				  }
			});
		}
	});
});

$('.fa-eraser').parent().each(function(){
	$(this).on('click', function(){
		var obj =  $(this);
		var textArea = $(this).parent().parent().children('textarea');
		
		$.ajax({
			url : '${ pageContext.request.contextPath }/gbComment/gbCommentDelete.do',
			  data : {
				  bcNo : textArea.attr('id')
			  }, success : function(data){
				  if(data != 0){
					  alert("댓글 삭제 성공!");		
					  obj.parents('li').remove();
					  $('[for=' + textArea.attr('id') + ']').remove();
				  } else {
					  alert("댓글 삭제 실패!");
				  }
			  }
		});
	});
});

/*
$(function(){
	$.ajax({
		url : '${ pageContext.request.contextPath }/gbComment/gbCommentSelectList.do',
		data : {bNo : '${gymBoard.bNo}'},
		success : function(data){
			
			  for(var i in data){
				  let comments = document.getElementById("comments");
				  createNewComment(data[i].bcContent, data[i].bcNo);
				  while (comments.firstChild) {
					     comments.removeChild(comments.firstChild);
				 }
				  displayComments(listOfComments, 0);
			  };
		  }
	});
});
*/

/* 댓글만들기 스크립트 끝! */
 /* 좋아요 스크립트 시작 */
 
 $(function(){
    	
    	var userNo = "${member.userNo}";
    	var bNo = "${gymBoard.bNo}";
    	
    	$.ajax({
    		url: "${pageContext.request.contextPath}/favorite/checkFavorite",
    		data: {
    			userNo : userNo,
    			bNo : bNo
    		},
    		dataType: "json",
    		async: false,
    		success: function(data){
    			
    			if(data.Favorite.fStatus == 'Y'){
    				$("#favorite").css("color", "red");
    			}
    			
    		}
    	});
    	
   });


 $("#favorite").on("click", function(){
   
   var userNo = "${member.userNo}";
   var bNo = "${gymBoard.bNo}";
   
   $.ajax({
      url: "${pageContext.request.contextPath}/favorite/checkFavorite",
      data: {
         userNo : userNo,
         bNo : bNo
      },
      dataType: "json",
      async: false,
      success: function(data){
              
         var fStatus = data.Favorite.fStatus;
         
         if(fStatus == null || fStatus == 'N'){
            $.ajax({
               url: "${pageContext.request.contextPath}/favorite/doFavorite",
               data: {
                   fStatus : fStatus,
                   userNo : userNo,
                   bNo : bNo
                },
                async: false,
                success: function(data){
                   if(data == 1){
                      alert("좋아요를 눌러주셔서 감사합니다.");
                       $("#favorite").css("color", "red");
                       
                       var text = $('#favorite').text();
                       
                       $.ajax({
                          url: "${pageContext.request.contextPath}/favorite/favoriteNumber",
                          data: {
                             bNo : bNo
                          },
                          async: false,
                          success: function(data){
                             $('#favorite').html('<i class="fas fa-heart"></i>&nbsp;&nbsp;' + data);
                          }
                       });
                   }
                }
            });
         } else {
            $.ajax({
               url: "${pageContext.request.contextPath}/favorite/cancelFavorite",
               data: {
                  userNo : userNo,
                   bNo : bNo
               },
               async: false,
               success: function(data){
                  if(data == 1) {
                     alert("좋아요를 취소하였습니다.");
                       $("#favorite").css("color", "#0099AE");
                       
                       $.ajax({
                          url: "${pageContext.request.contextPath}/favorite/favoriteNumber",
                          data: {
                             bNo : bNo
                          },
                          async: false,
                          success: function(data){
                             $('#favorite').html('<i class="fas fa-heart"></i>&nbsp;&nbsp;' + data);
                          }
                       });
                  }
               }
            });
         }
      }
   });
});
 
 /* 좋아요 스크립트 끝 */
</script>
    
    
    <c:import url="../common/footer.jsp"/>
</body>
</html>