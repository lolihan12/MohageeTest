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
    <title>수다방</title>
    
    <c:import url="../common/commonUtil.jsp"/>

</head><!--/head-->

<style>
    	#board_content{
    		width : 1300px;
    		border : none;
    		font-size : 20px;
    		height : auto;
    			font-family:cookierun;
    	}
    	
    	#board_title{
    		/* font-family : ; */
    		font-size : 30px;
    			font-family:cookierun;
    	}
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
</style>

<body>

	<c:import url="../common/header.jsp"/>

	
    <section id="page-breadcrumb">
        <div class="vertical-center sun">
             <div class="container">
                <div class="row">
                    <div class="action">
                        <div class="col-sm-12">
                            <h1 class="title" style="font-family:cookierun;">게시글 상세보기</h1>
                        </div>                                                                                
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--/#page-breadcrumb-->

    <section id="blog-details" class="padding-top">
        <div class="container">
            <div class="row">
                <div class="col-md-9 col-sm-7">
                    <div class="row">
                    
                    <!-- 게시글 -->
                         <div class="col-md-12 col-sm-12">
                            <div class="single-blog blog-details two-column">
                            
                       <c:forEach var="talkAttachment" items="${talkAttachmentList}">
                            <!-- 이미지 영역 -->
                                <div class="post-thumb">
                                   	<img src="${ pageContext.request.contextPath }/resources/talkUpload/${talkAttachment.tFileName}" class="img-responsive" alt="">
                                	<br />
                                </div>
                          </c:forEach>      
                                
                                <div class="post-content overflow">
                                    <h2 class="post-title bold"><p style="font-family:cookierun;">${talkBoard.tTitle} </p></h2>
                                    <pre class="form-control" id="board_content" name="bContent"> ${talkBoard.tContent} </pre>
                                    <div class="post-bottom overflow">
                                        <ul class="nav navbar-nav post-nav">
                                         	<li><a href="#"><i class="far fa-clock" style="font-family:cookierun;"></i>&nbsp;&nbsp;${talkBoard.tDate}</a></li>
                                        </ul>
                                        
                                        <br /><br /><br />
                  <div>
					<a href="${ pageContext.request.contextPath }/talkBoard/talkBoardList.do">
					   <button type="button" class="btn btn-warning" id="rewriteBtn">목록으로</button>
					</a>&nbsp;    
					
					 <c:if test="${member.userNo eq talkBoard.tWriter}">                       
					<a href="${ pageContext.request.contextPath }/talkBoard/talkUpdate.do?tno=${talkBoard.tno}">
					   <button type="button" class="btn btn-primary" id="rewriteBtn">수정하기</button>
					</a>&nbsp;
					<a href="${ pageContext.request.contextPath }/talkBoard/talkBoardDelete.do?tno=${talkBoard.tno}">
					   <button type="button" class="btn btn-danger" id="deleteBtn">삭제하기</button>
					</a>
					</c:if>
					
				 </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                 </div>
             
            </div>
        </div>
    </section>
    
    <script>
    	
    
    

   /* 댓글만들기 스크립트 끝! */
    
    </script>
    
    
    <c:import url="../common/footer.jsp"/>
    
</body>
</html>
