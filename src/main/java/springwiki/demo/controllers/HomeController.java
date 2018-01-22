package springwiki.demo.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springwiki.demo.models.Category;
import springwiki.demo.models.Page;
import springwiki.demo.repositories.CategoryRepository;
import springwiki.demo.repositories.PageRepository;

@Controller
public class HomeController {

    @Autowired
    PageRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String projectDashboard() {

//        Category postCategory = new Category();
//        postCategory.setName("Categorty 1");
//        Page page = new Page();
//        page.setTitle("Hello World");
//        page.setContent("Something");
//        page.setCategory(postCategory);
//        postRepository.save(page);

        for (Category category : categoryRepository.findAll()) {
            System.out.println(category);
        }


        return "home";

    }
}
