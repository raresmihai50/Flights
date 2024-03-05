package com.example.examen.repository;

import com.example.examen.domain.Flight;
import com.example.examen.domain.Ticket;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepoTicket {
    private String url;
    private String username;
    private String password;

    public RepoTicket(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public List<Ticket> getAll(){
        List<Ticket> Tickets = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from ticket");
             ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long id_flight = resultSet.getLong("id_flight");
                Long id_client = resultSet.getLong("id_client");
                String username = resultSet.getString("username");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String purchasetimeS = resultSet.getString("purchasetime");
                LocalDateTime purchasetime = LocalDateTime.parse(purchasetimeS, formatter);
                Ticket ticket = new Ticket(username,id_flight, purchasetime, id_client);
                Tickets.add(ticket);

            }
            return Tickets;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<Ticket> save(Ticket entity){
        if (entity == null)
            throw new IllegalArgumentException("(SAVE)Entity cannot be null!");

        String insertSQL = "insert into ticket (id, id_flight, id_client, username, purchasetime) values (?, ?, ?, ?, ?)";
        try (var connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(insertSQL)) {

            statement.setLong(1, entity.getId());
            statement.setLong(2, entity.getIdFlight());
            statement.setLong(3, entity.getIdClient());
            statement.setString(4, entity.getUsername());
            statement.setTimestamp(5, Timestamp.valueOf(entity.getData().truncatedTo(ChronoUnit.SECONDS)));
            int response = statement.executeUpdate();
            return response == 0 ? Optional.of(entity) : Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
