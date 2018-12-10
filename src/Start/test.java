package Start;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import Graphics.*;
import Actions.*;
import connexion.DataBaseAccess;

public class test {

    /**
     * Launch the application.
     */

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login window = new login();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}








