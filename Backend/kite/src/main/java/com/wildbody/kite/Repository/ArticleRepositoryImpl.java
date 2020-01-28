package com.wildbody.kite.Repository;

import com.wildbody.kite.Dto.Article;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ArticleRepositoryImpl")
public class ArticleRepositoryImpl implements ArticleRepository {

    @Autowired
    SqlSession session;


    @Override
    public int articleInsert(Article a) {
        return session.insert("kite.article.insert", a);
    }

    @Override
    public int articleUpdate(Article a) {
        return session.update("kite.article.update", a);
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