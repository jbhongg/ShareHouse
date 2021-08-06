package com.ssafy.sharehouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.sharehouse.dto.Article;
import com.ssafy.sharehouse.dto.PageNavigation;
import com.ssafy.sharehouse.model.repository.ArticleRepo;

@Service
public class ArticleServiceImpl implements ArticleService {

	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	@Transactional
	public boolean writeArticle(Article Article) throws Exception {
		if(Article.getSubject() == null || Article.getContent() == null) {
			throw new Exception();
		}
		ArticleRepo ArticleRepo = sqlSession.getMapper(ArticleRepo.class);
		int k = ArticleRepo.writeArticle(Article);
		logger.debug("글번호 : {}", Article.getArticleno());
		if(Article.getFileInfos() != null) {
			logger.debug("업로드 파일 수 : {}", Article.getFileInfos().size());
			//ArticleRepo.fileRegist(Article);
		}
		return k==1;
	}

	@Override
	public List<Article> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pg") == null ? "1" : map.get("pg"));
//		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		param.put("start", start);
		param.put("spp", sizePerPage);
		return sqlSession.getMapper(ArticleRepo.class).listArticle(param);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		int naviSize = 10;
		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(ArticleRepo.class).getTotalCount(map);
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
	public Article getArticle(int articleno) throws Exception {
		return sqlSession.getMapper(ArticleRepo.class).getArticle(articleno);
	}

	@Override
	public boolean modifyArticle(Article Article) throws Exception {
		return sqlSession.getMapper(ArticleRepo.class).modifyArticle(Article)==1;
	}

	@Override
	public boolean deleteArticle(int articleno) throws Exception {
		return sqlSession.getMapper(ArticleRepo.class).deleteArticle(articleno)==1;
	}
	


}
