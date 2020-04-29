<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- cnd방식으로 sockjs불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>
<!-- 부트스트랩적용 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" 
      integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
      integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>실시간 채팅</title>
<script>
    /* SockJS객체생성 보낼 url경로를 매개변수로 등록 */
    var sock = new SockJS("<c:url value='/chatting'/>");
    /* /info : https://github.com/sockjs/sockjs-protocol/blob/master/sockjs-protocol.py#L250-L263 */
    
    sock.onmessage=onMessage;
   sock.onclose=onClose;
    
    var today=null;
    
   $(function(){
        $("#sendBtn").click(function(){
            console.log("send message.....");
            /* 채팅창에 작성한 메세지 전송 */
            sendMessage();
            /* 전송 후 작성창 초기화 */
            $("#message").val('');
        });
        
        $("#exitBtn").click(function(){
            console.log("exit message.....");
            /* 채팅창에 작성한 메세지 전송 */
            sock.onclose();
        });
  });
    
    function sendMessage() {
        /* 맵핑된 핸들러 객채의 handleTextMessage매소드가 실행 */
        sock.send($("#message").val());
    };
    
    function onMessage(evt){
        var data=evt.data;//new text객체로 보내준 값을 받아옴.
        var host=null;//메세지를 보낸 사용자 ip저장
        var strArray=data.split("|");//데이터 파싱처리하기
        var userName=null;//대화명 저장
        
        //전송된 데이터 출력해보기
        for(var i=0;i<strArray.length;i++) {
            console.log('str['+i+'] :' + strArray[i]);          
        }
        
        if(strArray.length>1) {
            sessionId=strArray[0];
            message=strArray[1];
            host=strArray[2].substr(1,strArray[2].indexOf(":")-1);
            userName=strArray[3];
            today=new Date();
            printDate=today.getFullYear()+"/"+(today.getMonth()+1)+"/"+today.getDate()+" "+today.getHours()+":"+today.getMinutes()+":"+today.getSeconds();
            
            console.log(today);
            var ck_host='${host}';
     
            console.log(sessionId);
            console.log(message);
            console.log('host : '+host);
            console.log('ck_host : '+ck_host);
            /* 서버에서 데이터를 전송할경우 분기 처리 */
            if(host==ck_host||(host==0&&ck_host.includes('0:0:'))) {
                var printHTML="<div class='well' style='margin-left: 30%;'>";
                printHTML+="<div class='alert alert-info'>";
                printHTML+="<sub>"+printDate+"</sub><br/>";
                printHTML+="<strong>["+userName+"] : "+message+"</strong>";
                printHTML+="</div>";
                printHTML+="</div>";
                $('#chatdata').append(printHTML);
            } else {
                var printHTML="<div class='well'  style='margin-left: -5%;margin-right:30%;'>";
                printHTML+="<div class='alert alert-warning'>";
                printHTML+="<sub>"+printDate+"</sub><br/>";
                printHTML+="<strong>["+userName+"] : "+message+"</strong>";
                printHTML+="</div>";
                printHTML+="</div>";
                $('#chatdata').append(printHTML);
                
            }
            //console.log('chatting data : '+data);
        } else {
            //나가기 구현
            today=new Date();
            printDate=today.getFullYear()+"/"+today.getMonth()+"/"+today.getDate()+" "+today.getHours()+":"+today.getMinutes()+":"+today.getSeconds();
            message=strArray[0];
            var printHTML="<div class='well'  style='margin-left30%:'>";
            printHTML+="<div class='alert alert-danger'>";
            printHTML+="<sub>"+printDate+"</sub><br/>";
            printHTML+="<strong>[서버관리자] : "+message+"</strong>";
            printHTML+="</div>";
            printHTML+="</div>";
            $('#chatdata').append(printHTML);   
        }
    };
    
    function onClose(){
        location.href='${pageContext.request.contextPath}';
        self.close();
    };
</script>
<style>
div { padding:5%; }
</style>
</head>
<body>
<div class='form-group'>
    <table style="width: 900px; margin : auto; background-image: url(${ pageContext.request.contextPath }/resources/images/chat_Background.jpg);">
    
    
    <tr>
       <td align="center" style="width: 600px; color: white;">
          <!-- 방 이름 -->
          <h2 style = "color : lightyellow;">
             ${member.nickName}님, 채팅방입니다^^
          </h2>
       </td>   
       <td align="center" style="width: 300px;"> 
          <!-- 방 나가기 버튼 -->
          <button class='btn btn-primary' type="button" id='exitBtn'>나가기</button>
       </td>
    
    </tr>
    
    </table>
    
    <!-- 채팅방 구현 부분 -->
    <table style="height : 600px; width: 900px; 
       margin: auto; margin-bottom: 10px; padding: 0; background-color: rgb(170, 224, 206);"
         class='panel panel-default' >
    
    <!-- 채팅창 부분 -->
    <col width="400px;" /><!-- 메세지 입력 영역 -->
    
    <!-- 오른쪽 리스트 출력영역 -->
    <col width="300px;"/> <!-- 보내기 영역 -->    
    
    <tr height="600px;">
       <!-- 채팅 내용 출력 -->
       <td style="width:550px;" colspan="2" >
          
          <div style="width:100%; height: 550px; background-color:rgba(237, 241, 243, 0.8); 
                   over-flow-y:scroll; over-flow-x:inherit; 
                   margin: 3px; border-radius:3px;" id='chatdata' class='panel-body' ></div>
          
       </td>
    
    </tr>
    
    <!-- 입력 창 -->
    <tr height="100px;">
       
       <td>
          <input type="text"  name='message' id='message' size="50" value="" style="width: 100%; height: 80px; border-radius:5px;
                font-weight: bold; margin:auto; box-shadow: 0.5px 0.5px 0.5px 0.5px #A0A0C8 inset;" 
                class="form-control form-control-lg" placeholder="내용 입력" />
       </td>
       
       <td>
          <button class='btn btn-primary' type="button" id='sendBtn' style="width:100%; height:70px;">전송</button>
       </td>
    </tr>
    </table>
</div>

</body>
</html>