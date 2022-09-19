package com.example.esm_famil;

import com.example.esm_famil.model.Game;
import com.example.esm_famil.network.Client;
import com.example.esm_famil.network.ServerMessageManagerJoiningGame;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class WaitingGuestPageController {

    private Game game;

    private Client client;

    private ServerMessageManagerJoiningGame messageManager;

    @FXML
    private AnchorPane waitingGuestPagePane;

    @FXML
    void initialize() {

    }
}
