package com.example.esm_famil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClientFxController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}