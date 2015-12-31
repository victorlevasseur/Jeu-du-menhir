package fr.utt.girardguittard.levasseur.menhir.ui.gui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class ControllerListCartesIngredients implements ListSelectionListener {
	
	private ViewCartesIngredients view;

	private MainJoueur main;
	
	public ControllerListCartesIngredients(MainJoueur main, ViewCartesIngredients view) {
		
	}

	public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        
        if (lsm.isSelectionEmpty()) {
            view.setLabel("Pas de sélection.");
        } 
        else {
        	int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
                    view.setLabel("<html><pre>" + main.getCarteIngredient(i) + "</pre></html>");
                }
            }
        }

	}
    
}
