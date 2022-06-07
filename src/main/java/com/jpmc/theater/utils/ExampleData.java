package com.jpmc.theater.utils;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

// todo - read this from db?
public class ExampleData {
    public static List<Showing> get() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);

        return List.of(
            new Showing(turningRed, 1, LocalDate.now().atTime(9, 0)),
            new Showing(spiderMan, 2, LocalDate.now().atTime(11, 0)),
            new Showing(theBatMan, 3, LocalDate.now().atTime(12, 50)),
            new Showing(turningRed, 4, LocalDate.now().atTime(14, 30)),
            new Showing(spiderMan, 5, LocalDate.now().atTime(16, 10)),
            new Showing(theBatMan, 6, LocalDate.now().atTime(17, 50)),
            new Showing(turningRed, 7, LocalDate.now().atTime(19, 30)),
            new Showing(spiderMan, 8, LocalDate.now().atTime(21, 10)),
            new Showing(theBatMan, 9, LocalDate.now().atTime(23, 0))
        );
    }

}
