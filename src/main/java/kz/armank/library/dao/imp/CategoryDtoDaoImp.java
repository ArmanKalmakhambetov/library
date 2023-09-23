package kz.armank.library.dao.imp;

import kz.armank.library.dao.abstracts.CategoryDtoDao;
import kz.armank.library.dto.CategoryDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDtoDaoImp implements CategoryDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<CategoryDto>> getCategoryDto() {
        List<CategoryDto> list = new ArrayList<>();
        list.add((new CategoryDto("Роман", 100L)));
//        return Optional.of(entityManager.createQuery(
//                """
//                        SELECT new kz.armank.library.dto.CategoryDto(c.name, count (b))
//                        FROM Book b
//                        JOIN b.categories c
//                        GROUP BY c.name
//                        """,
//                CategoryDto.class)
//                .getResultList());
        return Optional.of(list);
    }
}
