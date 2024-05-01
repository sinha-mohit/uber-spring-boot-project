// Class view of database tables
package uberbackend.uberreviewservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity // java layer level
//@Table(name = "booking_review") // database level
@Inheritance(strategy = InheritanceType.JOINED)
public class Review extends BaseModel {
    @Column(nullable = false)
    protected String content;

    protected Double rating;

//    @OneToOne // Doubt? Is this should be present since it is 1:1 mapping in Booking table?? Ans: No for now as 1:1 mapping can exit anywhere
//    protected Booking booking;
}

// new review (content, rating) so id, createdAt and updatedAt should be handled automatically