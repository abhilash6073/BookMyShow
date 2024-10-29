package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private String referenceNumber;
    private int amount;
    private PaymentStatus status;
    private PaymentMode paymentMode;
    private PaymentProvider paymentProvider;
}
