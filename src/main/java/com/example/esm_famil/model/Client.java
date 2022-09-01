package com.example.esm_famil.model;

import com.example.esm_famil.ClientFx_CreateController;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private int port = 8080;
    private String serverAddress = "127.0.0.1";
    private BufferedReader reader;
    private PrintWriter writer;
    private ClientFx_CreateController clientFxCreateController;
    private int gameId;

    public Client (ClientFx_CreateController clientFxCreateController) {
        this.clientFxCreateController = clientFxCreateController;

        try {
            socket = new Socket(serverAddress, port);

            reader =  new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));


            writer = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())), true);

            Thread thread = new Thread(new ServerMessageManager(reader, this));
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setGameId(int gameId) {
        this.gameId = gameId;
    }


    public int createGame (String password) {
        // debug
        System.out.println("in client create game");
        writer.println("CREATE NEW GAME");
        writer.println(password);

        return gameId;
    }

    public void sendingGameFields (ArrayList<String> texts) {
        // debug
        System.out.println("in client sending fields");

        writer.println("GAME FIELDS");
        writer.println(gameId);
        writer.println(texts.size());

        for (int i = 0; i < texts.size(); i++) {
            writer.println(texts.get(i));
        }
    }
}
