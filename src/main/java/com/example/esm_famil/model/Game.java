package com.example.esm_famil.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Game {
    private static int idNumber = 0;
    private int id;
    private String password;
    private ObservableList<String> fields = FXCollections.observableArrayList();

    public Game(String password) {
        id = idNumber++;
        this.password = password;
    }

    public Game () {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addField (String text) {
        fields.add(text);
    }

    public void addField (ArrayList<String> texts) {
        fields.addAll(texts);
    }
}
