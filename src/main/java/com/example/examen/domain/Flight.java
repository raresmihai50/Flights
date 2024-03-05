package com.example.examen.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight extends Entity<Long> {
    private long flightId;
    private String from;
    private String to;
    private LocalDateTime departureTime;
    private LocalDateTime landingTime;
    private int seats;

    public Flight(Long id, String from, String to, LocalDateTime departureTime, LocalDateTime landingTime, int seats) {
        this.flightId = id;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
        this.from = from;
        this.to = to;
        this.seats = seats;
    }

    private void setflightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getIdflight() {
        return this.flightId;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    public LocalDateTime getDepartureTime() {
        return this.departureTime;
    }

    public LocalDateTime getLandingTime() {
        return this.landingTime;
    }
    public int getSeats(){
        return this.seats;
    }
    @Override
    public int hashCode() {
        return Objects.hash(flightId, from, to, departureTime, landingTime, seats);
    }
}
