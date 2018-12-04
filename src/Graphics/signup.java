package Graphics;

import connexion.AjoutUtilisateur;
import connexion.DataBaseAccess;
import connexion.IdentificationUtilisateur;
import requetes.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import connexion.DataBaseAccess;
import connexion.IdentificationUtilisateur;

import javax.swing.JButton;

public class signup {

	public JFrame frame;
	private JTextField email;
	private JTextField nom;
	private JTextField prenom;
	private JTextField addresse;


	/**
	 * Create the application.
	 */
	public signup(DataBaseAccess data) {
		initialize(data);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(DataBaseAccess data) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(159, 12, 142, 25);
		frame.getContentPane().add(panel);

		JLabel lblSignup = new JLabel("Signup");
		panel.add(lblSignup);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(29, 73, 106, 15);
		frame.getContentPane().add(lblEmail);

		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(29, 114, 106, 15);
		frame.getContentPane().add(lblNom);

		JLabel lblPrenom = new JLabel("Prénom");
		lblPrenom.setBounds(29, 150, 106, 15);
		frame.getContentPane().add(lblPrenom);

		JLabel lblNewLabel = new JLabel("Addresse");
		lblNewLabel.setBounds(29, 186, 106, 15);
		frame.getContentPane().add(lblNewLabel);

		JLabel label = new JLabel("");
		label.setBounds(231, 73, 174, 15);
		frame.getContentPane().add(label);

		email = new JTextField();
		email.setBounds(159, 71, 229, 17);
		frame.getContentPane().add(email);
		email.setColumns(10);

		nom = new JTextField();
		nom.setBounds(159, 112, 234, 17);
		frame.getContentPane().add(nom);
		nom.setColumns(10);

		prenom = new JTextField();
		prenom.setBounds(159, 148, 234, 17);
		frame.getContentPane().add(prenom);
		prenom.setColumns(10);

		addresse = new JTextField();
		addresse.setBounds(159, 184, 234, 17);
		frame.getContentPane().add(addresse);
		addresse.setColumns(10);

		JButton btnSignup = new JButton("Signup");
		btnSignup.setBounds(169, 222, 117, 25);
		frame.getContentPane().add(btnSignup);
		btnSignup.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(email.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas inserer votre email !");
				}
				if(nom.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas inserer votre nom !");
				}
				if(prenom.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas inserer votre prenom !");
				}
				if(addresse.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas inserer votre addresse !");
				}
				else {
					try {
						new AjoutUtilisateur(data,  email.getText(),  nom.getText(),  prenom.getText(),  addresse.getText());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "sql error");
						e.printStackTrace();
					}
					choixDebut windowDebut = new choixDebut(data, email.getText());
					windowDebut.frame.setVisible(true);
					frame.dispose();
				}

			}
		});
	}
}
