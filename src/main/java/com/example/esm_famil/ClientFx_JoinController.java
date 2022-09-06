package com.example.esm_famil;

import com.example.esm_famil.database.Const;
import com.example.esm_famil.database.DBHandler;
import com.example.esm_famil.model.Game;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ClientFx_JoinController {

    private ObservableList games = FXCollections.observableArrayList();

    private DBHandler dbHandler;

    @FXML
    private ImageView joinPageRefreshBtn;

    @FXML
    private MFXLegacyListView<Game> joinPageListView;

    @FXML
    private MFXButton joinPageCreateNewGameBtn;

    @FXML
    void initialize() {
        addGamesToTheGamesList();

        joinPageCreateNewGameBtn.setOnAction(e -> {
            showCreatePage();
        });

        joinPageRefreshBtn.setOnMouseClicked(e -> {
            addGamesToTheGamesList();
        });
    }

    private void addGamesToTheGamesList(){
        dbHandler = new DBHandler();
        ResultSet resultSet = dbHandler.getGames();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt(Const.GAMES_ID);
                String hostname = resultSet.getString(Const.GAMES_HOSTNAME);
                String groupName = resultSet.getString(Const.GAMES_GROUPNAME);
                Timestamp dateCreated = resultSet.getTimestamp(Const.GAMES_DATECREATED);
                int numberOfRound = resultSet.getInt(Const.GAMES_NUMBEROFROUND);
                String password = resultSet.getString(Const.GAMES_PASSWORD);

                Game game = new Game(id);
                game.setHostName(hostname);
                game.setGroupName(groupName);
                game.setDateCreated(dateCreated);
                game.setNumberOfRound(numberOfRound);
                game.setPassword(password);

                games.add(game);
            }

            joinPageListView.setItems(games);
            joinPageListView.setCellFactory(CellController -> new CellController());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showCreatePage() {
        joinPageCreateNewGameBtn.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("clientFx_Create.fxml"));

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Stage stage = new Stage();
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
