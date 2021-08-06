package com.ssafy.sharehouse.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.sharehouse.dto.AreaCode;
import com.ssafy.sharehouse.dto.HouseRent;
import com.ssafy.sharehouse.dto.WishList;
import com.ssafy.sharehouse.dto.User;
import com.ssafy.sharehouse.model.service.WishListService;

@RestController
@RequestMapping("/wish")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class WishListController {
	
	private static final Logger logger = LoggerFactory.getLogger(WishListController.class);
	
	@Autowired
	private WishListService wishListService;
	
	@GetMapping(value="/{userid}")
	public ResponseEntity<List<HouseRent>> listInterest(@PathVariable String userid) {
		try {
			System.out.println(userid);
			List<HouseRent> list = wishListService.search(userid);
			for(HouseRent r:list)
				System.out.println(r);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<HouseRent>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<List<HouseRent>>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<HouseRent>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/{userid}/{rentNo}")
	public ResponseEntity<List<HouseRent>> create(@PathVariable String userid, @PathVariable int rentNo) {
		try {
			wishListService.registWishRent(userid, rentNo);
			List<HouseRent> list = wishListService.search(userid);
			return new ResponseEntity<List<HouseRent>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<HouseRent>>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/{userid}/{rentNo}")
	public ResponseEntity<List<HouseRent>> delete(@PathVariable String userid, @PathVariable int rentNo) {
		try {
			//System.out.println(map.get("userid")+" "+map.get("rentNo"));
			wishListService.removeWishRent(userid, rentNo);
			List<HouseRent> list = wishListService.search(userid);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<HouseRent>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<List<HouseRent>>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<HouseRent>>(HttpStatus.NOT_FOUND);
		}
	}

}
