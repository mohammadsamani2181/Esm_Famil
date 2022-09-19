package com.example.esm_famil.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

public class Game {
    private static int idNumber = 0;
    private int id;
    private String password;
    private String hostName;
    private String groupName;
    private Timestamp dateCreated;
    private int numberOfRound;
    private boolean isEnoughPlayer = false;
    private String gameLetter;
    private ObservableList<String> fields = FXCollections.observableArrayList();

    public Game(String password, String hostName, String groupName) {
        id = idNumber++;
        this.password = password;
        this.hostName = hostName;
        this.groupName = groupName;
    }

    public Game (int id) {
        this.id = id;
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

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ObservableList<String> getFields() {
        return fields;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getNumberOfRound() {
        return numberOfRound;
    }

    public void setNumberOfRound(int numberOfRound) {
        this.numberOfRound = numberOfRound;
    }

    public void setGameLetter(String gameLetter) {
        this.gameLetter = gameLetter;
    }

    public String getGameLetter() {
        return gameLetter;
    }

    public boolean isEnoughPlayer() {
        return isEnoughPlayer;
    }

    public void setEnoughPlayer(boolean enoughPlayer) {
        isEnoughPlayer = enoughPlayer;
    }

    public void addField (String text) {
        fields.add(text);
    }

    public void addField (ObservableList<String> texts) {
        fields.addAll(texts);
    }

}
