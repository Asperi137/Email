package com.email;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static outils.LecteurFichierAdresse.enregistrerAdresses;
import static outils.LecteurFichierAdresse.recupAdresses;

/**
 * The type E mail controller.
 */
public class EMailController implements Initializable {

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
     * The Btn open.
     */
    @FXML
    private Button btnOpen;

    /**
     * On new click.
     */
    @FXML
    public void onNewClick() {
        txtSujet.setText("");
        cbxAdrMail.setValue("");
        txtMail.setText("");
    }

    /**
     * On open click.
     */
    @FXML
    public void onOpenClick() {

    }

    /**
     * On quitclick.
     * quit le programme quand on click
     */
    @FXML
    public void onQuitclick() {
        System.exit(0);
    }

    /**
     * On send click.
     * si les champ necessaire sont remplie
     * envoie le mail (simple popup disant que le mail a ete envoyer pour le moment)
     * et reenregistre les adresse mail dans le fichier d'adressesmail si une nouvelle adresse a été ajoutermail
     */
    @FXML
    public void onSendClick() {
        if (cbxAdrMail.getValue() != null && !cbxAdrMail.getValue().equals("")) {
            if (!cbxAdrMail.getItems().contains(cbxAdrMail.getValue())) {
                cbxAdrMail.getItems().add(cbxAdrMail.getValue());
                enregistrerAdresses(cbxAdrMail.getItems(), "adressesmail.csv");
            }
            JOptionPane.showMessageDialog(null,
                    "mail envoyé a :" + cbxAdrMail.getValue(),
                    "mail envoyé",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Override de la fonction initialize .
     * On appele la fonction init() qui est private
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    /**
     * initialise les paramètre de l'interface graphique.
     * ajout des adresse se trouvant le fichier d'adresse mail
     */
    private void init() {
        ArrayList ajou = recupAdresses("adressesmail.csv");
        cbxAdrMail.getItems().addAll(ajou);
    }

}