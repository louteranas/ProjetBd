package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Actions.Actions;
import connexion.DataBaseAccess;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class ajoutSalle {

	public JFrame frame;
	private JTextField textField;
	private static String email;
	private static DataBaseAccess data;

	/**
	 * Create the application.
	 * @param frame2 
	 * @param email2 
	 * @param data2 
	 * @param data 
	 */
	public ajoutSalle(DataBaseAccess data, String email, JFrame parentFrame) {
		ajoutSalle.data = data;
		ajoutSalle.email = email;
		initialize(parentFrame);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param parentFrame 
	 */
	private void initialize(JFrame parentFrame) {
		Actions act = new Actions(email, data);
		Vector<Integer> idEnchere = new Vector<Integer>();
		Vector<String> charactEnchere = new Vector<String>();
		act.getDataEnchere(idEnchere, charactEnchere);
		int nbr_enchere = idEnchere.size();
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 90*(nbr_enchere - 1) + 70 );
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCreationDeSalle = new JLabel("Creation de salle ");
		lblCreationDeSalle.setBounds(250, 30, 150, 15);
		frame.getContentPane().add(lblCreationDeSalle);
		
		JLabel lblNomDeLa = new JLabel("Nom de la salle ");
		lblNomDeLa.setBounds(26, 78, 160, 15);
		frame.getContentPane().add(lblNomDeLa);
		
		JTextField nomSalle = new JTextField();
		nomSalle.setBounds(204, 76, 160, 19);
		frame.getContentPane().add(nomSalle);
		nomSalle.setColumns(10);
		
		Vector<JCheckBox> checkBox = new Vector<>();
		for(int i = 0; i < nbr_enchere; i++) {
			JCheckBox chckbxNewCheckBox = new JCheckBox(charactEnchere.elementAt(i));
			checkBox.add(chckbxNewCheckBox);
			chckbxNewCheckBox.setBounds(94, 140 + i*30, 500, 23);
			frame.getContentPane().add(chckbxNewCheckBox);
		}
		
		
		JLabel lblNewLabel = new JLabel("Type d'enchère");
		lblNewLabel.setBounds(26, 117, 117, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton0 = new JButton("retour");
		btnNewButton0.setBounds(10, 12, 100, 25);
		btnNewButton0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parentFrame.setVisible(true);
				
			}
		});
		frame.getContentPane().add(btnNewButton0);
		
		JButton btnCrer = new JButton("créer");
		btnCrer.setBounds(151, 90*(nbr_enchere-1), 117, 25);
		btnCrer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					for(JCheckBox box : checkBox) {
						if (box.isSelected() && !nomSalle.getText().isEmpty()) {
							act.newCat(nomSalle.getText());
							act.newSalle(nomSalle.getText(), idEnchere.elementAt(checkBox.indexOf(box)));
							act.commit();
							JOptionPane.showMessageDialog(null, "Salle bien crée !");
						}
						login window = new login();
						window.frame.setVisible(true);
						frame.dispose();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnCrer);
	}
}
