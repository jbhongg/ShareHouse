package com.ssafy.sharehouse.model.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.sharehouse.dto.HouseDeal;
import com.ssafy.sharehouse.dto.HouseRent;
import com.ssafy.sharehouse.dto.User;

@Repository
public interface WishListRepo {
	public int registWishRent(Map<String, Object> map);//관심지역등록
	public int removeWishRent(Map<String, Object> map);
	public List<HouseRent> search(String userId);//관심지역 동 이름 리턴
}
