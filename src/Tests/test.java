package Tests;

import java.sql.SQLException;
import java.util.ArrayList;

import Actions.Actions;
import connexion.DataBaseAccess;

public class test {

    /**
     * Launch the application.
     */

<<<<<<< HEAD
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					login window = new login();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

    public static void main(String[] args) {
        DataBaseAccess data = new DataBaseAccess();
        Actions act = new Actions("leila.kany@gmail.com", data);
        ArrayList<String> car = new ArrayList<>();
        car.add("BMW");
        car.add("rapide");
        try {
            act.newSalle("immobilier", 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					login window = new login();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

//    public static void main(String[] args) {
//        DataBaseAccess data = new DataBaseAccess();
//        Actions act = new Actions("leila.kany@gmail.com", data);
//        ArrayList<String> car = new ArrayList<>();
//        car.add("BMW");
//        car.add("rapide");
//        try {
//            act.newVente(car, 40000, 35, "Voiture BMW", 30000, 5, 4  );
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//	}
}