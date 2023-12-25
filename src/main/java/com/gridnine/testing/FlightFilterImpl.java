package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterImpl implements FlightFilter {
    private List<Flight> currentFlightList = new ArrayList<>();

    private final LocalDateTime time = LocalDateTime.now();

    /**
     * Метод выводит список всех перелетов
     * @param flightList список перелетов
     */
    public void segmentsOfFlight(List<Flight> flightList) {
        for (Flight flight : flightList) {
            for (int i = 0; i < flight.getSegments().size(); i++) {
                System.out.println(flight.getSegments().get(i));
            }
        }
    }
    /**
     * Метод выводит список всех перелетов где вылет до текущего момента времени
     * @param flightList список перелетов
     */
    @Override
    public List<Flight> departureBeforeTheCurrentTime(List<Flight> flightList) {
        currentFlightList = flightList.stream().filter(flight -> flight.getSegments()
                .stream().anyMatch(segment -> segment.getDepartureDate()
                        .isBefore(time))).collect(Collectors.toList());
        currentFlightList.forEach(System.out::println);

        return currentFlightList;
    }
    /**
     * Метод выводит список всех перелетов где сегменты с датой прилета раньше даты вылета
     * @param flightList список перелетов
     */
    @Override
    public List<Flight> arrivalDateBeforeDepartureDate(List<Flight> flightList) {
        currentFlightList = flightList.stream().filter(flight -> flight.getSegments()
                .stream().anyMatch(segment -> segment.getArrivalDate()
                        .isBefore(segment.getDepartureDate()))).collect(Collectors.toList());
        currentFlightList.forEach(System.out::println);
        return currentFlightList;
    }
    /**
     * Метод выводит список всех перелетов где время проведенное на земле превышает 2 часа
     * @param flightList список перелетов
     */
    @Override
    public List<Flight> theTotalTimeSpentOnTheGroundExceedsTwoHours(List<Flight> flightList) {
        for (Flight flight : flightList) {
            List<Segment> segments = flight.getSegments();
            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime departure = segments.get(i + 1).getDepartureDate();
                LocalDateTime arrival = segments.get(i).getArrivalDate();
                if (departure.isAfter(arrival.plusHours(2))) {
                    currentFlightList.add(flight);
                }
            }
        }
        currentFlightList.forEach(System.out::println);
        return currentFlightList;
    }

}
