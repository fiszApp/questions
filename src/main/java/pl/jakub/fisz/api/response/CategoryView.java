package pl.jakub.fisz.api.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.jakub.fisz.data.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CategoryView {

    Long id;

    String name;

    Long userId;

    String description;

    public static CategoryView from(@NonNull Category category) {
        return CategoryView.builder()
                .id(category.getId())
                .name(category.getName())
                .userId(category.getUserId())
                .description(category.getDescription())
                .build();
    }

}
