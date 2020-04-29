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
<c:import url="../common/commonUtil.jsp" />

<script>

	$("#tag").tagsinput('items');

	function gymBoardInsert(){
	   location.href = "JQ{ pageContext.request.contextPath}/gymBoard/gymBoardInsertForm.do";
	}
   
	
   
   
</script>

<style>
	.bootstrap-tagsinput input{
		display: none;
	}
	
	span[data-role = remove]{
		display: none;
	}
	
	.bootstrap-tagsinput{
		display: initial;
		border: none;
		box-shadow: none;
	}
	.label{
		font-size: 85%;
	}
</style>

</head>
<body>
   <c:import url="../common/header.jsp" />

   <section id="page-breadcrumb">
      <div class="vertical-center sun">
         <div class="container">
            <div class="row">
               <div class="action">
                  <div class="col-sm-12">
                     <h1 class="title" style="font-family:cookierun;">어떤 운동 해볼까?</h1>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </section>
   <!--/#action-->


<!-- 글 목록 폼 시작 -->
<section id="portfolio" class="padding-top padding-bottom">
   <div class="container">
   
   <!--  글쓰기 버튼  -->
   
   <c:set value="${ member.userGrade }" var="userGrade"/>
   
      <c:if test="${ !empty member}">
   <c:if test = "${ userGrade eq 'E' }">
   <div align="right">
         <a href="${ pageContext.request.contextPath }/gymBoard/gymBoardInsertForm.do">
            <button type="button" class="btn btn-success" id="writerBtn" style="font-family:cookierun;">글쓰기</button>
         </a>
   </div>
   </c:if>
      </c:if>
      
      <!-- 태그 리스트  -->
      <div class="row">
            <ul class="masonery-filter text-center">
               <li><a class="btn btn-default active" href="#" data-filter="*" style="font-family:cookierun;">All</a></li>
               <li><a class="btn btn-default" href="#" data-filter=".1" style="font-family:cookierun;">쇠질</a></li>
               <li><a class="btn btn-default" href="#" data-filter=".2" style="font-family:cookierun;">홈트레이닝</a></li>
               <li><a class="btn btn-default" href="#" data-filter=".3" style="font-family:cookierun;">요가&필라테스</a></li>
               <li><a class="btn btn-default" href="#" data-filter=".4" style="font-family:cookierun;">철인삼종</a></li>
               <li><a class="btn btn-default" href="#" data-filter=".5" style="font-family:cookierun;">식단</a></li>
            </ul>
            


         <!--  글 목록  -->      
         <div class="masonery-items masonery_area">
            <c:forEach var="gymBoard" items="${list}">
         
            <!--  글 리스트 1 -->
               <div class="col-md-3 col-sm-4 masonery-item branded mobile ${gymBoard.bCategory}">
                  <div class="single-blog two-column">
                  
                  <!-- 사진파일 -->
                  <c:if test="${ fn:trim(gymBoard.bFileType)  == 'I'}">
                     <div class="post-thumb">
                        <a href="${pageContext.request.contextPath }/gymBoard/gymBoardDetail.do?bNo=${gymBoard.bNo}">
                           <img src="${pageContext.request.contextPath }/resources/upload/${gymBoard.titleFilename}"
                                 class="img-responsive" alt=""></a>                  
                     </div>
                     </c:if>
                     <!-- 태그  -->
                     <div class="post-content overflow">
                        <!--  제목 -->
                        <h2 class="post-title bold">
                           <a href="${ pageContext.request.contextPath }/gymBoard/gymBoardDetail.do?bNo=${gymBoard.bNo}"
                           style="font-family:cookierun;">${ gymBoard.bTitle }</a>
                        </h2>

                        <div class="post-bottom overflow">
                           <ul class="nav nav-justified post-nav">	
		                      <li id="favorite" style="color: #0099AE"><i class="fas fa-heart"></i>&nbsp;&nbsp;${ gymBoard.favoriteCount }</li>
		                                  
		                      <li style="color: #0099AE"><i class="fas fa-comments"></i>&nbsp;&nbsp;${ gymBoard.commentCount }</li>
							</ul>
							<div class="post-content overflow">   
				               <i style="color: #0099AE" class="fas fa-tags"></i>&nbsp;&nbsp;
				               <input id="#tag" style="color: #0099AE" type="text" data-role="tagsinput" value="${ gymBoard.bTag }"/>
				            </div>
                        </div>
                     </div>
                  </div>
               </div>
            </c:forEach>
            </div>            
            
            
         </div>
      </div>

   </section>
   <c:import url="../common/footer.jsp" />
</body>
</html>