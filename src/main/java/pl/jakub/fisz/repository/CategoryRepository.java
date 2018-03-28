package pl.jakub.fisz.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.fisz.data.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
