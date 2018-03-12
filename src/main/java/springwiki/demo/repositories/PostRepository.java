package springwiki.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import springwiki.demo.entities.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findBySlugAndIdNot(String newSlug, Long id);

    List<Post> findAllByStatus(boolean status);

    List<Post> findByTitleContaining(String query);

    Post findBySlug(String slug);
}
