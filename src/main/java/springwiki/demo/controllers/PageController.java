package springwiki.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springwiki.demo.models.Page;
import springwiki.demo.repositories.PageRepository;

@Controller
public class PageController {

    @Autowired
    PageRepository postRepository;

    @RequestMapping(value = "/page/new", method = RequestMethod.GET)
    public String projectDashboard() {
        return "page/new";
    }


    @RequestMapping(value = "/page/{slug}", method = RequestMethod.GET)
    public String showPost(
            Model model,
            @PathVariable(required = true) String slug) {

        Page post = postRepository.findBySlug(slug);


        System.out.println(post.getCategory().getName());

        model.addAttribute("post", post);

        return "post/view";
    }

}
