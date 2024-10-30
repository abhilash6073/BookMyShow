package com.scaler.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{

    //Booking is the same as Ticket
    private String bookingNumber;

    @ManyToOne
    private User user;

    @ManyToOne
    private Show show;

    @OneToMany
    private List<ShowSeat> showSeats;
    private int amount;

    @OneToMany
    private List<Payment> payments;

    @Enumerated(EnumType.ORDINAL) //BookingStatus is an enum, we dont want to store the entire string status repeatedly, hence we would want the string values to have corresponding numerical values
    private BookingStatus bookingStatus;

/*
 * cardinality between booking : user would be M:1, so you can add the annotation @ManyToOne
 * Same for Show: ManyToOne
 * While mentioning cardinality, the first belongs to the one outside (e.g. in this case Booking), and the second one belongs to the one inside
 * e.g. user, show etc
 * Booking to ShowSeat: 1 booking can have many showseats, but one showseat can belong to one booking at a time
 * However, in case the booking gets cancelled, and then the showseat is booked again, then the DB will have multiple entries along with different status
 * Hence we will be taking this as ManyToMany
 *
 * Booking : Payment (Object) 1:M
 */


}
