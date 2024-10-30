package com.scaler.bookmyshow.dtos;

import com.scaler.bookmyshow.models.Show;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookingRequestDto {
    private Long userId;
    private Show showId;
    private List<Long> showSeatId;

}
