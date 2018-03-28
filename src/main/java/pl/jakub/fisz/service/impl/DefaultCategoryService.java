package pl.jakub.fisz.service.impl;

import com.google.common.collect.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.fisz.api.request.CreateCategoryRequest;
import pl.jakub.fisz.api.response.CategoryView;
import pl.jakub.fisz.data.Category;
import pl.jakub.fisz.repository.CategoryRepository;
import pl.jakub.fisz.service.CategoryService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class DefaultCategoryService implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryView createCategory(CreateCategoryRequest createCategoryRequest) {
        Category categoryToSave = Category.builder()
                .name(createCategoryRequest.getName())
                .userId(createCategoryRequest.getUserId())
                .build();

        return CategoryView.from(categoryRepository.save(categoryToSave));
    }

    @Override
    public List<CategoryView> getCategories() {
        return Streams.stream(categoryRepository.findAll())
                .map(CategoryView::from)
                .collect(toList());
    }
}
