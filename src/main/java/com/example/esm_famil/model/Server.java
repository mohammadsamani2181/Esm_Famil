package com.example.esm_famil.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ArrayList<Game> games = new ArrayList<>();
    private ArrayList<ArrayList<ClientManager>> clientManagers = new ArrayList<>(5);
    private ServerSocket serverSocket;
    private int serverPort = 8080;

    {
        for (int i = 0; i < clientManagers.size(); i++) {
            clientManagers.add(new ArrayList<ClientManager>());
        }
    }

    public Server () {
        try {
            serverSocket = new ServerSocket(serverPort);
            Socket socket = serverSocket.accept();
            Thread thread = new Thread(new ClientManager(this, socket));
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int createNewGame (String password) {
        Game newGame = new Game(password);
        games.add(newGame);

        // debug
        System.out.println("in server gameId :" + newGame.getId());
        System.out.println("in server gamePassword :" + newGame.getPassword());
        return newGame.getId();
    }

    public void addGameFields(int gameId, String text) {
        games.get(gameId).addField(text);
    }
}
