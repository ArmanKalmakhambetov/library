package kz.armank.library.dao.imp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import kz.armank.library.dao.abstracts.CategoryDao;
import kz.armank.library.model.Category;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CategoryDaoImp implements CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category getCategoryByCategoryName(String name) {
        Category findCategory = entityManager
                .createQuery("from Category category where name = :name", Category.class)
                .setParameter("name", name)
                .setHint("org.hibernate.cacheable", true)
                .getSingleResult();
        Hibernate.initialize(findCategory.getBooks());

        return findCategory;
    }

    @Override
    public Category createCategory(Category category) {
        return entityManager.merge(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return entityManager.createQuery("from Category ", Category.class)
                .setHint("org.hibernate.cacheable", true)
                .getResultList();
    }

    @Override
    public Set<Category> findSetCategories(Set<Category> categories) {
        Set<Category> categorySet = new HashSet<>();
        for (Category category : categories) {
            categorySet.add(getCategoryByCategoryName(category.getName()));
        }
        return categorySet;
    }

}
