package kz.armank.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private int year;

    @Column(length = 800)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE})
    @JoinTable(name = "category_of_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rating> ratings;

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
        return year == book.year && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(description, book.description) && Objects.equals(categories, book.categories) && Objects.equals(reviews, book.reviews) && Objects.equals(ratings, book.ratings) && Objects.equals(image, book.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, year, description, categories, reviews, ratings, image);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", categories=" + categories +
                ", reviews=" + reviews +
                ", ratings=" + ratings +
                ", image='" + image + '\'' +
                '}';
    }
}


