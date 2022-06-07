package com.jpmc.theater.vo;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;

public class Movie {
    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private Boolean isSpecial;

    // todo - do we want these discount values to be updatable? if so, move to db or config
    private static Map<Integer, Double> sequenceDiscountMap = Map.of(
        1, 3d,
        2, 2d
    );
    private static final Double SPECIAL_DISCOUNT_MULTIPLIER = 0.2;

    public Movie(String title, Duration runningTime, double ticketPrice, Boolean isSpecial) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.isSpecial = isSpecial;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing.getSequenceOfTheDay());
    }

    private double getDiscount(int showSequence) {
        // 20% discount for special movie
        double specialDiscount = this.isSpecial ? ticketPrice * SPECIAL_DISCOUNT_MULTIPLIER : 0;

        // check for sequence discount
        Double sequenceDiscount = sequenceDiscountMap.get(showSequence);
        if (sequenceDiscount == null) {
            sequenceDiscount = 0d;
        }

        // biggest discount wins
        return Math.max(specialDiscount, sequenceDiscount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(isSpecial, movie.isSpecial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, isSpecial);
    }
}