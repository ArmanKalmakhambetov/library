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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@CrossOrigin(origins = "https://library-front-jade.vercel.app/")
@Api(value = "Книги", description = "Операции с книгами")
public class LibraryRestController {

    private final BookService bookService;
    private final BookDtoService bookDtoService;
    private final CategoryService categoryService;
    private final CategoryDtoService categoryDtoService;


    // Показать все книги
    @GetMapping
    @ApiOperation(value = "Получить список всех книг", response = List.class)
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/book_dto")
    @ApiOperation(value = "Получить Dto книг", response = BookDto.class)
    public ResponseEntity<BookDto> getBookDtoById() {
        return new ResponseEntity<>(bookDtoService.getById().get(), HttpStatus.OK);
    }

    // Показать книгу по ID
    @GetMapping("/{id}")
    @ApiOperation(value = "Получить книгу по id", response = Book.class)
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    // Добавить новую книгу
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        List<Category> actualCategories = new ArrayList<>();

        for (Category category : book.getCategories()) {
            // Попробуйте найти существующую категорию по имени
            Category existingCategory = categoryService.getCategoryByCategoryName(category.getName());

            if (existingCategory != null) {
                // Если категория существует, добавьте ее в список фактических категорий
                actualCategories.add(existingCategory);
            } else {
                // Если категория не существует, создайте новую и добавьте ее в список фактических категорий
                Category newCategory = new Category();
                newCategory.setName(category.getName());
                // Другие операции с новой категорией, если необходимо
                categoryService.createCategory(newCategory);
                actualCategories.add(newCategory);
            }
        }

        // Установите список фактических категорий для книги и сохраните ее
        book.setCategories(actualCategories);


        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.OK);
    }

    // Обновить существующую книгу
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@Valid @PathVariable Long id, @RequestBody Book updatedBook) {

        updatedBook.setId(id);

        List<Category> actualCategories = new ArrayList<>();

        for (Category category : updatedBook.getCategories()) {
            // Попробуйте найти существующую категорию по имени
            Category existingCategory = categoryService.getCategoryByCategoryName(category.getName());

            if (existingCategory != null) {
                // Если категория существует, добавьте ее в список фактических категорий
                actualCategories.add(existingCategory);
            } else {
                // Если категория не существует, создайте новую и добавьте ее в список фактических категорий
                Category newCategory = new Category();
                newCategory.setName(category.getName());
                // Другие операции с новой категорией, если необходимо
                categoryService.createCategory(newCategory);
                actualCategories.add(newCategory);
            }
        }

        // Установите список фактических категорий для книги и сохраните ее
        updatedBook.setCategories(actualCategories);

        return new ResponseEntity<>(bookService.updateBook(updatedBook), HttpStatus.OK);
    }

    // Удалить книгу по ID
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    // Добавить новую категорию
    @PostMapping("/categories")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.OK );
    }

    // Получить все категории
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK );
    }

    @GetMapping("/categories/dto")
    public ResponseEntity<List<CategoryDto>> getCategoryDto() {
        return new ResponseEntity<>(categoryDtoService.getCategoryDto().get(), HttpStatus.OK );
    }


}

