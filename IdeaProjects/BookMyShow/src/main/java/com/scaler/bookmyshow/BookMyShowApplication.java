package com.scaler.bookmyshow;

import com.scaler.bookmyshow.models.BaseModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication {

    public static void main(String[] args) {
//        BaseModel baseModel = new BaseModel();



        SpringApplication.run(BookMyShowApplication.class, args);
    }


}
