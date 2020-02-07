package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Article;
import java.util.List;

public interface ArticleRepository {

    int initArticle(Article article);

    int articleInsert(Article article);

    int articleUpdate(Article article);

    int articleDelete(int id);

    Article articleInfo(Article article);

    List<Article> articleList();

    Article oneArticle(int articleid);

    List<Article> infi(String company);
}