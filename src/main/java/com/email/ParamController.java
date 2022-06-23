package com.email;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ParamController {

    @javafx.fxml.FXML
    private Button btnSave;
    @javafx.fxml.FXML
    private Button btnAnnuler;
    @javafx.fxml.FXML
    private Button btnDosMail;
    @javafx.fxml.FXML
    private Button btnFileContact;
    @javafx.fxml.FXML
    private TextField txtDosMail;
    @javafx.fxml.FXML
    private TextField txtFileContact;
    @javafx.fxml.FXML
    private TextField txtYoursAdMail;

    @javafx.fxml.FXML
    public void onbtnDosMailCLick() {
    }

    @javafx.fxml.FXML
    public void onbtnSaveClick() {
    }

    @javafx.fxml.FXML
    public void onbtnFileContactClick() {
    }

    public void onbtnAnnulerClick() {
        Stage stage = (Stage) btnAnnuler.getScene().getWindow();
        stage.close();
    }
}
