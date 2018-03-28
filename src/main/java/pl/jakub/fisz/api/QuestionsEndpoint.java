package pl.jakub.fisz.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jakub.fisz.api.request.CreateQuestionRequest;
import pl.jakub.fisz.api.response.QuestionView;
import pl.jakub.fisz.service.QuestionService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping("/questions")
public class QuestionsEndpoint {

    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public QuestionView createQuestion(@RequestBody CreateQuestionRequest createQuestionRequest) {
        return questionService.saveQuestion(createQuestionRequest);
    }

    @GetMapping(value = "",
            produces = APPLICATION_JSON_VALUE)
    public List<QuestionView> getQuestions() {
        return questionService.getQuestions();
    }

    @GetMapping(value = "/{id}",
            produces = APPLICATION_JSON_VALUE)
    public List<QuestionView> getQuestionsByCategory(@PathVariable("id") Long categoryId) {
        return questionService.getQuestionByCategory(categoryId);
    }


}
