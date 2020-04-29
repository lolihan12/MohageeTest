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
	${ member.nickName }님의 마이페이지
  </title>
  <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
  
  <c:import url="../common/myPageUtil.jsp"/>
  
  <style>
  	span.guide{
		display:none;
		position : absolute;
		font-size: 12px;
		top: 280px;
		right: 30px;
	}
	span.ok{
		color:green;
	}
	
	span.error{
		color:red;
	}
	
	@media screen and (min-width: 10px) {
		input.nickNameInput{
			display:inline;
			width: 72%;
		}
		
		button.nickNameButton{
			width: auto;
			height: 30px;
		}
	}
	
	@media screen and (min-width: 768px) {
		input.nickNameInput{
			display:inline;
			width: 60%;
		}
		
		button.nickNameButton{
			width: auto;
			height: 30px;
		}
	}
	
	@media screen and (min-width: 992px) {
		input.nickNameInput{
			width: 70%;
			display:inline;
		}
		
		button.nickNameButton{
			width: 25%;
		}
	}
	
	@media screen and (min-width: 1200px) {
		input.nickNameInput{
			display:inline;
			width: 75%;
		}
		
		button.nickNameButton{
			width: 20%;
		}
	}
	
	textarea {
		width: 300px;
		height: auto;
		background: center center no-repeat; /* This ruins default border */
		border: none;
		resize: none;
		color: white;
		text-align: center;
		overflow: auto;
	}
	
	#profileDiv:hover{
		cursor: pointer;
	}
  </style>
  
</head>

<body class="profile-page sidebar-collapse">
	<c:url var="pUpFile" value="/resources/profile/"/>
	<c:set value="${ member.userNo }" var="userNo"/>
	<c:set value="${ member.userGrade }" var="grade"/>
	<c:set value="${ member.introduce }" var="introduce"/>
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
        </ul>
      </div>
    </div>
  </nav>
  <!-- End Navbar -->
  <div class="wrapper">
	<form method="POST" action="${ pageContext.request.contextPath }/member/memberUpdate.do" 
	      onsubmit="return fn_submit();" enctype="multipart/form-data">
    <div class="page-header page-header-small" filter-color="orange">
      <div class="page-header-image" data-parallax="true" style="background-image:url('${pageContext.request.contextPath}/resources/myPageResources/img/background.png');">
      </div>
      <div class="container" style="height: auto;">
        <div id="profileDiv" class="photo-container">
          <img id="profile" src="${ pUpFile }${profile.pRenamedFileName}">
        </div>
        <input style="display:none;" type="file" id="profilePhoto" name="profilePhoto" onchange="LoadImg(this,1)">
        <input type="hidden" name="profilePhotoCheck" id="profilePhotoCheck" value="0"/>
        <h3 class="title">${ member.nickName }</h3>
        <p class="category">
        	<c:if test="${ grade eq 'U' }">일반회원</c:if>
        	<c:if test="${ grade eq 'E' }">에디터</c:if>
        </p>
        <div class="content" style="position: relative;height: auto; width: auto;">
          <div class="social-description" style="height: auto; width: auto;">
          	
	            <h3>자기 소개</h3>
	            <div style="margin: auto;">
	            	<textarea name="introduce" id="introduce">${ member.introduce }</textarea>
	            </div>

          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-6 ml-auto mr-auto">
            <h4 class="title text-center">
				Profile Update
			</h4>
          <div class="tab-content gallery">
          <div class="tab-pane active" id="home" role="tabpanel">
            	<input type="hidden" name="userNo" value="${ member.userNo }" readonly/>
 				<div class="modal-body mx-3">
			        <div class="md-form mb-5">
			        <i class="fas fa-envelope prefix grey-text"></i>
			        <label data-error="wrong" data-success="right" for="Sign-defaultForm-email">ID</label>
			          <section>
			             <input readonly value="${ member.userId }" type="email" name="userId" id="Sign-defaultForm-email" class="form-control validate" placeholder="example@example.com">
			          </section>
			        </div>
			        
			        <br>
			        <div class="md-form mb-4">
			          <i class="fas fa-lock prefix grey-text"></i>
			          <label data-error="wrong" data-success="right" for="Sign-defaultForm-pass">Change Password</label>
			          <input type="password" name="password" id="Sign-defaultForm-pass" class="form-control validate" placeholder="영문 대소문자 6~18자리(비밀번호 변경을 원하실경우만 작성해주세요)">
			        </div>
			        <br>
			        <div class="md-form mb-4">
			          <i class="fas fa-lock prefix grey-text"></i>
			          <label data-error="wrong" data-success="right" for="Sign-defaultForm-passCheck">Check Password</label>
			          <input type="password" name="passwordCheck" id="Sign-defaultForm-passCheck" class="form-control validate" placeholder="비밀번호 확인">
			        <span class="guide ok">일치 합니다</span>
			          <span class="guide error">불일치 합니다</span>
			          <input type="hidden" name="passwordSameCheck" id="passwordSameCheck" value="1"/>
			        </div>
			        <br>
			        <div class="md-form mb-4">
			            <i class="fas fa-user prefix grey-text"></i>
			            <label data-error="wrong" data-success="right" for="Sign-defaultForm-Name">Name</label>
			            <input readonly value="${ member.userName }" type="text" name="userName" id="Sign-defaultForm-Name" class="form-control validate" placeholder="한글만 적어주세요">
			        </div>
			        <br>
			        <div class="md-form mb-4">
			            <i class="fas fa-user prefix grey-text"></i>
			            <label data-error="wrong" data-success="right" for="Sign-defaultForm-NickName">NickName</label>
			            <section>
			               <input value="${ member.nickName }" type="text" name="nickName" id="Sign-defaultForm-NickName" class="form-control validate nickNameInput" placeholder="NickName">
			            <button id="checkNick" class="btn btn-success nickNameButton" type="button">중복확인</button>
			            <input type="hidden" id="nickNameSameCheck" value="1"/>
			          </section>
			        </div>
			        <br>
			        <div class="md-form mb-4">
			            <i class="fas fa-phone prefix grey-text"></i>
			            <label data-error="wrong" data-success="right" for="Sign-defaultForm-Phone">Phone</label>
			            <input value="${ member.phone }" type="text" name="phone" id="Sign-defaultForm-Phone" class="form-control validate" placeholder="'-' 제외한 전화번호">
			        </div>
		
		      		</div>
			         <div class="modal-footer d-flex justify-content-center">
			           <input type="submit" class="btn btn-info" id="join" value="Update Profile" >
			         </div>
		      
		      </div>
          </div>
          </div>
        </div>
      </div>
    </div>
    <footer class="footer footer-default">
      <div class=" container ">
        <nav>
          <ul>
            <li>
              <a href="https://www.creative-tim.com">
                Creative Tim
              </a>
            </li>
            <li>
              <a href="http://presentation.creative-tim.com">
                About Us
              </a>
            </li>
            <li>
              <a href="http://blog.creative-tim.com">
                Blog
              </a>
            </li>
          </ul>
        </nav>
        <div class="copyright" id="copyright">
          &copy;
          <script>
            document.getElementById('copyright').appendChild(document.createTextNode(new Date().getFullYear()))
          </script>, Designed by
          <a href="https://www.invisionapp.com" target="_blank">Invision</a>. Coded by
          <a href="https://www.creative-tim.com" target="_blank">Creative Tim</a>.
        </div>
      </div>
    </footer>
    </form>
  </div>
  
<script>

	$(function(){
		
		$('#profileDiv').on('click', function(){
			$('#profilePhoto').click();
		});
		
	});
	
	function LoadImg(value, num) {
	    if(value.files) {
	    	
	       var reader = new FileReader();
	       
	       reader.onload = function(e){
	    	   $('#profile').attr('src', e.target.result);
	       }
	       
	       reader.readAsDataURL(value.files[0]);
	    }
	    
	    $("#profilePhotoCheck").val(1);
	 }
	
	$("#Sign-defaultForm-passCheck").on("keyup", function(){
		var p1 = $("#Sign-defaultForm-passCheck").val();
		var p2 = $("#Sign-defaultForm-pass").val();
		
		if(p1 != p2){
			$(".guide.error").show();
			$(".guide.ok").hide();
			$("#passwordSameCheck").val(0);
		} else if(p1 == p2){
			$(".guide.ok").show();
			$(".guide.error").hide();
			$("#passwordSameCheck").val(1);
		}
	});
	
	$("#Sign-defaultForm-NickName").on("keyup", function(){
		$('#nickNameSameCheck').val(0);
		
		console.log($('#nickNameSameCheck').val());
	});
	
	$("#Sign-defaultForm-pass").on("keyup", function(){
		$('#passwordSameCheck').val(0);
	});
	
	$("#Sign-defaultForm-pass").on("keyup", function(){
		
		var p1 = $("#Sign-defaultForm-passCheck").val();
		var p2 = $("#Sign-defaultForm-pass").val();
		
		if(p1 != p2){
			$(".guide.error").show();
			$(".guide.ok").hide();
			$("#passwordSameCheck").val(0);
		} else if(p1 == p2){
			$(".guide.ok").show();
			$(".guide.error").hide();
			$("#passwordSameCheck").val(1);
		}
		
	});
	
	$("#checkNick").on("click", function(){
		var nick = $("#Sign-defaultForm-NickName").val();
    	
    	$.ajax({
    		url : "${pageContext.request.contextPath}/nick/checkNick.do",
    		data : {
    			nick : nick
    		},
    		success: function(data){
    			if(data == 1){
    				alert("이미 존재하는 닉네임입니다.");
    				$("#nickNameSameCheck").val(0);
    			} else {
    				alert("사용가능한 닉네임입니다.")
    				$("#nickNameSameCheck").val(1);
    			}
    		}, error: function(){
    			alert("닉네임 체크에 실패했습니다.");
    		}
    	});
	});
	
	function fn_submit(){
		
		if($('#nickNameSameCheck').val() == 0){
			
			alert("닉네임 중복확인을 해주세요");
			$("#Sign-defaultForm-NickName").focus();
			
			return false;
			
		} else if($('#passwordSameCheck').val() == 0){
			
			alert("비밀번호가 불일치 합니다.");
			$("#Sign-defaultForm-pass").focus();
			
			return false;
			
		}
		
		return true;
	}
	
	textarea = document.querySelector("#introduce"); 
    textarea.addEventListener('input', autoResize, false); 
  
    function autoResize() { 
        this.style.height = 'auto'; 
        this.style.height = this.scrollHeight + 'px'; 
    }
	
</script>
</body>
</html>














