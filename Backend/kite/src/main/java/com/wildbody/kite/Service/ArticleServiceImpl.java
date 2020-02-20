package com.wildbody.kite.Service;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.DTO.MemberArticle;
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
    public Article articleInfo(int articleid) {
        return repo.articleInfo(articleid);
    }

    @Override
    public List<Article> articleList() {
        return repo.articleList();
    }

    @Override
    public Article oneArticle(int articleid) {
        return repo.oneArticle(articleid);
    }
    
    @Override
    public Article oneScarpArticle(int memberid, int articleid) {
    	MemberArticle ma = new MemberArticle();
    	ma.setMemberid(memberid);
    	ma.setArticleid(articleid);
    	return repo.oneScrapArticle(ma);
    }

    @Override
    public List<Article> infi(String company) {
        return repo.infi(company);
    }
    

	@Override
	public List<Article> myKeywordArticle(int memberid, String keyword) {
		return repo.myKeywordArticle(memberid, keyword);
	}
	
	@Override
	public List<Article> companyKeywordArticle(String keyword) {
		return repo.companyKeywordArticle(keyword);
	}
}