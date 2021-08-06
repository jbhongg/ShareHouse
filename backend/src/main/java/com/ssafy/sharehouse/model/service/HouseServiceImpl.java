package com.ssafy.sharehouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sharehouse.dto.AreaCode;
import com.ssafy.sharehouse.dto.HouseRent;
import com.ssafy.sharehouse.dto.PageNavigation;
import com.ssafy.sharehouse.model.repository.HouseRepo;

@Service
public class HouseServiceImpl implements HouseService {
	
private static final Logger logger = LoggerFactory.getLogger(HouseServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public HouseRent search(int dealNo) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(HouseRepo.class).search(dealNo);
	}
	
	@Override
	public List<HouseRent> searchAll(PageNavigation bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HouseRent> searchDong(Map<String, String> map) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("dong", map.get("dong"));
		int currentPage = Integer.parseInt(map.get("pg")==null ? "1":map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		
		param.put("start", start);
		param.put("spp", sizePerPage);
		return sqlSession.getMapper(HouseRepo.class).searchDong(param);
	}

	@Override
	public List<HouseRent> searchGugun(Map<String, String> map) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("gugun", map.get("gugun"));
		int currentPage = Integer.parseInt(map.get("pg")==null ? "1":map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		
		param.put("start", start);
		param.put("spp", sizePerPage);
		return sqlSession.getMapper(HouseRepo.class).searchGugun(param);
	}
	
	@Override
	public List<HouseRent> searchAptName(Map<String, String> map) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("dong", map.get("dong"));
		param.put("aptName", map.get("aptName"));
		int currentPage = Integer.parseInt(map.get("pg")==null ? "1":map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		
		param.put("start", start);
		param.put("spp", sizePerPage);
		return sqlSession.getMapper(HouseRepo.class).serchAptName(param);
	}

	@Override
	public List<HouseRent> searchGeneral(Map<String, String> map) {
		return null;
	}

	@Override
	public List<AreaCode> getSido() {
		return sqlSession.getMapper(HouseRepo.class).getSido();
	}

	@Override
	public List<AreaCode> getGugunInSido(String sido) {
		return sqlSession.getMapper(HouseRepo.class).getGugunInSido(sido);
	}

	@Override
	public List<AreaCode> getDongInGugun(String gugun) {
		return sqlSession.getMapper(HouseRepo.class).getDongInGugun(gugun);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) {
		int naviSize = 10;
		int currentPage = Integer.parseInt(map.get("pg") == null ? "1" : map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(HouseRepo.class).getTotalCount(map);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public List<HouseRent> selectMostPopularTen() {
		return sqlSession.getMapper(HouseRepo.class).selectMostPopularTen();
	}

	@Override
	public int getWishNumberByRentId(int rentNo) {
		return sqlSession.getMapper(HouseRepo.class).getWishNumberByRentId(rentNo);
	}

	@Override
	public int getChatRoomNumberByRentId(int rentNo) {
		return sqlSession.getMapper(HouseRepo.class).getChatRoomNumberByRentId(rentNo);
	}


}
