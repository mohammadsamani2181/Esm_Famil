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

    public void setGame(Game game) {
        this.game = game;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ServerMessageManagerJoiningGame getMessageManager() {
        return messageManager;
    }

    public void setMessageManager(ServerMessageManagerJoiningGame messageManager) {
        this.messageManager = messageManager;
    }

    public AnchorPane changePane(AnchorPane newPane) {
        Platform.runLater(() -> {
            this.waitingGuestPagePane.getChildren().setAll(newPane);
        });
        return waitingGuestPagePane;
    }
}
