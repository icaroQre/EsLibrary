package com.controller;

import com.model.Book;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class BooksController { 

    @FXML
    protected ListView<Book> lvBooks; 

    @FXML
    public void initialize () {
        App.addOnchangeScreenListener(new App.OnChangeScreen(){

            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("books")){
                    updateList();
                }
            }
        });
        updateList();
    }

    @FXML
    protected void buttonRegisterBook (ActionEvent event){
        App.changeScene("registerBook");
    }
    
    @FXML
    protected void buttonDeleteBook (ActionEvent event){
        ObservableList<Book> ol = lvBooks.getSelectionModel().getSelectedItems();
    
        if(!ol.isEmpty()){
                Book book = ol.get(0);
                book.delete();
                updateList();
            }
        }
        
        public void updateList () {
            
            lvBooks.getItems().clear();
        
            for (Book book: Book.all()){
                    lvBooks.getItems().add(book);
                }
        }
            
        @FXML
        protected void buttonStudents (ActionEvent event){
            App.changeScene("main");
        }

        @FXML
        protected void buttonTitles (ActionEvent event){
            App.changeScene("titles");
        }
    }
        