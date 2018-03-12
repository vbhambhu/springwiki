package springwiki.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springwiki.demo.entities.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    //Category findByName(String name);


}
