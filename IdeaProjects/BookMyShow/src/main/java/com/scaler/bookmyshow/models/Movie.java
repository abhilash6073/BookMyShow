package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Movie extends BaseModel {
    private String title;
    private Date releaseDate;
    private Feature features;
}
