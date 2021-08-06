package com.ssafy.sharehouse.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.sharehouse.dto.Message;

@Repository
public interface MessageRepo {
	int createMessage(Message message);
	List<Message> getMessageByChatRoomId(@Param("roomId") int roomId, @Param("idx") int idx);
}
