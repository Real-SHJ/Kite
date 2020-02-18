package com.wildbody.kite.DTO;

import java.util.List;

import lombok.Data;

//-------------------Message 보내는 DTO
@Data
public class Message{
    private int sno;
    private int sendid;
    private int receiveid;
    private List<Integer> receivelist;
    private int articleid;

}
