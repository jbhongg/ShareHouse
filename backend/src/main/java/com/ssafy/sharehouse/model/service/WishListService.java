package com.ssafy.sharehouse.model.service;

import java.util.List;

import com.ssafy.sharehouse.dto.HouseDeal;
import com.ssafy.sharehouse.dto.HouseRent;

public interface WishListService {
	public int registWishRent(String userId, int rentNo);//관심지역등록
	public int removeWishRent(String userId, int rentNo);
	public List<HouseRent> search(String userId);//관심지역 동 이름 리턴
}
