package outils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import javax.swing.*;

public class controlAction {

    public static void exitApp() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter ?");
        alert.setHeaderText("Un message est en cours de rédaction !");
        alert.setContentText(
                "Êtes-vous sur de vouloir quitter l'application alors que le message en cours n'a pas été envoyé ?");

        if (alert.showAndWait().get() == ButtonType.OK) {

            System.out.println("Quitter");
            System.exit(0);
        }

    }
}
