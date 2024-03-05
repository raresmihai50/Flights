package com.example.examen.repository;

import com.example.examen.domain.Flight;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RepoFlight {
    private String url;
    private String username;
    private String password;

    public RepoFlight(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

    }

    public List<Flight> getAll() {

        List<Flight> Flights = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from flight");
             ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                Long flightId = resultSet.getLong("id");
                String from = resultSet.getString("from");
                String to = resultSet.getString("to");
                int seats = resultSet.getInt("seats");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String departureTimeString = resultSet.getString("departuretime");
                String landingTimeString = resultSet.getString("landingtime");
                LocalDateTime departureTime = LocalDateTime.parse(departureTimeString, formatter);
                LocalDateTime landingTime = LocalDateTime.parse(landingTimeString, formatter);
                Flight flight = new Flight(flightId, from, to, departureTime, landingTime, seats);
                Flights.add(flight);

            }
            return Flights;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
