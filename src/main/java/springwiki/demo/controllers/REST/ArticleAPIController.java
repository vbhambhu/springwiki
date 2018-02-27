package springwiki.demo.controllers.REST;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springwiki.demo.entities.Article;
import springwiki.demo.services.ArticleService;

import java.util.List;

@RestController
public class ArticleAPIController {


    @Autowired
    ArticleService articleService;

    @ResponseBody
    @RequestMapping(value = "api/article/search", method = RequestMethod.GET)
    public List<Article> searchArticle(@RequestParam(name = "q") String query){

        return articleService.findbyKeyword(query);

    }


}
