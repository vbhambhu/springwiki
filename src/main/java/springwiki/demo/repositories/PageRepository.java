package springwiki.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import springwiki.demo.models.Page;

public interface PageRepository extends JpaRepository<Page, Long> {

    Page findBySlug(String slug);


}
