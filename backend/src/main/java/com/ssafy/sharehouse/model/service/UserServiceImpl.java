package com.ssafy.sharehouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sharehouse.dto.User;
import com.ssafy.sharehouse.model.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int join(User user) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(UserRepo.class).join(user);
	}
	
	@Override
	public User login(String id, String password) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("password", password);
		return sqlSession.getMapper(UserRepo.class).login(map);
	}

	@Override
	public User searchId(String id) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(UserRepo.class).searchId(id);
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(UserRepo.class).update(user);
	}

	@Override
	public int remove(String id) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(UserRepo.class).remove(id);
	}

	@Override
	public List<User> searchAll() {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(UserRepo.class).searchAll();
	}

}
