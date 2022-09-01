package com.example.esm_famil;


import com.example.esm_famil.model.Server;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main_Server extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        new Server();
    }
}
