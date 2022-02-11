package com.xxx.server.controller;

import com.xxx.server.pojo.Admin;
import com.xxx.server.pojo.ChatMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * websocket
 */
@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/ws/chat")
    public void handler(Authentication authentication, ChatMsg chatMsg){
        Admin principal = (Admin) authentication.getPrincipal();
        //发送人
        chatMsg.setFrom(principal.getUsername());
        //昵称
        chatMsg.setFormNickName(principal.getName());
        chatMsg.setData(LocalDateTime.now());
        //发送消息
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(),"/queue/chat",chatMsg);
    }
}
