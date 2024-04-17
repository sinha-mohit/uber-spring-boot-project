// communicate with the database and execute queries
package uberbackend.uberreviewservice.repositories;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uberbackend.uberreviewservice.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> { // communicate with the database and execute queries

}
