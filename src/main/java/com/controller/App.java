package com.controller;

import java.util.ArrayList;

import com.model.Book;
import com.model.Student;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    // Armazenado o estágio da cena
    private static Stage stage;

    // Armazenado as cenas
    private static Scene mainScene;
    private static Scene registerStudentScene;
    private static Scene booksScene;
    private static Scene registerBookScene;
    private static Scene titlesScene;
    private static Scene registerTitlesScene;

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {

        // Date date1 = new Date(0);
        // Date date2 = new Date(0);
        // Loan loan = new Loan(date1, date1, 0f, 3);
        // loan.save();
        
        // Student student = new Student("Lucas", "1234", "1234", "1234");
        // student.save();
        // Student student = Student.find(4);
        // student.verifyDebit();
        
        // System.out.println("\n \n \n");
        
        // Book book = Book.find(8);
        // student.loanBook(book);
        // System.exit(0);

        stage = primaryStage;

        // Carrega o arquivo FXML de cada tela e atribui as variáveis
        Parent fxmlMain = FXMLLoader.load(getClass().getResource("/com/view/main_screen.fxml"));
        mainScene = new Scene(fxmlMain);
        Parent fxmlRegisterStudent = FXMLLoader.load(getClass().getResource("/com/view/register_student_screen.fxml"));
        registerStudentScene = new Scene(fxmlRegisterStudent);
        Parent fxmlBooks = FXMLLoader.load(getClass().getResource("/com/view/books_screen.fxml"));
        booksScene = new Scene(fxmlBooks);
        Parent fxmlRegisterBook = FXMLLoader.load(getClass().getResource("/com/view/register_book_screen.fxml"));
        registerBookScene = new Scene(fxmlRegisterBook);
        Parent fxmlTitles = FXMLLoader.load(getClass().getResource("/com/view/titles_screen.fxml"));
        titlesScene = new Scene(fxmlTitles);
        Parent fxmlRegisterTitles = FXMLLoader.load(getClass().getResource("/com/view/register_title_screen.fxml"));
        registerTitlesScene = new Scene(fxmlRegisterTitles);

        // Define a cena no palco (stage) e exibe
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Bem vindo a EsLibrary!");
        primaryStage.show();
    }

    public static void changeScene(String scene, Object userData) {
        switch (scene) {
            case "main":
                stage.setScene(mainScene);
                notifyAllListeners("main", userData);
                break;

            case "registerStudent":
                stage.setScene(registerStudentScene);
                notifyAllListeners("registerStudent", userData);
                break;

            case "books":
                stage.setScene(booksScene);
                notifyAllListeners("books", userData);
                break;

            case "registerBook":
                stage.setScene(registerBookScene);
                notifyAllListeners("registerBook", userData);
                break;

            case "titles":
                stage.setScene(titlesScene);
                notifyAllListeners("titles", userData);
                break;

            case "registerTitle":
                stage.setScene(registerTitlesScene);
                notifyAllListeners("registerTitle", userData);
                break;
        
            default:
                break;
        }
    }

    public static void changeScene(String scene) {
        changeScene(scene, null);
    }

    public static void main(String[] args) {
        launch(args);
    }

    //==== Implementação de listeners para mudança de tela ============================================================
    static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen {
        void onScreenChanged(String newScreen, Object userData);
    }

    public static void addOnchangeScreenListener(OnChangeScreen newListener) {
        listeners.add(newListener);
    }

    private static void notifyAllListeners(String newScreen, Object userData) {
        for (OnChangeScreen listener : listeners) {
            listener.onScreenChanged(newScreen, userData);
        }
    }

}