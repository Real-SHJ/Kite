package com.wildbody.kite.DTO;

import lombok.Data;

//-------------------Message 보내는 DTO
@Data
public class Message{
    private int sno;
    private int sendid;
    private int receiveid;
    private int articleid;
}
