package com.example.esm_famil.network;

import com.example.esm_famil.ClientFx_CreateController;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket socket;
    private int port = 8080;
    private String serverAddress = "127.0.0.1";
    private BufferedReader reader;
    private PrintWriter writer;
    private ClientFx_CreateController clientFxCreateController;

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

            Thread thread = new Thread(new ServerMessageManager(reader, clientFxCreateController));
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Client () {

        try {
            socket = new Socket(serverAddress, port);

            reader =  new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));


            writer = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())), true);

            Thread thread = new Thread(new ServerMessageManager(reader, clientFxCreateController));
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void createGame (String password, String hostName, String groupName, String numberOfRound) {
        writer.println("CREATE NEW GAME");
        writer.println(password);
        writer.println(hostName);
        writer.println(groupName);
        writer.println(numberOfRound);
    }

    public void sendingGameFields (ArrayList<String> texts, int gameId) {
        writer.println("GAME FIELDS");
        writer.println(gameId);
        writer.println(texts.size());

        for (int i = 0; i < texts.size(); i++) {
            writer.println(texts.get(i));
        }
    }
}
