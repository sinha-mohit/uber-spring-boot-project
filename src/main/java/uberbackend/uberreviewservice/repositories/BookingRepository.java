package uberbackend.uberreviewservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uberbackend.uberreviewservice.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
