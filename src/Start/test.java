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
//    public static void main(String[] args) {
//        DataBaseAccess data = new DataBaseAccess();
//        Admin act = new Admin("leila.kany@gmail.com", data);
//
//        try {
//            System.out.println(act.vainqueursAsc(act.getIdVenteProduit(41)));
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//    }
//}

//
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable();) {
//			public void run() {
//				try {
//					login window = new login();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		};
//	}
    public static void main(String[] args) {
        DataBaseAccess data = new DataBaseAccess();
        Achat act = new Achat("leila.kany@gmail.com", data);

        try {

            System.out.println(act.venteFinie(22));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



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


