package uberbackend.uberreviewservice.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import uberbackend.uberreviewservice.models.Review;
import uberbackend.uberreviewservice.repositories.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Review> findReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> findAllReviews(Long id) {
        return reviewRepository.findAll();
    }

    @Override
    public boolean deleteReviewById(Long id) {
        try {
            reviewRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}
