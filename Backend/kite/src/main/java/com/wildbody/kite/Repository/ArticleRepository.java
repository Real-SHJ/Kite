package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.MemberArticle;

import java.util.List;

public interface ArticleRepository {

	public int initArticle(Article article);

	public int articleInsert(Article article);

	public int articleUpdate(Article article);

	public int articleDelete(int id);

	public Article articleInfo(int articleid);

	public List<Article> articleList();

	public Article oneArticle(int articleid);
	
	public Article oneScrapArticle(MemberArticle ma);

	public List<Article> infi(String company);

	public List<Article> myKeywordArticle(int memberid, String keyword);
	
	public List<Article> companyKeywordArticle(String keyword);
}