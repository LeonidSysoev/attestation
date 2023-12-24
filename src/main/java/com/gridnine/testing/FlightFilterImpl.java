package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterImpl implements FlightFilter {
    private List<Flight> currentFlightList = new ArrayList<>();
    private List<Segment> segments = new ArrayList<>();

    private final LocalDateTime time = LocalDateTime.now();

    @Override
    public void segmentsOfFlight(List<Flight> flightList) {
        for (Flight flight : flightList) {
            for (int i = 0; i < flight.getSegments().size(); i++) {
                System.out.println(flight.getSegments().get(i));
            }
        }
    }

    @Override
    public List<Flight> departureBeforeTheCurrentTime(List<Flight> flightList) {
        currentFlightList = flightList.stream().filter(flight -> flight.getSegments()
                .stream().anyMatch(segment -> segment.getDepartureDate()
                        .isBefore(LocalDateTime.now()))).collect(Collectors.toList());
        currentFlightList.forEach(System.out::println);
//        for (Flight flight : flightList) {
//            segments.addAll(flight.getSegments());
////            if (segments.size() <= 0) {
////                throw new IllegalArgumentException();
////            }
//            LocalDateTime departure = segments.get(0).getDepartureDate();
//            if (departure.isBefore(time)) {
//                //System.out.println(flight);
//                currentFlightList.add(flight);
//                currentFlightList.forEach(System.out::println);
//            }
//        }
        return currentFlightList;
    }

    @Override
    public List<Flight> arrivalDateBeforeDepartureDate(List<Flight> flightList) {
//        for (Flight flight : flightList) {
//            segments.addAll(flight.getSegments());
//            if (segments.size() <= 0) {
//                throw new IllegalArgumentException();
//            }
//            LocalDateTime departure = segments.get(0).getDepartureDate();
//            LocalDateTime arrival = segments.get(0).getArrivalDate();
//            if (arrival.isBefore(departure)) {
//                System.out.println(flight);
//                currentFlightList.add(flight);
//            }
//        }
        currentFlightList = flightList.stream().filter(flight -> flight.getSegments()
                .stream().anyMatch(segment -> segment.getArrivalDate()
                        .isBefore(segment.getDepartureDate()))).collect(Collectors.toList());
        currentFlightList.forEach(System.out::println);
        return currentFlightList;
    }

    @Override
    public List<Flight> theTotalTimeSpentOnTheGroundExceedsTwoHours(List<Flight> flightList) {
        for (Flight flight : flightList) {
            //segments.addAll(flight.getSegments());
            segments = flight.getSegments();
            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime departure = segments.get(i + 1).getDepartureDate();
                LocalDateTime arrival = segments.get(i).getArrivalDate();
                if (arrival.plusHours(2).isBefore(departure)) {
                    currentFlightList.add(flight);
                }
            }
        }
        currentFlightList.forEach(System.out::println);
        return currentFlightList;
    }

}
