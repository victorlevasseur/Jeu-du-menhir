package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.EventQueue;

/**
 * Classe permettant de lancer le jeu en GUI.
 */
public class JeuGUI {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControllerMenuPrincipal menuController = new ControllerMenuPrincipal();
					ViewMenuPrincipal frame = new ViewMenuPrincipal();
					menuController.setView(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
