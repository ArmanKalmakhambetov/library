package kz.armank.library.dao.abstracts;

import kz.armank.library.model.Book;

import java.util.List;

public interface BookDao {

    Book createBook(Book book);

    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book updateBook(Book book);

    void deleteBook(Long id);

    void saveCategoryToUser(Long userId, String categoryName);

    void removeCategoryFromBook(Long bookId, String categoryName);
}
