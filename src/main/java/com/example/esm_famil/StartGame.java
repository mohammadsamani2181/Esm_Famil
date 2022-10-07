package com.example.esm_famil;

import com.example.esm_famil.model.Game;
import com.example.esm_famil.network.Client;
import com.example.esm_famil.network.ServerMessageManagerCreatingGame;
import com.example.esm_famil.network.ServerMessageManagerJoiningGame;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StartGame {
    private Client client;
    private Game game;
    private AnchorPane pane;
    private ServerMessageManagerJoiningGame messageManagerJoiningGame;
    private ServerMessageManagerCreatingGame messageManagerCreatingGame;
    private static int roundNumber = 0;

    public StartGame(Client client, Game game) {
        this.client = client;
        this.game = game;

        roundNumber++;
    }


    public void start() {
        System.out.println(messageManagerCreatingGame);
        System.out.println(messageManagerJoiningGame);
        System.out.println("number of round game :" + game.getNumberOfRound());
        System.out.println("number of round :" + roundNumber);

        if (roundNumber <= game.getNumberOfRound()) {


            if (client.isHost()) {
                showChooseAlphabetLetterPage();

            }else {

                showWaitingPage();
            }


        }else {

        }
    }

    private void showChooseAlphabetLetterPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseAlphabetLetter.fxml"));


        try {
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            ChooseAlphabetLetterController controller = loader.getController();
            controller.setClient(client);
            controller.setGame(game);
            controller.setShoGamePage(this);

            pane = messageManagerCreatingGame.changePane(loader.getRoot());
        });
    }

    private void showWaitingPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("waitingForChoosingAlphabetLetter.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.setImplicitExit(false);
        pane = messageManagerJoiningGame.changePane(loader.getRoot());
    }

    public void loadGamePage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gamePage.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GamePageController controller = loader.getController();
        controller.setStartGame(this);
        controller.showCells();

        Platform.runLater(() -> {
            this.getPane().getChildren().setAll((Node) loader.getRoot());
        });
    }

    public ServerMessageManagerJoiningGame getMessageManagerJoiningGame() {
        return messageManagerJoiningGame;
    }

    public void setMessageManagerJoiningGame(ServerMessageManagerJoiningGame messageManagerJoiningGame) {
        this.messageManagerJoiningGame = messageManagerJoiningGame;
    }

    public ServerMessageManagerCreatingGame getMessageManagerCreatingGame() {
        return messageManagerCreatingGame;
    }

    public void setMessageManagerCreatingGame(ServerMessageManagerCreatingGame messageManagerCreatingGame) {
        this.messageManagerCreatingGame = messageManagerCreatingGame;
    }

    public void setGameLetter(String letter) {
        game.setGameLetter(letter);
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public Client getClient() {
        return client;
    }

    public Game getGame() {
        return game;
    }
}
