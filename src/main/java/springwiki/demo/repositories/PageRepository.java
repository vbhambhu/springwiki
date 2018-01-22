package springwiki.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import springwiki.demo.entities.Page;

public interface PageRepository extends JpaRepository<Page, Long> {

    //Page findBySlug(String slug);


}
