package com.example.esm_famil;

import com.example.esm_famil.model.Game;
import com.example.esm_famil.network.Client;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class ChooseAlphabetLetterController {

    private Client client;

    private Game game;

    private StartGame startGame;

    @FXML
    private MFXTextField chooseAlphabetTxtFld;

    @FXML
    private MFXButton chooseAlphabetStartBtn;

    @FXML
    void initialize() {
        chooseAlphabetTxtFld.setTextLimit(1);

        chooseAlphabetStartBtn.setOnAction(e -> {
            sendAlphabetLetter();
        });
    }

    private void sendAlphabetLetter() {
        if (!chooseAlphabetTxtFld.getText().equals("")) {
            client.sendAlphabetLetter(game.getId(), chooseAlphabetTxtFld.getText());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            showGamePage();

        }else {
            System.out.println("you must enter something");
        }
    }

    private void showGamePage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gamePage.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GamePageController controller = loader.getController();
        controller.setStartGame(startGame);
        controller.showCells();

        startGame.getPane().getChildren().setAll((Node) loader.getRoot());
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setShoGamePage(StartGame startGame) {
        this.startGame = startGame;
    }
}

