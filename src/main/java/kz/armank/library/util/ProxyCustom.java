package kz.armank.library.util;

import kz.armank.library.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProxyCustom {


    private static List<Book> books = new ArrayList<>();

    public ProxyCustom() {

    }


    public List<Book> getBooks() {
        return books;
    }


    public void add(List<Book> bookList) {
        books.addAll(bookList);
    }
}
