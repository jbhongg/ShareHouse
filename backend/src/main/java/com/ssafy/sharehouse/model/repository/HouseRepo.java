package com.ssafy.sharehouse.model.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.sharehouse.dto.AreaCode;
import com.ssafy.sharehouse.dto.HouseRent;
import com.ssafy.sharehouse.dto.PageNavigation;

@Repository
public interface HouseRepo {
	public void loadData();
	public HouseRent search(int dealNo);
	public int getTotalCount(Map<String, String> map);
	public int getWishNumberByRentId(int rentNo);
	public int getChatRoomNumberByRentId(int rentNo);
	public List<HouseRent> searchAll(PageNavigation bean);
	public List<HouseRent> searchDong(Map<String, Object> map);
	public List<HouseRent> searchGugun(Map<String, Object> map);
	public List<HouseRent> serchAptName(Map<String, Object> map);
	public List<HouseRent> searchGeneral(Map<String, Object> map);
	public List<HouseRent> selectMostPopularTen();
	
	public List<AreaCode> getSido();
	public List<AreaCode> getGugunInSido(String sido);
	public List<AreaCode> getDongInGugun(String gugun);
}
