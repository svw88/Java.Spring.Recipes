package app.repository.recipes;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeStore extends CrudRepository<Recipe, Long> {

    @Query("select r from Recipe r where UPPER(r.name) like UPPER(CONCAT('%', ?1,'%'))")
    List<Recipe> findByName(String name, Sort sort);

    @Query("select r from Recipe r where UPPER(r.category) = UPPER(?1)")
    List<Recipe> findByCategory(String category, Sort sort);
}
