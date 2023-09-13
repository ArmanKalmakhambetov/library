package kz.armank.library.service.abstracts;

import kz.armank.library.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();

    Review getReviewById(Long id);

    Review addReview(Review review);

    Review updateReview(Review review);

    void deleteReview(Long id);
}
