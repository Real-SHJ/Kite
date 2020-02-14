package com.wildbody.kite.DTO;

import java.util.List;
import lombok.Data;

@Data
public class Member {

	private int memberid;
	private String email;
	private String pw;
	private String lastname;
	private String firstname;
	private String birthday;
	private String gender;
	private String area;
	private String job;
	private String company;
	private String image;
	private List<Article> articleList;
	private List<String> keywordList;

}