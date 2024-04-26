package uberbackend.uberreviewservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uberbackend.uberreviewservice.models.Booking;
import uberbackend.uberreviewservice.models.Driver;

import java.util.List;
import java.util.Set;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByDriverId(Long driverId);

    List<Booking> findAllByDriverIn(List<Driver> drivers);
}
