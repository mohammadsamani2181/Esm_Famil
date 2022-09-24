package com.example.esm_famil;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;


public class FieldCellController extends ListCell<String> {

    private FXMLLoader loader;

    private GamePageController gamePageController;

    private String gameLetter;

    @FXML
    private AnchorPane fieldCellRoot;

    @FXML
    private MFXTextField fieldCellTxtFld;

    @FXML
    void initialize() {

    }

    public FieldCellController(GamePageController gamePageController) {
        this.gamePageController = gamePageController;
        this.gameLetter = gamePageController.getGame().getGameLetter();
    }

    @Override
    protected void updateItem(String field, boolean empty) {
        super.updateItem(field, empty);

        if (field == null || empty) {
            setText(null);
            setGraphic(null);
        }
        else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("fieldCell.fxml"));
                loader.setController(this);
                gamePageController.addController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            
            fieldCellTxtFld.setPromptText(field);

            fieldCellTxtFld.textProperty().addListener((observableValue, oldValue, newValue) -> {
                System.out.println(gameLetter.charAt(0));
                System.out.println("ol value : " + oldValue);
                System.out.println("new value : " + newValue);
                try {

                    if (newValue.charAt(0) != gameLetter.charAt(0)) {
                        fieldCellTxtFld.setText("");
                    }

                }catch (StringIndexOutOfBoundsException exception) {

                }
            });

            setText(null);
            setGraphic(fieldCellRoot);
        }
    }

    public void setGamePageController(GamePageController gamePageController) {
        this.gamePageController = gamePageController;
    }

    public MFXTextField getFieldCellTxtFld() {
        return fieldCellTxtFld;
    }
}

