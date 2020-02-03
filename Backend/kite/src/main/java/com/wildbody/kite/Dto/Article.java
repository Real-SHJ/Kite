package com.wildbody.kite.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article {

    private int articleid;
    private String company;
    private String title;
    private String summary;
    private String newspaper;
    private String publicationDate;
    private String url;
    private String image;
    private String content;
    private String keyword;

}
