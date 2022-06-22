package outils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Fichier {
    private String nomFichier;

    public Fichier(String text) {
        File fichier = new File(text);
        this.nomFichier = text;
    }

    public String getContenu() {
        return "";
    }

    public void setContenu(final String text) {
        try {
            FileWriter fileWriter = new FileWriter(nomFichier);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
