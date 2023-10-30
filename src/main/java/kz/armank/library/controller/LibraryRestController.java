package kz.armank.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.armank.library.dto.BookDto;
import kz.armank.library.dto.CategoryDto;
import kz.armank.library.model.Book;
import kz.armank.library.model.Category;
import kz.armank.library.service.abstracts.BookDtoService;
import kz.armank.library.service.abstracts.BookService;
import kz.armank.library.service.abstracts.CategoryDtoService;
import kz.armank.library.service.abstracts.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@CrossOrigin(origins = {"https://library-front-jade.vercel.app/","http://localhost:3000/"})
@Api(value = "Книги")
@Slf4j
public class LibraryRestController {

    private final BookService bookService;
    private final BookDtoService bookDtoService;
    private final CategoryService categoryService;
    private final CategoryDtoService categoryDtoService;


    // Показать все книги
    @GetMapping
    @ApiOperation(value = "Получить список всех книг", response = List.class)
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("Начало метода вывести все книги из RestController");
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/book_dto")
    @ApiOperation(value = "Получить Dto книг", response = BookDto.class)
    public ResponseEntity<BookDto> getBookDtoById() {
        log.info("Начало метода вывести Dto книг из RestController");
        return new ResponseEntity<>(bookDtoService.getById().get(), HttpStatus.OK);
    }

    // Показать книгу по ID
    @GetMapping("/{id}")
    @ApiOperation(value = "Получить книгу по id", response = Book.class)
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        log.info("Начало метода вывести книгу по id из RestController");
        log.info("Id книги пришедшее с фронта " + id);
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    // Добавить новую книгу
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        log.info("Начало метода добавить новую книгу из RestController");
        log.info("Книга пришедшая с фронта " + book);
        List<Category> actualCategories = new ArrayList<>();

        for (Category category : book.getCategories()) {
            Category existingCategory = categoryService.getCategoryByCategoryName(category.getName());

            if (existingCategory != null) {
                actualCategories.add(existingCategory);
            } else {
                Category newCategory = new Category();
                newCategory.setName(category.getName());
                categoryService.createCategory(newCategory);
                actualCategories.add(newCategory);
            }
        }

        book.setCategories(actualCategories);
        log.info("Измененная книга " + book);

        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.OK);
    }

    // Обновить существующую книгу
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@Valid @PathVariable Long id, @RequestBody Book updatedBook) {
        log.info("Начало метода обновить книгу из RestController");
        log.info("Книга пришедшая с фронта " + updatedBook);
        updatedBook.setId(id);
        List<Category> actualCategories = new ArrayList<>();

        for (Category category : updatedBook.getCategories()) {
            Category existingCategory = categoryService.getCategoryByCategoryName(category.getName());
            if (existingCategory != null) {
                actualCategories.add(existingCategory);
            } else {
                Category newCategory = new Category();
                newCategory.setName(category.getName());
                categoryService.createCategory(newCategory);
                actualCategories.add(newCategory);
            }
        }

        updatedBook.setCategories(actualCategories);
        log.info("Книга после выполнения логики " + updatedBook);

        return new ResponseEntity<>(bookService.updateBook(updatedBook), HttpStatus.OK);
    }

    // Удалить книгу по ID
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        log.info("Начало метода удалить книгу из RestController");
        log.info("Id книги которую удалим " + id);
        bookService.deleteBook(id);
    }

    // Добавить новую категорию
    @PostMapping("/categories")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        log.info("Начало метода добавить новую категорию из RestController");
        log.info("Категория которая пришла с фронта = " + category);
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.OK );
    }

    // Получить все категории
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        log.info("Начало метода вывести все категории");
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK );
    }

    @GetMapping("/categories/dto")
    public ResponseEntity<List<CategoryDto>> getCategoryDto() {
        log.info("Начало метода вывести CategoryDTO из RestController");
        return new ResponseEntity<>(categoryDtoService.getCategoryDto().get(), HttpStatus.OK );
    }


}

