package outils;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Fichier {
    private String nomFichier;

    public Fichier(String nomFichierV) {
        try {
            File fichier = new File(nomFichierV);
            fichier.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getCause());
        }
        this.nomFichier = nomFichierV;
    }

    public String getContenu() {
        try {
            FileReader fileReader = new FileReader(nomFichier);
            LineNumberReader lineNumberReader
                    = new LineNumberReader(fileReader);
            String texte = "";
            String ligneLue;
            ArrayList<String> lstCombo = new ArrayList<>();
            do {
                ligneLue = lineNumberReader.readLine();
                if (ligneLue != null) {
                    lstCombo.add(ligneLue);
                    texte += ligneLue + "\n";
                }
                if (texte == "") {
                    throw new IOException("fichier vide");
                }
            } while (ligneLue != null);
            return texte;
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
            JOptionPane.showMessageDialog(null,
                    "veuillez saisir un nom de fichier ",
                    "Erreur",
                    JOptionPane.WARNING_MESSAGE);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null,
                    "fichier non trouvé !!!",
                    "Erreur",
                    JOptionPane.WARNING_MESSAGE);
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,
                    ioe.getMessage(),
                    "Erreur",
                    JOptionPane.WARNING_MESSAGE);
        }
        return "fichier vide";
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
