package kz.armank.library.dao.imp;

import kz.armank.library.dao.abstracts.CategoryDtoDao;
import kz.armank.library.dto.CategoryDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Repository
public class CategoryDtoDaoImpl implements CategoryDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<CategoryDto>> getCategoryDto() {
        return Optional.of(entityManager.createQuery(
                "SELECT new kz.armank.library.dto.CategoryDto(c.name, count (b))\n" +
                "FROM Book b\n" +
                "JOIN b.categories c\n" +
                "GROUP BY c.name\n",
                CategoryDto.class)
                .getResultList());
    }
}
