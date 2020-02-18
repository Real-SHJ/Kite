package com.wildbody.kite.DTO;

import lombok.Data;

@Data
public class MemberKeyword {
	private int memberid;
	private int articleid;
	private String keyword;
	private int count;
}
