package com.example.esm_famil;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.fxml.FXML;

public class ClientFx_CreateController {

    @FXML
    private MFXCheckbox createPageFirstnameBox;

    @FXML
    private MFXCheckbox createPageFoodBox;

    @FXML
    private MFXCheckbox createPageLastnameBox;

    @FXML
    private MFXCheckbox createPageObjectBox;

    @FXML
    private MFXCheckbox createPageCityBox;

    @FXML
    private MFXCheckbox createPageFruitBox;

    @FXML
    private MFXCheckbox createPageCountryBox;

    @FXML
    private MFXCheckbox createPageCarBox;

    @FXML
    private MFXCheckbox createPageFlowerBox;

    @FXML
    private MFXCheckbox createPageAnimalBox;

    @FXML
    private MFXTextField createPagePlayerNameFld;

    @FXML
    private MFXButton createPageCreateBtn;

    @FXML
    private MFXToggleButton createPageType;

    @FXML
    private MFXTextField createPageGroupNameFld;

    @FXML
    private MFXPasswordField createPagePasswordFld;

    @FXML
    private MFXLegacyComboBox<String> createPageNumberOfRound;

    @FXML
    void initialize() {
        createPageNumberOfRound.getItems().addAll("1", "2", "3", "4");
        createPageNumberOfRound.getSelectionModel().select("1");


    }
}
