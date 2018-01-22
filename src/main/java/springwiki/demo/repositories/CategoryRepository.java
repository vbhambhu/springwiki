package springwiki.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import springwiki.demo.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    //Category findByName(String name);


}
