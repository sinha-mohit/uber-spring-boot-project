package uberbackend.uberreviewservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseModel {
//    @OneToOne // so first save review in DB then save booking in db. reverse saving will not work else use CascadeType.PERSIST
//    @OneToOne(cascade = {CascadeType.PERSIST}) if by chance we are saving booking without saving review, this annotation will first save review and then booking automatically. So we don't need to save review separately for saving booking in database.
//    {CascadeType.REMOVE} if booking is deleted, then delete all the associated review data too
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Review review; // we have added a 1:1 relationship between booking and review
    @Enumerated(value = EnumType.STRING) // store as string in database
    private BookingStatus bookingStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    private Long totalDistance;
}
