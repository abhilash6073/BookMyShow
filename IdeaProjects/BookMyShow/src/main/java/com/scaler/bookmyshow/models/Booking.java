package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{

    //Booking is the same as Ticket
    private String bookingNumber;
    private User user;
    private List<ShowSeat> showSeats;
    private int amount;
    private List<Payment> payments;
    private BookingStatus bookingStatus;



}
