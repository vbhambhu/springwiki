package springwiki.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springwiki.demo.entities.Menu;
import springwiki.demo.repositories.MenuRepository;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;


    public List<Menu> getAllMenuItems() {
        return menuRepository.findAll();
    }
}
