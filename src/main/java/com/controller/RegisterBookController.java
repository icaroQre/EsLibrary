package com.controller;

import com.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterBookController {

    @FXML
    protected void initialize () {
        App.addOnchangeScreenListener(new App.OnChangeScreen(){

            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("registerBook")){
                    System.out.println("Nova tela: " + newScreen + " , " + userData);
                }
            }

        });
    }

    @FXML
    private TextField tfAvaiable;

    @FXML
    private TextField tfTitleId;

    @FXML
    protected void buttonCancel (ActionEvent event){
        App.changeScene("books");
    }

    @FXML
    void buttonRegister(ActionEvent event) {
        Book book = new Book(Integer.parseInt(tfAvaiable.getText()), Integer.parseInt(tfTitleId.getText()));
        book.save();
        App.changeScene("books");
    }

}