package com.ssafy.sharehouse.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Message {
	private int messageId;
	private String content;
	private int chatRoomId;
	private int rentNo;
	private String senderId;
	private String senderName;
}
