package com.jpmc.theater;

import com.jpmc.theater.utils.ExampleData;
import com.jpmc.theater.vo.Theater;

public class StartApp {
    public static void main(String[] args) {
        Theater theater = new Theater(ExampleData.get());
        theater.printSchedule();
        theater.printSchedule(true);
    }
}
