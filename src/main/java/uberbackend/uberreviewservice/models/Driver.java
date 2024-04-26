package uberbackend.uberreviewservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.data.repository.cdi.Eager;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "driver", fetch = FetchType.EAGER) // so one to many relationship is mapped by driver property on the other side that is booking class having driver property
    private List<Booking> bookings;

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", bookings=" + bookings +
                ", id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
