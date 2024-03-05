package com.example.examen;

import com.example.examen.controller.LogInController;
import com.example.examen.domain.Client;
import com.example.examen.domain.Flight;
import com.example.examen.repository.RepoClient;
import com.example.examen.repository.RepoFlight;
import com.example.examen.repository.RepoTicket;
import com.example.examen.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/examen";
        String username = "postgres";
        String password = "raresmihai1";
        RepoClient repoclient = new RepoClient(url, username, password);
        RepoFlight repoflight = new RepoFlight(url, username, password);
        RepoTicket repoticket = new RepoTicket(url, username, password);
        Service service = new Service(repoclient, repoflight, repoticket);
        repoclient.getAll();
        repoflight.getAll();
        repoticket.getAll();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 300);
        LogInController ctr = fxmlLoader.getController();
        ctr.setService(service);
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
