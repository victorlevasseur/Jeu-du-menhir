package fr.utt.girardguittard.levasseur.menhir.ui.gui;

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
		this.partie = new Partie(view.getNombreJoueur(), view.isSimple());
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
