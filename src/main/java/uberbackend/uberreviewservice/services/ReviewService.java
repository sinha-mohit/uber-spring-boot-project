package uberbackend.uberreviewservice.services;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import uberbackend.uberreviewservice.custom.CustomDriver;
import uberbackend.uberreviewservice.models.*;
import uberbackend.uberreviewservice.repositories.BookingRepository;
import uberbackend.uberreviewservice.repositories.DriverRepository;
import uberbackend.uberreviewservice.repositories.PassengerRepository;
import uberbackend.uberreviewservice.repositories.ReviewRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static uberbackend.uberreviewservice.models.BookingStatus.IN_RIDE;

@Service
public class ReviewService implements CommandLineRunner {
    private final DriverRepository driverRepository;
    private final PassengerRepository passengerRepository;
    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;

    // constructor based dependency injection (we can use autowired too but not recommended)
    public ReviewService(DriverRepository driverRepository, PassengerRepository passengerRepository, ReviewRepository reviewRepository, BookingRepository bookingRepository) {
        this.driverRepository = driverRepository;
        this.passengerRepository = passengerRepository;
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("********CommandLineRunner********");

        // Saving
        Driver driver_1 = Driver
                .builder()
                .name("Rajesh")
                .licenseNumber("DL98765")
                .build();
        driverRepository.save(driver_1);

        Passenger passenger_1 = Passenger
                .builder()
                .name("Mohit")
                .build();
        passengerRepository.save(passenger_1);


        Review review = Review // id, createdAt and updatedAt are automatically handled by spring boot
                .builder()
                .content("Bad ride quality")
                .rating(1.0)
                .build(); // code to create plain java object
        reviewRepository.save(review); // this executes sql queries

        Booking booking_1 = Booking
                .builder()
                .review(review)
                .endTime(new Date())
                .driver(driver_1)
                .passenger(passenger_1)
                .bookingStatus(IN_RIDE)
                .build();
        bookingRepository.save(booking_1);

        // Fetching
        Optional<Driver> driver = driverRepository.findByIdAndLicenseNumber(1L, "DL98765");
        if (driver.isPresent()) {
            System.out.println(driver.get().toString());
            System.out.println(driver.get().getLicenseNumber());
            System.out.println(driver.get().getName());
        }

        List<Booking> bookings = bookingRepository.findAllByDriverId(1L);
        for (Booking booking : bookings) {
            System.out.println(booking.getBookingStatus());
        }

        Optional<Driver> driver_2 = driverRepository.rawFindByIdAndLicenseNumber(1L, "DL98765");
        if (driver_2.isPresent()) {
            System.out.println(driver_2.get().toString());
            System.out.println(driver_2.get().getLicenseNumber());
            System.out.println(driver_2.get().getName());
        }

        Optional<Driver> driver_3 = driverRepository.hqlFindByIdAndLicenseNumber(1L, "DL98765");
        if (driver_3.isPresent()) {
            System.out.println(driver_3.get().toString());
            System.out.println(driver_3.get().getLicenseNumber());
            System.out.println(driver_3.get().getName());
        }

        CustomDriver customDriver = driverRepository.hqlFindLicenseAndId("DL98765", 1L);
        if (customDriver != null) {
            System.out.println(customDriver.toString());
        }
    }
}
