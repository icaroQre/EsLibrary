package com.controller;

import com.model.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterStudentController {
    
    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfHood;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfNumber;

    @FXML
    private TextField tfRegistration;

    @FXML
    private TextField tfStreet;

    @FXML
    protected void buttonCancel (ActionEvent event){
        App.changeScene("main");
    }
    
    @FXML
    protected void buttonRegister (ActionEvent event){
        
        Student student = new Student(tfName.getText(), tfRegistration.getText(), tfCpf.getText(), tfStreet.getText() + " - " + tfNumber.getText() + " - " + tfHood.getText());
        student.save();
        App.changeScene("main");
    }

}
