package com.email;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import outils.Fichier;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static outils.ControlAction.EMAIL_ADDRESS_PATTERN;
import static outils.ControlAction.isValiEmail;

public class ParamController implements Initializable {
    private final static Fichier FICH_PARAM = new Fichier("src/main/resources/paramètre/parametre.csv");
    @FXML
    private Button btnSave;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnDosMail;
    @FXML
    private Button btnFileContact;
    @FXML
    private TextField txtDosMail;
    @FXML
    private TextField txtFileContact;
    @FXML
    private TextField txtYoursAdMail;

    @FXML
    public void onbtnDosMailCLick() {

    }

    @FXML
    public void onbtnSaveClick() {
        String svg = String.format("adr: %s%ncontact: %s%ndosMail: %s",
                txtYoursAdMail.getText(),
                txtFileContact.getText(),
                txtDosMail.getText());
        FICH_PARAM.setContenu(svg);
        onbtnAnnulerClick();
    }

    @FXML
    public void onbtnFileContactClick() {
        String nomFichier;
        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "csv ou txt", "csv", "txt");
            chooser.setCurrentDirectory(new File("./" + txtFileContact.getText()));
            chooser.setFileFilter(filter);
            chooser.showDialog(null, "ok");
            txtFileContact.setText(chooser.getSelectedFile().getPath());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void onbtnAnnulerClick() {
        Stage stage = (Stage) btnAnnuler.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    private void init() {
        String param = FICH_PARAM.getContenu();
        String adrMail = param.substring(4, param.indexOf("contact: ") - 1).trim();
        String fileContact = param.substring(param.indexOf("contact: ") + 8, param.indexOf("dosMail: ") - 1).trim();
        String dosMail = param.substring(param.indexOf("dosMail: ") + 8, param.length()).trim();
        txtYoursAdMail.setText(adrMail);
        txtFileContact.setText(fileContact);
        txtDosMail.setText(dosMail);

    }
}
