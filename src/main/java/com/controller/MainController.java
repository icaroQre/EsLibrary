package com.controller;

import com.model.Student;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainController { 

    @FXML
    protected ListView<Student> lvStudents; 

    @FXML
    public void initialize () {
        App.addOnchangeScreenListener(new App.OnChangeScreen(){

            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("main")){
                    updateList();
                }
            }
        });
        updateList();
    }

    @FXML
    protected void buttonRegisterStudent (ActionEvent event){
        App.changeScene("registerStudent");
    }

    @FXML
    protected void buttonBooks (ActionEvent event){
        App.changeScene("books");
    }

    @FXML
    protected void buttonDeleteStudent (ActionEvent event){
        ObservableList<Student> ol = lvStudents.getSelectionModel().getSelectedItems();

        if(!ol.isEmpty()){
            Student student = ol.get(0);
            student.delete();
            updateList();
        }
    }

    public void updateList () {

        lvStudents.getItems().clear();
        
        for (Student student: Student.all()){
            lvStudents.getItems().add(student);
        }
    }
}
