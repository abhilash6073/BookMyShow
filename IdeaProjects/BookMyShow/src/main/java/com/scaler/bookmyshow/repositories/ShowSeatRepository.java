package com.scaler.bookmyshow.repositories;

import com.scaler.bookmyshow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {


    List<ShowSeat> findAllById(List<Long> showSeatIds); //select * from show_seats where id IN (1,2,3...)
    //here we are not using Optional<List<ShowSeat>> because even if the List is empty, it will not be null
    //whereas objects can be null and can lead to null pointer exception

    ShowSeat save(ShowSeat showSeat);
    //Note that input and output both are ShowSeat
    //Difference if that output object would have been added to the DB and would have the primary key ID as well
    //In case the object is already there in the DB, it would just update the values keeping the same PK
    //save here is a combination of update + insert =>  Upsert



}
