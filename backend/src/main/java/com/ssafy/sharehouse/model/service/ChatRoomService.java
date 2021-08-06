package com.ssafy.sharehouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.sharehouse.dto.ChatRoom;

public interface ChatRoomService {
	void create(int rentNo);
	void enterChatRoom(String roomid, String userid);
	int searchChatRoom(int rentNo);
	int searchUserInChatRoom(Map<String, String> map);
	ChatRoom readChatRoom(int rentNo);
	List<ChatRoom> readAllChatRoom();
	List<ChatRoom> readAllChatRoomWithUserId(String userid);
	int readUserInChatRoom(int rentNo);
	void exitChatRoom(int roomId, String userId);
}
