package uberbackend.uberreviewservice.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import uberbackend.uberreviewservice.models.Booking;
import uberbackend.uberreviewservice.models.Review;
import uberbackend.uberreviewservice.repositories.BookingRepository;
import uberbackend.uberreviewservice.repositories.ReviewRepository;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService implements CommandLineRunner {
    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;

    // constructor based dependency injection (we can use autowired too but not recommended)
    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("********CommandLineRunner********");

//        Review review = Review // id, createdAt and updatedAt are automatically handled by spring boot
//                .builder()
//                .content("Bad ride quality")
//                .rating(1.0)
//                .build(); // code to create plain java object
//
//        reviewRepository.save(review); // this executes sql queries
//
//        Booking booking = Booking
//                        .builder()
//                        .review(review)
//                        .endTime(new Date())
//                        .build();
//
//        bookingRepository.save(booking);
//
//
//        List<Review> reviewList = reviewRepository.findAll();
//        for(Review r : reviewList) {
//            System.out.println(r.toString());
//        }
    }
}
