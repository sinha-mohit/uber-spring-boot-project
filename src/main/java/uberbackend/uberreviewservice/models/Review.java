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
@EntityListeners(AuditingEntityListener.class) // related to createdAt and updatedAt
@Entity // java layer level
@Table(name = "booking_review") // database level
public class Review {
    @Id // this annotation makes the id property a primary key of our table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Identity means auto_increment
    private Long id;

    @Column(nullable = false)
    private String content;

    private Double rating;

    @Temporal(TemporalType.TIMESTAMP) // What type of Date object to be stored (Date / Time / Timestamp)
    @CreatedDate // this annotation tells spring that only handle it for object creation
    @Column(nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP) // What type of Date object to be stored
    @LastModifiedDate // this annotation tells spring that only handle it for object update
    @Column(nullable = false)
    private Date updatedAt;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

// new review (content, rating) so id, createdAt and updatedAt should be handled automatically