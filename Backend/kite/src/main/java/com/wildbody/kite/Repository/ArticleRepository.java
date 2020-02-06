package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Article;
import java.util.List;

public interface ArticleRepository {

    int initArticle(Article article);

    public int articleInsert(Article article);

    public int articleUpdate(Article article);

    public int articleDelete(int id);

    public Article articleInfo(Article article);

    public List<Article> articleList();
}