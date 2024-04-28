package uberbackend.uberreviewservice.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uberbackend.uberreviewservice.models.*;
import uberbackend.uberreviewservice.repositories.BookingRepository;
import uberbackend.uberreviewservice.repositories.DriverRepository;
import uberbackend.uberreviewservice.repositories.PassengerRepository;
import uberbackend.uberreviewservice.repositories.ReviewRepository;

import java.util.*;

import static uberbackend.uberreviewservice.models.BookingStatus.CAB_ARRIVED;

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

    public String GenerateRandomAlphabeticString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String GenerateRandomAlphanumericString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Transactional
    public void loadData() {

        // Saving
        Driver driver_1 = Driver
                .builder()
                .name(GenerateRandomAlphabeticString())
                .licenseNumber(GenerateRandomAlphanumericString())
                .build();
        driverRepository.save(driver_1);

        Driver driver_2 = Driver
                .builder()
                .name(GenerateRandomAlphabeticString())
                .licenseNumber(GenerateRandomAlphanumericString())
                .build();
        driverRepository.save(driver_2);

        Passenger passenger_1 = Passenger
                .builder()
                .name("Mohit")
                .build();
        passengerRepository.save(passenger_1);

        Passenger passenger_2 = Passenger
                .builder()
                .name("Sanket")
                .build();
        passengerRepository.save(passenger_2);


        Review review_1 = Review // id, createdAt and updatedAt are automatically handled by spring boot
                .builder()
                .content("Bad ride quality")
                .rating(1.0)
                .build(); // code to create plain java object
        reviewRepository.save(review_1); // this executes sql queries

        Review review_2 = Review // id, createdAt and updatedAt are automatically handled by spring boot
                .builder()
                .content("Good Car")
                .rating(5.0)
                .build(); // code to create plain java object
        reviewRepository.save(review_2); // this executes sql queries

//        Booking booking_1 = Booking
//                .builder()
//                .review(review_1)
//                .endTime(new Date())
//                .driver(driver_1)
//                .passenger(passenger_1)
//                .bookingStatus(IN_RIDE)
//                .build();
//        bookingRepository.save(booking_1);
//
//        Booking booking_2 = Booking
//                .builder()
//                .review(review_2)
//                .endTime(new Date())
//                .driver(driver_1)
//                .passenger(passenger_2)
//                .bookingStatus(CAB_ARRIVED)
//                .build();
//        bookingRepository.save(booking_2);

        for(int i = 0; i < 10; i++) { // driver_1 has 10 bookings
            bookingRepository.saveAndFlush(Booking.builder()
                    .endTime(new Date())
                    .driver(driver_1)
                    .bookingStatus(CAB_ARRIVED)
                    .build());
        }
    }

    @Transactional
    public void fetchData() {
        // Fetching
        List<Driver> drivers = driverRepository.findAllDriversByIdIn(Arrays.asList(1L, 2L, 3L)); // say N driver ids, so one query to fetch all the N drivers (1 query)
        // Let's say you have N drivers, then below code can be a disaster -> leads to N+1 famous database problem
        // In order to fetch special type of data, you make N+1 queries
        for(Driver driver : drivers) { // Now due to looping, we will do N more queries to get bookings of each N driver (N queries)
            System.out.println("****************start****************");
            System.out.println(driver.getName());
            final List<Booking> bookings = driver.getBookings();

            if(bookings != null) {
                for (Booking booking : bookings) {
                    System.out.println(booking.getBookingStatus());
                }
            }
            System.out.println("****************end****************");
        }

//        // Solution to above N+1 problem : only 3 queries
//        System.out.println("**************************************************************************");
//        List<Driver> drivers1 = driverRepository.findAllDriversByIdIn( Arrays.asList(1L, 2L, 3L)); // 1st Query
//        Set<Booking> bookings = bookingRepository.findAllByDriverIn(drivers1); // 2nd Query
//        for (Booking booking : bookings) {
//            System.out.println(booking.getBookingStatus()); // 3rd Query
//        }

//        Optional<Driver> driver = driverRepository.findDriverById(1L);
//        if (driver.isPresent()) {
//            System.out.println(driver.get().getName());
//            System.out.println(driver.get().getLicenseNumber());
//
//            Set<Booking> bookings = driver.get().getBookings();
//            for (Booking booking : bookings) {
//                System.out.println(booking.getBookingStatus());
//            }
//        }

//        List<Booking> bookings = bookingRepository.findAllByDriverId(1L);
//        for (Booking booking : bookings) {
//            System.out.println(booking.getBookingStatus());
//        }

//        Optional<Driver> driver = driverRepository.findByIdAndLicenseNumber(1L, "DL98765");
//        if (driver.isPresent()) {
//            System.out.println(driver.get().toString());
//            System.out.println(driver.get().getLicenseNumber());
//            System.out.println(driver.get().getName());
//        }

//        Optional<Driver> driver_2 = driverRepository.rawFindByIdAndLicenseNumber(1L, "DL98765");
//        if (driver_2.isPresent()) {
//            System.out.println(driver_2.get().toString());
//            System.out.println(driver_2.get().getLicenseNumber());
//            System.out.println(driver_2.get().getName());
//        }
//
//        Optional<Driver> driver_3 = driverRepository.hqlFindByIdAndLicenseNumber(1L, "DL98765");
//        if (driver_3.isPresent()) {
//            System.out.println(driver_3.get().toString());
//            System.out.println(driver_3.get().getLicenseNumber());
//            System.out.println(driver_3.get().getName());
//        }
//
//        CustomDriver customDriver = driverRepository.hqlFindLicenseAndId("DL98765", 1L);
//        if (customDriver != null) {
//            System.out.println(customDriver.toString());
//        }
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("********CommandLineRunner********");
        loadData();
        fetchData();
    }
}
