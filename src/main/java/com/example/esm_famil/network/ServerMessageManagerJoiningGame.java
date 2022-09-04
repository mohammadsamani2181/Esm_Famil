package com.example.esm_famil.network;

import com.example.esm_famil.CellController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.util.Scanner;

public class ServerMessageManagerJoiningGame implements Runnable{
    private Scanner scan;
    private CellController cellController;

    public ServerMessageManagerJoiningGame(BufferedReader reader, CellController cellController) {
        scan = new Scanner(reader);
        this.cellController = cellController;
    }

    @Override
    public void run() {
        while (true) {
            String message = scan.nextLine();

            if (message.equals("GAME FIELDS")) {
                ObservableList<String> fields = FXCollections.observableArrayList();

                int size = scan.nextInt();
                scan.nextLine();

                for (int i = 0; i < size; i++) {
                    fields.add(scan.nextLine());
                }

                cellController.setGameFields(fields);
            }
        }
    }
}
