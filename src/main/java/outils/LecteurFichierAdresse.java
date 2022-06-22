package outils;

import javafx.collections.ObservableList;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * The type Lecteur fichier adresse.
 */
public class LecteurFichierAdresse {
    /**
     * Enregistrer adresses.
     * on enregistre les adresses dans un fichier
     * dont le nom est donné en entrée.
     *
     * @param adresses   the adresses
     * @param nomFichier the nom fichier
     */
    public static void enregistrerAdresses(final ObservableList adresses,
                                           final String nomFichier) {
        try {
            String add = "";
            FileWriter fileWriter = new FileWriter(nomFichier);
            for (int i = 0; i < adresses.size(); i++) {
                add += adresses.get(i) + " \n";
            }
            fileWriter.write(add);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Recupère les adresses dans le fichier donné en entrer .
     * et les renvoies dans une array list.
     * ouverture d'un reader sur le fichier dont le nom est entrer en parametre
     * on lit chaque ligne dans le reader et pour chaque ligne
     * on ajoute une adresse mail dans l'ArrayList
     * on recupere les NullPointerException (si le nom donné en parametre n'existe pas)
     * pour afficher en popup le fait qu'on ne point nulpart
     * on recupere les FileNotFoundException (si le fichier donné en parametre n'existe pas)
     * pour afficher en popup le fait qu'il existe pas
     * on recupere les IOException et on affiche le message du IOException en popup
     *
     * @param nomFichier the nom fichier
     * @return the array list
     */
    public static ArrayList recupAdresses(final String nomFichier) {
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
            return lstCombo;
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
        return null;
    }

}
