package com.example.examen.controller;

import com.example.examen.domain.Client;
import com.example.examen.domain.Flight;
import com.example.examen.domain.Ticket;
import com.example.examen.service.Service;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class AppController {
    private Client c;
    private Service serv;
    @FXML
    private Label lbl_nume;
    @FXML
    private TableView<Flight> table;
    @FXML
    private TableColumn<Flight, String> id_column;
    @FXML
    private TableColumn<Flight, String> from_column;
    @FXML
    private TableColumn<Flight, String> to_column;
    @FXML
    private TableColumn<Flight, String> departure_column;
    @FXML
    private TableColumn<Flight, String> landing_column;
    @FXML
    private TableColumn<Flight, String> seats_column;
    @FXML
    private ComboBox<String> from_cmbbox;
    @FXML
    private ComboBox<String> to_cmbbox;
    @FXML
    private DatePicker date_picker;
    @FXML
    private Button btn_clear;
    @FXML
    private TableView<Ticket> tbl_ticket_client;
    @FXML
    private TableColumn<Ticket, Long> tblcol_flight_id_ticket;
    @FXML
    private TableColumn<Ticket, String> tblcol_username_ticket;
    @FXML
    private TableColumn<Ticket, LocalDateTime> tblcol_purchasetime_ticket;
    @FXML
    private TableView<Ticket> tbl_ticket_data;
    @FXML
    private TableColumn<Ticket, Long> tblcol_fid_data;
    @FXML
    private TableColumn<Ticket, String> tblcol_username_data;
    ObservableList<Flight> obs_lst_flight = FXCollections.observableArrayList();
    ObservableList<String> obs_lst_from_cmbbox = FXCollections.observableArrayList();
    ObservableList<String> obs_lst_to_cmbbox = FXCollections.observableArrayList();
    ObservableList<Ticket> obs_lst_ticket = FXCollections.observableArrayList();
    ObservableList<Ticket> obs_lst_ticket_data = FXCollections.observableArrayList();

    public void setApp(Service serv, Client c) {
        this.serv = serv;
        this.c = c;
        lbl_nume.setText(c.getName());
        //setComboBoxes();
        setCombo();
        setTable();
        setTableTickets();
        setTable24Ian();
        loadTable24Ian();
        loadTable();
        loadTableTickets();
    }

    private void loadTable24Ian() {
        obs_lst_ticket_data.clear();
        LocalDateTime data24Ian = LocalDateTime.of(2024, 1, 24, 0, 0);
        List<Ticket> tickets = serv.get_all_tickets();
        for (Ticket t : tickets) {
            if (Objects.equals(t.getData().toLocalDate(), data24Ian.toLocalDate())) {
                obs_lst_ticket_data.add(t);
            }
        }
    }

    private void setTable24Ian() {
        tbl_ticket_data.setItems(obs_lst_ticket_data);
        tblcol_fid_data.setCellValueFactory(new PropertyValueFactory<Ticket, Long>("idFlight"));
        tblcol_username_data.setCellValueFactory(new PropertyValueFactory<Ticket, String>("username"));
    }


    public void setCombo() {
        HashSet<String> from = new HashSet<>();
        HashSet<String> to = new HashSet<>();
        for (Flight flight : serv.get_all_flights()) {
            from.add(flight.getFrom());
            to.add(flight.getTo());
        }
        from_cmbbox.getItems().addAll(from);
        to_cmbbox.getItems().addAll(to);
    }

    public void setTable() {
        table.setItems(obs_lst_flight);
        id_column.setCellValueFactory(cellDataFeatures -> {
            Flight f = cellDataFeatures.getValue();
            return new SimpleStringProperty(f.getIdflight().toString());
        });
        from_column.setCellValueFactory(new PropertyValueFactory<Flight, String>("from"));
        to_column.setCellValueFactory(new PropertyValueFactory<Flight, String>("to"));
        departure_column.setCellValueFactory(c -> {
            Flight f = c.getValue();
            return new SimpleStringProperty(f.getDepartureTime().toString());
        });
        landing_column.setCellValueFactory(c -> {
            Flight f = c.getValue();
            return new SimpleStringProperty(f.getLandingTime().toString());
        });
        seats_column.setCellValueFactory(c -> {
            Flight f = c.getValue();
            return new SimpleStringProperty(String.valueOf(f.getSeats()));
        });

        table.setItems(obs_lst_flight);
    }

    public void setTableTickets() {
        tbl_ticket_client.setItems(obs_lst_ticket);
        tblcol_flight_id_ticket.setCellValueFactory(new PropertyValueFactory<Ticket, Long>("idFlight"));
        tblcol_purchasetime_ticket.setCellValueFactory(new PropertyValueFactory<Ticket, LocalDateTime>("data"));
        tblcol_username_ticket.setCellValueFactory(new PropertyValueFactory<Ticket, String>("username"));

    }

    public void loadTableTickets() {
        obs_lst_ticket.clear();
        List<Ticket> tickets = serv.get_all_tickets();
        for (Ticket t : tickets) {
            if (Objects.equals(c.getUsername(), t.getUsername())) {
                obs_lst_ticket.add(t);
            }
        }

    }

    public void loadTable() {
        LocalDate start = date_picker.getValue();
        String from = from_cmbbox.getValue();
        String to = to_cmbbox.getValue();
        if (start != null && from != null && to != null) {

            obs_lst_flight.clear();
            for (Flight fl : serv.get_all_flights()) {
                if (fl.getDepartureTime().toLocalDate().isEqual(start) && fl.getFrom().equals(from) && fl.getTo().equals(to)) {
                        obs_lst_flight.add(fl);
                }
            }
        }
    }

    public void clear_filters() {
        from_cmbbox.setValue(null);
        to_cmbbox.setValue(null);
        date_picker.setValue(null);
        obs_lst_flight.clear();
    }

    public void handleCumpara() {
        try {
            Flight flight = table.getSelectionModel().getSelectedItem();

            if (flight != null) {
                serv.saveTicket(serv.createTicket(flight, c));
                loadTableTickets();
                loadTable24Ian();
                MessageAlert.showErrorMessage(null, "Ai cumparat un Ticket !");
            } else {
                MessageAlert.showErrorMessage(null, "Nu ai selectat niciun Ticket !");
            }
        } catch (NullPointerException e) {
            System.out.println("Eroare: Elementul selectat este null.");
            e.printStackTrace();
        }
    }
}