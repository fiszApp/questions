package pl.jakub.fisz.service.impl;

import com.google.common.collect.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakub.fisz.api.request.CreateQuestionRequest;
import pl.jakub.fisz.api.request.EditQuestionRequest;
import pl.jakub.fisz.api.response.QuestionView;
import pl.jakub.fisz.data.Category;
import pl.jakub.fisz.data.Question;
import pl.jakub.fisz.repository.CategoryRepository;
import pl.jakub.fisz.repository.QuestionRepository;
import pl.jakub.fisz.service.QuestionService;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@Service
public class DefaultQuestionService implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
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
    @Transactional(readOnly = true)
    public List<QuestionView> getQuestions() {
        return Streams.stream(questionRepository.findAll())
                .map(QuestionView::from)
                .collect(toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionView> getQuestionByCategory(Long categoryId) {
        Category category = categoryRepository.findOne(categoryId);

        return questionRepository.findByCategory(category).stream()
                .map(QuestionView::from)
                .collect(toList());
    }

    @Override
    @Transactional
    public boolean deleteQuestion(Long id) {
        if (isNull(questionRepository.findOne(id))) {
            return false;
        }
        questionRepository.delete(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionView getQuestion(Long id) {
        return QuestionView.from(questionRepository.findOne(id));
    }

    @Override
    @Transactional
    public boolean editQuestion(Long id, EditQuestionRequest request) {
        Question question = questionRepository.findOne(id);

        if (isNull(question)) {
            return false;
        }

        editQuestion(request, question);
        questionRepository.save(question);
        return true;
    }

    private void editQuestion(EditQuestionRequest request, Question question) {
        question.setAnswer(request.getAnswer());
        question.setQuestion(request.getQuestion());

        Category category = categoryRepository.findOne(request.getCategoryId());
        question.setCategory(category);
    }
}
