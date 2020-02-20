package com.wildbody.kite.Service;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.MemberArticle;

import java.util.List;

public interface ArticleService {

	public int initArticle(Article article);

	public int articleInsert(Article article);

	public int articleUpdate(Article article);

	public int articleDelete(String id);

	public Article articleInfo(int articleid);

	public List<Article> articleList();

	public Article oneArticle(int articleid);
	
	public Article oneScarpArticle(int memberid, int articleid);

	public List<Article> infi(String company);
	
	public List<Article> myKeywordArticle(int memberid, String keyword);
	
	public List<Article> companyKeywordArticle(String keyword);
}
