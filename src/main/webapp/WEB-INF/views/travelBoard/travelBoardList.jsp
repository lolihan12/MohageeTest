<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title style="font-family:cookierun;">JJUUU's MOHAGEE</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:import url="../common/commonUtil.jsp" />

<script>

	$("#tag").tagsinput('items');

   function travelBoardInsert(){
      location.href = "JQ{ pageContext.request.contextPath}/travelBoard/travelBoardInsertForm.do";
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
	body{
	font-family : cookierun;
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
                     <h1 class="title" style="font-family:cookierun;">어딜 가볼까?</h1>
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
   
   <c:if test = "${ userGrade eq 'E' }">
   <div align="right">
      <c:if test="${ !empty member}">
         <a href="${ pageContext.request.contextPath }/travelBoard/travelBoardInsertForm.do" style="font-family:cookierun;">
            <button type="button" class="btn btn-success" id="writerBtn" >글쓰기</button>
         </a>
      </c:if>
   </div>
      </c:if>
      <!-- 태그 리스트  -->
      <div class="row">
            <ul class="masonery-filter text-center">
               <li><a class="btn btn-default active" href="#" data-filter="*" style="font-family:cookierun;">ALL</a></li>
               <li><a class="btn btn-default" href="#" data-filter=".korea" style="font-family:cookierun;">국내</a></li>
               <li><a class="btn btn-default" href="#" data-filter=".asia" style="font-family:cookierun;">아시아</a></li>
               <li><a class="btn btn-default" href="#" data-filter=".america" style="font-family:cookierun;">아메리카</a></li>
               <li><a class="btn btn-default" href="#" data-filter=".europe" style="font-family:cookierun;">유럽</a></li>
               <li><a class="btn btn-default" href="#" data-filter=".oceania" style="font-family:cookierun;">오세아니아</a></li>
               <li><a class="btn btn-default" href="#" data-filter=".africa" style="font-family:cookierun;">아프리카</a></li>
            </ul>
            


         <!--  글 목록  -->      
         <div class="masonery-items masonery_area">
         
         
            <c:forEach var="travelBoard" items="${list}">
            
         
            <!--  글 리스트 1 -->
               <div class="col-md-3 col-sm-4 masonery-item branded mobile ${travelBoard.bCategory}">
                  <div class="single-blog two-column">
                  
                  <!-- 사진파일 -->
                  <c:if test="${ fn:trim(travelBoard.bFileType)  == 'I'}">
                     <div class="post-thumb">
                        <a href="${ pageContext.request.contextPath }/travelBoard/travelBoardDetail.do?bNo=${travelBoard.bNo}">
                           <img src="${ pageContext.request.contextPath }/resources/upload/${travelBoard.titleFilename}" class="img-responsive"></a>                  
                     </div>
                     </c:if>
                     
                     <!-- 태그  -->
                     <div class="post-content overflow">                        
                        <!--  제목 -->
                        <h2 class="post-title bold">
                           <a href="${ pageContext.request.contextPath }/travelBoard/travelBoardDetail.do?bNo=${travelBoard.bNo}"
                           style="font-family:cookierun;">${ travelBoard.bTitle }</a>
                        </h2>

                        <div class="post-bottom overflow">
                           <ul class="nav nav-justified post-nav">	
		                      <li id="favorite" style="color: #0099AE"><i class="fas fa-heart"></i>&nbsp;&nbsp;${ travelBoard.favoriteCount }</li>
		                                  
		                      <li style="color: #0099AE"><i class="fas fa-comments"></i>&nbsp;&nbsp;${ travelBoard.commentCount }</li>
							</ul>
							<div class="post-content overflow">   
				               <i style="color: #0099AE" class="fas fa-tags"></i>&nbsp;&nbsp;
				               <input id="tag" style="color: #0099AE" type="text" data-role="tagsinput" value="${ travelBoard.bTag }"/>
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