package com.controller;

import com.model.Title;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class TitlesController {

    @FXML
    private ListView<Title> lvTitles;

    @FXML
    public void initialize () {
        App.addOnchangeScreenListener(new App.OnChangeScreen(){

            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("titles")){
                    updateList();
                }
            }
        });
        updateList();
    }

    @FXML
    void buttonBooks(ActionEvent event) {
        App.changeScene("books");
    }

    @FXML
    void buttonDeleteTitle(ActionEvent event) {
        ObservableList<Title> ol = lvTitles.getSelectionModel().getSelectedItems();

        if(!ol.isEmpty()){
            Title title = ol.get(0);
            title.delete();
            updateList();
        }
    }

    @FXML
    void buttonRegisterTitle(ActionEvent event) {
        App.changeScene("registerTitle");
    }

    @FXML
    void buttonStudents(ActionEvent event) {
        App.changeScene("main");
    }

    public void updateList () {
            
            lvTitles.getItems().clear();
        
            for (Title title: Title.all()){
                    lvTitles.getItems().add(title);
                }
        }

}
