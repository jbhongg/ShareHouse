package com.ssafy.sharehouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.ssafy.sharehouse.dto.Article;
import com.ssafy.sharehouse.dto.PageNavigation;
import com.ssafy.sharehouse.model.service.ArticleService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private ArticleService articleService;

	@ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> retrieveBoard(
			@RequestParam(required = false, defaultValue = "10") String spp, @RequestParam(required = false) String key,
			@RequestParam(required = false) String word, @RequestParam(required = false, defaultValue = "1") String pg)
			throws Exception {
		logger.debug("retrieveArticle - 호출");
		Map<String, String> map = new HashMap<>();
		map.put("spp", spp);// sizePerPage
		map.put("pg", pg);// currentPage
		map.put("key", key);
		map.put("word", word);

		Map<String, Object> result = new HashMap<>();
		List<Article> list = articleService.listArticle(map);
		PageNavigation pageNavigation = articleService.makePageNavigation(map);
		if (list != null && !list.isEmpty()) {
			result.put("list", list);
			result.put("navigation", pageNavigation);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Article.class)
	@GetMapping("{no}")
	public ResponseEntity<Article> detailBoard(@PathVariable int no) throws Exception {
		logger.debug("detailArticle - 호출");
		return new ResponseEntity<Article>(articleService.getArticle(no), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Article Article) throws Exception {
		logger.debug("writeArticle - 호출");
		if (articleService.writeArticle(Article)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{no}")
	public ResponseEntity<String> updateBoard(@PathVariable int no, @RequestBody Article Article) throws Exception {
		logger.debug("updateArticle - 호출");
		logger.debug("" + Article);

		if (articleService.modifyArticle(Article)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteBoard(@PathVariable int no) throws Exception {
		logger.debug("deleteArticle - 호출");
		if (articleService.deleteArticle(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
