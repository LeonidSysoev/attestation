package com.gridnine.testing;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightFilterImplTest {
    FlightFilter filter = new FlightFilterImpl();
    LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);


    @Test
    void departureBeforeTheCurrentTimeTest() {
        Flight flight1 = new Flight(List.of(
                new Segment(threeDaysFromNow, threeDaysFromNow.plusDays(3)),
                new Segment(threeDaysFromNow.minusDays(4), threeDaysFromNow)));
        Flight flight2 = new Flight(List.of(
                new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(5))));
        List<Flight> flights = Arrays.asList(flight1, flight2);
        assertEquals(1, filter.departureBeforeTheCurrentTime(flights).size());
    }

    @Test
    void arrivalDateBeforeDepartureDateTest() {
        Flight flight1 = new Flight(List.of(
                new Segment(threeDaysFromNow, threeDaysFromNow.plusDays(3)),
                new Segment(threeDaysFromNow.minusDays(4), threeDaysFromNow)));
        Flight flight2 = new Flight(List.of(
                new Segment(threeDaysFromNow, threeDaysFromNow.minusHours(6))));
        List<Flight> flights = Arrays.asList(flight1, flight2);
        assertEquals(1, filter.arrivalDateBeforeDepartureDate(flights).size());
    }

    @Test
    void theTotalTimeSpentOnTheGroundExceedsTwoHoursTest() {
        Flight flight1 = new Flight(List.of(
                new Segment(threeDaysFromNow, threeDaysFromNow.plusDays(3)),
                new Segment(threeDaysFromNow.minusDays(4), threeDaysFromNow)));
        Flight flight2 = new Flight(List.of(
                new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                new Segment(threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(8))));
        List<Flight> flights = List.of(flight1, flight2);
        assertEquals(1, filter.theTotalTimeSpentOnTheGroundExceedsTwoHours(flights).size());
    }
}