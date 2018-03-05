package springwiki.demo.controlleradvices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import springwiki.demo.repositories.CategoryRepository;

@ControllerAdvice
public class CategoriesAdvice {

    @Autowired
    CategoryRepository categoryRepository;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
    }
}
