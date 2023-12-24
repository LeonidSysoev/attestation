package com.gridnine.testing;

import java.util.List;

public interface FlightFilter {
    List<Flight> departureBeforeTheCurrentTime(List<Flight> flightList);

    List<Flight> arrivalDateBeforeDepartureDate(List<Flight> flightList);
    List<Flight> theTotalTimeSpentOnTheGroundExceedsTwoHours(List<Flight> flightList);

}
