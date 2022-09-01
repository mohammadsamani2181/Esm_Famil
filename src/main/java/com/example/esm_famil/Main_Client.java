package com.example.esm_famil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_Client extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main_Client.class.getResource("clientFx_Create.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 430);
        stage.setTitle("Esm & Famil Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}