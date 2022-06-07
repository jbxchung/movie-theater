package com.jpmc.theater.vo;

import com.jpmc.theater.vo.Movie;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    // todo - do we want these discount values to be updatable? if so, move to db or config
    private static Map<Integer, Double> sequenceDiscountMap = Map.of(
            1, 3d,
            2, 2d,
            7, 1d
    );
    private static final Double SPECIAL_DISCOUNT_MULTIPLIER = 0.2;
    private static final Double MATINEE_DISCOUNT_MULTIPLIER = 0.25;
    private static final LocalDateTime matineeStartTime = LocalDate.now().atTime(11, 0);
    private static final LocalDateTime matineeEndTime = LocalDate.now().atTime(15, 0);

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public double calculateTicketPrice() {
        return movie.getTicketPrice() - this.getDiscount();
    }

    private double getDiscount() {
        // 25% discount for matinee
        double matineeDiscount = (showStartTime.isAfter(matineeStartTime) && showStartTime.isBefore(matineeEndTime)) ? movie.getTicketPrice() * MATINEE_DISCOUNT_MULTIPLIER : 0;

        // 20% discount for special movie
        double specialDiscount = movie.getSpecial() ? movie.getTicketPrice() * SPECIAL_DISCOUNT_MULTIPLIER : 0;

        // check for sequence discount
        Double sequenceDiscount = sequenceDiscountMap.get(sequenceOfTheDay);
        if (sequenceDiscount == null) {
            sequenceDiscount = 0d;
        }

        // biggest discount wins
        return Collections.max(Arrays.asList(matineeDiscount, specialDiscount, sequenceDiscount));
    }
}
