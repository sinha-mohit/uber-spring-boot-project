package uberbackend.uberreviewservice.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class) // related to createdAt and updatedAt
@MappedSuperclass // to handle inheritance in Spring Data JPA (No table for this parent class and one table for each child class having its own attributes and parent class attributes)
public abstract class BaseModel { // due to abstract class, we cannot initiate object out of this BaseModel class. We have to extend this class
    @Id // this annotation makes the id property a primary key of our table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Identity means auto_increment
    protected Long id;

    @Temporal(TemporalType.TIMESTAMP) // What type of Date object to be stored (Date / Time / Timestamp)
    @CreatedDate // this annotation tells spring that only handle it for object creation
    @Column(nullable = false)
    protected Date createdAt;

    @Temporal(TemporalType.TIMESTAMP) // What type of Date object to be stored
    @LastModifiedDate // this annotation tells spring that only handle it for object update
    @Column(nullable = false)
    protected Date updatedAt;
}
