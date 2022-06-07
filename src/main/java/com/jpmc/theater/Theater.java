package com.jpmc.theater;

import com.jpmc.theater.utils.ExampleData;
import com.jpmc.theater.utils.TimeUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Theater {
    private List<Showing> schedule;

    public Theater(List<Showing> showings) {
        this.schedule = showings;
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public void printSchedule() {
        System.out.println(LocalDate.now());
        System.out.println("===================================================");
        schedule.forEach(s ->
            System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + TimeUtils.humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );
        System.out.println("===================================================");
    }

    public static void main(String[] args) {
        Theater theater = new Theater(ExampleData.get());
        theater.printSchedule();
    }
}
