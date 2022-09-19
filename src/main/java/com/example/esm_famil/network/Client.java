package com.example.esm_famil.network;

import com.example.esm_famil.CellController;
import com.example.esm_famil.ClientFx_CreateController;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private int port = 8080;
    private String serverAddress = "127.0.0.1";
    private BufferedReader reader;
    private PrintWriter writer;
    private ClientFx_CreateController clientFxCreateController;
    private CellController cellController;
    private boolean isHost;

    public Client (ClientFx_CreateController clientFxCreateController) {
        this.clientFxCreateController = clientFxCreateController;
        this.isHost = true;

        try {
            socket = new Socket(serverAddress, port);

            reader =  new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));


            writer = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())), true);

            ServerMessageManagerCreatingGame manager = new ServerMessageManagerCreatingGame(reader, clientFxCreateController);
            clientFxCreateController.setMessageManager(manager);

            Thread thread = new Thread(manager);
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Client (CellController cellController) {
        this.cellController = cellController;
        this.isHost = false;

        try {
            socket = new Socket(serverAddress, port);

            reader =  new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));


            writer = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())), true);

            ServerMessageManagerJoiningGame messageManager = new ServerMessageManagerJoiningGame(reader, cellController);
            cellController.setMessageManager(messageManager);

            Thread thread = new Thread(messageManager);
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean isHost() {
        return isHost;
    }

    public void createGame (String password, String hostName, String groupName, String numberOfRound) {
        writer.println("CREATE NEW GAME");
        writer.println(password);
        writer.println(hostName);
        writer.println(groupName);
        writer.println(numberOfRound);
    }

    public void sendGameFields(ObservableList<String> texts, int gameId) {
        writer.println("GAME FIELDS");
        writer.println(gameId);
        writer.println(texts.size());

        for (int i = 0; i < texts.size(); i++) {
            writer.println(texts.get(i));
        }
    }

    public void checkGamePlayersForJoin (int gameId) {
        writer.println("CHECK FOR JOIN");
        writer.println(gameId);
    }

    public void playerIsEnough(int gameId) {
        writer.println("PLAYER IS ENOUGH");
        writer.println(gameId);
    }

    public void joinGame(int gameId, String playerName) {
        writer.println("JOIN GAME");
        writer.println(gameId);
        writer.println(playerName);
    }

    public void startGame(int gameId) {
        writer.println("START");
        writer.println(gameId);
    }

    public void sendAlphabetLetter(int gameId, String letter) {
        writer.println("GAME LETTER");
        writer.println(gameId);
        writer.println(letter);
    }
}
