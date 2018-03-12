package springwiki.demo.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springwiki.demo.entities.Post;
import springwiki.demo.repositories.CategoryRepository;
import springwiki.demo.services.PostService;

@Controller
public class HomeController {

    @Autowired
    PostService postService;

    @Autowired
    CategoryRepository categoryRepository;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {

        model.addAttribute("posts", postService.getAllByHome());
        return "home";

    }


    @RequestMapping(value = "/tutorial/{slug}", method = RequestMethod.GET)
    public String articleView(@PathVariable String slug,  Model model) {


        Post post = postService.getBySlug(slug);

        model.addAttribute ("post", post);
        model.addAttribute ("metaTitle", post.getTitle());
        model.addAttribute ("metaDescription", post.getMetaDescription());

        return "article";

    }







}
