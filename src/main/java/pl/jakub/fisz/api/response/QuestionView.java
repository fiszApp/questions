package pl.jakub.fisz.api.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.jakub.fisz.data.Question;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class QuestionView {

    Long id;

    String question;

    String answer;

    CategoryView category;

    public static QuestionView from(Question question) {
        return QuestionView.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .answer(question.getAnswer())
                .category(CategoryView.from(question.getCategory()))
                .build();
    }
}
