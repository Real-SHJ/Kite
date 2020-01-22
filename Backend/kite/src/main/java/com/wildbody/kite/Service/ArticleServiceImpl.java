package com.wildbody.kite.Service;

import com.wildbody.kite.Dto.Article;
import com.wildbody.kite.Repository.ArticleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ArticleServiceImpl")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository repo;

    public ArticleServiceImpl() {
        super();
    }

    @Override
    public int articleInsert(String company, String title, String summary, String newspaper,
        String publicationDate,
        String url, String image, String content, String keyword) {
        return repo.articleInsert(
            new Article(0, company, title, summary, newspaper, publicationDate, url, image, content,
                keyword));
    }

    @Override
    public int articleUpdate(int articleid, String company, String title, String summary,
        String newspaper,
        String publicationDate, String url, String image, String content, String keyword) {
        return repo.articleUpdate(
            new Article(articleid, company, title, summary, newspaper, publicationDate, url,
                image, content, keyword));
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