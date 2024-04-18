package uberbackend.uberreviewservice.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import uberbackend.uberreviewservice.models.Review;
import uberbackend.uberreviewservice.repositories.ReviewRepository;
import java.util.List;

@Service
public class ReviewService implements CommandLineRunner {
    private final ReviewRepository reviewRepository;

    // constructor based dependency injection (we can use autowired too but not recommended)
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("********CommandLineRunner********");
        Review review = Review.builder() // id, createdAt and updatedAt are automatically handled by spring boot
                .content("Bad ride quality")
                .rating(1.0)
                .build(); // code to create plain java object
        System.out.println(review.toString());
        reviewRepository.save(review); // this executes sql queries

        List<Review> reviewList = reviewRepository.findAll();
        for(Review r : reviewList) {
            System.out.println(r.toString());
        }
    }
}
