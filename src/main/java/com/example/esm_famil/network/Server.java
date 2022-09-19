package com.example.esm_famil.network;

import com.example.esm_famil.database.DBHandler;
import com.example.esm_famil.model.Game;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static ArrayList<Game> games = new ArrayList<>();
    private static int listCapacity = 5;
    private static List<List<ClientManager>> clientManagers = new ArrayList<>(listCapacity);
    private DBHandler dbHandler;
    private ServerSocket serverSocket;
    private int serverPort = 8080;

    {
        for (int i = 0; i < listCapacity; i++) {
            clientManagers.add(new ArrayList<ClientManager>());
        }
    }


    public Server () {

        try {
            serverSocket = new ServerSocket(serverPort);

            while (true) {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new ClientManager(this, socket));
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int createNewGame (String password, String hostName, String groupName, int numberOfRound) {
        Game newGame = new Game(password, hostName, groupName);
        dbHandler = new DBHandler();

        newGame.setDateCreated(new Timestamp(System.currentTimeMillis()));
        newGame.setNumberOfRound(numberOfRound);

        games.add(newGame);
        dbHandler.createNewGame(newGame);

        return newGame.getId();
    }

    public void addClientManagerAsHost(int gameId, ClientManager clientManager) {
        clientManagers.get(gameId).add(clientManager);
    }

    public void addClientManagerAsGuest(int gameId, ClientManager clientManager) {
        clientManagers.get(gameId).add(clientManager);
        clientManagers.get(gameId).get(0).playerJoined(clientManager);
    }

    public void addGameFields(int gameId, String text) {
        games.get(gameId).addField(text);
    }

    public ObservableList<String> getGameFields (int gameId) {
        return games.get(gameId).getFields();
    }

    public int getGameNumberOfRound (int gameId) {
        return games.get(gameId).getNumberOfRound();
    }

    public void playerIsEnough (int gameId) {
        games.get(gameId).setEnoughPlayer(true);
        dbHandler.deleteGame(gameId);
    }

    public boolean checkGamePlayersForJoin (int gameId) {
        if (games.get(gameId).isEnoughPlayer()) {
            return true;
        }

        return false;
    }
}
