package com.wildbody.kite.Dto;

import java.util.List;
import lombok.Data;

@Data
public class Member {

    private int id;
    private String email;
    private String pw;
    private String lastname;
    private String firstname;
    private String birthday;
    private int gender;
    private String area;
    private String job;
    private String company;
    private String image;
    private List<Article> articleList;
    private List<String> keywordList;
    private String refreshToken;

}