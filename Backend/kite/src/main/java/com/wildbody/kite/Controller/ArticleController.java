package com.wildbody.kite.Controller;

import com.wildbody.kite.Dto.Article;
<<<<<<< HEAD:Backend/kite/src/main/java/com/wildbody/kite/Controller/ArticleController.java

=======
>>>>>>> a5e070104e235c060f0d792e4e28fb5758f344bc:Backend/kite/src/main/java/com/wildbody/kite/Controller/ArticleController.java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/news/")
public class ArticleController {
  @GetMapping("select")
  public Article select(Article news) {
    return null;
  }
}
