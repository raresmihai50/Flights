package com.example.examen.service;

import com.example.examen.domain.Client;
import com.example.examen.domain.Flight;
import com.example.examen.domain.Ticket;
import com.example.examen.repository.RepoClient;
import com.example.examen.repository.RepoFlight;
import com.example.examen.repository.RepoTicket;

import java.time.LocalDateTime;
import java.util.*;

public class Service {
    RepoClient repoclient;
    RepoFlight repoflight;
    RepoTicket repoticket;

    public Service(RepoClient repoclient, RepoFlight repoflight,RepoTicket repoticket) {
        this.repoclient = repoclient;
        this.repoflight = repoflight;
        this.repoticket =  repoticket;
    }

    public Client get_client(String username) {
        return repoclient.getOne(username);
    }

    public List<String> get_from() {
        List<String> departures = new ArrayList<>();
        List<Flight> flights = repoflight.getAll();
        for (Flight f : flights) {
            departures.add(f.getFrom());
        }
        return departures;
    }

    public List<String> get_to() {
        List<String> departures = new ArrayList<>();
        List<Flight> flights = repoflight.getAll();
        for (Flight f : flights) {
            departures.add(f.getTo());
        }
        return departures;
    }
    public List<Flight> get_flight_to_from(String from, String to){
        List<Flight> flihgts = repoflight.getAll();
        List<Flight> res = new ArrayList<>();
        for (Flight f : flihgts){
            if (Objects.equals(f.getFrom(), from) && Objects.equals(f.getTo(), to)){
                res.add(f);
            }
        }
        return res;
    }
    public List<Flight> get_flight_from(String from){
        List<Flight> flights = repoflight.getAll();
        List<Flight> res = new ArrayList<>();
        for (Flight f : flights){
            if (Objects.equals(f.getFrom(), from)){
                res.add(f);
            }
        }
        return res;
    }
    public List<Flight> get_flight_to(String to){
        List<Flight> flihgts = repoflight.getAll();
        List<Flight> res = new ArrayList<>();
        for (Flight f : flihgts){
            if (Objects.equals(f.getTo(), to)){
                res.add(f);
            }
        }
        return res;
    }


    public List<Flight> get_all_flights() {
        return repoflight.getAll();
    }
    public List<Ticket> get_all_tickets(){return repoticket.getAll();}
    public Optional<Ticket> saveTicket(Ticket ticket){
        return repoticket.save(ticket);
    }

    public Ticket createTicket(Flight flight, Client c) {
        Ticket t = new Ticket(c.getUsername(),flight.getIdflight(), LocalDateTime.now(),c.getId());
        Random rand = new Random();
        t.setId(rand.nextLong(10000));
        return t;
    }
}
