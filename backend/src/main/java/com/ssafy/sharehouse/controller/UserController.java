package com.ssafy.sharehouse.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.sharehouse.dto.User;
import com.ssafy.sharehouse.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@ApiOperation(value="사용자 정보 조회")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@GetMapping(value="/list")
	@ApiOperation(value="모든 회원 정보를 반환한다", response = List.class)
	public ResponseEntity<List<User>> userlist() {
		List<User> list = userService.searchAll();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/join")
	@ApiOperation(value = "새로운 회원을 등록한다.", response = List.class)
	public ResponseEntity<String> join(@RequestBody User user) {
		if (userService.join(user) != 0) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(value = "/login")
	@ApiOperation(value = "로그인한다.", response = User.class)
	public ResponseEntity<User> login(@RequestBody User user) {
		try {
			User login = userService.login(user.getId(), user.getPassword());
			if (login != null) {
				return new ResponseEntity<User>(login, HttpStatus.OK);
			} else {
				return new ResponseEntity<User>(login, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/{userId}")
	@ApiOperation(value = "유저의 정보를 수정한다", response = User.class)
	public ResponseEntity<User> modify(@PathVariable String userId, @RequestBody User user) {
		try {
			int k = userService.update(user);
			User login = userService.login(user.getId(), user.getPassword());
			if (k == 1) {
				return new ResponseEntity<User>(login, HttpStatus.OK);
			} else {
				return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{userId}")
	@ApiOperation(value = "유저의 정보를 삭제한다", response = User.class)
	public ResponseEntity<String> delete(@PathVariable String userId) {
		try {
			int k = userService.remove(userId);
			if (k == 1) {
				return new ResponseEntity<String>(HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
