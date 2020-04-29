<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title style="font-family:cookierun;">ALICE's MOHAGEE</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:import url="../common/commonUtil.jsp" />

<script>

	$("#tag").tagsinput('items');
	
	function showBoardInsert(){
		location.href = "JQ{ pageContext.request.contextPath}/showBoard/showBoardInsert.do";
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
	.post-title{
  		font-family : bingrae;
  		font-size : 20px;
  		color : black;
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
							<h1 class="title" style="font-family:cookierun;">공연, 뭐 볼까?</h1>
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
					<a href="${ pageContext.request.contextPath }/showBoard/showBoardInsert.do">
						<button type="button" class="btn btn-success" id="writerBtn">글쓰기</button>
					</a>
			</div>
		</c:if>
	</c:if>

	<!-- 태그 리스트  -->
	<div class="row">
			<ul class="masonery-filter text-center" style="font-family:cookierun;">
		<li><a class="btn btn-default active" href="#" data-filter="*">All</a></li>
		<li><a class="btn btn-default" href="#" data-filter=".musical">라이센스</a></li>
		<li><a class="btn btn-default" href="#" data-filter=".create">창작 뮤지컬</a></li>
		<li><a class="btn btn-default" href="#" data-filter=".original">오리지널 내한</a></li>
		<li><a class="btn btn-default" href="#" data-filter=".act">연극</a></li>
	</ul>

<!--  글 목록  -->		
<div class="masonery-items masonery_area">
	<c:forEach var="ShowBoard" items="${list}">
				<!--  글 리스트 1 -->
					<div class="col-md-3 col-sm-4 masonery-item branded mobile ${ ShowBoard.bCategory} ">
						<div class="single-blog two-column">
						
						<!-- 사진파일 -->
						<c:if test="${ fn:trim(ShowBoard.bFileType)  == 'I'}">
							<div class="post-thumb">
								<a href="${ pageContext.request.contextPath }/showBoard/showBoardDetail.do?bNo=${ShowBoard.bNo}">
									<img src="${ pageContext.request.contextPath }/resources/upload/${ShowBoard.titleFilename}" class="img-responsive"></a>				
							</div>
						</c:if>
							<div class="post-content overflow">	
								
								<!--  제목 -->
								<h2 class="post-title bold">
									<a href="${ pageContext.request.contextPath }/showBoard/showBoardDetail.do?bNo=${ShowBoard.bNo}">${ ShowBoard.bTitle }</a>
								</h2>

								<div class="post-bottom overflow">
									<ul class="nav nav-justified post-nav">	
		                                <li id="favorite" style="color: #0099AE"><i class="fas fa-heart"></i>&nbsp;&nbsp;${ ShowBoard.favoriteCount }</li>
		                                  
		                                <li style="color: #0099AE"><i class="fas fa-comments"></i>&nbsp;&nbsp;${ ShowBoard.commentCount }</li>
									</ul>
									<div class="post-content overflow">   
						               <i style="color: #0099AE" class="fas fa-tags"></i>&nbsp;&nbsp;
						               <input id="tag" style="color: #0099AE" type="text" data-role="tagsinput" value="${ ShowBoard.bTag }"/>
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