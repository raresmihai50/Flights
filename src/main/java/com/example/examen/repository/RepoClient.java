package com.example.examen.repository;

import com.example.examen.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepoClient {
    private String url;
    private String username;
    private String password;

    public RepoClient(String url, String username, String password){
        this.url=url;
        this.username=username;
        this.password=password;

    }

    public List<Client> getAll() {

        List<Client> Clienti = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from client");
             ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String username= resultSet.getString("username");
                String name= resultSet.getString("name");
                Client Client = new Client(username,name);
                Client.setId(id);
                Clienti.add(Client);

            }
            return Clienti;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Client getOne(String username){
        List<Client> Clienti = getAll();
        for (Client c:Clienti){
            if (Objects.equals(c.getUsername(), username))
                return c;
        }
        return null;
    }
}
