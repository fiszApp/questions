package pl.jakub.fisz.service.impl;

import com.google.common.collect.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakub.fisz.api.request.CreateCategoryRequest;
import pl.jakub.fisz.api.response.CategoryView;
import pl.jakub.fisz.data.Category;
import pl.jakub.fisz.repository.CategoryRepository;
import pl.jakub.fisz.service.CategoryService;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@Service
public class DefaultCategoryService implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryView createCategory(CreateCategoryRequest createCategoryRequest) {
        Category categoryToSave = Category.builder()
                .name(createCategoryRequest.getName())
                .description(createCategoryRequest.getDescription())
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

    @Override
    @Transactional
    public boolean deleteCategory(Long categoryId) {
        if (isNull(categoryRepository.findOne(categoryId))) {
            return false;
        }
        categoryRepository.delete(categoryId);
        return true;
    }
}
