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
    public Boolean getSpecial() {
        return isSpecial;
    }

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