package com.ssafy.sharehouse.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.sharehouse.dto.Message;
import com.ssafy.sharehouse.model.service.MessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
@RestController
public class MessageController {
	private final MessageService messageService;
	private final SimpMessagingTemplate template;

	@MessageMapping("/message")
	public void sendMessage(@Payload Message chatMessage) {
		log.info("전달 메세지 : " + chatMessage);
		
		messageService.createMessage(chatMessage);
		template.convertAndSend("/sub/" + chatMessage.getChatRoomId(), chatMessage);
	}
}
