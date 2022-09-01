package com.example.esm_famil.model;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private int port = 8080;
    private String serverAddress = "127.0.0.1";
    private BufferedReader reader;
    private PrintWriter writer;

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
