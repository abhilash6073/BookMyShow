package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.exceptions.ShowNotFoundException;
import com.scaler.bookmyshow.exceptions.ShowSeatNotAvailableException;
import com.scaler.bookmyshow.exceptions.UserNotFoundException;
import com.scaler.bookmyshow.models.*;
import com.scaler.bookmyshow.repositories.BookingRepository;
import com.scaler.bookmyshow.repositories.ShowRepository;
import com.scaler.bookmyshow.repositories.ShowSeatRepository;
import com.scaler.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculatorService;
    private BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          PriceCalculatorService priceCalculatorService,
                          BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
        this.bookingRepository = bookingRepository;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE) //making the entire method threadsafe
    public Booking createBooking(Long userId, List<Long> showSeatIds, Long showId) throws UserNotFoundException, ShowNotFoundException, ShowSeatNotAvailableException {

        /*
        1. Get the user object from the DB with the given userID
        2. Get the show object from the DB with the given showId
        3. Get the list of showSeats with the given showSeatIds
        -----------TAKE A LOCK------------------------
        4. Check if all the seats are available or not
        5. If not, throw an exception
        6. If yes, mark the status of all the showSeats as BLOCKED
        ------------RELEASE THE LOCK------------------
        7. Save the changes in the DB as well
        8. Create the booking with pending status [save Booking object to DB]
        9. Return the Booking object
        10. Create a booking repository and Save the booking to DB
         *
         */


//         1. Get the user object from the DB with the given userID
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with id: "+ userId + " not found.");
        }

        User user = optionalUser.get();

//        2. Get the show object from the DB with the given showId
        Optional<Show> optionalShow = showRepository.findById(showId);

        if (optionalShow.isEmpty()) {
            throw new ShowNotFoundException("Show with id: "+ showId + " not found.");
        }
        Show show = optionalShow.get();

//           3. Get the list of showSeats objects with the given showSeatIds
      List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

//   4. Check if all the seats are available or not
//   5. If not, throw an exception
        for(ShowSeat showSeat : showSeats) {
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new ShowSeatNotAvailableException("ShowSeat with id:" + showSeat.getId() + " not AVAILABLE");
            }
        }

//         6. If AVAILABLE, mark the status of all the showSeats as BLOCKED
        for(ShowSeat showSeat : showSeats) {
                showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            //        7. Save the changes in the DB as well
            showSeatRepository.save(showSeat);
        }

//          8. Create the booking with pending status [save Booking object to DB]
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setCreatedAt(new Date());
        booking.setUser(user);
        booking.setShow(show);
        booking.setPayments(new ArrayList<>());
        booking.setAmount(priceCalculatorService.calculatePrice(show, showSeats));
        booking.setShowSeats(showSeats);




       // return booking; (this booking object doesn't have any ID associated with it)
        //we need to return a Booking object with id
       return bookingRepository.save(booking);
    }
}
