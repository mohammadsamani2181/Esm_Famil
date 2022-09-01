package com.example.esm_famil.model;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientManager implements Runnable {
    private Server server;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;


    public ClientManager(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            reader =  new BufferedReader(
                          new InputStreamReader(
                            socket.getInputStream()));

            Scanner scan = new Scanner(reader);

            writer = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())), true);

            while (true) {
                String message = scan.nextLine();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
