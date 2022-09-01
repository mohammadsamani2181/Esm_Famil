package com.example.esm_famil.model;

public class Game {
    private static int idNumber = -1;
    private int id;
    private String password;

    {
        id = idNumber++;
    }

    public Game(String password) {
        this.password = password;
    }
}
