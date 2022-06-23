package com.email;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import outils.Fichier;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static outils.ControlAction.isValiEmail;
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
        if (confirmNewMess("Voulez vous créer un nouveau message ?")) {
            txtSujet.setText("");
            txtMail.setText("");
        }
    }

    /**
     * On open click.
     */
    @FXML
    public void onOpenClick() {
        String nomFichier;
        if (confirmNewMess("Voulez vous ouvrir un nouveau message ?")) {
            try {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "csv ou txt", "csv", "txt");
                chooser.setCurrentDirectory(new File("msg"));
                chooser.setFileFilter(filter);
                chooser.showDialog(null, "ok");
                nomFichier = chooser.getSelectedFile().getPath();
                Fichier fichier = new Fichier(nomFichier);

                String sujet = chooser.getSelectedFile().getName().replace(".txt", "");
                String absolutpath = chooser.getSelectedFile().getParent();
                String adr = absolutpath.substring((absolutpath.lastIndexOf(File.separator) + 1));
                txtSujet.setText("Re " + sujet);
                cbxAdrMail.setValue(adr);
                txtMail.setText(fichier.getContenu());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * demande la confiirmation si un nouveau message peut effacer l'ancien.
     *
     * @param s le message a afficher pour la confirmation
     * @return boolean de confirmation
     */
    private boolean confirmNewMess(final String s) {
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
     * (creation d'un fichier dont le nom est le sujet et
     * le dossier dans lequel il est creer est l'adresse du destinataire pour le moment)
     * et reenregistre les adresse mail dans le fichier d'adressesmail
     * si une nouvelle adresse a été ajoutermail
     */
    @FXML
    public void onSendClick() {

        String adr = cbxAdrMail.getValue().toString().trim();
        if (isValiEmail(adr)) {
            String chemin = String.format("msg/%s", adr).replace(" ", "_");
            String nomFichier = String.format("%s/%s.txt", chemin, txtSujet.getText().trim()).replace(" ", "_");
            Fichier fichier;
            String mess = "";
            mess += String.format(txtMail.getText());
            if (!cbxAdrMail.getItems().contains(adr)) {
                cbxAdrMail.getItems().add(adr);
                enregistrerAdresses(cbxAdrMail.getItems(), "adressesmail.csv");
            }
            try {
                Path path = Paths.get(chemin);
                Files.createDirectories(path);
            } catch (Exception e) {
                System.out.println(" probleme " + e.getCause());
            }
            fichier = new Fichier(nomFichier);
            fichier.setContenu(mess);

            JOptionPane.showMessageDialog(null,
                    "mail envoyé a : " + cbxAdrMail.getValue(),
                    "mail envoyé",
                    JOptionPane.INFORMATION_MESSAGE);
        } else JOptionPane.showMessageDialog(null,
                adr + " n'est pas une adresse mail Valide",
                "Vérifiez l'adresse mail ",
                JOptionPane.WARNING_MESSAGE);
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
        ArrayList<String> ajou = recupAdresses("adressesmail.csv");
        cbxAdrMail.getItems().addAll(ajou);
        btnSend.disableProperty().bind(txtMail.textProperty().isEmpty()
                                               .or(txtSujet.textProperty().isEmpty())
                                               .or(cbxAdrMail.valueProperty().isNull()));

    }

}
