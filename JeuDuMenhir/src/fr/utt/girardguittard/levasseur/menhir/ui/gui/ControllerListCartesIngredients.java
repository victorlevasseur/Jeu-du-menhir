package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class ControllerListCartesIngredients implements ListSelectionListener {
	
	/**
	 * La vue concernée
	 */
	private ViewCartesIngredients view;

	/**
	 * La main du joueur concerné
	 */
	private MainJoueur main;
	
	/**
	 * Constructeur de ce controller
	 * Initialise les attributs
	 * @param m La main du joueur concerné
	 * @param v La vue concernée
	 */
	public ControllerListCartesIngredients(MainJoueur m, ViewCartesIngredients v) {
		this.main = m;
		this.view = v;
	}

	/**
	 * L'action a effectué lorsque que le joueur change la sélection de la liste des cartes ingrédients
	 */
	public void valueChanged(ListSelectionEvent e) {
		
		//Récupération du modèle
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        
        if (lsm.isSelectionEmpty()) {
            view.setLabel("Pas de sélection.");
        } 
        else {
        	//Récupération de l'index sélectionné
        	int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
                	//Modification du label
                    view.setLabel("<html><pre>" + main.getCarteIngredient(i) + "</pre></html>");
                }
            }
        }

	}
    
}
