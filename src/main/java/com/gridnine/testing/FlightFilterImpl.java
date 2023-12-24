package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterImpl implements FlightFilter {
    private final List<Flight> currentFlightList = new ArrayList<>();
    private final List<Segment> segments = new ArrayList<>();

    private final LocalDateTime time = LocalDateTime.now();

    @Override
    public List<Flight> departureBeforeTheCurrentTime(List<Flight> flightList) {
        for (Flight flight : flightList) {
            segments.addAll(flight.getSegments());
            if (segments.size() <= 0) {
                throw new IllegalArgumentException();
            }
            LocalDateTime departure = segments.get(0).getDepartureDate();
            //LocalDateTime arrival = segments.get(0).getArrivalDate();
            if (departure.isBefore(time)) {
                System.out.println(flight.toString());
                currentFlightList.add(flight);
            }
        }
        return currentFlightList;
    }

    @Override
    public List<Flight> arrivalDateBeforeDepartureDate(List<Flight> flightList) {
        return null;
    }

    @Override
    public List<Flight> theTotalTimeSpentOnTheGroundExceedsTwoHours(List<Flight> flightList) {
        return null;
    }

    private void flightToString(Flight flight) {
        flight.toString();

    }
}
