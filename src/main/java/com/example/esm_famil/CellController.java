package com.example.esm_famil;

import com.example.esm_famil.model.Game;
import com.example.esm_famil.network.Client;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class CellController extends ListCell<Game> {

    private FXMLLoader loader;

    private Client client;

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
    private MFXButton cellJoinBtn;

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

            cellJoinBtn.setOnAction(e -> {
                showAndControlIdentifyPage(game.getPassword());
            });
        }
    }

    private void showAndControlIdentifyPage(String gamePassword) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("identifyPage.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        IdentifyPageController identifyController = loader.getController();
        Label errorLbl = identifyController.getIdentifyPageErrorLbl();

        identifyController.getIdentifyPageOkBtn().setOnAction(e -> {
            String playerName = identifyController.getIdentifyPagePlayerNameFld().getText();
            String password = identifyController.getIdentifyPageGamePasswordFld().getText();


            if (!playerName.equals("") && !password.equals("")) {

                if (password.equals(gamePassword)) {
                    client = new Client();

                }else {
                    errorLbl.setText("Game Password is incorrect.");
                    makeInvisible(errorLbl);
                }

            }else {
                errorLbl.setText("You must enter your name and Game Password.");
                makeInvisible(errorLbl);
            }
        });


        Stage stage = new Stage();
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void makeInvisible (Label label) {
        PauseTransition visiblePause1 = new PauseTransition(
                Duration.seconds(7)
        );
        visiblePause1.setOnFinished(
                event -> label.setVisible(false)
        );
        visiblePause1.play();



        PauseTransition visiblePause2 = new PauseTransition(
                Duration.seconds(7)
        );
        visiblePause2.setOnFinished(
                event -> label.setVisible(true)
        );
        visiblePause2.play();



        PauseTransition visiblePause3 = new PauseTransition(
                Duration.seconds(7)
        );
        visiblePause3.setOnFinished(
                event -> label.setText("")
        );
        visiblePause3.play();
    }

}
