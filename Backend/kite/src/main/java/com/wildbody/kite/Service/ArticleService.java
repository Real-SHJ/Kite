package com.wildbody.kite.Service;

import com.wildbody.kite.Dto.Article;
import java.util.List;

public interface ArticleService {

    int initArticle(Article article);

    int articleInsert(Article article);

    int articleUpdate(Article article);

    int articleDelete(String id);

    Article articleInfo(String id);

    List<Article> articleList();
}