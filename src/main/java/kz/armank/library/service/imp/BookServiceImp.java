package kz.armank.library.service.imp;

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

    private final BookRepo bookRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepo.getById(id);
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }
}
