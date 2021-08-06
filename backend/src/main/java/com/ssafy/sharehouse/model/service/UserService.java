package com.ssafy.sharehouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.sharehouse.dto.User;
//서비스를 제공하기위해서 DAO를 만들어서 서비스를 제공한다.
public interface UserService {
	public int join(User user); // DB에 회원 추가 하는 메소드
	public User login(String id, String password) ; // DB에 접근해서 로그인가능한지 판단하는 메소드
	public int update(User user); // DB에 사용자의 정보를 수정하는 메소드
	public User searchId(String id); // DB에 저장된 정보를 뽑아서 로그인 유효성 검사나 사용자 삭제, 사용자 수정 등에서 사용되는 메소드
	public int remove(String id); //DB에 사용자 삭제하는 메소드
	List<User> searchAll(); //DB에 존재하는 사용자 모두 리턴하는 메소드
}
