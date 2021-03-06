package pl.jakub.fisz.service;

import pl.jakub.fisz.api.request.CreateQuestionRequest;
import pl.jakub.fisz.api.request.EditQuestionRequest;
import pl.jakub.fisz.api.response.QuestionView;

import java.util.List;

public interface QuestionService {

    QuestionView saveQuestion(CreateQuestionRequest createQuestionRequest);

    List<QuestionView> getQuestions();

    List<QuestionView> getQuestionByCategory(Long categoryId);

    boolean deleteQuestion(Long id);

    QuestionView getQuestion(Long id);

    boolean editQuestion(Long id, EditQuestionRequest request);
}
