package kz.armank.library.service.imp;

import kz.armank.library.model.Category;
import kz.armank.library.repo.CategoryRepo;
import kz.armank.library.service.abstracts.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return categoryRepo.getById(id);
    }

    @Override
    @Transactional
    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    @Transactional
    public Category updateCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
