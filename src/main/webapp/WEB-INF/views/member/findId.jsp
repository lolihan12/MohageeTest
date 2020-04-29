<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <title>
    아이디 찾기
  </title>
  <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
  
  <c:import url="../common/myPageUtil.jsp"></c:import>
  
</head>

<body class="login-page sidebar-collapse">
  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg bg-primary fixed-top navbar-transparent " color-on-scroll="400">
    <div class="container">
      <div class="dropdown button-dropdown">
        <a href="${ pageContext.request.contextPath }/gotoIndex.do">
          <i class="fas fa-home fa-2x"></i>
        </a>
      </div>
      <div class="navbar-translate">
        <a class="navbar-brand" href="${ pageContext.request.contextPath }/gotoIndex.do"  data-placement="bottom" target="_blank">
			메인페이지
        </a>
        <button class="navbar-toggler navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-bar top-bar"></span>
          <span class="navbar-toggler-bar middle-bar"></span>
          <span class="navbar-toggler-bar bottom-bar"></span>
        </button>
      </div>
      <div class="collapse navbar-collapse justify-content-end" id="navigation" data-nav-image="${pageContext.request.contextPath}/resources/myPageResources/img/blurred-image-1.jpg">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="${ pageContext.request.contextPath }/member/findPwPage.do" rel="tooltip" title="Find Password">
            	<i class="fas fa-key fa-2x"></i>
	            <p class="d-lg-none d-xl-none">Find Password</p>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- End Navbar -->
  <div class="page-header">
    <div class="page-header-image" style="background-image:url(${pageContext.request.contextPath}/resources/images/myPageLogin.png)"></div>
    <div class="content">
      <div class="container">
        <div class="col-md-4 ml-auto mr-auto">
          <div class="card card-login card-plain">
            <form onsubmit="return check();" class="form" method="POST" action="${ pageContext.request.contextPath }/member/findId.do">
              <div class="card-header text-center">
                <div class="logo-container">
                  <img src="${ pageContext.request.contextPath }/resources/images/myPageLogo.png" alt="">
                </div>
              </div>
              <div class="card-body">
              <div class="input-group no-border input-lg">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="fas fa-user users_circle-08"></i>
                    </span>
                  </div>
                  <input name="name" id="name" type="text" class="form-control" placeholder="Your Name">
                </div>
                <div class="input-group no-border input-lg">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="fas fa-phone users_circle-08"></i>
                    </span>
                  </div>
                  <input name="phone" id="phone" type="text" class="form-control" placeholder="Your Phone Number">
                </div>
              </div>
              <div class="card-footer text-center" style="width: 310px; margin:auto;">
                <button class="btn btn-primary btn-round btn-lg btn-block">Go To Find Id</button>
              </div>
            </form>
            </div>
          </div>
        </div>
      </div>
    </div>
     <script>
		
    	function check(){
    		
			var reg1 = /^[가-힣]{2,5}$/;
			var name = $("#name").val();
			
			var reg2 = /^\d{2,3}\d{3,4}\d{4}$/;
			var phone = $('#phone').val();
    		
    		if( !reg1.test(name) ){
    			alert("이름을 확인해주세요");
    			$("#name").focus();
    			return false;
    		} else if( !reg2.test(phone) ){
    			alert("전화번호를 확인해주세요");
    			$("#phone").focus();
    			return false;
    		}
    		
    		return true;
    		
    	}
    </script>
 </body>
</html>