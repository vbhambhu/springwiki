package springwiki.demo.controlleradvices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import springwiki.demo.repositories.MenuRepository;


@ControllerAdvice
public class AppAdvice {

    @Autowired
    MenuRepository menuRepository;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("menuItems", menuRepository.findAll());
    }




}
