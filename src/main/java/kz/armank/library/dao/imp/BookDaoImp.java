package kz.armank.library.dao.imp;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import kz.armank.library.dao.abstracts.BookDao;
import kz.armank.library.model.Book;
import kz.armank.library.model.Category;
import kz.armank.library.util.ProxyCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoImp implements BookDao {


    @PersistenceContext
    private EntityManager entityManager;

    private final ProxyCustom proxyTest;

    @Override
    @Transactional
    public Book createBook(Book book) {
        return entityManager.merge(book);
    }

    @Override
    @Query(name = "Books.findAllBooksWithCategories", value = "SELECT b FROM Book b LEFT JOIN FETCH b.categories")
    public List<Book> getAllBooks() {


        return entityManager.createQuery("from Book").setHint("org.hibernate.cacheable", true).getResultList();

    }

    @Override
    @Query(name = "Book.findBookWithCategories", value = "select b from Book b left join fetch b.categories")
    public Book getBookById(Long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    @Query(name = "Book.updateBookWithCategories", value = "select b from Book b left join fetch b.categories")
    public Book updateBook(Book book) {

        return entityManager.merge(book);
    }

    @Override
    public void deleteBook(Long id) {
        entityManager.remove(getBookById(id));
    }

    @Override
    public void saveCategoryToUser(Long bookId, String categoryName) {
        Book book = getBookById(bookId);
        book.addCategory(entityManager
                .createQuery("from Category c where c.name =:name", Category.class)
                .setParameter("name", categoryName)
                .getSingleResult());
        updateBook(book);
    }


    @Override
    public void removeCategoryFromBook(Long bookId, String categoryName) {
        Book book = getBookById(bookId);

        for (Category category : book.getCategories()) {
            if (category.getName().equals(categoryName)) {
                book.removeCategory(category);
                break;
            }
        }
        updateBook(book);
    }
}
