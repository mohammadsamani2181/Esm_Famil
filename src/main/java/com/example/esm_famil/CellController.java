package com.example.esm_famil;

import com.example.esm_famil.model.Game;
import com.example.esm_famil.network.Client;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class CellController extends ListCell<Game> {

    private FXMLLoader loader;

    private Client client;

    private boolean isGamePlayerEnough = false;

    private ServerMessageManagerJoiningGame messageManager;

    private WaitingGuestPageController waitingPageController;

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
                client = new Client(this);
                client.checkGamePlayersForJoin(game.getId());

                // wait for checking player
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                if (!isGamePlayerEnough) {
                    showAndControlIdentifyPage(game.getPassword());

                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("! You can't join in this game");
                    alert.show();

                    getListView().getItems().remove(getItem());
                }
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
        Label identifyPageErrorLbl = identifyController.getIdentifyPageErrorLbl();

        identifyController.getIdentifyPageOkBtn().setOnAction(e -> {
            String playerName = identifyController.getIdentifyPagePlayerNameFld().getText();
            String password = identifyController.getIdentifyPageGamePasswordFld().getText();


            if (!playerName.equals("") && !password.equals("")) {

                if (password.equals(gamePassword)) {
                    client.joinGame(getItem().getId(), playerName);

                    getListView().getScene().getWindow().hide();
                    identifyPageErrorLbl.getScene().getWindow().hide();

                    showWaitingGuestPage();
                }else {
                    identifyPageErrorLbl.setText("Game Password is incorrect.");
                    makeInvisible(identifyPageErrorLbl);
                }

            }else {
                identifyPageErrorLbl.setText("You must enter your name and Game Password.");
                makeInvisible(identifyPageErrorLbl);
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

    public void setGameFields (ObservableList<String> fields) {
        getItem().addField(fields);
        System.out.println(getItem().getFields());
    }

    public void setGameNumberOfRound (int numberOfRound) {
        getItem().setNumberOfRound(numberOfRound);
    }

    private void showWaitingGuestPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("waitingGuestPage.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void setGamePlayerEnough(boolean gamePlayerEnough) {
        isGamePlayerEnough = gamePlayerEnough;
    }

    public ServerMessageManagerJoiningGame getMessageManager() {
        return messageManager;
    }

    public void setMessageManager(ServerMessageManagerJoiningGame messageManager) {
        this.messageManager = messageManager;
    }

    public Client getClient() {
        return client;
    }

    public Game getGame() {
        return getItem();
    }

    public WaitingGuestPageController getWaitingPageController() {
        return waitingPageController;
    }
}
