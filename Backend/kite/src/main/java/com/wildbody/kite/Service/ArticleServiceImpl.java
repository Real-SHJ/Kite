package com.wildbody.kite.Service;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.Repository.ArticleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository repo;


    @Override
    public int initArticle(Article article) {
        return repo.initArticle(article);
    }

    @Override
    public int articleInsert(Article a) {
        return repo.articleInsert(a);
    }

    @Override
    public int articleUpdate(Article a) {
        return repo.articleUpdate(a);
    }

    @Override
    public int articleDelete(String id) {
        return repo.articleDelete(Integer.parseInt(id));
    }

    @Override
    public Article articleInfo(String id) {
        return repo.articleInfo(Integer.parseInt(id));
    }

    @Override
    public List<Article> articleList() {
        return repo.articleList();
    }

}