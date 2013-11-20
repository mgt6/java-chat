package com.taycon.mark.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.adapter.TextWebSocketHandlerAdapter;

import java.util.HashSet;
import java.util.Set;

public class ChatServiceWebSocketHandler extends TextWebSocketHandlerAdapter {

    private ChatService chatService;

    private Set<WebSocketSession> sessions = new HashSet<WebSocketSession>();


    @Autowired
    public ChatServiceWebSocketHandler(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String reply = chatService.sendMessage(session.getId(), message.getPayload());
        for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(new TextMessage(reply));
        }
    }
}
