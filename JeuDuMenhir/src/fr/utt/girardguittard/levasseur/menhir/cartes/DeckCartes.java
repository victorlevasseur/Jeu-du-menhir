package fr.utt.girardguittard.levasseur.menhir.cartes;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Représente un deck de carte de type E
 *
 * @param <E> le type de cartes gérées par le deck (sera CarteIngredient ou CarteAllies suivant le deck voulu)
 */
public class DeckCartes<E> {
	/**
	 * Le tas de carte (mélangé).
	 */
	private Queue<E> tasDeCarte;
	
	/**
	 * Toutes les cartes gérées par le deck.
	 */
	private List<E> cartes;
	
	/**
	 * Construit un deck vide.
	 */
	public DeckCartes() {
		this.tasDeCarte = new LinkedList<E>();
		this.cartes = new ArrayList<E>();
	}
	
	public void remettreCartesEtMelanger() {		
		this.tasDeCarte.clear();
		
		//On crée un tableau qui contient les index de toutes les cartes dans "cartes"
		//On mélange ce tableau d'indice, et on ajoute les cartes pointées par les index
		//dans la "file" (tas) de carte.
		ArrayList<Integer> cartesAMelanger = new ArrayList<Integer>(cartes.size());
		for(int i = 0; i < this.cartes.size(); i++) {
			cartesAMelanger.add(i);
		}
		
		Collections.shuffle(cartesAMelanger);
		
		for(Iterator<Integer> it = cartesAMelanger.iterator(); it.hasNext(); ) {
			this.tasDeCarte.add(this.cartes.get(it.next().intValue()));
		}
	}
	
	public E getCarte() {
		return this.tasDeCarte.remove();
	}
	
	public void remettreCarteDansTas(E carte) {
		if(!this.tasDeCarte.contains(carte)) {
			this.tasDeCarte.add(carte);
		}
	}
}
