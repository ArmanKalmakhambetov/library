package kz.armank.library.service.imp;

import kz.armank.library.dao.abstracts.BookDao;
import kz.armank.library.model.Book;
import kz.armank.library.repo.BookRepo;
import kz.armank.library.service.abstracts.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImp implements BookService {

    private final BookDao bookRepo;


    @Override
    @Transactional
    public Book createBook(Book book) {
        return bookRepo.createBook(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepo.getBookById(id);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        return bookRepo.updateBook(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepo.deleteBook(id);
    }

    @Override
    @Transactional
    public void saveCategoryToUser(Long bookId, String categoryName) {
        bookRepo.saveCategoryToUser(bookId, categoryName);
    }

    @Override
    @Transactional
    public void removeCategoryFromBook(Long bookId, String categoryName) {
        bookRepo.removeCategoryFromBook(bookId, categoryName);
    }
}
