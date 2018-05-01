package pl.jakub.fisz.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jakub.fisz.api.request.CreateQuestionRequest;
import pl.jakub.fisz.api.response.QuestionView;
import pl.jakub.fisz.service.QuestionService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/questions")
public class QuestionsEndpoint {

    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public QuestionView createQuestion(@RequestBody CreateQuestionRequest createQuestionRequest) {
        log.info("Creating question: {}.", createQuestionRequest);
        return questionService.saveQuestion(createQuestionRequest);
    }

    @GetMapping(value = "",
            produces = APPLICATION_JSON_VALUE)
    public List<QuestionView> getQuestions() {
        log.info("Getting all questions.");
        return questionService.getQuestions();
    }

    @GetMapping(value = "/{id}",
            produces = APPLICATION_JSON_VALUE)
    public List<QuestionView> getQuestionsByCategory(@PathVariable("id") Long categoryId) {
        log.info("Getting questions with category {}.", categoryId);
        return questionService.getQuestionByCategory(categoryId);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteQuestion(@PathVariable("id") Long questionId) {
        log.info("Deleting questions with id {}.", questionId);
        return questionService.deleteQuestion(questionId);
    }


}
