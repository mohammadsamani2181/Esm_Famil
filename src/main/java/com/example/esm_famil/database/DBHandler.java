package com.example.esm_famil.database;

import com.example.esm_famil.model.Game;

import java.sql.*;

public class DBHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/"
                + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);


        return dbConnection;
    }


    public void createNewGame (Game game) {
        String insert = "INSERT INTO " + Const.GAMES_TABLE +
                "(" +
                Const.GAMES_ID + ", " + Const.GAMES_HOSTNAME + ", " +
                Const.GAMES_GROUPNAME + ", " + Const.GAMES_PASSWORD + ", " +
                Const.GAMES_DATECREATED +
                ")" + "VALUES(?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setInt(1, game.getId());
            preparedStatement.setString(2, game.getHostName());
            preparedStatement.setString(3, game.getGroupName());
            preparedStatement.setString(4, game.getPassword());
            preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
