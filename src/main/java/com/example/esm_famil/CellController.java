package com.example.esm_famil;

import com.example.esm_famil.model.Game;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CellController extends ListCell<Game> {

    private FXMLLoader loader;

    @FXML
    private AnchorPane cellPane;

    @FXML
    private Label cellDateCreated;

    @FXML
    private Label cellGroupName;

    @FXML
    private Label cellHostName;

    @FXML
    private Label cellNumberOfRound;

    @FXML
    private MFXButton cellJoinButton;

    @FXML
    void initialize() {

    }

    @Override
    protected void updateItem(Game game, boolean empty) {
        super.updateItem(game, empty);

        if (game == null || empty) {
            setText(null);
            setGraphic(null);
        }else {
            loader = new FXMLLoader(getClass().getResource("cell.fxml"));
            loader.setController(this);

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            cellHostName.setText(game.getHostName());
            cellGroupName.setText(game.getGroupName());
            cellNumberOfRound.setText(Integer.toString(game.getNumberOfRound()));
            cellDateCreated.setText(game.getDateCreated().toString());

            setText(null);
            setGraphic(cellPane);
        }
    }
}
