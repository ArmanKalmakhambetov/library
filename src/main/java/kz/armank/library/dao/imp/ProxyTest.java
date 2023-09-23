package kz.armank.library.dao.imp;

import kz.armank.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProxyTest {


    private static List<Book> books = new ArrayList<>();

    public ProxyTest() {

    }


    public List<Book> getBooks() {
        return books;
    }


    public void add(List<Book> bookList) {
        books.addAll(bookList);
    }
}
