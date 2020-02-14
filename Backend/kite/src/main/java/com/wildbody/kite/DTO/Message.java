package com.wildbody.kite.DTO;

import lombok.Data;

//-------------------Message 보내는 DTO
@Data
public class Message {
	private int sendID;
	private int receiveID;
	private String mContents;
	private int articleID;
}
