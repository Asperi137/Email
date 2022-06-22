package com.email;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import outils.Fichier;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static outils.LecteurFichierAdresse.enregistrerAdresses;
import static outils.LecteurFichierAdresse.recupAdresses;
import static outils.ControlAction.exitApp;

/**
 * The type E mail controller.
 */
public class EMailController implements Initializable {

    /**
     * The Cbx adr mail.
     */
    @FXML
    private ComboBox cbxAdrMail;
    /**
     * The Txt mail.
     */
    @FXML
    private TextArea txtMail;
    /**
     * The Txt sujet.
     */
    @FXML
    private TextField txtSujet;
    /**
     * The Btn send.
     */
    @FXML
    private Button btnSend;
    /**
     * The Btn new.
     */
    @FXML
    private Button btnNew;
    /**
     * The Btn open.
     */
    @FXML
    private Button btnOpen;

    /**
     * On quitclick.
     * quit le programme quand on click
     */
    @FXML
    public void onQuitclick() {
        exitApp();
    }

    /**
     * On new click.
     */
    @FXML
    public void onNewClick() {
        if (confNewMess("Voulez vous créer un nouveau message ?")) {
            txtSujet.setText("");
            txtMail.setText("");
        }
    }

    /**
     * On open click.
     */
    @FXML
    public void onOpenClick() {
        confNewMess("Voulez vous ouvrir un nouveau message ?");

    }

    /**
     * demande la confiirmation si un nouveau message peut effacer l'ancien.
     *
     * @param s le message a afficher pour la confirmation
     * @return boolean de confirmation
     */
    private boolean confNewMess(final String s) {
        if (!txtMail.getText().isBlank() || !txtSujet.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(s);
            alert.setContentText(s);
            if (alert.showAndWait().get() == ButtonType.OK) {
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * On send click.
     * si les champ necessaire sont remplie envoie le mail
     * (simple popup disant que le mail a ete envoyer pour le moment)
     * et reenregistre les adresse mail dans le fichier d'adressesmail
     * si une nouvelle adresse a été ajoutermail
     */
    @FXML
    public void onSendClick() {
        String nomFichier = "msg1.txt";
        Fichier fichier = new Fichier(nomFichier);
        String mess = "";
        if (cbxAdrMail.getValue() != null
                    && !cbxAdrMail.getValue().equals("")) {
            if (!cbxAdrMail.getItems().contains(cbxAdrMail.getValue())) {
                cbxAdrMail.getItems().add(cbxAdrMail.getValue());
                enregistrerAdresses(cbxAdrMail.getItems(), "adressesmail.csv");
                mess += String.format("to : %s%nfrom : %s%nSujet : %s%n%n%s",
                        cbxAdrMail.getValue(),
                        "you",
                        txtSujet.getText(),
                        txtMail.getText());
                fichier.setContenu(mess);
            }
            JOptionPane.showMessageDialog(null,
                    "mail envoyé a : " + cbxAdrMail.getValue(),
                    "mail envoyé",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Override de la fonction initialize .
     * On appele la fonction init() qui est private
     */
    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        init();
    }

    /**
     * initialise les paramètre de l'interface graphique.
     * ajout des adresse se trouvant le fichier d'adresse mail
     * intitialise l'ecouteur du bouton send
     * sur les champ txtSujet, txtMail et cbxAdrMail
     */
    private void init() {
        ArrayList ajou = recupAdresses("adressesmail.csv");
        cbxAdrMail.getItems().addAll(ajou);
        btnSend.disableProperty().bind(txtMail.textProperty().isEmpty()
                                               .or(txtSujet.textProperty().isEmpty())
                                               .or(cbxAdrMail.valueProperty().isNull()));

    }

}
