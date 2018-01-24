package springwiki.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import springwiki.demo.entities.User;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
