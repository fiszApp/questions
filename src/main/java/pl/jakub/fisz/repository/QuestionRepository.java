package pl.jakub.fisz.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.fisz.data.Category;
import pl.jakub.fisz.data.Question;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findByCategory(Category category);

}