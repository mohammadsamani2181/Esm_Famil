package com.example.esm_famil;

import com.example.esm_famil.model.Game;
import com.example.esm_famil.network.Client;
import com.example.esm_famil.network.ServerMessageManagerCreatingGame;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class WaitingHostPageController {

    private Client client;

    private Game game;

    private ServerMessageManagerCreatingGame messageManager;

    private ObservableList<String> playerList = FXCollections.observableArrayList();

    @FXML
    private MFXLegacyListView<String> waitingHostPageListView;

    @FXML
    private MFXButton waitingHostPageStartBtn;

    @FXML
    private AnchorPane waitingHostPagePane;

    @FXML
    void initialize() {
        waitingHostPageListView.setItems(playerList);

        waitingHostPageStartBtn.setOnAction(e -> {
            gamePlayerIsEnough ();

            startGame();
        });
    }

    private void startGame() {
        client.startGame(game.getId());
    }

    private void gamePlayerIsEnough() {
        client.playerIsEnough(game.getId());
    }

    public AnchorPane changePane(AnchorPane newPane) {
        Platform.runLater(() -> {
            this.waitingHostPagePane.getChildren().setAll(newPane);
        });

        return this.waitingHostPagePane;
    }

    public void setMessageManager(ServerMessageManagerCreatingGame messageManager) {
        this.messageManager = messageManager;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void addNewPlayerName (String playerName) {
        playerList.add(playerName);
    }

    public Client getClient() {
        return client;
    }

    public Game getGame() {
        return game;
    }
}
