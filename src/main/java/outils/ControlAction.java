package outils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * The type Control action.
 */
public class ControlAction {

    /**
     * Exit app.
     */
    public static void exitApp() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter ?");
        alert.setHeaderText("Un message est en cours de rédaction !");
        alert.setContentText("Êtes-vous sur de vouloir quitter l'application");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.exit(0);
        }

    }
}
