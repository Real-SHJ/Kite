package com.wildbody.kite.Repository;

import com.wildbody.kite.Dto.Article;
import java.util.List;

public interface ArticleRepository {

    public int articleInsert(Article a);

    public int articleUpdate(Article a);

    public int articleDelete(int id);

    public Article articleInfo(int id);

    public List<Article> articleList();
}