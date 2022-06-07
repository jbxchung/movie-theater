package com.jpmc.theater.vo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpmc.theater.utils.TimeUtils;

import java.time.LocalDate;
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
        this.printSchedule(false);
    }

    public void printSchedule(Boolean json) {
        System.out.println(LocalDate.now());
        System.out.println("===================================================");
        if (json) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            System.out.println(gson.toJson(schedule));
        } else {
            schedule.forEach(s ->
                    System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + TimeUtils.humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
            );
        }
        System.out.println("===================================================");
    }
}
