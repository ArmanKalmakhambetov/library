package kz.armank.library.repo;

import kz.armank.library.model.Book;
import kz.armank.library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book as b WHERE b.category = :category")
    List<Book> getBooksByCategory(@Param("category") Category category);
}
