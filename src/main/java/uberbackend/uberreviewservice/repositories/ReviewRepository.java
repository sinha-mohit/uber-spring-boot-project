// communicate with the database and execute queries
package uberbackend.uberreviewservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uberbackend.uberreviewservice.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> { // communicate with the database and execute queries

}
