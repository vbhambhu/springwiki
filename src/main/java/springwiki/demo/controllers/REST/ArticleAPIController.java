package springwiki.demo.controllers.REST;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springwiki.demo.entities.Post;
import springwiki.demo.services.PostService;

import java.util.List;

@RestController
public class ArticleAPIController {


    @Autowired
    PostService articleService;

    @ResponseBody
    @RequestMapping(value = "api/article/search", method = RequestMethod.GET)
    public List<Post> searchArticle(@RequestParam(name = "q") String query){

        return articleService.findbyKeyword(query);

    }


}
