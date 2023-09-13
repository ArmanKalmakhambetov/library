package kz.armank.library.service.abstracts;

import kz.armank.library.model.Rating;

import java.util.List;

public interface RatingService {

    List<Rating> getAllRatings();

    Rating getRatingById(Long id);

    Rating addRating(Rating rating);

    Rating updateRating(Rating rating);

    void deleteRating(Long id);
}
