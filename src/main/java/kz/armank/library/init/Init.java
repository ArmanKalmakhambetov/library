package kz.armank.library.init;

import kz.armank.library.service.abstracts.BookService;
import kz.armank.library.service.abstracts.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Init {
    private final BookService bookDao;
    private final CategoryService categoryDao;


//    @PostConstruct
//    public void init() {
//
//
//        categoryDao.createCategory(new Category("Фантастика"));
//        categoryDao.createCategory(new Category("Роман"));
//
//        Book fantasyBook = new Book();
//        fantasyBook.setTitle("Космическая одисея");
//        fantasyBook.setAuthor("Артур Кларк");
//        fantasyBook.setDescription("Описание фантастики");
//        fantasyBook.setYear(2001);
//        fantasyBook.setImage("https://cv8.litres.ru/pub/c/cover_415/10323688.webp");
//        fantasyBook.addCategory(categoryDao.getCategoryByCategoryName("Фантастика"));
//        bookDao.createBook(fantasyBook);
//
//        Book romanBook = new Book();
//        romanBook.setTitle("Война и мир");
//        romanBook.setAuthor("Лев Толстой");
//        romanBook.setDescription("Описание романа");
//        romanBook.setYear(1836);
//        romanBook.setImage("https://cv4.litres.ru/pub/c/cover_415/66691848.webp");
//        romanBook.addCategory(categoryDao.getCategoryByCategoryName("Роман"));
//        bookDao.createBook(romanBook);
//
//    }
}
