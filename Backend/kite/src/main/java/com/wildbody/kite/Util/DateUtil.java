package com.wildbody.kite.Util;

import com.wildbody.kite.DTO.Article;
import com.wildbody.kite.Service.ArticleService;
import com.wildbody.kite.Service.MemberService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class DateUtil {
	static final int offset = 6;
	private static DateUtil instance = null;
	@Autowired
	private ArticleService asvc;
	@Autowired
	private MemberService msvc;

	public static DateUtil getInstance() {
		if (instance == null) {
			return new DateUtil();
		}
		return instance;
	}

	public List<Article> makeInfi(int page, List<Article> alist) {
		if (page == 0) {
			return alist;
		}
		int start = (page - 1) * offset;
		int end = start + offset;
		if (end > alist.size()) {
			return alist.subList(start, alist.size());
		}
		return alist.subList(start, end);
	}

	public long getTime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}

	public List<Article> getMatchedList(List<Article> list, String start, String end) {
		List<Article> ret = new ArrayList<>();
		if (start != null && end == null) {
			long startDate = this.getTime(start);
			for (Article a : list) {
				if (this.getTime(a.getPublicationDate()) >= startDate) {
					ret.add(a);
				}
			}
		} else if (start == null && end != null) {
			long endDate = this.getTime(end);
			for (Article a : list) {
				if (this.getTime(a.getPublicationDate()) <= endDate) {
					ret.add(a);
				}
			}
		}
		long startDate = this.getTime(start), endDate = this.getTime(end);
		for (Article a : list) {
			if (this.getTime(a.getPublicationDate()) >= startDate && this.getTime(a.getPublicationDate()) <= endDate) {
				ret.add(a);
			}
		}
		return ret;
	}
}
