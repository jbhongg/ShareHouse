package com.ssafy.sharehouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.sharehouse.dto.AreaCode;
import com.ssafy.sharehouse.dto.HouseRent;
import com.ssafy.sharehouse.dto.PageNavigation;
import com.ssafy.sharehouse.model.repository.HouseRepo;
import com.ssafy.sharehouse.model.service.HouseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/house")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class HouseController {

	private static final Logger logger = LoggerFactory.getLogger(HouseController.class);
	
	@Autowired
	private HouseService houseService;	

    @ApiOperation(value = "모든 시(도)의 정보를 반환한다.", response = List.class)
	@GetMapping(value="/area")
	public ResponseEntity<List<AreaCode>> getSido(){
		List<AreaCode> list = houseService.getSido();
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<AreaCode>>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

    @ApiOperation(value = "선택한 시(도) 내의 모든 구(군)의 정보를 반환한다.", response = List.class)
	@GetMapping(value="/sido/{code}")
	public ResponseEntity<List<AreaCode>> getGugunInSido(@PathVariable String code){
    	System.out.println(code);
		List<AreaCode> list = houseService.getGugunInSido(code);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<AreaCode>>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

    @ApiOperation(value = "선택한 구(군) 내의 모든 동의 정보를 반환한다.", response = List.class)
	@GetMapping(value="/gugun/{code}")
	public ResponseEntity<List<AreaCode>> getDongInGugun(@PathVariable String code){

		List<AreaCode> list = houseService.getDongInGugun(code);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<AreaCode>>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

    @ApiOperation(value = "선택한 지역 내의 모든 실거래 정보를 반환한다.", response = List.class)
	@GetMapping(value="/dong/{code}")
	public ResponseEntity<Map<String, Object>> searchDong(@PathVariable String code, @RequestParam(required=false, defaultValue="10") String spp, @RequestParam(required=false, defaultValue="1") String pg){
		Map<String, String> map=new HashMap<>();
		map.put("dong", code);
		map.put("spp", spp);
		map.put("pg", pg);
				
		Map<String, Object> result = new HashMap<>();
		List<HouseRent> list = houseService.searchDong(map);
		PageNavigation navigation = houseService.makePageNavigation(map);
		
		if(list != null && !list.isEmpty()) {
			result.put("list", list);
			result.put("navigation", navigation);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
	}

    @ApiOperation(value = "유저의 관심 지역 내의 모든 실거래 정보를 반환한다.", response = List.class)
	@GetMapping(value="/interest/{code}")
	public ResponseEntity<Map<String, Object>> searchGugun(@PathVariable String code, @RequestParam(required=false, defaultValue="10") String spp, @RequestParam(required=false, defaultValue="1") String pg){
		Map<String, String> map=new HashMap<>();
		map.put("gugun", code);
		map.put("spp", spp);
		map.put("pg", pg);
				
		Map<String, Object> result = new HashMap<>();
		List<HouseRent> list = houseService.searchGugun(map);
		PageNavigation navigation = houseService.makePageNavigation(map);
		
		if(list != null && !list.isEmpty()) {
			result.put("list", list);
			result.put("navigation", navigation);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
	}

    @ApiOperation(value = "검색한 아파트의 이름에 일치하는 아파트 리스트를 반환한다.", response = List.class)
	@GetMapping(value="/search/{aptName}")
	public ResponseEntity<Map<String, Object>> searchApart(@PathVariable String aptName,@RequestParam String dong, @RequestParam(required=false, defaultValue="10") String spp, @RequestParam(required=false, defaultValue="1") String pg){
		Map<String, String> map=new HashMap<>();
		map.put("dong", dong);
		map.put("aptName", aptName);
		map.put("spp", spp);
		map.put("pg", pg);
		System.out.println(map);
		
		Map<String, Object> result = new HashMap<>();
		List<HouseRent> list = houseService.searchAptName(map);
		PageNavigation navigation = houseService.makePageNavigation(map);
		if(list != null && !list.isEmpty()) {
			result.put("list", list);
			result.put("navigation", navigation);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
	}
    

    @ApiOperation(value = "유저들이 찜한 수가 많은 최상위 10개 매물을 반환한다.", response = List.class)
	@GetMapping(value="/popular")
	public ResponseEntity<List<HouseRent>> selectMostPopularTen(){
		List<HouseRent> list = houseService.selectMostPopularTen();
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<HouseRent>>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<HouseRent>>(HttpStatus.NO_CONTENT);
		}
	}
    
    @ApiOperation(value="특정 매물을 찜한 사람이 몇 명인지 조회한다", response = Integer.class)
    @GetMapping(value="/{rentNo}/wish")
    public ResponseEntity<Integer> getWishNumberByRentId(@PathVariable int rentNo){
    	return new ResponseEntity<Integer>(houseService.getWishNumberByRentId(rentNo), HttpStatus.OK);
    }
    
    @ApiOperation(value="특정 매물의 채팅방에 들어간 사람이 몇 명인지 조회한다", response = Integer.class)
    @GetMapping(value="/{rentNo}/room")
    public ResponseEntity<Integer> getChatRoomNumberByRentId(@PathVariable int rentNo){
    	return new ResponseEntity<Integer>(houseService.getChatRoomNumberByRentId(rentNo), HttpStatus.OK);
    }
}
