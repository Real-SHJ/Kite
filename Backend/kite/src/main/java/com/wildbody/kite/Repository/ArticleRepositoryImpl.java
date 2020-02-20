package com.wildbody.kite.Repository;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.MemberArticle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
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
	public Article articleInfo(int articleid) {
		return session.selectOne("kite.article.selectOne", articleid);
	}

	@Override
	public List<Article> articleList() {
		return session.selectList("kite.article.selectList");
	}

	@Override
	public Article oneArticle(int articleid) {
		return session.selectOne("kite.article.oneArticle", articleid);
	}
	
	@Override
	public Article oneScrapArticle(MemberArticle ma) {
		Article article = oneArticle(ma.getArticleid());
		String content = session.selectOne("kite.article.oneScrapArticle", ma);
		article.setContent(content);
		return article;
	}

	@Override
	public List<Article> infi(String company) {
		return session.selectList("kite.article.infi", company);
	}
	
	@Override
	public List<Article> myKeywordArticle(int memberid, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberid", memberid);
		map.put("keyword", keyword);
		return session.selectList("kite.keyword.mykeywordarticle", map);
	}
	
	@Override
	public List<Article> companyKeywordArticle(String keyword) {
		return session.selectList("kite.keyword.companyKeywordArticle", keyword);
	}
}
