package recipe.server.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe.server.recipes.entity.Images;

public interface ImageRepository extends JpaRepository<Images, Long> {
}
