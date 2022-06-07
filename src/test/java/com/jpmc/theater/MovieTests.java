package com.jpmc.theater;

import com.jpmc.theater.vo.Movie;
import com.jpmc.theater.vo.Showing;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void testSpecialMovieDiscount() {
        // movie special should be 20% off
        Double basePrice = 20d;
        Double expectedPrice = basePrice - (basePrice * 0.2);

        // is special
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),basePrice, true);

        // make sure the time is not in the matinee window
        LocalDateTime showingTime = LocalDate.now().atTime(20, 0);

        // make sure the sequence does not give a discount (1, 2, or 7)
        Showing showing = new Showing(spiderMan, 5, showingTime);
        Double result = showing.calculateTicketPrice();

        System.out.println(String.format("Calculated price: %s, Expected Price: %s", result, expectedPrice));

        assertEquals(expectedPrice, result);
    }

    @Test
    void testSeventhMovieDiscount() {
        // new requirement - the 7th movie should have a $1 discount
        Double basePrice = 20d;
        Double expectedPrice = basePrice - 1;

        // not special
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), basePrice, false);

        // make sure the time is not in the matinee window
        LocalDateTime showingTime = LocalDate.now().atTime(20, 0);

        // 7th movie sequence
        Showing showing = new Showing(spiderMan, 7, showingTime);
        Double result = showing.calculateTicketPrice();

        System.out.println(String.format("Calculated price: %f, Expected Price: %f", result, expectedPrice));

        assertEquals(expectedPrice, result);
    }

    @Test
    void testMatineeDiscount() {
        // new requirement - movies between 11am and 4pm should be 25% off
        Double basePrice = 20d;
        Double expectedPrice = basePrice - (basePrice * 0.25);

        // not special
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), basePrice, false);

        // set time inside matinee window
        LocalDateTime showingTime = LocalDate.now().atTime(12, 0);

        // make sure the sequence does not give a discount (1, 2, or 7)
        Showing showing = new Showing(spiderMan, 5, showingTime);
        Double result = showing.calculateTicketPrice();

        System.out.println(String.format("Calculated price: %f, Expected Price: %f", result, expectedPrice));

        assertEquals(expectedPrice, result);
    }

    @Test
    void testNoDiscount() {
        Double basePrice = 20d;

        // not special
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), basePrice, false);

        // set time outside matinee window
        LocalDateTime showingTime = LocalDate.now().atTime(20, 0);

        // make sure the sequence does not give a discount (1, 2, or 7)
        Showing showing = new Showing(spiderMan, 5, showingTime);
        Double result = showing.calculateTicketPrice();

        System.out.println(String.format("Calculated price: %f, Expected Price: %f", result, basePrice));

        assertEquals(basePrice, result);
    }
}
