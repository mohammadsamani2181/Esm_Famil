package com.example.esm_famil.network;

import com.example.esm_famil.database.DBHandler;
import com.example.esm_famil.model.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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

    public int createNewGame (String password, String hostName, String groupName) {
        Game newGame = new Game(password, hostName, groupName);
        dbHandler = new DBHandler();
        games.add(newGame);
        dbHandler.createNewGame(newGame);

        return newGame.getId();
    }

    public void addClientManager (int gameId, ClientManager clientManager) {
        clientManagers.get(gameId).add(clientManager);
    }

    public void addGameFields(int gameId, String text) {
        games.get(gameId).addField(text);
    }
}
