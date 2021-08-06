package com.ssafy.sharehouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sharehouse.dto.ChatRoom;
import com.ssafy.sharehouse.model.repository.ChatRoomRepo;
import com.ssafy.sharehouse.model.repository.WishListRepo;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void create(int rentNo) {
		sqlSession.getMapper(ChatRoomRepo.class).create(rentNo);
	}

	@Override
	public int searchChatRoom(int rentNo) {
		return sqlSession.getMapper(ChatRoomRepo.class).searchChatRoom(rentNo);
	}

	@Override
	public ChatRoom readChatRoom(int rentNo) {
		return sqlSession.getMapper(ChatRoomRepo.class).readChatRoom(rentNo);
	}

	@Override
	public List<ChatRoom> readAllChatRoom() {
		return sqlSession.getMapper(ChatRoomRepo.class).readAllChatRoom();
	}

	@Override
	public int readUserInChatRoom(int rentNo) {
		return sqlSession.getMapper(ChatRoomRepo.class).readUserInChatRoom(rentNo);
	}

	@Override
	public void enterChatRoom(String roomid, String userid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("roomid", roomid);
		map.put("userid", userid);
		sqlSession.getMapper(ChatRoomRepo.class).enterChatRoom(map);
	}

	@Override
	public List<ChatRoom> readAllChatRoomWithUserId(String userid) {
		return sqlSession.getMapper(ChatRoomRepo.class).readAllChatRoomWithUserId(userid);
	}

	@Override
	public int searchUserInChatRoom(Map<String, String> map) {
		return sqlSession.getMapper(ChatRoomRepo.class).searchUserInChatRoom(map);
	}

	@Override
	public void exitChatRoom(int roomId, String userId) {   
		Map<String, Object> map = new HashMap<String, Object>();
	   map.put("roomId", roomId);
	   map.put("userId", userId);
	   sqlSession.getMapper(ChatRoomRepo.class).exitChatRoom(map);

	}
	
}
