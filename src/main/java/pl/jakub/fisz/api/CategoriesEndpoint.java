package pl.jakub.fisz.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jakub.fisz.api.request.CreateCategoryRequest;
import pl.jakub.fisz.api.response.CategoryView;
import pl.jakub.fisz.service.CategoryService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/categories")
public class CategoriesEndpoint {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public CategoryView createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        log.info("Creating category {}.", createCategoryRequest);
        return categoryService.createCategory(createCategoryRequest);
    }

    @GetMapping(value = "",
            produces = APPLICATION_JSON_VALUE)
    public List<CategoryView> getCategories() {
        log.info("Getting all categories.");
        return categoryService.getCategories();
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteCategory(@PathVariable("id") Long categoryId) {
        log.info("Deleting category with id {}.", categoryId);
        return categoryService.deleteCategory(categoryId);
    }

}
