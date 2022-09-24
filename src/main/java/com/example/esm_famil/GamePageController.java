package com.example.esm_famil;

import com.example.esm_famil.model.Game;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.application.Platform;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GamePageController {

    private StartGame startGame;

    private ArrayList<FieldCellController> fieldCellControllers;

    @FXML
    private MFXLegacyListView<String> gamePageListView;

    @FXML
    void initialize() {
        fieldCellControllers = new ArrayList<>();


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    for (int i = 0; i < fieldCellControllers.size(); i++) {
                        System.out.println("controllers[" + i + "] :" + fieldCellControllers.get(i).getFieldCellTxtFld().getText());
                    }

                    StartGame newStartGame = new StartGame(startGame.getClient(), startGame.getGame());
                    if (startGame.getClient().isHost()) {
                        newStartGame.setMessageManagerCreatingGame(startGame.getMessageManagerCreatingGame());
                    }else {
                        newStartGame.setMessageManagerJoiningGame(startGame.getMessageManagerJoiningGame());
                    }

                    newStartGame.start();
                });
            }
        }, 20000);
    }

    public void addController(FieldCellController controller) {
        fieldCellControllers.add(controller);
    }

    public void showCells() {
        gamePageListView.setItems(startGame.getGame().getFields());
        gamePageListView.setCellFactory(FieldCellController -> new FieldCellController(this));
    }

    public void setShowGamePage(StartGame startGame) {
        this.startGame = startGame;
    }

    public ArrayList<FieldCellController> getControllersSize() {
        return fieldCellControllers;
    }

    public Game getGame() {
        return startGame.getGame();
    }

}

