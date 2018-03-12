package springwiki.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springwiki.demo.entities.Category;
import springwiki.demo.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public List<Category> findAll() {

        return categoryRepository.findAll();

    }

    public void create(Category category) {
        categoryRepository.save(category);
    }

    public Category getById(Long id) {
        return categoryRepository.getOne(id);
    }
}
