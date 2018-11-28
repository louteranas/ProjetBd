package Tests;

import java.sql.SQLException;
import java.util.ArrayList;

import Actions.Actions;
import connexion.DataBaseAccess;

public class test {

    /**
     * Launch the application.
     */

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
}