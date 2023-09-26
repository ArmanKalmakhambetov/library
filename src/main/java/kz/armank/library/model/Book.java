package kz.armank.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private int year;

    private int pageVolume;

    @Column(length = 800)
    private String description;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE})
    @JoinTable(name = "category_of_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    @Column(length = 800)
    private String review;

    @Column
    @Min(value = 1)
    @Max(value = 10)
    private Integer rating;

    @Column(name = "image", length = 800)
    private String image;

    public void addCategory(Category category) {
        categories.add(category);
        category.addBook(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.removeBook(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && pageVolume == book.pageVolume && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(description, book.description) && Objects.equals(categories, book.categories) && Objects.equals(review, book.review) && Objects.equals(rating, book.rating) && Objects.equals(image, book.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, year, pageVolume, description, categories, review, rating, image);
    }

    @Override
    public String toString() {
        return "Book{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", author='" + author + '\'' +
               ", year=" + year +
               ", pageVolume=" + pageVolume +
               ", description='" + description + '\'' +
               ", categories=" + categories +
               ", review='" + review + '\'' +
               ", rating=" + rating +
               ", image='" + image + '\'' +
               '}';
    }
}


