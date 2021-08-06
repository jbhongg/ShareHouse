package com.ssafy.sharehouse.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.sharehouse.dto.ChatRoom;
import com.ssafy.sharehouse.dto.Message;
import com.ssafy.sharehouse.model.service.ChatRoomService;
import com.ssafy.sharehouse.model.service.MessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/chat")
@RequiredArgsConstructor
@RestController
public class ChatController {

	private final ChatRoomService chatRoomService;
	private final MessageService messageService;
	final int PAGE = 10;

	// 매물 번호로 방이 이미 존재하는지 조회
	@GetMapping("/search/{rentNo}")
	public ResponseEntity<String> searchChatRoom(@PathVariable int rentNo) {
		int isExist = chatRoomService.searchChatRoom(rentNo);
		if (isExist > 0)
			return ResponseEntity.status(HttpStatus.OK).body("EXISTS");
		return ResponseEntity.status(HttpStatus.OK).body("EMPTY");
	}

	// 특정 매물 번호의 채팅방 리턴
	@GetMapping("/room/{rentNo}")
	public ResponseEntity<Object> readChatRoom(@PathVariable int rentNo) {
		ChatRoom chatRoom = chatRoomService.readChatRoom(rentNo);
		if (chatRoom != null)
			return ResponseEntity.status(HttpStatus.OK).body(chatRoom);
		return ResponseEntity.status(HttpStatus.OK).body("FAIL");
	}

	// 모든 채팅방 리스트 리턴
	@GetMapping("/rooms")
	public ResponseEntity<List<ChatRoom>> readAllChatRoom() {
		List<ChatRoom> chatRooms = chatRoomService.readAllChatRoom();
		if (chatRooms != null && chatRooms.size() != 0)
			return ResponseEntity.status(HttpStatus.OK).body(chatRooms);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	// 모든 채팅방 리스트 리턴
	@GetMapping("/rooms/{userid}")
	public ResponseEntity<List<ChatRoom>> readAllChatRoomWithUserId(@PathVariable String userid) {
		List<ChatRoom> chatRooms = chatRoomService.readAllChatRoomWithUserId(userid);
		if (chatRooms != null && chatRooms.size() != 0)
			return ResponseEntity.status(HttpStatus.OK).body(chatRooms);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	// 특정 채팅방을 매물 번호로 생성
	@PostMapping("/room/{rentNo}/{userId}")
	public ResponseEntity<ChatRoom> create(@PathVariable int rentNo, @PathVariable String userId) {
		if (chatRoomService.searchChatRoom(rentNo) == 0) {
			chatRoomService.create(rentNo);
		}
		ChatRoom chatRoom = chatRoomService.readChatRoom(rentNo);
		chatRoomService.enterChatRoom(String.valueOf(chatRoom.getRoomId()), userId);
		return ResponseEntity.status(HttpStatus.OK).body(chatRoom);

	}

	// 특정 채팅방에 속한 유저 리스트 전체 반환
	@GetMapping("/room/{rentNo}/users")
	public ResponseEntity<Integer> readUserInChatRoom(@PathVariable int rentNo) {
		int chatNum = chatRoomService.readUserInChatRoom(rentNo);
		return ResponseEntity.status(HttpStatus.OK).body(chatNum);
	}

	// 특정 채팅방 의 메세지 최근 10개
	@GetMapping("/room/message/{id}")
	public ResponseEntity<List<Message>> roomInfo(@PathVariable int id,
			@RequestParam(value = "page", defaultValue = "0") String page) {
		int idx = page.equals("0") ? 0 : Integer.parseInt(page) * PAGE + 1;
		List<Message> msgList = messageService.getMessageByChatRoomId(id, idx);
		return ResponseEntity.status(HttpStatus.OK).body(msgList);
	}

	@DeleteMapping("/{roomId}/{userId}")
	public ResponseEntity<String> exitChatRoom(@PathVariable int roomId, @PathVariable String userId){
	   try{
		  chatRoomService.exitChatRoom(roomId, userId);
	      return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
	   }catch(Exception e){
		      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("FAIL");
	   }
	}
}