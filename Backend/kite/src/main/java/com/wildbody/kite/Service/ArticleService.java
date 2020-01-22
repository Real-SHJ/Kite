package com.wildbody.kite.Service;

import java.util.List;

import com.wildbody.kite.Dto.Article;

public interface ArticleService{
	public int articleInsert(String company, String title, String summary, String newspaper,
			String publicationDate, String url, String image, String content, String keyword);

	public int articleUpdate(int articleid, String company, String title, String summary, String newspaper,
			String publicationDate, String url, String image, String content, String keyword);

	public int articleDelete(String id);

	public Article articleInfo(String id);

	public List<Article> articleList();
}