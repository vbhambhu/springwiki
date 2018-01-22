package springwiki.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springwiki.demo.entities.Page;
import springwiki.demo.repositories.PageRepository;

import java.util.List;

@Service
public class PageService {

    @Autowired
    PageRepository pageRepository;


    public List<Page> findAll() {
       return pageRepository.findAll();
    }

    public void create(Page page) {
        pageRepository.save(page);
    }
}
