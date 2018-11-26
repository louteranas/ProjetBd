package Tests;

import java.awt.EventQueue;
import java.sql.SQLException;

import connexion.Actions;
import connexion.DataBaseAccess;
import connexion.IdentificationUtilisateur;

public class test {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        DataBaseAccess data = new DataBaseAccess();
        Actions act = new Actions("leila.kany@gmail.com", data);
        try {
            act.produitsCat("ordinateur").affichageResultat();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}