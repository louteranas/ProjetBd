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
//    public static void main(String[] args) {
//        DataBaseAccess data = new DataBaseAccess();
//        Vente act = new Vente("leila.kany@gmail.com", data);
//        ArrayList<String>  car= new ArrayList<>();
//        car.add("tres beau");
//
//        try {
//            act.newVente(car, 100, 5, "velo", 30, 3, 3);
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//    }
//}
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








