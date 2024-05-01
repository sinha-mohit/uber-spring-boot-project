package uberbackend.uberreviewservice.services;

import uberbackend.uberreviewservice.models.Review;

import java.util.List;
import java.util.Optional;

// TODO: add more CRUD methods here
public interface ReviewService {
    public Optional<Review> findReviewById(Long id);

    public List<Review> findAllReviews(Long id);

    public boolean deleteReviewById(Long id);

}
