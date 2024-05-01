// communicate with the database and execute queries
package uberbackend.uberreviewservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uberbackend.uberreviewservice.models.Review;

import java.util.Date;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> { // communicate with the database and execute queries
    Integer countAllByRatingIsLessThanEqual(Integer givenRating);

    List<Review> findAllByRatingIsLessThanEqual(Integer givenRating);

    List<Review> findAllByCreatedAtBefore(Date createdAt);

//    raw query : runtime time check for any error
//    @Query(value = "select * from Booking b inner join Review r on b.review_id = r.id where b.id = :bookingId", nativeQuery = true)
//    Hibernate query (below) : checks for syntactical errors at run time
//    @Query("select r from Booking b inner join Review r on b.review = r where b.id = :bookingId") //or
    @Query("select r from Booking b inner join Review r where b.id = :bookingId") // matching hibernate does automatically
    Review findReviewByBookingId(Long bookingId); // since it's a 1:1 (booking:review) mapping, hence it can exit anywhere, hence we have to do inner join

}
