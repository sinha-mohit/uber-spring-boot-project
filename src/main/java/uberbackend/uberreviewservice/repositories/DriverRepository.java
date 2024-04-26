package uberbackend.uberreviewservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uberbackend.uberreviewservice.models.Driver;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

    // Here in raw mysql query, you have to give same column name as present in table
    // Error is thrown at run time if there is error in query (thrown by mysql)
    @Query(nativeQuery = true, value = "SELECT * FROM Driver WHERE id = :id AND license_number = :licenseNumber")
    Optional<Driver> rawFindByIdAndLicenseNumber(Long id, String licenseNumber);

    // Spring data JPA Hibernate query (recommended)
    // error is thrown at compile time
    @Query("SELECT d FROM Driver as d WHERE d.licenseNumber = :license AND d.id = :id")
    Optional<Driver> hqlFindByIdAndLicenseNumber(Long id, String license);
}