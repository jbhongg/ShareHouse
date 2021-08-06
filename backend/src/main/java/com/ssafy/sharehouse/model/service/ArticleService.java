package com.ssafy.sharehouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.sharehouse.dto.Article;
import com.ssafy.sharehouse.dto.PageNavigation;

public interface ArticleService {
	public boolean writeArticle(Article guestBookDto) throws Exception;
	public List<Article> listArticle(Map<String, String> map) throws Exception;
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	
	public Article getArticle(int articleno) throws Exception;
	public boolean modifyArticle(Article guestBookDto) throws Exception;
	public boolean deleteArticle(int articleno) throws Exception;
	
}
