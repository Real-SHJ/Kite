package com.wildbody.kite.Controller;

import com.wildbody.kite.Dto.Article;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/news/")
public class ArticleController {
  @GetMapping("select")
  public Article select(Article news) {
    return null;
  }
}
