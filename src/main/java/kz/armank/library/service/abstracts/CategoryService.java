package kz.armank.library.service.abstracts;

import kz.armank.library.model.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    Category getCategoryByCategoryName (String name);

    Category createCategory (Category category);

    List<Category> getAllCategories();

    Set<Category> findSetCategories(Set<Category> categories);
}
