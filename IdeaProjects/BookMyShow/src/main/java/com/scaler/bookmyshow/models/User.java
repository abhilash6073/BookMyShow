package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "users") //name of the table in DB will be users
//by default name of the table will the name of the entity/model,
// but User is a keyword, hence cannot be used
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;

    @OneToMany
    private List<Booking> bookings;
}
