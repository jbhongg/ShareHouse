package com.ssafy.sharehouse.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.sharehouse.dto.Message;

public interface MessageService {
	int createMessage(Message message);
	List<Message> getMessageByChatRoomId(int roomId, int idx);
}
