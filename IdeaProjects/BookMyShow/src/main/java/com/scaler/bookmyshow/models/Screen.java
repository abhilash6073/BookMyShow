package com.scaler.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel {
    private String name; //AUDI1, AUDI2 etc

    //Screen : Seat -> 1:M
    @OneToMany
    private List<Seat> seats;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection //this is used only for collection of enums, in the above case seat is not an enum
    private List<Feature> features;
}
