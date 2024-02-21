package com.controller;

import com.model.Title;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterTitleController {

    @FXML
    private TextField tfDays;

    @FXML
    private TextField tfEdition;

    @FXML
    private TextField tfIsbn;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfYear;

    @FXML
    void buttonCancel(ActionEvent event) {
        App.changeScene("titles");
    }

    @FXML
    void buttonRegister(ActionEvent event) {
        Title title = new Title(tfName.getText(), Integer.parseInt(tfDays.getText()), tfIsbn.getText(), Integer.parseInt(tfEdition.getText()), Integer.parseInt(tfYear.getText()));
        title.save();
        App.changeScene("titles");
    }
}
