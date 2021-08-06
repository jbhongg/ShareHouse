package com.ssafy.sharehouse.model.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.sharehouse.dto.Article;

@Repository
public interface ArticleRepo {
	public int writeArticle(Article guestBookDto) throws SQLException;
	public List<Article> listArticle(Map<String, Object> map) throws SQLException;
	public int getTotalCount(Map<String, String> map) throws SQLException;
	
	public Article getArticle(int articleno) throws SQLException;
	public int modifyArticle(Article guestBookDto) throws SQLException;
	public int deleteArticle(int articleno) throws SQLException;
}
