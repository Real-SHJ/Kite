package com.wildbody.kite.Controller;

import com.wildbody.kite.Dto.News;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/news/")
public class NewController {
  @GetMapping("select")
  public News select(News news) {
    return null;
  }
}
