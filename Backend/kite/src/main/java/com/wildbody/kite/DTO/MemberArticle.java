package com.wildbody.kite.DTO;

import lombok.Data;

@Data
public class MemberArticle {

	private int memberid;
	private int articleid;
	private String company;
	private String content;

}