package com.ssafy.sharehouse.model.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.sharehouse.dto.ChatRoom;

@Repository
public interface ChatRoomRepo {
	void create(int rentNo);
	void enterChatRoom(Map<String, String> map);
	int searchChatRoom(int rentNo);
	int searchUserInChatRoom(Map<String, String> map);
	ChatRoom readChatRoom(int rentNo);
	List<ChatRoom> readAllChatRoom();
	List<ChatRoom> readAllChatRoomWithUserId(String userid);
	int readUserInChatRoom(int rentNo);
	void exitChatRoom(Map<String, Object> map);
}
