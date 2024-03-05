package com.example.examen.controller;

import com.example.examen.MainApp;
import com.example.examen.domain.Client;
import com.example.examen.service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {
    private Service serv;
    @FXML
    private Button btn_login;
    @FXML
    private TextField txtfield;

    public void setService(Service serv) {
        this.serv = serv;
    }
    public void handleLogin(ActionEvent actionEvent){
        String username = txtfield.getText();
        Client c = serv.get_client(username);
        if (c==null){
            MessageAlert.showErrorMessage(null, "Username Inexistent !");
        }
        else {
            showApp(c);
        }
    }
    public void showApp(Client c){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("app-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 1400, 700);
            stage.setScene(scene);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Client");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            AppController appController = fxmlLoader.getController();
            appController.setApp(serv,c);
            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
