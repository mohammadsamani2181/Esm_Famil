package com.example.esm_famil;

import com.example.esm_famil.network.Client;
import com.example.esm_famil.model.Game;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.fxml.FXML;

import java.util.ArrayList;

public class ClientFx_CreateController {


    private Client client;

    private Game game;

    private int gameId = -1;

    private ArrayList<MFXCheckbox> checkboxes = new ArrayList<>();

    private ArrayList<String> selectedCheckBoxesText = new ArrayList<>();


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
    private MFXTextField createPageHostNameFld;

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
        // setting items for comboBox
        createPageNumberOfRound.getItems().addAll("1", "2", "3", "4");


        // connecting to the server
        client = new Client(this);


        // adding checkboxes to the array list
        addToTheArrayList();


        createPageCreateBtn.setOnAction(e -> {
            checkingFields();
        });
    }

    private void addToTheArrayList() {
        checkboxes.add(createPageFirstnameBox);
        checkboxes.add(createPageLastnameBox);
        checkboxes.add(createPageFoodBox);
        checkboxes.add(createPageCityBox);
        checkboxes.add(createPageAnimalBox);
        checkboxes.add(createPageCountryBox);
        checkboxes.add(createPageFruitBox);
        checkboxes.add(createPageFlowerBox);
        checkboxes.add(createPageCarBox);
        checkboxes.add(createPageObjectBox);
    }

    private void checkingFields() {
        int numberOfSelected = 0;

        for (MFXCheckbox checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                numberOfSelected++;
                selectedCheckBoxesText.add(checkbox.getText());
            }
        }

        if (numberOfSelected >= 5) {

            if (!areFieldsEmpty()) {
                createGame();

            }else {
                System.out.println("you must enter the information");
            }


        }else {
            selectedCheckBoxesText.clear();
            System.out.println("you must choose at least 5 checkBoxes");
        }
    }

    private boolean areFieldsEmpty() {
        if (!createPageGroupNameFld.getText().equals("") &&
            !createPageHostNameFld.getText().equals("") &&
            !createPagePasswordFld.getText().equals("") &&
            createPageNumberOfRound.getValue() != null) {

            return false;
        }

        return true;
    }

    private void createGame () {
        client.createGame(createPagePasswordFld.getText(), createPageHostNameFld.getText(),
                          createPageGroupNameFld.getText(), createPageNumberOfRound.getValue());

    }

    public void setGameFields () {
        String password = createPagePasswordFld.getText();
        String hostName = createPageHostNameFld.getText();
        String groupName = createPageGroupNameFld.getText();

        game.setPassword(password);
        game.setHostName(hostName);
        game.setGroupName(groupName);

        game.addField(selectedCheckBoxesText);

        client.sendingGameFields(selectedCheckBoxesText, game.getId());
    }

    public void setGame (Game game) {
        this.game = game;
    }

}
