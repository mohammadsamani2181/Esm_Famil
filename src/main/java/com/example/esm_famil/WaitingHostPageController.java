package com.example.esm_famil;

import com.example.esm_famil.model.Game;
import com.example.esm_famil.network.Client;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class WaitingHostPageController {

    private Client client;

    private Game game;

    private ObservableList<String> playerList = FXCollections.observableArrayList();

    @FXML
    private MFXLegacyListView<String> waitingHostPageListView;

    @FXML
    private MFXButton waitingHostPageStartBtn;

    @FXML
    void initialize() {

    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}