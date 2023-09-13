package kz.armank.library.service.abstracts;

import kz.armank.library.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category addCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(Long id);
}
