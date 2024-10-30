package com.scaler.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//ensures that all the attributes of the BaseModel class is added to the tables
//of all the child classes
public class BaseModel {
    @Id //makes id the primary key attribute for all the child classes of Base Model
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
    private Long id;

    private Date createdAt;
    private Date lastModifiedAt;
}
