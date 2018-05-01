package pl.jakub.fisz.service;

import pl.jakub.fisz.api.request.CreateCategoryRequest;
import pl.jakub.fisz.api.response.CategoryView;

import java.util.List;

public interface CategoryService {

    CategoryView createCategory(CreateCategoryRequest createCategoryRequest);

    List<CategoryView> getCategories();

    boolean deleteCategory(Long categoryId);
}
