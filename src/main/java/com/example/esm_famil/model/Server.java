package com.example.esm_famil.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private int serverPort = 8080;

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
}
