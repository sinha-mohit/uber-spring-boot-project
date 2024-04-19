package uberbackend.uberreviewservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uberbackend.uberreviewservice.models.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
