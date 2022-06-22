package com.email;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableBooleanValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import outils.controlAction;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static outils.controlAction.*;

import static outils.LecteurFichierAdresse.*;

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
        txtSujet.setText("");
        cbxAdrMail.setValue("");
        txtMail.setText("");
//        demandeConfirmation("Voulez vous créer un nouveau message ?");
    }

    /**
     * On open click.
     */
    @FXML
    public void onOpenClick() {
//        demandeConfirmation("Voulez vous ouvrir un nouveau message ?");

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    /**
     * initialise les paramètre de l'interface graphique.
     * ajout des adresse se trouvant le fichier d'adresse mail
     * intitialise l'ecouteur du bouton send sur les champ txtSujet, txtMail et cbxAdrMail
     */
    private void init() {
        ArrayList ajou = recupAdresses("adressesmail.csv");
        cbxAdrMail.getItems().addAll(ajou);
        btnSend.disableProperty().bind(txtMail.textProperty().isEmpty()
                                               .or(txtSujet.textProperty().isEmpty())
                                               .or(cbxAdrMail.valueProperty().isNull()));

    }

}