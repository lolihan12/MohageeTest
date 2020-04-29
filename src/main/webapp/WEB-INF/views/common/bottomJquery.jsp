<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Bootstrap tether Core JavaScript -->
<script src="${pageContext.request.contextPath}/resources/templates/assets/plugins/bootstrap/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/templates/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- slimscrollbar scrollbar JavaScript -->
<script src="${pageContext.request.contextPath}/resources/templates/resources/js/jquery.slimscroll.js"></script>
<!--Wave Effects -->
<script src="${pageContext.request.contextPath}/resources/templates/resources/js/waves.js"></script>
<!--Menu sidebar -->
<script src="${pageContext.request.contextPath}/resources/templates/resources/js/sidebarmenu.js"></script>
<!--stickey kit -->
<script src="${pageContext.request.contextPath}/resources/templates/assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
<!--Custom JavaScript -->
<script src="${pageContext.request.contextPath}/resources/templates/resources/js/custom.js"></script>
<!-- ============================================================== -->
<!-- Style switcher -->
<!-- ============================================================== -->
<script src="${pageContext.request.contextPath}/resources/templates/assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>

<script type="text/javascript">
	var sock = new SockJS("<c:url value='/approvalCount'/>");

	sock.onmessage = onMessage;

	function onMessage(evt) {
		var data = JSON.parse(evt.data);
		var btnArr = [ 'btn-success', 'btn-warning', 'btn-info' ];
		var approvalMSG;
		if (data.length != 0) {
			$('#approvalHeartbit').css('display', 'block');
			$('#approvalNoMSG').css('display', 'none');

			approvalMSG = $("<div class='message-center'>");
			for (var i = 0; i < data.length; i++) {
				var alink = $('<a>').attr(
						'href',
						'${pageContext.request.contextPath }/approval/approvalDoc/v/'
								+ data[i].adoc_no);
				var circleBtn = $("<div class='btn " + btnArr[i % 3]
						+ " btn-circle'><i class='fa fa-link'></i></div>");
				var mail_content = $("<div class='mail-contnet ml-2'>");
				mail_content.append("<h5 class='text-overflow'>" + data[i].adoc_subject + "</h5>");
				mail_content.append("<span class='mail-desc'>"
						+ data[i].writerName + "</span>");
				mail_content.append(" <span class='time'>"
						+ new Date(data[i].adoc_uploadDate).toLocaleString()
						+ "</span>");

				alink.append(circleBtn).append(mail_content);
				approvalMSG.append(alink);
			}

		} else {

			approvalMSG = $("<div class='m-3' align='center' ><h5 class='text-info'>현재 결재 대기 중인 문서가 없습니다</h5></div>");

		}
		$('#approvalNotify').append(approvalMSG);

		$('#approvalCnt').html('결재 미결함(' + data.length + ')');

	}

	var DMsock = new SockJS("<c:url value='/DMCount'/>");

	DMsock.onmessage = onDMMessage;

	function onDMMessage(evt) {
		var data = JSON.parse(evt.data);
		var DMMSG;
		if (data.length != 0) {
			$('#DMHeartbit').css('display', 'block');
			$('#DMNoMSG').css('display', 'none');

			DMMSG = $("<div class='message-center'>");
			for (var i = 0; i < data.length; i++) {
				var alink = $('<a>').attr(
						'href',
						'${pageContext.request.contextPath }/dm/selectOneDm.do/'+data[i].dm_no);
				var mail_content = $("<div class='mail-contnet ml-2'>");
				mail_content.append("<h5 class='text-overflow'>" + data[i].dm_subject + "</h5>");
				mail_content.append("<span class='mail-desc'>"
						+ data[i].dm_from_name + "</span>");
				mail_content.append(" <span class='time'>"
						+ new Date(data[i].dm_date).toLocaleString()
						+ "</span>");

				alink.append(mail_content);
				DMMSG.append(alink);
			}

		} else {

			DMMSG = $("<div class='m-3' align='center' ><h5 class='text-purple'>도착한 쪽지가 없습니다!</h5></div>");

		}
		$('#DMNotify').append(DMMSG);

		$('#DMCnt').html('받은 쪽지함(' + data.length + ')');

	}

	var chatSock = new SockJS("<c:url value='/chatTop'/>");
	chatSock._transportTimeout = function() {
		console.log('chat 타임아웃!!!');
	};

	chatSock.onmessage = onChatMessage;

	function onChatMessage(evt) {
		var data = JSON.parse(evt.data);
		var chatMSG;
		if (data.length != 0) {

			chatMSG = $("<div class='message-center'>");
			for (var i = 0; i < data.length; i++) {
				var alink = $('<a>').attr(
						'href',
						'${pageContext.request.contextPath }/chat/croom/'
								+ data[i].croom_no);

				var chat_content = $("<div class='mail-contnet ml-2'>");
				chat_content.append("<h5 class='text-overflow'>"
						+ (data[i].chat_content == undefined ? "최근 채팅이 없습니다."
								: data[i].chat_content) + "</h5>");
				chat_content.append("<span class='mail-desc'>"
						+ data[i].croom_title + "</span>");
				chat_content.append(" <span class='time'>"
						+ (data[i].chat_sendtime == undefined ? "" : new Date(
								data[i].chat_sendtime).toLocaleString())
						+ "</span>");

				alink.append(chat_content);
				chatMSG.append(alink);
			}

		} else {

			chatMSG = $("<div class='m-3' align='center' ><h5 class='text-danger'>개설된 채팅방이 없습니다!</h5></div>");

		}
		$('#chatNotify').append(chatMSG);

	}
</script>

