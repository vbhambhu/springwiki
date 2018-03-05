package springwiki.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import springwiki.demo.entities.Article;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Article findBySlugAndIdNot(String newSlug, Long id);

    List<Article> findAllByStatus(boolean status);

    List<Article> findByTitleContaining(String query);

    Article findBySlug(String slug);
}
