package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilterImpl();

        flightFilter.segmentsOfFlight(flightList);
        System.out.println("____________________");
        flightFilter.departureBeforeTheCurrentTime(flightList);
        System.out.println("____________________");
        flightFilter.arrivalDateBeforeDepartureDate(flightList);
        System.out.println("____________________");
        flightFilter.theTotalTimeSpentOnTheGroundExceedsTwoHours(flightList);

    }
}