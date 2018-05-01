package pl.jakub.fisz.api.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CreateCategoryRequest {

    String name;

    String description;

    Long userId;

}
