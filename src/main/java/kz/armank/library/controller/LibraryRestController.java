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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {

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



//    @GetMapping("/by-category/{categoryId}")
//    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable Long categoryId) {
//        Category category = categoryService.getCategoryById(categoryId);
//
//        if (category == null) {
//            return ResponseEntity.notFound().build();
//        }
//
////        List<Book> books = bookService.getBooksByCategory(category);
////        return ResponseEntity.ok();
//        return ResponseEntity.ok(new ArrayList<>());
//    }

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

