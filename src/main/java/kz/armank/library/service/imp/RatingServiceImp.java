package kz.armank.library.service.imp;

import kz.armank.library.model.Rating;
import kz.armank.library.model.Rating;
import kz.armank.library.repo.RatingRepo;
import kz.armank.library.service.abstracts.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImp implements RatingService {
    private final RatingRepo ratingRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Rating getRatingById(Long id) {
        return ratingRepo.getById(id);
    }

    @Override
    @Transactional
    public Rating addRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    @Override
    @Transactional
    public Rating updateRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    @Override
    @Transactional
    public void deleteRating(Long id) {
        ratingRepo.deleteById(id);
    }
}
