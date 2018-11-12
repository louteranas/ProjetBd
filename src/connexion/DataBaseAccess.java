package connexion;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DataBaseAccess {
	static final String CONN_URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
	private String USER;
	private String password;
	private Connection conn;
	
	public DataBaseAccess() {
		this.USER = setUser();
		this.password = setPassword();
		try {
			// initialisation du driver Oracle
			System.out.print("loading necessary drivers.."); 
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("loaded");
			
			// Etablissement de la connection
			System.out.print("Connecting to the database... "); 
			this.conn = DriverManager.getConnection(DataBaseAccess.CONN_URL, this.USER, this.password);
			System.out.println("connected");
		} 
		catch (SQLException excep) {
			System.err.println("failed");
			excep.printStackTrace(System.err);
		}
	}
		
	
	public String setUser() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print("veuillez entrez votre nom d'utilisateur");
		return scan.nextLine();
		
	}
	
	public String setPassword() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print("veuillez entrez votre MDP");
		return scan.nextLine();
		
	}
	
	public String setPasswordConsole() {
		Console console = System.console();
        if (console == null) {
            System.out.println("Ce programme doit s'exécuter dans une console." );
            return null;
        }
        
        System.out.println("veuillez entrez votre mdp, ne vous inquietez pas il sera cacher quand vous le taperez" );
        char[] texte = console.readPassword();
        String password = new String(texte);
        return password;
	}
	
	public Connection getConn() {
		return this.conn;
	}
	
	public void closeConnection() throws SQLException {
		this.conn.close();
	}
	
		
}
