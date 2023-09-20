package kz.armank.library.service.imp;

import kz.armank.library.dao.abstracts.CategoryDao;
import kz.armank.library.model.Category;
import kz.armank.library.repo.CategoryRepo;
import kz.armank.library.service.abstracts.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryDao categoryDao;


    @Override
    @Transactional(readOnly = true)
    public Category getCategoryByCategoryName(String name) {
        return categoryDao.getCategoryByCategoryName(name);
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        return categoryDao.createCategory(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Override
    public Set<Category> findSetCategories(Set<Category> categories) {
        return categoryDao.findSetCategories(categories);
    }
}
