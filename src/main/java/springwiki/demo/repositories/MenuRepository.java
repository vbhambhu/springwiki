package springwiki.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springwiki.demo.entities.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
