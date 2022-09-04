package com.example.esm_famil;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class IdentifyPageController {

    @FXML
    private MFXTextField identifyPagePlayerNameFld;

    @FXML
    private MFXPasswordField identifyPageGamePasswordFld;

    @FXML
    private MFXButton identifyPageOkBtn;

    @FXML
    private Label identifyPageErrorLbl;

    @FXML
    void initialize() {

    }

    public MFXTextField getIdentifyPagePlayerNameFld() {
        return identifyPagePlayerNameFld;
    }

    public MFXPasswordField getIdentifyPageGamePasswordFld() {
        return identifyPageGamePasswordFld;
    }

    public MFXButton getIdentifyPageOkBtn() {
        return identifyPageOkBtn;
    }

    public Label getIdentifyPageErrorLbl() {
        return identifyPageErrorLbl;
    }
}
