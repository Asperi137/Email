package outils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.regex.Pattern;

/**
 * The type Control action.
 */
public class ControlAction {

    public final static Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
    );

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

    public static boolean isValiEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

}
