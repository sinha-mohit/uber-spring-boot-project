package uberbackend.uberreviewservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.repository.cdi.Eager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends BaseModel {
    private String name;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    // 1:N -> Driver : Bookings -> Driver has many bookings, booking belongs to a driver
    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY) // so one to many relationship is mapped by driver property on the other side that is booking class having driver property
    @Fetch(FetchMode.SUBSELECT) // to prevent N+1 problem
    private List<Booking> bookings; // wrt overall app performance, in this driver model, booking should be loaded lazily where ever driver class is requested
}
