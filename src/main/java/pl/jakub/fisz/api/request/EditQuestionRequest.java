package pl.jakub.fisz.api.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EditQuestionRequest {

    String question;

    String answer;

    Long categoryId;

}
