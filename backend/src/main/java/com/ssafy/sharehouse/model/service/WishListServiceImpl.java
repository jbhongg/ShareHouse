package com.ssafy.sharehouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sharehouse.dto.HouseDeal;
import com.ssafy.sharehouse.dto.HouseRent;
import com.ssafy.sharehouse.model.repository.WishListRepo;

@Service
public class WishListServiceImpl implements WishListService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int registWishRent(String userId, int rentNo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("rentNo", rentNo);
		return sqlSession.getMapper(WishListRepo.class).registWishRent(map);
	}

	@Override
	public int removeWishRent(String userId, int rentNo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("rentNo", rentNo);
		return sqlSession.getMapper(WishListRepo.class).removeWishRent(map);
	}

	@Override
	public List<HouseRent> search(String userId) {
		// TODO Auto-generated method stub
		System.out.println(userId);
		return sqlSession.getMapper(WishListRepo.class).search(userId);
	}


}
