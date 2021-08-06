package com.ssafy.sharehouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sharehouse.dto.Message;
import com.ssafy.sharehouse.model.repository.MessageRepo;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int createMessage(Message message) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MessageRepo.class).createMessage(message);
	}

	@Override
	public List<Message> getMessageByChatRoomId(int roomId, int idx) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MessageRepo.class).getMessageByChatRoomId(roomId, idx);
	}

}
