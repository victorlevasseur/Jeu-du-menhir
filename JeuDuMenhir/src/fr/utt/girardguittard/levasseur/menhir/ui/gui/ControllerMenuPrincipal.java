package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fr.utt.girardguittard.levasseur.menhir.Partie;

public class ControllerMenuPrincipal implements ActionListener{
	
	private Partie partie;
	
	private ViewMenuPrincipal view;
	
	public ControllerMenuPrincipal(){
		this.partie = null;
		this.view = null;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.partie = new Partie(view.getNombreJoueur(), !view.isSimple());
		this.view.setVisible(false);
		
		EventQueue.invokeLater(new Runnable() {
			private Partie partie;
			
			public Runnable setPartie(Partie partie) {
				this.partie = partie;
				
				return this;
			}
			
			public void run() {
				try {
					ViewJeu frame = new ViewJeu(this.partie);
					ControllerJeu menuController = new ControllerJeu(this.partie, frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.setPartie(this.partie));
	}

	public Partie getPartie() {
		return partie;
	}

	public ViewMenuPrincipal getView() {
		return view;
	}

	public void setView(ViewMenuPrincipal view) {
		if(this.view != null) {
			this.view.deconnecterController(this);
		}
		this.view = view;
		this.view.connecterController(this);
	}
}
