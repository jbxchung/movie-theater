package com.jpmc.theater.utils;

import com.jpmc.theater.vo.Movie;
import com.jpmc.theater.vo.Showing;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

// this is an auxiliary class with sample data to aid in testing
// todo - read actual data from db or other config
public class ExampleData {
    public static List<Showing> get() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, true);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, false);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, false);

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
