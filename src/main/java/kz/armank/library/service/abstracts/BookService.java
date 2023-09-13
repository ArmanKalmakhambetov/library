package kz.armank.library.service.abstracts;


import kz.armank.library.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book addBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Long id);

}
