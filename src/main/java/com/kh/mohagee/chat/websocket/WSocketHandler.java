package com.kh.mohagee.chat.websocket;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.kh.mohagee.member.model.vo.Member;

public class WSocketHandler extends TextWebSocketHandler {

	// 사용자 목록
    private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
    // 서버에 로그(기록) 남기는 객체
    private Logger logger=LoggerFactory.getLogger(WSocketHandler.class);

    
    // 웹소켓이 연결된 후에 동작할 메소드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        
    	sessionList.add(session);
        
    	//system.out.printf("%s", session.getId());
        logger.debug("{}연결됨", session.getId());
        
        //session에 있는 로그인정보 가져오기
        Member loggin=(Member)session.getAttributes().get("member");
        
        //채팅방에 입장하면 상대방에게 입장 메세지 출력
        for(WebSocketSession one : sessionList) {
            if(one==session) continue;
            one.sendMessage(new TextMessage(loggin.getNickName()+"님이 입장하셨습니다."));
        }
        
        //super.afterConnectionEstablished(session);
    }

    
    // 사용자간 데이터를 주고받을 때 동작하는 메소드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        
        Member loggin=(Member)session.getAttributes().get("member");
        
        //사용자가 보낸 메세지를 접속한 사용자에게 전송
        //구분자를 이용한 문자열로 view단에 전송하여 분기 처리함 / 순서가 중요함.
        //session.getRemoteAddress()는 ip주소인데  보내는 사람과 받는사람을 구분하기 위해 전송
        for(WebSocketSession one : sessionList) {
        	one.sendMessage(new TextMessage(session.getId()+"|"+message.getPayload()+"|"+session.getRemoteAddress()+"|"+loggin.getUserId()));
        }
        //super.handleTextMessage(session, message);
    }

    // 사용자가 연결을 종료했을 때 동작하는 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	
    	sessionList.remove(session);
        logger.debug("{}연결끊김",session.getId());

        Member loggin=(Member)session.getAttributes().get("member");

        //채팅방 퇴장시 퇴장알림
        for (WebSocketSession one : sessionList) {
            if(one==session) continue;
            one.sendMessage(new TextMessage(loggin.getUserId()+"님이 퇴장하셨습니다."));
        }
        //super.afterConnectionClosed(session, status);
    }
    
    
}
