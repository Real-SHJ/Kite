package com.wildbody.kite.Service;

import java.util.List;

import com.wildbody.kite.Dto.Article;

public interface ArticleService{
	public int articleInsert(Article a);

	public int articleUpdate(Article a);

	public int articleDelete(String id);

	public Article articleInfo(String id);

	public List<Article> articleList();
}