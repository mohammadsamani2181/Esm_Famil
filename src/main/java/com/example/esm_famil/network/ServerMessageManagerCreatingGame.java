package com.example.esm_famil.network;

import com.example.esm_famil.ClientFx_CreateController;
import com.example.esm_famil.StartGame;
import com.example.esm_famil.WaitingHostPageController;
import com.example.esm_famil.model.Game;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.util.Scanner;

public class ServerMessageManagerCreatingGame implements Runnable{
    private Scanner scan;
    private ClientFx_CreateController clientFx;
    private WaitingHostPageController waitingController;
    private StartGame startGame;

    public ServerMessageManagerCreatingGame(BufferedReader reader,
                                            ClientFx_CreateController clientFx) {

        scan = new Scanner(reader);
        this.clientFx = clientFx;
    }


    @Override
    public void run() {

        while (true) {
            String message = scan.nextLine();

            if (message.equals("GAME ID")) {
                int gameId = scan.nextInt();

                Game game = new Game(gameId);
                clientFx.setGame(game);
            }

            else if (message.equals("PLAYER JOINED")) {
                String playerName = scan.nextLine();
                Platform.runLater(() -> {
                    waitingController.addNewPlayerName(playerName);
                });
            }

            else if (message.equals("START")) {
                startGame = new StartGame(waitingController.getClient(), waitingController.getGame());
                startGame.setMessageManagerCreatingGame(this);
                startGame.start();
            }

            else if (message.equals("LETTER")) {
                String letter = scan.nextLine();
                startGame.setGameLetter(letter);
            }

        }
    }

    public void setWaitingController(WaitingHostPageController waitingController) {
        this.waitingController = waitingController;
    }

    public AnchorPane changePane(AnchorPane newPane) {
        return waitingController.changePane(newPane);
    }
}
