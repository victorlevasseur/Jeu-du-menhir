package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Observer;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ViewMenuPrincipal extends JFrame implements Observer{

	private JPanel contentPane;
	
	private static final long serialVersionUID = 42L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMenuPrincipal frame = new ViewMenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewMenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JRadioButton rdbtnPartieSimple = new JRadioButton("Partie Simple");
		
		JRadioButton rdbtnPartieAvance = new JRadioButton("Partie Avancée");
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		//On ajoute les deux JRadioButton au groupe
		btnGroup.add(rdbtnPartieSimple);
		btnGroup.add(rdbtnPartieAvance);
		
		//Partie simple est la sélection par défaut
		rdbtnPartieSimple.setSelected(true);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		
		JLabel lblNombreDeJoueur = new JLabel("Nombre de Joueur");
		
		JButton btnJouer = new JButton("Jouer");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addComponent(rdbtnPartieSimple)
					.addPreferredGap(ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
					.addComponent(rdbtnPartieAvance)
					.addGap(47))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(121, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnJouer, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblNombreDeJoueur)
							.addGap(59)))
					.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(154))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnPartieSimple)
						.addComponent(rdbtnPartieAvance))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombreDeJoueur))
					.addGap(18)
					.addComponent(btnJouer)
					.addContainerGap(225, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void update(Observable obs, Object obj) {
		
	}
}
