package com.ssafy.sharehouse.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class ChatRoom implements Serializable{
	
	private int roomId;
	private String roomName;
	private String openDate;
	private int rentNo;
	
	public ChatRoom(int roomId, String roomName, String openDate, int rentNo) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.openDate = openDate;
		this.rentNo = rentNo;
	}

}
