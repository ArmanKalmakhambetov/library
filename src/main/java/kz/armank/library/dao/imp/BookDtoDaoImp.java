package kz.armank.library.dao.imp;

import kz.armank.library.dao.abstracts.BookDtoDao;
import kz.armank.library.dto.BookDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class BookDtoDaoImp implements BookDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<BookDto> getBookDto() {
        BookDto bookDto = entityManager.createQuery(
                "select new kz.armank.library.dto.BookDto\n" +
        "(sum (b.pageVolume),\n" +
        "count (b)\n" +
        ") from Book as b\n",
                BookDto.class)
                .getSingleResult();
         return Optional.of(bookDto);
    }
}