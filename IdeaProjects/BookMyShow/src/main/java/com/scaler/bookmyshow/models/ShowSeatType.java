package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel{

    //e.g. the for 9pm show, gold class seat type will be of Rs500
    private Show show;
    private SeatType seatType;
    private int price;
}
