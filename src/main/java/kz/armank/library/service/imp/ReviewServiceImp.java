package kz.armank.library.service.imp;

import kz.armank.library.model.Review;
import kz.armank.library.repo.ReviewRepo;
import kz.armank.library.service.abstracts.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImp implements ReviewService {

    private final ReviewRepo reviewRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Review> getAllReviews() {
        return reviewRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Review getReviewById(Long id) {
        return reviewRepo.getById(id);
    }

    @Override
    @Transactional
    public Review addReview(Review review) {
        return reviewRepo.save(review);
    }

    @Override
    @Transactional
    public Review updateReview(Review review) {
        return reviewRepo.save(review);
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        reviewRepo.deleteById(id);
    }
}
