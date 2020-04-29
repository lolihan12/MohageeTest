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
<c:import url="../common/commonUtil.jsp" />

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
   
     .post-title{
     font-family : bingrae;
     font-size : 20px;
     color : black;
  }
  
	.title{
		display: inline;
		width: 30px;
	}
	
	div.searchInput{
		width: 550px;
	}
	
	#searchInput{
		display: inline;
		margin: auto;
		width: 500px;
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
                     <!-- 검색 창 -->
		                     <form action="${ pageContext.request.contextPath }/search/formSearch.do">
		                     <h1 class="title" style="margin-right: 50px; font-family:cookierun;">검색</h1>
						     <select style="font-size: 20px; height: 35px;" name="select" id="searchSelect" required>
						     	<option value="" disabled selected>검색 범위</option>
						     	<option value="1">전체검색</option>
						     	<option value="2">제목+내용 검색</option>
						     	<option value="3">제목검색</option>
						     	<option value="4">내용검색</option>
						     	<option value="5">태그검색</option>
						     </select>
						     <input name="searchInput" id="searchInput" class="form-control mr-sm-2" type="text" placeholder="내용 입력" 
						     style="font-family:cookierun; font-size : 15px;"  aria-label="Search">
						     <button onclick="formSearch();" class="btn btn-success btn-rounded btn-sm my-0" type="submit">Search</button>
					     </form>
					<!--  검색 창 끝 -->
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
   
   <div class="row">
	<ul class="masonery-filter text-center" style="font-family:cookierun;">
		<li><a class="btn btn-default active" href="#" data-filter="*">All</a></li>
		<li><a class="btn btn-default" href="#" data-filter=".show">공연</a></li>
		<li><a class="btn btn-default" href="#" data-filter=".gym">운동</a></li>
		<li><a class="btn btn-default" href="#" data-filter=".travel">여행</a></li>
	</ul>
   
   
   <!--  글 목록  -->      
   <div id="searchForm" class="masonery-items masonery_area" style="height: auto;">

	   <c:forEach var="searchList" items="${indexSearchList}">
	   <c:set value="${searchList.bKind}" var="sListKind"></c:set>
	   <!--  글 리스트 1 -->
	      <div class="col-md-3 col-sm-4 masonery-item ${ sListKind eq 'G ' ? 'gym' : sListKind eq 'T ' ? 'travel' : 'show' }">
	         <div class="single-blog two-column">
	         
	         <!-- 사진파일 -->
	         <c:if test="${ fn:trim(searchList.bFileType)  == 'I'}">
	            <div class="post-thumb">
	            	<c:if test="${ sListKind eq 'S ' }">
	               <a href="${ pageContext.request.contextPath }/showBoard/showBoardDetail.do?bNo=${searchList.bNo}">
	                  <img src="${ pageContext.request.contextPath }/resources/upload/${searchList.titleFilename}" class="img-responsive"></a>            
	                </c:if>
	                <c:if test="${ sListKind eq 'T ' }">
	               <a href="${ pageContext.request.contextPath }/travelBoard/travelBoardDetail.do?bNo=${searchList.bNo}">
	                  <img src="${ pageContext.request.contextPath }/resources/upload/${searchList.titleFilename}" class="img-responsive"></a>            
	                </c:if>
	                <c:if test="${ sListKind eq 'G ' }">
	               <a href="${ pageContext.request.contextPath }/gymBoard/gymBoardDetail.do?bNo=${searchList.bNo}">
	                  <img src="${ pageContext.request.contextPath }/resources/upload/${searchList.titleFilename}" class="img-responsive"></a>            
	                </c:if>
	            </div>
	         </c:if>
	         <br />         
	            
	         <!--  제목 -->
	         <p class="post-title bold">
	         <c:if test="${ sListKind eq 'S ' }">
	            <a href="${ pageContext.request.contextPath }/showBoard/showBoardDetail.do?bNo=${searchList.bNo}">
	               ${ searchList.bTitle }
	            </a>
	         </c:if>
	         <c:if test="${ sListKind eq 'T ' }">
	            <a href="${ pageContext.request.contextPath }/travelBoard/travelBoardDetail.do?bNo=${searchList.bNo}">
	               ${ searchList.bTitle }
	            </a>
	         </c:if>
	         <c:if test="${ sListKind eq 'G ' }">
	            <a href="${ pageContext.request.contextPath }/gymBoard/gymBoardDetail.do?bNo=${searchList.bNo}">
	               ${ searchList.bTitle }
	            </a>
	         </c:if>
	         </p>
	         
	         
	   <!-- 좋아요, 댓글수 -->   
	       <div class="post-bottom overflow">
	         <ul class="nav nav-justified post-nav">   
	                       <li id="favorite" style="color: #0099AE"><i class="fas fa-heart"></i>&nbsp;&nbsp;${ searchList.favoriteCount }</li>
	                       <li style="color: #0099AE"><i class="fas fa-comments"></i>&nbsp;&nbsp;댓글 숫자</li>
	         </ul>       
	         
	            <!-- 태그  -->
	            <div class="post-content overflow">   
	               <i style="color: #0099AE" class="fas fa-tags"></i>&nbsp;&nbsp;
	               <input id="tag" style="color: #0099AE" type="text" data-role="tagsinput" value="${ searchList.bTag }"/>
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
   
	<script>
	
		$("#tag").tagsinput('items');
		
	</script>
   
</body>
</html>












