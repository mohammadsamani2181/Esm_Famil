package com.example.esm_famil.model;

import com.example.esm_famil.ClientFx_CreateController;

import java.io.BufferedReader;
import java.util.Scanner;

public class ServerMessageManager implements Runnable{
    private BufferedReader reader;
    private Scanner scan;
    private Client myClient;

    public ServerMessageManager(BufferedReader reader,
                                Client client) {

        scan = new Scanner(reader);
        this.myClient = client;
    }

    @Override
    public void run() {

        while (true) {
            String message = scan.nextLine();

            if (message.equals("GAME ID")) {
                int gameId = scan.nextInt();
                myClient.setGameId(gameId);
            }

        }
    }
}
