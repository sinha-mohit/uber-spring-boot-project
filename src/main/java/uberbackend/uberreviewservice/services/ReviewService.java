package uberbackend.uberreviewservice.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import uberbackend.uberreviewservice.models.Booking;
import uberbackend.uberreviewservice.models.Driver;
import uberbackend.uberreviewservice.models.Passenger;
import uberbackend.uberreviewservice.models.Review;
import uberbackend.uberreviewservice.repositories.BookingRepository;
import uberbackend.uberreviewservice.repositories.DriverRepository;
import uberbackend.uberreviewservice.repositories.PassengerRepository;
import uberbackend.uberreviewservice.repositories.ReviewRepository;

import java.util.Date;

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

        Driver driver_1 = Driver
                .builder()
                .name("Rajesh")
                .licenseNumber("DL98765" + Math.random())
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

        Booking booking = Booking
                .builder()
                .review(review)
                .endTime(new Date())
                .driver(driver_1)
                .passenger(passenger_1)
                .build();
        bookingRepository.save(booking);
    }
}
