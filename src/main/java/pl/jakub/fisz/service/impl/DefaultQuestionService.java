package pl.jakub.fisz.service.impl;

import com.google.common.collect.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.fisz.api.request.CreateQuestionRequest;
import pl.jakub.fisz.api.response.QuestionView;
import pl.jakub.fisz.data.Category;
import pl.jakub.fisz.data.Question;
import pl.jakub.fisz.repository.CategoryRepository;
import pl.jakub.fisz.repository.QuestionRepository;
import pl.jakub.fisz.service.QuestionService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class DefaultQuestionService implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public QuestionView saveQuestion(CreateQuestionRequest createQuestionRequest) {

        Category category = categoryRepository.findOne(createQuestionRequest.getCategoryId());

        Question questionToSave = Question.builder()
                .question(createQuestionRequest.getQuestion())
                .answer(createQuestionRequest.getAnswer())
                .category(category)
                .build();
        return QuestionView.from(questionRepository.save(questionToSave));
    }

    @Override
    public List<QuestionView> getQuestions() {
        return Streams.stream(questionRepository.findAll())
                .map(QuestionView::from)
                .collect(toList());
    }

    @Override
    public List<QuestionView> getQuestionByCategory(Long categoryId) {
        Category category = categoryRepository.findOne(categoryId);

        return questionRepository.findByCategory(category).stream()
                .map(QuestionView::from)
                .collect(toList());
    }
}
