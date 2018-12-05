package Graphics;

import connexion.DataBaseAccess;
import connexion.IdentificationUtilisateur;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JPanel;

public class login {

	public JFrame frame;
	private JTextField email;

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 266);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(177, 12, 134, 46);
		frame.getContentPane().add(panel);

		JLabel lblLeCoincoin = new JLabel("Le coincoin");
		panel.add(lblLeCoincoin);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(36, 107, 96, 25);
		frame.getContentPane().add(panel_1);

		JLabel lblEmail = new JLabel("Email");
		panel_1.add(lblEmail);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(177, 105, 134, 27);
		frame.getContentPane().add(panel_2);

		email = new JTextField();
		panel_2.add(email);
		email.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(177, 184, 139, 25);
		frame.getContentPane().add(panel_3);

		JButton btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				DataBaseAccess data = new DataBaseAccess();
				if(email.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bienvenue Admin !");
					choixAdmin windowDebut = new choixAdmin(data,email.getText());
					windowDebut.frame.setVisible(true);
					frame.setVisible(false);
					
				}
				else {				
					try {
						IdentificationUtilisateur user = new IdentificationUtilisateur(data, email.getText());
						if(!user.VerificationUser()) {
							choixDebut windowDebut = new choixDebut(data,email.getText());
							windowDebut.frame.setVisible(true);
							frame.dispose();
						}
						else {
							signup window = new signup(data);
							window.frame.setVisible(true);
							frame.setVisible(false);
						}
					}
					 catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "sql error");
						e.printStackTrace();
					}
				}
				}
		});
		panel_3.add(btnLogin);

	}
}
