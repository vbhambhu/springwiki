package springwiki.demo.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springwiki.demo.entities.Category;
import springwiki.demo.repositories.CategoryRepository;
import springwiki.demo.repositories.PageRepository;
import springwiki.demo.services.ArticleService;

@Controller
public class HomeController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryRepository categoryRepository;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {

//        Category postCategory = new Category();
//        postCategory.setName("Categorty 1");
//        Page page = new Page();
//        page.setTitle("Hello World");
//        page.setContent("Something");
//        page.setCategory(postCategory);
//        postRepository.save(page);

//        for (Category category : categoryRepository.findAll()) {
//            System.out.println(category);
//        }

        model.addAttribute("articles", articleService.getAllByHome());

        return "home";

    }


    @RequestMapping(value = "/tutorial/{slug}", method = RequestMethod.GET)
    public String articleView(@PathVariable String slug,  Model model) {

        model.addAttribute ("article", articleService.getBySlug(slug));

        return "article";

    }







}
