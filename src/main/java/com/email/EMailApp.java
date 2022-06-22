package com.email;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

import static outils.controlAction.exitApp;

/**
 * The type E mail app.
 */
public class EMailApp extends Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * lancement de l'interface
     *
     * @param stage
     */

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EMailApp.class.getResource("EMail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 300);
        stage.setOnCloseRequest(event -> {
            event.consume();
            exitApp();
        });
        stage.setTitle("Envoi de mails");
        stage.setScene(scene);
        stage.show();

    }
}