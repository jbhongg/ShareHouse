package com.ssafy.sharehouse.dto;
//사용자의 정보 - 이름, 아이디, 비밀번호, 이메일, 핸드폰번호, 주소

import lombok.Data;

@Data
public class User {
	private String id;
	private String name;
	private String password;
	private String email;
	private String phone;
	private String address;
	private String gender;
	private String birth;
	private String area;
}
