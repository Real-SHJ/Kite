package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Article;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ArticleRepositoryImpl")
public class ArticleRepositoryImpl implements ArticleRepository {

    @Autowired
    SqlSession session;


    @Override
    public int initArticle(Article article) {
        return session.insert("kite.article.insert", article);
    }

    @Override
    public int articleInsert(Article aritcle) {
        return session.insert("kite.article.insert", aritcle);
    }

    @Override
    public int articleUpdate(Article aritcle) {
        return session.update("kite.article.update", aritcle);
    }

    @Override
    public int articleDelete(int id) {
        return session.delete("kite.article.delete", id);
    }

    @Override
    public Article articleInfo(int id) {
        Article dto = session.selectOne("kite.article.selectOne", id);
        return dto;
    }

    @Override
    public List<Article> articleList() {
        List<Article> list = session.selectList("kite.article.selectList");
        return list;
    }
}