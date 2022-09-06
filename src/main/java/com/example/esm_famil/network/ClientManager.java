package com.example.esm_famil.network;


import javafx.collections.ObservableList;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientManager implements Runnable {
    private Server server;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String name;


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
                    String hostName = scan.nextLine();
                    String groupName = scan.nextLine();
                    int numberOfRound = Integer.parseInt(scan.nextLine());

                    int gameId = server.createNewGame(password, hostName, groupName, numberOfRound);
                    server.addClientManagerAsHost(gameId, this);

                    writer.println("GAME ID");
                    writer.println(gameId);

                }

                else if (message.equals("GAME FIELDS")) {
                    int gameId = scan.nextInt();
                    int size = scan.nextInt();
                    scan.nextLine();

                    for (int i = 0; i < size; i++) {
                        server.addGameFields(gameId ,scan.nextLine());
                    }
                }

                else if (message.equals("JOIN GAME")) {
                    int gameId = scan.nextInt();
                    scan.nextLine();
                    this.name = scan.nextLine();

                    server.addClientManagerAsGuest(gameId, this);
                    ObservableList<String> fields = server.getGameFields(gameId);

                    writer.println("GAME FIELDS");
                    writer.println(fields.size());

                    for (String field : fields) {
                        writer.println(field);
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void playerJoined (ClientManager clientManager) {
        writer.println("PLAYER JOINED");
        writer.println(clientManager.name);
    }
}
