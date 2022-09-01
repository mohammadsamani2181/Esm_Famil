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

                if (message.equals("CREATE NEW GAME")) {
                    String password = scan.nextLine();
                    int gameId = server.createNewGame(password);

                    writer.println("GAME ID");
                    writer.println(gameId);
                }

                else if (message.equals("GAME FIELDS")) {
                    int gameId = scan.nextInt();
                    int size = scan.nextInt();

                    for (int i = 0; i < size; i++) {
                        server.addGameFields(gameId ,scan.nextLine());
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
