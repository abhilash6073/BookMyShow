package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dtos.CreateBookingRequestDto;
import com.scaler.bookmyshow.dtos.CreateBookingResponseDto;
import com.scaler.bookmyshow.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public CreateBookingResponseDto createBooking(CreateBookingRequestDto requestDto){
        return null;

    }

}
