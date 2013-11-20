package com.taycon.mark.chat;

public class DefaultChatService implements ChatService{

    @Override
    public String sendMessage(String user, String message) {
        return "{\"user\" : \"" + user + "\", \"message\" : \"" + message + "\"}";
    }
}
