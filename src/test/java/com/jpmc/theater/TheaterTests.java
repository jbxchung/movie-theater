package com.jpmc.theater;

import com.jpmc.theater.utils.ExampleData;
import com.jpmc.theater.vo.Customer;
import com.jpmc.theater.vo.Reservation;
import com.jpmc.theater.vo.Theater;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(ExampleData.get());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        assertEquals(reservation.totalFee(), 50);
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(ExampleData.get());
        theater.printSchedule();
    }
}
