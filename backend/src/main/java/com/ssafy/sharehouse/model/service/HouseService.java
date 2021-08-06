package com.ssafy.sharehouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.sharehouse.dto.AreaCode;
import com.ssafy.sharehouse.dto.HouseRent;
import com.ssafy.sharehouse.dto.PageNavigation;

public interface HouseService {
	public int getWishNumberByRentId(int rentNo);
	public int getChatRoomNumberByRentId(int rentNo);
	public HouseRent search(int dealNo);
	public PageNavigation makePageNavigation(Map<String, String> map);
	public List<HouseRent> searchAll(PageNavigation bean);
	public List<HouseRent> searchDong(Map<String, String> map);
	public List<HouseRent> searchGugun(Map<String, String> map);
	public List<HouseRent> searchAptName(Map<String, String> map);
	public List<HouseRent> searchGeneral(Map<String, String> map);
	public List<HouseRent> selectMostPopularTen();
	
	public List<AreaCode> getSido();
	public List<AreaCode> getGugunInSido(String sido);
	public List<AreaCode> getDongInGugun(String gugun);
}
