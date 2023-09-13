package kz.armank.library.controller;

import kz.armank.library.model.Book;
import kz.armank.library.model.Category;
import kz.armank.library.model.Rating;
import kz.armank.library.model.Review;
import kz.armank.library.service.abstracts.BookService;
import kz.armank.library.service.abstracts.CategoryService;
import kz.armank.library.service.abstracts.RatingService;
import kz.armank.library.service.abstracts.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class LibraryRestController {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final ReviewService reviewService;
    private final RatingService ratingService;


    // Показать все книги
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    // Показать книгу по ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    // Добавить новую книгу
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Category category = new Category();
        category.setName(book.getCategory().getName());
        book.setCategory(category);
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK);
    }

    // Обновить существующую книгу
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book newUpdateBook = new Book();
        newUpdateBook.setTitle(updatedBook.getTitle());
        newUpdateBook.setAuthor(updatedBook.getAuthor());
        newUpdateBook.setDescription(updatedBook.getDescription());
        newUpdateBook.setCategory(updatedBook.getCategory());
        newUpdateBook.setImage(updatedBook.getImage());
        return new ResponseEntity<>(bookService.updateBook(newUpdateBook), HttpStatus.OK);
    }

    // Удалить книгу по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Книга с id = " + id + " успешно удалена", HttpStatus.OK );
    }

    // Добавить новую категорию
    @PostMapping("/categories")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.OK );
    }

    // Получить все категории
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK );
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK );
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id) {
        Category updatedCategory = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryService.updateCategory(updatedCategory), HttpStatus.OK );
    }

    // Удалить Category по ID
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Категория с id = " + id + " успешно удалена", HttpStatus.OK );
    }

    // Добавить новый обзор
    @PostMapping("/reviews")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.addReview(review), HttpStatus.OK);
    }

    // Получить все обзоры
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id) {
        Review updatedReview = reviewService.getReviewById(id);
        return new ResponseEntity<>(reviewService.updateReview(updatedReview), HttpStatus.OK );
    }

    // Удалить Review по ID
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>("Обзор с id = " + id + " успешно удален", HttpStatus.OK );
    }

    // Добавить новую оценку
    @PostMapping("/ratings")
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        return new ResponseEntity<>(ratingService.addRating(rating), HttpStatus.OK);
    }

    // Получить все оценки
    @GetMapping("/ratings")
    public ResponseEntity<List<Rating>> getAllRatings() {
        return new ResponseEntity<>(ratingService.getAllRatings(), HttpStatus.OK);
    }

    @PutMapping("/ratings/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long id) {
        Rating updatedRating = ratingService.getRatingById(id);
        return new ResponseEntity<>(ratingService.updateRating(updatedRating), HttpStatus.OK );
    }

    // Удалить Review по ID
    @DeleteMapping("/ratings/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return new ResponseEntity<>("Оценка с id = " + id + " успешно удалена", HttpStatus.OK );
    }
}

